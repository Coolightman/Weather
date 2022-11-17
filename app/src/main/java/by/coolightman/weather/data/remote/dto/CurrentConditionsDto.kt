package by.coolightman.weather.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CurrentConditionsDto(
    @SerializedName("datetimeEpoch") var datetimeEpoch: Long? = null,
    @SerializedName("temp") var temp: Double? = null,
    @SerializedName("feelslike") var feelsLike: Double? = null,
    @SerializedName("humidity") var humidity: Double? = null,
    @SerializedName("dew") var dew: Double? = null,
    @SerializedName("windspeed") var windSpeed: Double? = null,
    @SerializedName("winddir") var windDir: Double? = null,
    @SerializedName("pressure") var pressure: Double? = null,
    @SerializedName("conditions") var conditions: String? = null,
    @SerializedName("icon") var icon: String? = null,
    @SerializedName("sunriseEpoch") var sunriseEpoch: Long? = null,
    @SerializedName("sunsetEpoch") var sunsetEpoch: Long? = null,
    @SerializedName("moonphase") var moonPhase: Double? = null
)
