package by.coolightman.weather.data.mappers

import by.coolightman.weather.data.local.modelDb.DayWeatherDb
import by.coolightman.weather.data.remote.dto.DayWeatherDto
import by.coolightman.weather.domain.model.DayWeather
import by.coolightman.weather.util.orEmpty

fun DayWeatherDto.toDbModel(stampId: Long): DayWeatherDb = DayWeatherDb(
    stampId = stampId,
    datetimeEpoch = datetimeEpoch.orEmpty(),
    tempMax = tempMax.orEmpty(),
    tempMin = tempMin.orEmpty(),
    icon = icon.orEmpty()
)

fun DayWeatherDb.toModel():DayWeather = DayWeather(
    id,
    stampId,
    datetimeEpoch,
    tempMax,
    tempMin,
    icon
)