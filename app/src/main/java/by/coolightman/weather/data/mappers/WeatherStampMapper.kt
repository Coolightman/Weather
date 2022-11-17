package by.coolightman.weather.data.mappers

import by.coolightman.weather.data.local.modelDb.WeatherStampDb
import by.coolightman.weather.data.remote.dto.WeatherStampDto
import by.coolightman.weather.util.orEmpty

fun WeatherStampDto.toDbModel(): WeatherStampDb = WeatherStampDb(
    resolvedAddress = resolvedAddress.orEmpty(),
    address = address.orEmpty(),
    timezone = timezone.orEmpty(),
    tzOffset = tzOffset.orEmpty()
)