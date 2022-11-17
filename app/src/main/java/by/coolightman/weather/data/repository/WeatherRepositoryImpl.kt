package by.coolightman.weather.data.repository

import android.util.Log
import by.coolightman.weather.data.remote.dto.DayWeatherDto
import by.coolightman.weather.data.remote.dto.HourWeatherDto
import by.coolightman.weather.data.remote.dto.WeatherStampDto
import by.coolightman.weather.data.remote.service.ApiService
import by.coolightman.weather.domain.model.ApiState
import by.coolightman.weather.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.Calendar

class WeatherRepositoryImpl(
    private val apiService: ApiService
) : WeatherRepository {

    override suspend fun fetchWeatherStamp(place: String): Flow<ApiState> = flow {
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
    }

    private suspend fun setDataToDb(stampDto: WeatherStampDto) {
        val daysDto = takeNext14Days(stampDto)
        val hoursDto = takeNext24Hours(stampDto)
        val currentConditionsDto = stampDto.currentConditions
        
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