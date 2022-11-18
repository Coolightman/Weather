package by.coolightman.weather.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import by.coolightman.weather.ui.screen.BaseScreen
import by.coolightman.weather.ui.screen.BaseViewModel
import by.coolightman.weather.ui.theme.WeatherTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            WeatherTheme(darkTheme = true) {

                val systemUiController = rememberSystemUiController()
                val barsColor = MaterialTheme.colors.background

                SideEffect {
                    systemUiController.setSystemBarsColor(
                        color = barsColor,
                        darkIcons = false
                    )
                }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val viewModel: BaseViewModel = koinViewModel()
                    val uiState by viewModel.uiState.collectAsState()
                    BaseScreen(
                        uiState = uiState,
                        onClickRefresh = {}
                    )
                }
            }
        }
    }
}