package by.coolightman.weather.util

import java.net.UnknownHostException

fun Throwable.getFormattedMessage(): String {
    return when (this) {
        is UnknownHostException -> "Check INTERNET connection"
        else -> {
            this.localizedMessage?.let { localizedMessage } ?: "Ups! Have some problems"
        }
    }
}