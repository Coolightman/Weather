package by.coolightman.weather.data.local.modelDb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WeatherStampDb(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val createdAt: Long = System.currentTimeMillis(),
    val resolvedAddress: String,
    val address: String,
    val timezone: String,
    val tzOffset: Int
)