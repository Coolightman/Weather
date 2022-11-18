package by.coolightman.weather.ui.screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import by.coolightman.weather.R

@Composable
fun NowParamsBlock(
    wind: Int,
    windDirection: Int,
    pressure: Int,
    humidity: Int,
    tintedColor: Color = MaterialTheme.colors.onSurface.copy(0.7f)
) {
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxHeight()
            .width(180.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Icon(
                painter = painterResource(R.drawable.ic_round_wind_24),
                contentDescription = "wind",
                tint = tintedColor
            )
            SpacerVert(with = 12.dp)
            Text(
                text = wind.toString(),
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = " m/s, $windDirection",
                color = tintedColor
            )
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            Icon(
                painter = painterResource(R.drawable.ic_round_pressure_24),
                contentDescription = "pressure",
                tint = tintedColor
            )
            SpacerVert(with = 12.dp)
            Text(
                text = pressure.toString(),
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = " mmHg",
                color = tintedColor
            )
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            Icon(
                painter = painterResource(R.drawable.ic_round_humidity_24),
                contentDescription = "humidity",
                tint = tintedColor
            )
            SpacerVert(with = 12.dp)
            Text(
                text = humidity.toString(),
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = "%",
                color = tintedColor
            )
        }
    }
}