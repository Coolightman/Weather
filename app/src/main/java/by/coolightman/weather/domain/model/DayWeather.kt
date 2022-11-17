package by.coolightman.weather.domain.model

data class DayWeather(
    val id: Long,
    val stampId: Long,
    val datetimeEpoch: Long,
    val tempMax: Double,
    val tempMin: Double,
    val icon: String
)
