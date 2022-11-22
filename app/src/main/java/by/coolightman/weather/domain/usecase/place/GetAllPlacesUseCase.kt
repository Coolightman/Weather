package by.coolightman.weather.domain.usecase.place

import by.coolightman.weather.domain.repository.PlaceRepository

class GetAllPlacesUseCase(
    private val placeRepository: PlaceRepository
) {
    operator fun invoke() = placeRepository.getAllFlow()
}