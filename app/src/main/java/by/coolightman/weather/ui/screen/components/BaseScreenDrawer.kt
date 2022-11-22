package by.coolightman.weather.ui.screen.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import by.coolightman.weather.ui.theme.WeatherTheme

@Composable
fun BaseScreenDrawer(
    onEnterPlace: (String) -> Unit
) {
    var place by remember {
        mutableStateOf("")
    }

    var errorPlaceText by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        OutlinedTextField(
            value = place,
            onValueChange = {
                place = it
                if (errorPlaceText) {
                    errorPlaceText = false
                }
            },
            placeholder = {
                Text(
                    text = "Place..",
                    color = MaterialTheme.colors.onSurface.copy(0.5f)
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "search"
                )
            },
            isError = errorPlaceText,
            label = {
                if (errorPlaceText) {
                    Text(text = "Title too short")
                }
            },
            shape = CircleShape,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        Box(modifier = Modifier.fillMaxWidth()) {
            Button(
                onClick = {
                    if (place.length > 2) {
                        onEnterPlace(place)
                    } else {
                        errorPlaceText = true
                    }
                },
                shape = CircleShape,
                modifier = Modifier
                    .width(100.dp)
                    .align(Alignment.Center)
            ) {
                Text(text = "Search")
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    WeatherTheme(true) {
        BaseScreenDrawer(onEnterPlace = {})
    }
}