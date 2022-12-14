package by.coolightman.weather.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.coolightman.weather.domain.model.ApiState
import by.coolightman.weather.domain.usecase.place.DeletePlaceUseCase
import by.coolightman.weather.domain.usecase.place.GetAllPlacesUseCase
import by.coolightman.weather.domain.usecase.preferences.GetStringPreferenceUseCase
import by.coolightman.weather.domain.usecase.preferences.PutStringPreferenceUseCase
import by.coolightman.weather.domain.usecase.weather.FetchWeatherDataByCityUseCase
import by.coolightman.weather.domain.usecase.weather.GetLasWeatherStampUseCase
import by.coolightman.weather.util.LAST_REFRESH_PREF_KEY
import by.coolightman.weather.util.PLACE_PREF_KEY
import by.coolightman.weather.util.THIRTY_MINUTES_MILLIS
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BaseViewModel(
    private val fetchWeatherDataByCityUseCase: FetchWeatherDataByCityUseCase,
    private val getLasWeatherStampUseCase: GetLasWeatherStampUseCase,
    private val getStringPreferenceUseCase: GetStringPreferenceUseCase,
    private val putStringPreferenceUseCase: PutStringPreferenceUseCase,
    private val getAllPlacesUseCase: GetAllPlacesUseCase,
    private val deletePlaceUseCase: DeletePlaceUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(BaseUiState())
    val uiState: StateFlow<BaseUiState> = _uiState.asStateFlow()

    init {
        getLastWeatherStamp()
        getWeatherPlacePreferences()
        getLastRefreshDate()
        getPlaces()
    }

    private fun getPlaces() {
        viewModelScope.launch {
            getAllPlacesUseCase().collectLatest {
                _uiState.update { currentState ->
                    currentState.copy(places = it)
                }
            }
        }
    }

    private fun getLastRefreshDate() {
        viewModelScope.launch {
            val lastRefresh =
                getStringPreferenceUseCase(LAST_REFRESH_PREF_KEY, "0").first().toLong()
            val delay = System.currentTimeMillis() - lastRefresh
            if (delay > THIRTY_MINUTES_MILLIS) {
                fetchWeatherStampByPref()
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

    fun fetchWeatherStampByPlace(place: String) {
        viewModelScope.launch {
            fetchWeatherData(place)
            delay(1500)
            updatePlacePrefIfSuccess(uiState.value.apiState, place)
        }
    }

    fun deletePlace(placeId: Long) {
        viewModelScope.launch {
            deletePlaceUseCase(placeId)
        }
    }

    private fun fetchWeatherStampByPref() {
        viewModelScope.launch {
            val currentPlace = uiState.value.currentPlace
            fetchWeatherData(currentPlace)
        }
    }

    private suspend fun fetchWeatherData(place: String) {
        fetchWeatherDataByCityUseCase(place).collectLatest {
            _uiState.update { currentState ->
                currentState.copy(apiState = it)
            }
            updateLastRefresh(it)
        }
    }

    private suspend fun updatePlacePrefIfSuccess(apiState: ApiState, place: String) {
        if (apiState is ApiState.Success) {
            putStringPreferenceUseCase(PLACE_PREF_KEY, place)
        }
    }

    private suspend fun updateLastRefresh(apiState: ApiState) {
        if (apiState is ApiState.Success) {
            val now = System.currentTimeMillis()
            putStringPreferenceUseCase(LAST_REFRESH_PREF_KEY, now.toString())
        }
    }

    fun onClickRefresh() {
        fetchWeatherStampByPref()
    }
}