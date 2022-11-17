package by.coolightman.weather.data.local.modelDb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CurrentConditionsDb(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
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
