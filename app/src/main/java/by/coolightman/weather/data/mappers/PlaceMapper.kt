package by.coolightman.weather.data.mappers

import by.coolightman.weather.data.local.modelDb.PlaceDb
import by.coolightman.weather.domain.model.Place

fun PlaceDb.toModel(): Place = Place(
    id,
    address,
    resolvedAddress,
    selected
)

fun Place.toModelDb(): PlaceDb = PlaceDb(
    id,
    address,
    resolvedAddress,
    selected
)