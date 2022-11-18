package by.coolightman.weather.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.coolightman.weather.domain.usecase.FetchWeatherDataByCityUseCase
import by.coolightman.weather.domain.usecase.GetLasWeatherStampUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BaseViewModel(
    val fetchWeatherDataByCityUseCase: FetchWeatherDataByCityUseCase,
    val getLasWeatherStampUseCase: GetLasWeatherStampUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(BaseUiState())
    val uiState: StateFlow<BaseUiState> = _uiState.asStateFlow()

    init {
//        fetchWeatherStamp()
        getLastWeatherStamp()
    }

    private fun getLastWeatherStamp() {
        viewModelScope.launch{
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

    private fun fetchWeatherStamp() {
        viewModelScope.launch{
            fetchWeatherDataByCityUseCase("гродно").collectLatest {
                _uiState.update { currentState ->
                    currentState.copy(apiState = it)
                }
            }
        }
    }

    fun onClickRefresh() {
        fetchWeatherStamp()
    }
}