package by.coolightman.weather.ui.screen

import by.coolightman.weather.domain.model.ApiState
import by.coolightman.weather.domain.model.DayWeather
import by.coolightman.weather.domain.model.HourWeather

data class BaseUiState(
    val apiState: ApiState = ApiState.Empty,
    val resolvedAddress: String = "",
    val temp: Int = 0,
    val icon: String = "cloudy",
    val conditions: String = "Overcast",
    val feelsLikeTemp: Int = 0,
    val windSpeed: Int = 0,
    val windDir: Int = 0,
    val pressure: Int = 0,
    val humidity: Int = 0,
    val hours24Forecast: List<HourWeather> = emptyList(),
    val days14Forecast: List<DayWeather> = emptyList(),
    val lastRefresh: Long = 0,
    val sunrise: Long = 0,
    val sunset: Long = 0
)
