package by.coolightman.weather.domain.usecase.weather

import by.coolightman.weather.domain.repository.WeatherRepository

class FetchWeatherDataByCityUseCase(
    private val weatherRepository: WeatherRepository
) {
    operator fun invoke(place: String) = weatherRepository.fetchWeatherStamp(place)
}