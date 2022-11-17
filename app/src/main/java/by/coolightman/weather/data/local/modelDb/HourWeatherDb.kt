package by.coolightman.weather.data.local.modelDb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class HourWeatherDb(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val stampId: Long,
    val datetimeEpoch: Long,
    val temp: Double,
    val icon: String
)
