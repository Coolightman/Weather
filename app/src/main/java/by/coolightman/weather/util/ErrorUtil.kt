package by.coolightman.weather.util

import by.coolightman.weather.domain.model.ResponseException
import java.net.UnknownHostException

fun Throwable.getFormattedMessage(): String {
    return when (this) {
        is UnknownHostException -> "Check INTERNET connection"
        is ResponseException -> "Can't find this place"
        else -> {
            this.localizedMessage?.let { localizedMessage } ?: "Ups! Have some problems"
        }
    }
}