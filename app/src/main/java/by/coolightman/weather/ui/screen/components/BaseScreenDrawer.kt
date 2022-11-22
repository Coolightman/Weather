package by.coolightman.weather.ui.screen.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import by.coolightman.weather.R
import by.coolightman.weather.domain.model.Place
import by.coolightman.weather.ui.theme.WeatherTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BaseScreenDrawer(
    places: List<Place>,
    onEnterPlace: (String) -> Unit,
    onDeletePlace: (Long) -> Unit
) {
    val listState = rememberLazyListState()

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
            trailingIcon = {
                if (place.isNotEmpty()) {
                    Icon(
                        painter = painterResource(R.drawable.ic_baseline_cancel_24),
                        contentDescription = "clear",
                        modifier = Modifier.clickable {
                            place = ""
                        }
                    )
                }
            },
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

        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn(
                state = listState,
                contentPadding = PaddingValues(12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(items = places, key = { it.id }) { place ->
                    Card(
                        shape = CircleShape,
                        elevation = 2.dp,
                        border = if (place.selected) {
                            BorderStroke(1.dp, MaterialTheme.colors.primary)
                        } else null,
                        onClick = { onEnterPlace(place.address) },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(24.dp, 4.dp, 0.dp, 4.dp)
                        ) {
                            Column(Modifier.weight(1f)) {
                                Text(
                                    text = place.resolvedAddress,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                                Text(
                                    text = place.address,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    color = MaterialTheme.colors.onSurface.copy(0.5f)
                                )
                            }
                            IconButton(onClick = { onDeletePlace(place.id) }) {
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = "delete"
                                )
                            }
                        }
                    }
                }
            }
        }

    }
}

@Preview
@Composable
private fun Preview() {
    val places = listOf<Place>(
        Place(12, "grodno", "Гродна, Беларусь", true),
        Place(13, "minsk", "Менск, Беларусь", false)
    )
    WeatherTheme(true) {
        BaseScreenDrawer(
            places = places,
            onEnterPlace = {},
            onDeletePlace = {}
        )
    }
}