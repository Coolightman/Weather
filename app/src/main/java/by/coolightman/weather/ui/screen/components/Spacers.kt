package by.coolightman.weather.ui.screen.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
fun SpacerVert(with: Dp) {
    Spacer(modifier = Modifier.width(with))
}

@Composable
fun SpacerHorizon(height: Dp) {
    Spacer(modifier = Modifier.height(height))
}