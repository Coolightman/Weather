package by.coolightman.weather.data.mappers

import by.coolightman.weather.data.local.modelDb.CurrentConditionsDb
import by.coolightman.weather.data.remote.dto.CurrentConditionsDto
import by.coolightman.weather.util.orEmpty

fun CurrentConditionsDto.toDbModel(stampId: Long): CurrentConditionsDb = CurrentConditionsDb(
    stampId = stampId,
    datetimeEpoch = datetimeEpoch.orEmpty(),
    temp = temp.orEmpty(),
    feelsLike = feelsLike.orEmpty(),
    humidity = humidity.orEmpty(),
    dew = dew.orEmpty(),
    windSpeed = windSpeed.orEmpty(),
    windDir = windDir.orEmpty(),
    pressure = pressure.orEmpty(),
    conditions = conditions.orEmpty(),
    icon = icon.orEmpty(),
    sunriseEpoch = sunriseEpoch.orEmpty(),
    sunsetEpoch = sunsetEpoch.orEmpty(),
    moonPhase = moonPhase.orEmpty()
)