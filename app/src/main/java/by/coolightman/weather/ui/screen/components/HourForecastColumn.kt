package by.coolightman.weather.ui.screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import by.coolightman.weather.domain.model.HourWeather
import by.coolightman.weather.util.getIconRes
import by.coolightman.weather.util.toFormattedTime

@Composable
fun HourForecastColumn(
    item: HourWeather
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxHeight()
            .width(70.dp)
    ) {
        Text(
            text = item.datetimeEpoch.toFormattedTime(),
            color = MaterialTheme.colors.onSurface.copy(0.7f),
            fontSize = 14.sp
        )
        Image(
            painter = painterResource(item.icon.getIconRes()),
            contentDescription = "icon",
            modifier = Modifier.size(36.dp)
        )
        DegreeText(temp = item.temp.toInt())
    }
}