package by.coolightman.weather.ui.screen

import android.annotation.SuppressLint
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import by.coolightman.weather.R
import by.coolightman.weather.domain.model.ApiState
import by.coolightman.weather.domain.model.HourWeather
import by.coolightman.weather.ui.screen.components.BaseScreenDrawer
import by.coolightman.weather.ui.screen.components.DayForecastCard
import by.coolightman.weather.ui.screen.components.EmptyDaysForecastList
import by.coolightman.weather.ui.screen.components.HourForecastColumn
import by.coolightman.weather.ui.screen.components.ImagedBlock
import by.coolightman.weather.ui.screen.components.LastRefresh
import by.coolightman.weather.ui.screen.components.NowParamsBlock
import by.coolightman.weather.ui.screen.components.NowWeatherBlock
import by.coolightman.weather.ui.screen.components.SpacerHorizon
import by.coolightman.weather.ui.screen.components.SunSetRiseRow
import by.coolightman.weather.ui.screen.components.WeatherTopBar
import by.coolightman.weather.ui.theme.WeatherTheme
import by.coolightman.weather.util.getFormattedMessage
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun BaseScreen(
    uiState: BaseUiState,
    onClickRefresh: () -> Unit,
    onEnterPlace: (String) -> Unit
) {

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val lazyRowState = rememberLazyListState()
    val scrollState = rememberScrollState()
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(uiState.apiState) {
        if (uiState.apiState is ApiState.Failure) {
            scaffoldState.snackbarHostState.showSnackbar(
                message = uiState.apiState.error.getFormattedMessage()
            )
        }
        if (uiState.apiState is ApiState.Success){
            scaffoldState.drawerState.close()
            keyboardController?.hide()
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        drawerShape = RectangleShape,
        drawerContent = {
            BaseScreenDrawer(
                onEnterPlace = { onEnterPlace(it) }
            )
        }
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            WeatherTopBar(
                resolvedAddress = uiState.resolvedAddress,
                apiState = uiState.apiState,
                onClickMenu = { scope.launch { scaffoldState.drawerState.open() } },
                onClickRefresh = { onClickRefresh() }
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
            ) {
                ImagedBlock(image = painterResource(R.drawable.good_weather_day)) {
                    LastRefresh(uiState.lastRefresh)

                    NowWeatherBlock(
                        temp = uiState.temp,
                        icon = uiState.icon,
                        description = uiState.conditions,
                        feelTemp = uiState.feelsLikeTemp
                    )

                    LazyRow(
                        state = lazyRowState,
                        verticalAlignment = Alignment.CenterVertically,
                        contentPadding = PaddingValues(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        modifier = Modifier.height(130.dp)
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

                    SunSetRiseRow(
                        sunrise = uiState.sunrise,
                        sunset = uiState.sunset
                    )
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
                                index = index
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
    val time = System.currentTimeMillis()
    WeatherTheme {
        BaseScreen(
            uiState = BaseUiState(
                sunrise = time,
                sunset = time,
                lastRefresh = time,
                hours24Forecast = listOf(
                    HourWeather(
                        id = 1,
                        stampId = 1,
                        datetimeEpoch = time,
                        temp = 4.0,
                        icon = "rain-snow"
                    ),
                    HourWeather(
                        id = 2,
                        stampId = 1,
                        datetimeEpoch = time,
                        temp = -4.0,
                        icon = "snow"
                    ),
                    HourWeather(
                        id = 3,
                        stampId = 1,
                        datetimeEpoch = time,
                        temp = 0.0,
                        icon = "snow-showers-night"
                    )
                )
            ),
            onClickRefresh = {},
            onEnterPlace = {}
        )
    }
}