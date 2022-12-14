package by.coolightman.weather.domain.usecase.weather

import by.coolightman.weather.domain.repository.WeatherRepository

class GetLasWeatherStampUseCase(
    private val weatherRepository: WeatherRepository
) {
    operator fun invoke() = weatherRepository.getLastWeatherStamp()
}