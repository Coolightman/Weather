package by.coolightman.weather.domain.model

data class HourWeather(
    val id: Long,
    val stampId: Long,
    val datetimeEpoch: Long,
    val temp: Double,
    val icon: String
)
