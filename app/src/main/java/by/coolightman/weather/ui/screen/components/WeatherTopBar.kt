package by.coolightman.weather.ui.screen.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import by.coolightman.weather.R
import by.coolightman.weather.domain.model.ApiState
import by.coolightman.weather.util.mirror

@Composable
fun WeatherTopBar(
    resolvedAddress: String,
    apiState: ApiState,
    background: Color = MaterialTheme.colors.background,
    onClickMenu: () -> Unit,
    onClickRefresh: () -> Unit
) {
    val mainText by remember(resolvedAddress) {
        mutableStateOf(resolvedAddress.toMainText())
    }

    val secondText by remember(resolvedAddress) {
        mutableStateOf(resolvedAddress.toSecondText())
    }

    val infiniteTransition = rememberInfiniteTransition()
    val angle by infiniteTransition.animateFloat(
        initialValue = 0f, targetValue = 360f, animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1200, easing = LinearEasing
            )
        )
    )

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(background)
            .height(56.dp)
    ) {
        IconButton(
            onClick = { onClickMenu() }, modifier = Modifier.padding(start = 4.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_round_menu_24),
                contentDescription = "menu",
                tint = MaterialTheme.colors.primary
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
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            if (secondText.isNotEmpty()) {
                Text(
                    text = secondText,
                    fontSize = 14.sp,
                    color = MaterialTheme.colors.onSurface.copy(0.5f),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

            }
        }
        IconButton(
            onClick = { onClickRefresh() },
            enabled = apiState !is ApiState.Loading,
            modifier = Modifier.padding(end = 4.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_round_cached_24),
                contentDescription = "reload",
                tint = MaterialTheme.colors.primary,
                modifier = if (apiState is ApiState.Loading) {
                    Modifier
                        .mirror()
                        .rotate(-angle)
                } else {
                    Modifier.mirror()
                }
            )
        }

    }
}

private fun String.toMainText(): String {
    return if (this.contains(", ")) {
        val list = this.split(", ")
        list[0]
    } else {
        this
    }
}


private fun String.toSecondText(): String {
    return if (this.contains(",")) {
        val list = this.split(", ")
        list.last().trim()
    } else {
        ""
    }
}
