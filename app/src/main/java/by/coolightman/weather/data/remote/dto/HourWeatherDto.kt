package by.coolightman.weather.data.remote.dto

import com.google.gson.annotations.SerializedName

data class HourWeatherDto(
    @SerializedName("datetimeEpoch") var datetimeEpoch: Long? = null,
    @SerializedName("temp") var temp: Double? = null,
    @SerializedName("icon") var icon: String? = null
)
