package by.coolightman.weather.ui.screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import by.coolightman.weather.R
import by.coolightman.weather.util.toFormattedTime

@Composable
fun SunSetRiseRow(
    sunrise: Long,
    sunset: Long
) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 4.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.sunrise),
                contentDescription = "sunrise",
                modifier = Modifier.size(20.dp)
            )

            SpacerVert(with = 8.dp)

            Text(
                text = sunrise.toFormattedTime(),
                fontSize = 12.sp,
                color = MaterialTheme.colors.onSurface.copy(0.5f)
            )
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.sunset),
                contentDescription = "sunrise",
                modifier = Modifier.size(20.dp)
            )

            SpacerVert(with = 8.dp)

            Text(
                text = sunset.toFormattedTime(),
                fontSize = 12.sp,
                color = MaterialTheme.colors.onSurface.copy(0.5f)
            )
        }
    }
}