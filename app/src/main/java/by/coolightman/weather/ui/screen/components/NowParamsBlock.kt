package by.coolightman.weather.ui.screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
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
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
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
                text = " m/s,",
                color = tintedColor
            )

            SpacerVert(with = 4.dp)

            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_navigation_24),
                contentDescription = "direction",
                tint = tintedColor,
                modifier = Modifier
                    .rotate(windDirection.toFloat())
                    .size(20.dp)
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
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
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
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