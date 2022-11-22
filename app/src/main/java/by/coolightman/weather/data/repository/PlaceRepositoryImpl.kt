package by.coolightman.weather.data.repository

import by.coolightman.weather.data.local.dao.PlaceDao
import by.coolightman.weather.data.mappers.toModel
import by.coolightman.weather.data.mappers.toModelDb
import by.coolightman.weather.domain.model.Place
import by.coolightman.weather.domain.repository.PlaceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PlaceRepositoryImpl(
    private val placeDao: PlaceDao
) : PlaceRepository {

    override fun getAllFlow(): Flow<List<Place>> =
        placeDao.getAllFlow().map { list -> list.map { it.toModel() } }

    override suspend fun update(place: Place) {
        placeDao.update(place.toModelDb())
    }

    override suspend fun updateList(list: List<Place>) {
        placeDao.updateList(list.map { it.toModelDb() })
    }

    override suspend fun delete(id: Long) {
        placeDao.delete(id)
    }
}