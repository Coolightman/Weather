package by.coolightman.weather.data.remote.dto

import com.google.gson.annotations.SerializedName

data class WeatherStampDto(
    @SerializedName("latitude") var latitude: Double? = null,
    @SerializedName("longitude") var longitude: Double? = null,
    @SerializedName("resolvedAddress") var resolvedAddress: String? = null,
    @SerializedName("address") var address: String? = null,
    @SerializedName("timezone") var timezone: String? = null,
    @SerializedName("tzoffset") var tzoffset: Int? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("days") var days: ArrayList<DayWeatherDto> = arrayListOf(),
    @SerializedName("currentConditions") var currentConditions: CurrentConditionsDto? = CurrentConditionsDto()
)
