package by.coolightman.weather.data.local.modelDb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DayWeatherDb(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val stampId: Long,
    val datetimeEpoch: Long,
    val tempMax: Double,
    val tempMin: Double,
    val icon: String
)
