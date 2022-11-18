package by.coolightman.weather.ui.screen.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

@Composable
fun EmptyDaysForecastList() {

    val daysWeatherList by remember {
        mutableStateOf((1..14))
    }

    daysWeatherList.forEach { _ ->
        DayForecastCardContainer { }
    }
}