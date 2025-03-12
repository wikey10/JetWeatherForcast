package com.app.jetweatherforcast

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.app.jetweatherforcast.navigation.WeatherNavigation
import com.app.jetweatherforcast.ui.theme.JetWeatherForcastTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        enableEdgeToEdge()
        setContent {
            JetWeatherForcastTheme {
                val window = this.window

                SideEffect {
                    window.statusBarColor = Color.BLUE // Change color
                    WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = false
                }
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    WeatherApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun WeatherApp(modifier: Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        WeatherNavigation(modifier = modifier)
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetWeatherForcastTheme {

    }
}