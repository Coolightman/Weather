package by.coolightman.weather.data.mappers

import by.coolightman.weather.data.local.modelDb.CurrentConditionsDb
import by.coolightman.weather.data.remote.dto.CurrentConditionsDto
import by.coolightman.weather.domain.model.CurrentConditions
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

fun CurrentConditionsDb.toModel(): CurrentConditions = CurrentConditions(
    id,
    stampId,
    datetimeEpoch * 1000,
    temp,
    feelsLike,
    humidity,
    dew,
    windSpeed,
    windDir,
    pressure,
    conditions,
    icon,
    sunriseEpoch * 1000,
    sunsetEpoch * 1000,
    moonPhase
)