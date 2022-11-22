package by.coolightman.weather.data.repository

import android.util.Log
import by.coolightman.weather.data.local.dao.WeatherDao
import by.coolightman.weather.data.mappers.toDbModel
import by.coolightman.weather.data.mappers.toModel
import by.coolightman.weather.data.remote.dto.DayWeatherDto
import by.coolightman.weather.data.remote.dto.HourWeatherDto
import by.coolightman.weather.data.remote.dto.WeatherStampDto
import by.coolightman.weather.data.remote.service.ApiService
import by.coolightman.weather.domain.model.ApiState
import by.coolightman.weather.domain.model.EmptyWeatherStamp
import by.coolightman.weather.domain.model.WeatherStamp
import by.coolightman.weather.domain.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.util.Calendar

class WeatherRepositoryImpl(
    private val apiService: ApiService,
    private val weatherDao: WeatherDao
) : WeatherRepository {

    override fun getLastWeatherStamp(): Flow<WeatherStamp> =
        weatherDao.getAllCurrentConditions().map { list ->
            if (list.isNotEmpty()) {
                val lastConditions = list.last().toModel()
                val days14Forecast = weatherDao.getDays(lastConditions.stampId).map { it.toModel() }
                val hours24Forecast =
                    weatherDao.getHours(lastConditions.stampId).map { it.toModel() }
                deletePreviousStamps(lastConditions.stampId)
                weatherDao.getAllWeatherStamp(lastConditions.stampId)
                    .toModel(lastConditions, days14Forecast, hours24Forecast)
            } else EmptyWeatherStamp.stamp
        }

    private suspend fun deletePreviousStamps(currentStampId: Long) {
        weatherDao.getAllWeatherStamps()
            .filter { it.id != currentStampId }
            .forEach { deleteStampData(it.id) }
    }

    private suspend fun deleteStampData(stampId: Long) {
        weatherDao.deleteHours(stampId)
        weatherDao.deleteDays(stampId)
        weatherDao.deleteWeatherStamp(stampId)
        weatherDao.deleteCurrentConditions(stampId)
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
            } else{
                response.errorBody()?.let {
                    val errorMessage = it.string()
                    throw RuntimeException(errorMessage)
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
            weatherDao.insertDaysForecast(daysDto.map { it.toDbModel(stampId) })
            weatherDao.insertHoursForecast(hoursDto.map { it.toDbModel(stampId) })
            weatherDao.insertCurrentConditions(currentConditionsDto.toDbModel(stampId))
        }
    }

    private fun takeNext24Hours(stampDto: WeatherStampDto): List<HourWeatherDto> {
        val twoDaysHours = mutableListOf<HourWeatherDto>()
        stampDto.days.take(2).forEach { twoDaysHours.addAll(it.hours) }
        val currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        return twoDaysHours.drop(currentHour + 1).take(24)
    }

    private fun takeNext14Days(stampDto: WeatherStampDto): List<DayWeatherDto> {
        return stampDto.days.take(14)
    }
}