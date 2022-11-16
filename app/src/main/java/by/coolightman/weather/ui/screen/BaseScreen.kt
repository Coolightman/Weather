package by.coolightman.weather.ui.screen

import android.annotation.SuppressLint
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import by.coolightman.weather.R
import by.coolightman.weather.ui.screen.components.NowWeatherBlock
import by.coolightman.weather.ui.screen.components.WeatherTopBar
import by.coolightman.weather.ui.theme.TintFilter
import by.coolightman.weather.ui.theme.WeatherTheme
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun BaseScreen() {

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

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
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp)
                    .clip(RoundedCornerShape(0.dp, 0.dp, 32.dp, 32.dp))
                    .paint(
                        painter = painterResource(R.drawable.good_weather_day),
                        contentScale = ContentScale.FillWidth
                    )
                    .background(TintFilter)
            ) {
                NowWeatherBlock(
                    temp = "+3",
                    iconRes = R.drawable.cloudy,
                    description = "Overcast",
                    feelTemp = "-3"
                )
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