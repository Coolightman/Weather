package by.coolightman.weather.domain.usecase.place

import by.coolightman.weather.domain.repository.PlaceRepository

class DeletePlaceUseCase(
    private val placeRepository: PlaceRepository
) {
    suspend operator fun invoke(placeId: Long) = placeRepository.delete(placeId)
}