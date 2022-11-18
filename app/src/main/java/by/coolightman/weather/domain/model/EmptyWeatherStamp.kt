package by.coolightman.weather.domain.model

object EmptyWeatherStamp {
    val stamp: WeatherStamp = WeatherStamp(
        id = 0,
        createdAt = 0,
        resolvedAddress = "",
        address = "",
        timezone = "",
        tzOffset = 0,
        currentConditions = CurrentConditions(
            id= 0,
            stampId = 0,
            datetimeEpoch = 0,
            temp = 0.0,
            feelsLike = 0.0,
            humidity = 0.0,
            dew = 0.0,
            windSpeed = 0.0,
            windDir = 0.0,
            pressure = 0.0,
            conditions = "",
            icon = "",
            sunriseEpoch = 0,
            sunsetEpoch = 0,
            moonPhase = 0.0
        ),
        days14Forecast = emptyList(),
        hours24Forecast = emptyList()
    )

}