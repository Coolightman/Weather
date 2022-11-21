package by.coolightman.weather.domain.usecase.preferences

import by.coolightman.weather.domain.repository.PreferencesRepository

class GetStringPreferenceUseCase(
    private val repository: PreferencesRepository
) {
    operator fun invoke(key: String, defaultValue: String) =
        repository.getString(key, defaultValue)
}