package by.coolightman.weather.ui.screen.components

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun DayOfWeekText(
    modifier: Modifier = Modifier,
    text: String,
    isToday: Boolean = false,
    isTomorrow: Boolean = false
) {
    Text(
        text = if (isToday) {
            "Today"
        } else if (isTomorrow) {
            "Tomorrow"
        } else text,
        color = if (text.equals("Saturday") || text.equals("Sunday")) MaterialTheme.colors.primary
        else MaterialTheme.colors.onSurface,
        modifier = modifier
    )
}