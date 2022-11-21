package by.coolightman.weather.domain.usecase.preferences

import by.coolightman.weather.domain.repository.PreferencesRepository

class GetBooleanPreferenceUseCase(
    private val repository: PreferencesRepository
) {
    operator fun invoke(key: String, defaultValue: Boolean) =
        repository.getBoolean(key, defaultValue)
}