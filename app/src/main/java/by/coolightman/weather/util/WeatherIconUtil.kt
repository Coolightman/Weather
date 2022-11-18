package by.coolightman.weather.util

import by.coolightman.weather.R

fun String.getIconRes(): Int {
    return WeatherIcons.list.getOrDefault(this, R.drawable.protection)
}

private object WeatherIcons{
    val list: Map<String, Int> = mapOf(
        Pair("clear-day", R.drawable.clear_day),
        Pair("clear-night", R.drawable.clear_night),
        Pair("cloudy", R.drawable.cloudy),
        Pair("fog", R.drawable.fog),
        Pair("hail", R.drawable.hail),
        Pair("partly-cloudy-day", R.drawable.partly_cloudy_day),
        Pair("partly-cloudy-night", R.drawable.partly_cloudy_night),
        Pair("rain", R.drawable.rain),
        Pair("rain-snow", R.drawable.rain_snow),
        Pair("rain-snow-showers-day", R.drawable.rain_snow_showers_day),
        Pair("rain-snow-showers-night", R.drawable.rain_snow_showers_night),
        Pair("showers-day", R.drawable.showers_day),
        Pair("showers-night", R.drawable.showers_night),
        Pair("sleet", R.drawable.sleet),
        Pair("snow", R.drawable.snow),
        Pair("snow-showers-day", R.drawable.snow_showers_day),
        Pair("snow-showers-night", R.drawable.snow_showers_night),
        Pair("thunder", R.drawable.thunder),
        Pair("thunder-rain", R.drawable.thunder_rain),
        Pair("thunder-showers-day", R.drawable.thunder_showers_day),
        Pair("thunder-showers-night", R.drawable.thunder_showers_night),
        Pair("wind", R.drawable.wind),
    )
}