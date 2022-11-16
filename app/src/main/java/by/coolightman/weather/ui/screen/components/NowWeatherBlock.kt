package by.coolightman.weather.ui.screen.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NowWeatherBlock(
    temp: String,
    @DrawableRes iconRes: Int,
    description: String,
    feelTemp: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxHeight(0.5f)
    ) {
        SpacerHorizon(height = 24.dp)

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxHeight(0.5f)
        ) {
            Text(
                text = "$temp°",
                style = MaterialTheme.typography.h2.copy(
                    fontWeight = FontWeight.Normal
                )
            )

            Icon(
                painter = painterResource(iconRes),
                contentDescription = "weather icon",
                modifier = Modifier.size(42.dp)
            )
        }

        Text(
            text = description,
            fontSize = 18.sp
        )

        Text(
            text = "Feels like $feelTemp°",
            fontSize = 16.sp,
            color = MaterialTheme.colors.onSurface.copy(0.6f)
        )
    }
}