package by.coolightman.weather.ui.screen.components

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import by.coolightman.weather.util.DEGREE_UNIT
import by.coolightman.weather.util.updateSign

@Composable
fun DegreeText(
    modifier: Modifier = Modifier,
    temp: Int,
    isGray: Boolean = false
) {
    Text(
        text = "${temp.updateSign()}$DEGREE_UNIT",
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold,
        color = MaterialTheme.colors.onSurface.copy(
            alpha = if (isGray) 0.5f
            else 1f
        ),
        modifier = modifier
    )
}