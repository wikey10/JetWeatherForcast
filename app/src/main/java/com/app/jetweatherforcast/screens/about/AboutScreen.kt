package com.app.jetweatherforcast.screens.about

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.app.jetweatherforcast.R
import com.app.jetweatherforcast.widgets.WeatherAppBar

@Composable
fun AboutScreen(navController: NavController, modifier: Modifier){
    Scaffold(
        topBar = {
            WeatherAppBar(
                title = "About",
                icon = Icons.AutoMirrored.Filled.ArrowBack,
                isMainScreen = false,
                navController = navController
            ){
                navController.popBackStack()
            }
        }

    ) {
            innerPadding ->
        AboutScreenContent(modifier.padding(innerPadding))
    }
}

@Composable
fun AboutScreenContent(modifier: Modifier,){
  Column(modifier.padding(10.dp).fillMaxSize(),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Center
      ) {
      Text(text = stringResource(R.string.about_app,),
          style = MaterialTheme.typography.titleMedium,
          fontWeight = FontWeight.Bold
      )
      Text(text = stringResource(R.string.api_used,),
          style = MaterialTheme.typography.titleSmall,
          fontWeight = FontWeight.Medium
      )
  }
}