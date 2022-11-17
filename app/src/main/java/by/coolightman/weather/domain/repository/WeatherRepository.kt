package by.coolightman.weather.domain.repository

import by.coolightman.weather.domain.model.ApiState
import by.coolightman.weather.domain.model.WeatherStamp
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {

    fun fetchWeatherStamp(place: String): Flow<ApiState>

    fun getLastWeatherStamp(): Flow<WeatherStamp>
}