package by.coolightman.weather.util

import android.util.LayoutDirection
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.core.text.layoutDirection
import java.util.Locale

@Composable
fun Modifier.mirror(): Modifier {
    return if (Locale.getDefault().layoutDirection == LayoutDirection.LTR) {
        this.scale(
            scaleX = -1f,
            scaleY = 1f
        )
    }
    else this
}