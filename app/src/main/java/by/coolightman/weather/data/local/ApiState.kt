package by.coolightman.weather.data.local

sealed class ApiState {
    object Loading : ApiState()
    class Failure(val error: Throwable) : ApiState()
    class Success(val data: Any) : ApiState()
    object Empty : ApiState()
}
