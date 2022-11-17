package by.coolightman.weather.domain.repository

import by.coolightman.weather.domain.model.ApiState
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {

    suspend fun fetchWeatherStamp(place: String): Flow<ApiState>
}