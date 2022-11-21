package by.coolightman.weather.ui.screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import by.coolightman.weather.R
import by.coolightman.weather.util.mirror
import by.coolightman.weather.util.toFormattedTime

@Composable
fun LastRefresh(lastRefresh: Long) {
    Row(
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_round_cached_24),
            contentDescription = "reload",
            tint = MaterialTheme.colors.onSurface.copy(0.5f),
            modifier = Modifier.size(14.dp).mirror()
        )

        SpacerVert(with = 2.dp)

        Text(
            text = lastRefresh.toFormattedTime(),
            fontSize = 12.sp,
            color = MaterialTheme.colors.onSurface.copy(0.5f),
            fontWeight = FontWeight.Light,
        )
    }
}