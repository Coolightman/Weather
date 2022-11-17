package by.coolightman.weather.util


private const val EMPTY_INT = 0
private const val EMPTY_LONG = 0L
private const val EMPTY_DOUBLE = 0.0

fun Long?.orEmpty(): Long = this ?: EMPTY_LONG

fun Double?.orEmpty(): Double = this ?: EMPTY_DOUBLE

fun Int?.orEmpty(): Int = this ?: EMPTY_INT