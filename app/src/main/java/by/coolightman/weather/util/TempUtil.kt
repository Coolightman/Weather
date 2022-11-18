package by.coolightman.weather.util

fun Int.updateSign(): String =
    if (this > 0) "+$this"
    else "$this"