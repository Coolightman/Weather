package by.coolightman.weather.data.remote.dto

import com.google.gson.annotations.SerializedName

data class WeatherStampDto(
    @SerializedName("resolvedAddress") var resolvedAddress: String? = null,
    @SerializedName("address") var address: String? = null,
    @SerializedName("timezone") var timezone: String? = null,
    @SerializedName("tzoffset") var tzOffset: Int? = null,
    @SerializedName("days") var days: ArrayList<DayWeatherDto> = arrayListOf(),
    @SerializedName("currentConditions") var currentConditions: CurrentConditionsDto? = CurrentConditionsDto()
)
