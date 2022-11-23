package by.coolightman.weather.util

import by.coolightman.weather.R

fun String.getIconRes(): Int {
    return WeatherIcons.list.getOrDefault(this, R.drawable.protection)
}

fun String.getIconMonoRes(): Int {
    return WeatherIconsMono.list.getOrDefault(this, R.drawable.protection)
}

fun String.getBackgroundRes(): Int {
    return WeatherBackground.list.getOrDefault(this, R.drawable.cloudy_day_bck)
}

private object WeatherIcons {
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

private object WeatherIconsMono {
    val list: Map<String, Int> = mapOf(
        Pair("clear-day", R.drawable.clear_day_mono),
        Pair("clear-night", R.drawable.clear_night_mono),
        Pair("cloudy", R.drawable.cloudy_mono),
        Pair("fog", R.drawable.fog_mono),
        Pair("hail", R.drawable.hail_mono),
        Pair("partly-cloudy-day", R.drawable.partly_cloudy_day_mono),
        Pair("partly-cloudy-night", R.drawable.partly_cloudy_night_mono),
        Pair("rain", R.drawable.rain_mono),
        Pair("rain-snow", R.drawable.rain_snow_mono),
        Pair("rain-snow-showers-day", R.drawable.rain_snow_showers_day_mono),
        Pair("rain-snow-showers-night", R.drawable.rain_snow_showers_night_mono),
        Pair("showers-day", R.drawable.showers_day_mono),
        Pair("showers-night", R.drawable.showers_night_mono),
        Pair("sleet", R.drawable.sleet_mono),
        Pair("snow", R.drawable.snow_mono),
        Pair("snow-showers-day", R.drawable.snow_showers_day_mono),
        Pair("snow-showers-night", R.drawable.snow_showers_night_mono),
        Pair("thunder", R.drawable.thunder_mono),
        Pair("thunder-rain", R.drawable.thunder_rain_mono),
        Pair("thunder-showers-day", R.drawable.thunder_showers_day_mono),
        Pair("thunder-showers-night", R.drawable.thunder_showers_night_mono),
        Pair("wind", R.drawable.wind_mono),
    )
}

private object WeatherBackground {
    val list: Map<String, Int> = mapOf(
        Pair("clear-day", R.drawable.clear_day_bck),
        Pair("clear-night", R.drawable.clear_night_bck),
        Pair("cloudy", R.drawable.cloudy_day_bck),
        Pair("fog", R.drawable.fog_bck),
        Pair("hail", R.drawable.rain_night_bck),
        Pair("partly-cloudy-day", R.drawable.partly_cloudy_day_bck),
        Pair("partly-cloudy-night", R.drawable.partly_cloudy_night_bck),
        Pair("rain", R.drawable.rain_day_bck),
        Pair("rain-snow", R.drawable.rain_day_bck),
        Pair("rain-snow-showers-day", R.drawable.rain_day_bck),
        Pair("rain-snow-showers-night", R.drawable.rain_night_bck),
        Pair("showers-day", R.drawable.rain_day_bck),
        Pair("showers-night", R.drawable.rain_night_bck),
        Pair("sleet", R.drawable.rain_night_bck),
        Pair("snow", R.drawable.snow_day_bck),
        Pair("snow-showers-day", R.drawable.snow_day_bck),
        Pair("snow-showers-night", R.drawable.snow_night_bck),
        Pair("thunder", R.drawable.thunder_bck),
        Pair("thunder-rain", R.drawable.rain_night_bck),
        Pair("thunder-showers-day", R.drawable.rain_day_bck),
        Pair("thunder-showers-night", R.drawable.rain_night_bck),
        Pair("wind", R.drawable.wind_bck)
    )
}