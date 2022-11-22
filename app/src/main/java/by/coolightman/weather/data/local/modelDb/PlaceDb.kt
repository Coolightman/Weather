package by.coolightman.weather.data.local.modelDb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PlaceDb(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val address: String,
    val resolvedAddress: String,
    val selected: Boolean
)
