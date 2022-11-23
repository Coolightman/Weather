package by.coolightman.weather.ui.screen

import android.annotation.SuppressLint
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
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
    onEnterPlace: (String) -> Unit,
    onDeletePlace: (Long) -> Unit
) {

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val lazyRowState = rememberLazyListState()
    val scrollState = rememberScrollState()
    val keyboardController = LocalSoftwareKeyboardController.current

    var topBarAlpha by remember {
        mutableStateOf(0f)
    }

    LaunchedEffect(topBarAlpha){
        Log.d("BaseScreenTAG", "topBarAlpha: $topBarAlpha")
    }

    LaunchedEffect(uiState.apiState) {
        if (uiState.apiState is ApiState.Failure) {
            scaffoldState.snackbarHostState.showSnackbar(
                message = uiState.apiState.error.getFormattedMessage()
            )
        }
        if (uiState.apiState is ApiState.Success) {
            scaffoldState.drawerState.close()
        }
    }

    LaunchedEffect(scaffoldState.drawerState.isClosed) {
        if (scaffoldState.drawerState.isClosed) {
            keyboardController?.hide()
        }
    }

    BackHandler(scaffoldState.drawerState.isOpen) {
        scope.launch {
            scaffoldState.drawerState.close()
        }
    }

    LaunchedEffect(scrollState.value) {
        topBarAlpha = derivedStateOf {
            if (scrollState.value <= 200) {
                scrollState.value / 200f
            } else 1f
        }.value
    }

    Scaffold(
        scaffoldState = scaffoldState,
        drawerShape = RectangleShape,
        drawerContent = {
            BaseScreenDrawer(
                places = uiState.places,
                apiState = uiState.apiState,
                onEnterPlace = { onEnterPlace(it) },
                onDeletePlace = { onDeletePlace(it) }
            )
        }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            WeatherTopBar(
                resolvedAddress = uiState.resolvedAddress,
                apiState = uiState.apiState,
                alpha = topBarAlpha,
                onClickMenu = { scope.launch { scaffoldState.drawerState.open() } },
                onClickRefresh = { onClickRefresh() },
                modifier = Modifier.zIndex(1f)
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
            ) {
                ImagedBlock(image = uiState.icon) {

                    if (uiState.hours24Forecast.isNotEmpty()) {
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
                }

                LastRefresh(uiState.lastRefresh)

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp, 4.dp, 16.dp, 8.dp)
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
            onEnterPlace = {},
            onDeletePlace = {}
        )
    }
}