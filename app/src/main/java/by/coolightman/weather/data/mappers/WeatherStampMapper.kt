package by.coolightman.weather.data.mappers

import by.coolightman.weather.data.local.modelDb.WeatherStampDb
import by.coolightman.weather.data.remote.dto.WeatherStampDto
import by.coolightman.weather.domain.model.CurrentConditions
import by.coolightman.weather.domain.model.DayWeather
import by.coolightman.weather.domain.model.HourWeather
import by.coolightman.weather.domain.model.WeatherStamp
import by.coolightman.weather.util.orEmpty

fun WeatherStampDto.toDbModel(): WeatherStampDb = WeatherStampDb(
    resolvedAddress = resolvedAddress.orEmpty(),
    address = address.orEmpty(),
    timezone = timezone.orEmpty(),
    tzOffset = tzOffset.orEmpty()
)

fun WeatherStampDb.toModel(
    currentConditions: CurrentConditions,
    days14Forecast: List<DayWeather>,
    hours24Forecast: List<HourWeather>
): WeatherStamp = WeatherStamp(
    id,
    createdAt,
    resolvedAddress,
    address,
    timezone,
    tzOffset,
    currentConditions,
    days14Forecast,
    hours24Forecast
)