package by.coolightman.weather.ui.screen

import android.annotation.SuppressLint
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import by.coolightman.weather.R
import by.coolightman.weather.domain.model.HourWeather
import by.coolightman.weather.ui.screen.components.DayForecastCard
import by.coolightman.weather.ui.screen.components.EmptyDaysForecastList
import by.coolightman.weather.ui.screen.components.HourForecastColumn
import by.coolightman.weather.ui.screen.components.ImagedBlock
import by.coolightman.weather.ui.screen.components.NowParamsBlock
import by.coolightman.weather.ui.screen.components.NowWeatherBlock
import by.coolightman.weather.ui.screen.components.SpacerHorizon
import by.coolightman.weather.ui.screen.components.WeatherTopBar
import by.coolightman.weather.ui.theme.WeatherTheme
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun BaseScreen(
    uiState: BaseUiState,
    onClickRefresh: () -> Unit
) {

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val lazyRowState = rememberLazyListState()
    val scrollState = rememberScrollState()

    Scaffold(
        scaffoldState = scaffoldState,
        drawerShape = RectangleShape,
        drawerContent = {}
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            WeatherTopBar(
                resolvedAddress = uiState.resolvedAddress,
                onClickMenu = { scope.launch { scaffoldState.drawerState.open() } },
                onClickRefresh = { onClickRefresh() }
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
            ) {
                ImagedBlock(image = painterResource(R.drawable.good_weather_day)) {
                    NowWeatherBlock(
                        temp = uiState.temp,
                        icon = uiState.icon,
                        description = uiState.conditions,
                        feelTemp = uiState.feelsLikeTemp
                    )

                    LazyRow(
                        state = lazyRowState,
                        verticalAlignment = Alignment.CenterVertically,
                        contentPadding = PaddingValues(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        modifier = Modifier.fillMaxSize()
                    ) {
                        item {
                            NowParamsBlock(
                                wind = uiState.windSpeed,
                                windDirection = uiState.windDir,
                                pressure = uiState.pressure,
                                humidity = uiState.humidity
                            )
                        }
                        items(
                            items = uiState.hours24Forecast,
                            key = { it.id }
                        ) { item: HourWeather ->
                            HourForecastColumn(item = item)
                        }
                    }
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp, 16.dp, 16.dp, 8.dp)
                ) {
                    Text(
                        text = "14 day forecast",
                        style = MaterialTheme.typography.h6.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )

                    SpacerHorizon(height = 12.dp)

                    if (uiState.days14Forecast.isNotEmpty()) {
                        uiState.days14Forecast.forEachIndexed { index, dayWeather ->
                            DayForecastCard(
                                date = dayWeather.datetimeEpoch,
                                icon = dayWeather.icon,
                                maxTemp = dayWeather.tempMax.toInt(),
                                minTemp = dayWeather.tempMin.toInt(),
                                isFirst = index == 0
                            )
                        }
                    } else {
                        EmptyDaysForecastList()
                    }
                }
            }
        }
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun BaseScreenPreview() {
    WeatherTheme {
        BaseScreen(
            uiState = BaseUiState(
                hours24Forecast = listOf(
                    HourWeather(
                        id = 1,
                        stampId = 1,
                        datetimeEpoch = System.currentTimeMillis(),
                        temp = 4.0,
                        icon = "rain-snow"
                    ),
                    HourWeather(
                        id = 2,
                        stampId = 1,
                        datetimeEpoch = System.currentTimeMillis(),
                        temp = -4.0,
                        icon = "snow"
                    ),
                    HourWeather(
                        id = 3,
                        stampId = 1,
                        datetimeEpoch = System.currentTimeMillis(),
                        temp = 0.0,
                        icon = "snow-showers-night"
                    )
                )
            ),
            onClickRefresh = {}
        )
    }
}