package by.coolightman.weather.ui.screen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import by.coolightman.weather.ui.theme.WeatherTheme
import by.coolightman.weather.util.getIconMonoRes
import by.coolightman.weather.util.toDayOfWeek
import by.coolightman.weather.util.toMonthDay
import java.util.Calendar

@Composable
fun DayForecastCard(
    date: Long,
    icon: String,
    maxTemp: Int,
    minTemp: Int,
    index: Int
) {
    DayForecastCardContainer {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 4.dp)
        ) {
            Column {
                GrayText(text = date.toMonthDay())
                DayOfWeekText(
                    text = date.toDayOfWeek(),
                    isToday = index == 0,
                    isTomorrow = index == 1
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Icon(
                painter = painterResource(icon.getIconMonoRes()),
                contentDescription = "icon",
                modifier = Modifier.size(32.dp)
            )

            SpacerVert(with = 16.dp)

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.width(48.dp)
            ) {
                if (index == 0) {
                    GrayText(text = "Max")
                }
                DegreeText(temp = maxTemp)
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.width(48.dp)
            ) {
                if (index == 0) {
                    GrayText(text = "Min")
                }
                DegreeText(temp = minTemp, isGray = true)
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    WeatherTheme(darkTheme = true) {
        DayForecastCard(
            date = Calendar.getInstance().apply { add(Calendar.DAY_OF_MONTH, 1) }.timeInMillis,
            icon = "clear-day",
            maxTemp = -3,
            minTemp = -5,
            index = 0
        )
    }
}