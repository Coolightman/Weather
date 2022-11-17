package by.coolightman.weather.ui.screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.coolightman.weather.data.remote.service.ApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class BaseViewModel(
    private val apiService: ApiService
) : ViewModel() {

    private val _uiState = MutableStateFlow(BaseUiState())
    val uiState: StateFlow<BaseUiState> = _uiState.asStateFlow()

    init {
        fetchWeatherStamp()
    }

    private fun fetchWeatherStamp() {
        viewModelScope.launch {
            try {
                val response = apiService.getWeatherStamp("Minsk")
                Log.d("WEATHER_TAG", "response: $response")
                if (response.isSuccessful){
                    response.body()?.let {
                        Log.d("WEATHER_TAG", "body: $it")
                    }
                }
            } catch (e: Exception) {
                Log.d("WEATHER_TAG", "error: $e")
            }
        }
    }
}