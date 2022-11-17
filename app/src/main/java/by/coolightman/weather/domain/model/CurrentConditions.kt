package by.coolightman.weather.domain.model

data class CurrentConditions(
    val id: Long,
    val stampId: Long,
    val datetimeEpoch: Long,
    val temp: Double,
    val feelsLike: Double,
    val humidity: Double,
    val dew: Double,
    val windSpeed: Double,
    val windDir: Double,
    val pressure: Double,
    val conditions: String,
    val icon: String,
    val sunriseEpoch: Long,
    val sunsetEpoch: Long,
    val moonPhase: Double
)
