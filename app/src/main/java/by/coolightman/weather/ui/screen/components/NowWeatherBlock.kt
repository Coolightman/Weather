package by.coolightman.weather.ui.screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import by.coolightman.weather.util.DEGREE_UNIT
import by.coolightman.weather.util.getIconRes
import by.coolightman.weather.util.updateSign

@Composable
fun NowWeatherBlock(
    temp: Int,
    icon: String,
    description: String,
    feelTemp: Int
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
                text = "${temp.updateSign()}$DEGREE_UNIT",
                style = MaterialTheme.typography.h2.copy(
                    fontWeight = FontWeight.Normal,
                    shadow = Shadow(
                        color = Color.Black.copy(0.5f),
                        offset = Offset(1f, 1f),
                        blurRadius = 8f
                    )
                )
            )

            Image(
                painter = painterResource(id = icon.getIconRes()),
                contentDescription = "weather icon",
                modifier = Modifier.size(48.dp)
            )
        }

        Text(
            text = description,
            fontSize = 18.sp
        )

        Text(
            text = "Feels like ${feelTemp.updateSign()}$DEGREE_UNIT",
            fontSize = 16.sp,
            color = MaterialTheme.colors.onSurface.copy(0.6f)
        )
    }
}