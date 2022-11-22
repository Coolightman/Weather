package by.coolightman.weather.domain.model

data class Place(
    val id: Long,
    val address: String,
    val resolvedAddress: String,
    val selected: Boolean
)