package by.coolightman.weather.domain.model

data class WeatherStamp(
    val id: Long,
    val createdAt: Long,
    val resolvedAddress: String,
    val address: String,
    val timezone: String,
    val tzOffset: Int,
    val currentConditions: CurrentConditions,
    val days14Forecast: List<DayWeather>,
    val hours24Forecast: List<HourWeather>
)