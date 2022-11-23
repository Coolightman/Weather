package by.coolightman.weather.ui.screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import by.coolightman.weather.ui.theme.TintFilter
import by.coolightman.weather.util.getBackgroundRes

@Composable
fun ImagedBlock(
    image: String,
    content: @Composable () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
            .clip(RoundedCornerShape(0.dp, 0.dp, 32.dp, 32.dp))
            .paint(
                painter = painterResource(image.getBackgroundRes()),
                contentScale = ContentScale.FillWidth
            )
            .background(TintFilter)
            .padding(top = 56.dp)
    ) {
        content()
    }
}