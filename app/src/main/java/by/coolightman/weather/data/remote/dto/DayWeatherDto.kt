package by.coolightman.weather.data.remote.dto

import com.google.gson.annotations.SerializedName

data class DayWeatherDto(
    @SerializedName("datetimeEpoch") var datetimeEpoch: Long? = null,
    @SerializedName("tempmax") var tempMax: Double? = null,
    @SerializedName("tempmin") var tempMin: Double? = null,
    @SerializedName("icon") var icon: String? = null,
    @SerializedName("hours") var hours: ArrayList<HourWeatherDto> = arrayListOf()
)