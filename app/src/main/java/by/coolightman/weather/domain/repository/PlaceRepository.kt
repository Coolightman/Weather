package by.coolightman.weather.domain.repository

import by.coolightman.weather.domain.model.Place
import kotlinx.coroutines.flow.Flow

interface PlaceRepository {

    fun getAllFlow(): Flow<List<Place>>

    suspend fun update(place: Place)

    suspend fun updateList(list: List<Place>)

    suspend fun delete(id: Long)
}