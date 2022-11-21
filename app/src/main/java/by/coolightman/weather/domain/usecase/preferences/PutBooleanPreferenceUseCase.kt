package by.coolightman.weather.domain.usecase.preferences

import by.coolightman.weather.domain.repository.PreferencesRepository

class PutBooleanPreferenceUseCase(
    private val repository: PreferencesRepository
) {
    suspend operator fun invoke(key: String, value: Boolean) = repository.putBoolean(key, value)
}