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
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import by.coolightman.weather.R
import by.coolightman.weather.ui.screen.components.ImagedBlock
import by.coolightman.weather.ui.screen.components.NowParamsBlock
import by.coolightman.weather.ui.screen.components.NowWeatherBlock
import by.coolightman.weather.ui.screen.components.SpacerHorizon
import by.coolightman.weather.ui.screen.components.WeatherTopBar
import by.coolightman.weather.ui.theme.WeatherTheme
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun BaseScreen() {

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val lazyRowState = rememberLazyListState()
    val scrollState = rememberScrollState()

    val daysWeatherList by remember {
        mutableStateOf((1..14))
    }

    Scaffold(
        scaffoldState = scaffoldState,
        drawerShape = RectangleShape,
        drawerContent = {}
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            WeatherTopBar(
                mainText = "Hrodna",
                secondText = "district Folush",
                onClickMenu = { scope.launch { scaffoldState.drawerState.open() } },
                onClickReload = { }
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
            ) {
                ImagedBlock(image = painterResource(R.drawable.good_weather_day)) {
                    NowWeatherBlock(
                        temp = "+3",
                        icon = painterResource(R.drawable.cloudy),
                        description = "Overcast",
                        feelTemp = "-3"
                    )

                    LazyRow(
                        state = lazyRowState,
                        verticalAlignment = Alignment.CenterVertically,
                        contentPadding = PaddingValues(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        modifier = Modifier.fillMaxSize()
                    ) {
                        item {
                            NowParamsBlock(
                                wind = "4",
                                windDirection = "NW",
                                pressure = "748",
                                humidity = "89"
                            )
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

                    daysWeatherList.forEach { _ ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp)
                        ) {

                        }
                        SpacerHorizon(height = 8.dp)
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
        BaseScreen()
    }
}