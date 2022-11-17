package by.coolightman.weather.data.repository

import android.util.Log
import by.coolightman.weather.data.local.dao.WeatherDao
import by.coolightman.weather.data.local.modelDb.WeatherStampDb
import by.coolightman.weather.data.mappers.toDbModel
import by.coolightman.weather.data.mappers.toModel
import by.coolightman.weather.data.remote.dto.DayWeatherDto
import by.coolightman.weather.data.remote.dto.HourWeatherDto
import by.coolightman.weather.data.remote.dto.WeatherStampDto
import by.coolightman.weather.data.remote.service.ApiService
import by.coolightman.weather.domain.model.ApiState
import by.coolightman.weather.domain.model.WeatherStamp
import by.coolightman.weather.domain.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import java.util.Calendar

class WeatherRepositoryImpl(
    private val apiService: ApiService,
    private val weatherDao: WeatherDao
) : WeatherRepository {

    override fun getLastWeatherStamp(): Flow<WeatherStamp> = flow {
        val stampsDb = weatherDao.getAllWeatherStamps()
        if (stampsDb.isNotEmpty()) {
            val lastStampDb = stampsDb.last()
            val currentConditions = weatherDao.getCurrentConditions(lastStampDb.id).toModel()
            val days14Forecast = weatherDao.getDays(lastStampDb.id).map { it.toModel() }
            val hours24Forecast = weatherDao.getHours(lastStampDb.id).map { it.toModel() }
            val weatherStamp =
                lastStampDb.toModel(currentConditions, days14Forecast, hours24Forecast)
            emit(weatherStamp)
            deletePreviousStamps(stampsDb)
        }
    }

    private suspend fun deletePreviousStamps(stampsDb: List<WeatherStampDb>) {
        if (stampsDb.size > 1){
            stampsDb.dropLast(1).forEach {
                deleteStampData(it.id)
            }
        }
    }

    private suspend fun deleteStampData(stampId: Long) {
        weatherDao.deleteHours(stampId)
        weatherDao.deleteDays(stampId)
        weatherDao.deleteCurrentConditions(stampId)
        weatherDao.deleteWeatherStamp(stampId)
    }

    override fun fetchWeatherStamp(place: String): Flow<ApiState> = flow {
        emit(ApiState.Loading)
        try {
            val response = apiService.getWeatherStamp(place)
            Log.d("WeatherRepositoryImpl", "response: $response")
            if (response.isSuccessful) {
                response.body()?.let {
                    Log.d("WeatherRepositoryImpl", "body: $it")
                    setDataToDb(it)
                    emit(ApiState.Success(Unit))
                }
            }
        } catch (error: Exception) {
            Log.e("WeatherRepositoryImpl", "error: $error")
            emit(ApiState.Failure(error))
        }
    }.flowOn(Dispatchers.IO)

    private suspend fun setDataToDb(stampDto: WeatherStampDto) {
        withContext(Dispatchers.IO) {
            val currentConditionsDto = stampDto.currentConditions!!
            val daysDto = takeNext14Days(stampDto)
            val hoursDto = takeNext24Hours(stampDto)
            val stampId = weatherDao.insertWeatherStamp(stampDto.toDbModel())
            weatherDao.insertCurrentConditions(currentConditionsDto.toDbModel(stampId))
            weatherDao.insertDaysForecast(daysDto.map { it.toDbModel(stampId) })
            weatherDao.insertHoursForecast(hoursDto.map { it.toDbModel(stampId) })
        }
    }

    private fun takeNext24Hours(stampDto: WeatherStampDto): List<HourWeatherDto> {
        val twoDaysHours = mutableListOf<HourWeatherDto>()
        stampDto.days.take(2).forEach { twoDaysHours.addAll(it.hours) }
        val currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        return twoDaysHours.drop(currentHour).take(24)
    }

    private fun takeNext14Days(stampDto: WeatherStampDto): List<DayWeatherDto> {
        return stampDto.days.drop(1).take(14)
    }
}