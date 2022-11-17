package by.coolightman.weather.data.mappers

import by.coolightman.weather.data.local.modelDb.HourWeatherDb
import by.coolightman.weather.data.remote.dto.HourWeatherDto
import by.coolightman.weather.util.orEmpty

fun HourWeatherDto.toDbModel(stampId: Long): HourWeatherDb = HourWeatherDb(
    stampId = stampId,
    datetimeEpoch = datetimeEpoch.orEmpty(),
    temp = temp.orEmpty(),
    icon = icon.orEmpty()
)