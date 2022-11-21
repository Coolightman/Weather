package by.coolightman.weather.domain.usecase.preferences

import by.coolightman.weather.domain.repository.PreferencesRepository

class PutStringPreferenceUseCase(
    private val repository: PreferencesRepository
) {
    suspend operator fun invoke(key: String, value: String) = repository.putString(key, value)
}