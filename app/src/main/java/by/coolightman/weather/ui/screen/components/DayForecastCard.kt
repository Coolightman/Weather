package by.coolightman.weather.ui.screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import by.coolightman.weather.R
import by.coolightman.weather.ui.theme.WeatherTheme
import by.coolightman.weather.util.DEGREE_UNIT

@Composable
fun DayForecastCard(
    date: Long,
    icon: String,
    maxTemp: Int,
    minTemp: Int
) {
    DayForecastCardContainer {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
        ) {
            Column() {
                Text(text = "November 19")
                Text(text = "Tomorrow")
            }

            Spacer(modifier = Modifier.weight(1f))

            Image(
                painter = painterResource(id = R.drawable.cloudy),
                contentDescription = "icon",
                modifier = Modifier.size(36.dp)
            )

            SpacerVert(with = 32.dp)

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Max")
                Text(text = "$maxTemp$DEGREE_UNIT")
            }

            SpacerVert(with = 32.dp)

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Min")
                Text(text = "$minTemp$DEGREE_UNIT")
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    WeatherTheme(darkTheme = true) {
        DayForecastCard(
            date = System.currentTimeMillis(),
            icon = "",
            maxTemp = -3,
            minTemp = -5
        )
    }
}