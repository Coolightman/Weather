package by.coolightman.weather.ui.screen.components

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import by.coolightman.weather.ui.theme.ColorAccent

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
        color = if (text.equals("Saturday") || text.equals("Sunday")) ColorAccent
        else MaterialTheme.colors.onSurface,
        modifier = modifier
    )
}