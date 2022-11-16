package by.coolightman.weather.ui.screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import by.coolightman.weather.ui.theme.TintFilter

@Composable
fun ImagedBlock(
    image: Painter,
    content: @Composable () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .height(350.dp)
            .clip(RoundedCornerShape(0.dp, 0.dp, 32.dp, 32.dp))
            .paint(
                painter = image,
                contentScale = ContentScale.FillWidth
            )
            .background(TintFilter)
    ) {
        content()
    }
}