package by.coolightman.weather.ui.screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import by.coolightman.weather.R
import by.coolightman.weather.ui.theme.ColorAccent

@Composable
fun WeatherTopBar(
    mainText: String,
    secondText: String = "",
    background: Color = MaterialTheme.colors.background,
    onClickMenu: () -> Unit,
    onClickReload: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(background)
            .height(56.dp)
    ) {
        IconButton(
            onClick = { onClickMenu() },
            modifier = Modifier.padding(start = 4.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_round_menu_24),
                contentDescription = "menu",
                tint = ColorAccent
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
        ) {
            Text(
                text = mainText,
                fontWeight = FontWeight.Bold
            )
            if (secondText.isNotEmpty()) {
                Text(
                    text = secondText,
                    fontSize = 14.sp,
                    color = MaterialTheme.colors.onSurface.copy(0.5f)
                )

            }
        }

        IconButton(
            onClick = { onClickReload() },
            modifier = Modifier.padding(end = 4.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_round_cached_24),
                contentDescription = "reload",
                tint = ColorAccent
            )
        }
    }
}