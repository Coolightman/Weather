package by.coolightman.weather.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.coolightman.weather.domain.model.ApiState
import by.coolightman.weather.domain.usecase.FetchWeatherDataByCityUseCase
import by.coolightman.weather.domain.usecase.GetLasWeatherStampUseCase
import by.coolightman.weather.domain.usecase.preferences.GetStringPreferenceUseCase
import by.coolightman.weather.domain.usecase.preferences.PutStringPreferenceUseCase
import by.coolightman.weather.util.LAST_REFRESH_PREF_KEY
import by.coolightman.weather.util.PLACE_PREF_KEY
import by.coolightman.weather.util.THIRTY_MINUTES_MILLIS
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BaseViewModel(
    val fetchWeatherDataByCityUseCase: FetchWeatherDataByCityUseCase,
    val getLasWeatherStampUseCase: GetLasWeatherStampUseCase,
    val getStringPreferenceUseCase: GetStringPreferenceUseCase,
    val putStringPreferenceUseCase: PutStringPreferenceUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(BaseUiState())
    val uiState: StateFlow<BaseUiState> = _uiState.asStateFlow()

    init {
        getLastWeatherStamp()
        getWeatherPlacePreferences()
        getLastRefreshDate()
    }

    private fun getLastRefreshDate() {
        viewModelScope.launch {
            val lastRefresh =
                getStringPreferenceUseCase(LAST_REFRESH_PREF_KEY, "0").first().toLong()
            val delay = System.currentTimeMillis() - lastRefresh
            if (delay > THIRTY_MINUTES_MILLIS) {
                fetchWeatherStamp()
            }
        }
    }

    private fun getWeatherPlacePreferences() {
        viewModelScope.launch {
            getStringPreferenceUseCase(PLACE_PREF_KEY, "Grodno").collectLatest {
                _uiState.update { currentState ->
                    currentState.copy(currentPlace = it)
                }
            }
        }
    }

    private fun getLastWeatherStamp() {
        viewModelScope.launch {
            getLasWeatherStampUseCase().collectLatest { stamp ->
                _uiState.update { currentState ->
                    currentState.copy(
                        resolvedAddress = stamp.resolvedAddress,
                        temp = stamp.currentConditions.temp.toInt(),
                        icon = stamp.currentConditions.icon,
                        conditions = stamp.currentConditions.conditions,
                        feelsLikeTemp = stamp.currentConditions.feelsLike.toInt(),
                        windSpeed = stamp.currentConditions.windSpeed.toInt(),
                        windDir = stamp.currentConditions.windDir.toInt(),
                        pressure = stamp.currentConditions.pressure.toInt(),
                        humidity = stamp.currentConditions.humidity.toInt(),
                        hours24Forecast = stamp.hours24Forecast,
                        days14Forecast = stamp.days14Forecast,
                        lastRefresh = stamp.createdAt,
                        sunrise = stamp.currentConditions.sunriseEpoch,
                        sunset = stamp.currentConditions.sunsetEpoch
                    )
                }
            }
        }
    }

    fun setNewPlace(place: String){
        viewModelScope.launch {
            putStringPreferenceUseCase(PLACE_PREF_KEY, place)
            fetchWeatherStamp()
        }
    }

    private fun fetchWeatherStamp() {
        viewModelScope.launch {
            val currentPlace = uiState.value.currentPlace
            fetchWeatherDataByCityUseCase(currentPlace).collectLatest {
                _uiState.update { currentState ->
                    currentState.copy(apiState = it)
                }
                updateLastRefresh(it)
            }
        }
    }

    private suspend fun updateLastRefresh(apiState: ApiState) {
        if (apiState is ApiState.Success){
            val now = System.currentTimeMillis()
            putStringPreferenceUseCase(LAST_REFRESH_PREF_KEY, now.toString())
        }
    }

    fun onClickRefresh() {
        fetchWeatherStamp()
    }
}