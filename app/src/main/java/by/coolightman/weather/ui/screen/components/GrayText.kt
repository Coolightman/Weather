package by.coolightman.weather.ui.screen.components

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
fun GrayText(
    modifier: Modifier = Modifier,
    text: String
) {
    Text(
        text = text,
        fontSize = 14.sp,
        color = MaterialTheme.colors.onSurface.copy(0.5f),
        modifier = modifier
    )
}