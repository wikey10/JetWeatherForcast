package com.app.jetweatherforcast.screens.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.app.jetweatherforcast.R
import com.app.jetweatherforcast.navigation.WeatherScreens
import kotlinx.coroutines.delay

@Composable
fun WeatherSplashScreen(navController: NavController, modifier: Modifier) {

    val defaultCity = "San Diego"
    val scale = remember {
        androidx.compose.animation.core.Animatable(0f,)
    }

    LaunchedEffect(true, block =  {
        scale.animateTo(targetValue = 0.9f, animationSpec = tween(
            durationMillis = 800,
            easing = {
                OvershootInterpolator(8f).getInterpolation(it)
            }
        ))
        delay(2000L)
        navController.navigate("${WeatherScreens.MainScreen.name}/$defaultCity")
    }
    )

    Box(
        modifier = modifier
            .fillMaxSize().scale(scale.value), // Takes full screen size
        contentAlignment = Alignment.Center // Centers child inside Box
    ) {
        Surface(
            modifier = Modifier
                .size(330.dp), // Ensures Surface is a perfect circle
            shape = CircleShape,
            color = Color.White,
            border = BorderStroke(2.dp, Color.LightGray)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize() // Makes Column fill the Surface
                    .padding(1.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(painter = painterResource(R.drawable.sun), contentDescription = "",
                    modifier.size(85.dp), contentScale = ContentScale.Fit)
                Text("Find the sun?", style = MaterialTheme.typography.headlineMedium,
                    color = Color.LightGray)
            }
        }
    }
}

