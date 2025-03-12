package com.app.jetweatherforcast.screens.main

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.app.jetweatherforcast.data.DataOrException
import com.app.jetweatherforcast.model.WeatherModel
import com.app.jetweatherforcast.navigation.WeatherScreens
import com.app.jetweatherforcast.screens.settings.SettingsViewModel
import com.app.jetweatherforcast.utils.formatDate
import com.app.jetweatherforcast.utils.formatDecimals
import com.app.jetweatherforcast.widgets.HumidityWindPressureRow
import com.app.jetweatherforcast.widgets.SunriseSunsetRow
import com.app.jetweatherforcast.widgets.WeatherAppBar
import com.app.jetweatherforcast.widgets.WeatherStateImage
import com.app.jetweatherforcast.widgets.WeekListRow

@Composable
fun WeatherMainScreen(navController: NavController,modifier: Modifier,
                      mainViewModel: MainViewModel = hiltViewModel(),
                      settingsViewModel: SettingsViewModel = hiltViewModel(),
                      city:String?) {
    Log.d("city","MainScreen${city}")

    val curCity : String= if(city!!.isBlank()) "Tokyo" else city

    val unitFromDB = settingsViewModel.unitList.collectAsState().value

    var unit by remember {
        mutableStateOf("imperial")
    }

    var isImperial by remember {
        mutableStateOf(false)
    }

    if(!unitFromDB.isNullOrEmpty()){

        unit = unitFromDB[0].unit.split(" ").first().lowercase()

        isImperial = unit =="imperial"

        val weatherData = produceState<DataOrException<WeatherModel,Boolean,Exception>>(
            initialValue = DataOrException(loading = true)){
            value = mainViewModel.getWeatherData(city = curCity,units = unit)
        }.value

        if (weatherData.loading==true){
            CircularProgressIndicator()
        }
        else if(weatherData.data!=null){
            MainScaffold(weather= weatherData.data!!, navController,modifier,isImperial = isImperial)

        }
    }




}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScaffold(weather: WeatherModel,navController: NavController,modifier: Modifier,isImperial : Boolean){
    Scaffold(topBar = {
        WeatherAppBar(title = weather.city.name + ", ${weather.city.country}",
            navController = navController,
            onAddActionClicked = {
                navController.navigate(WeatherScreens.SearchScreen.name)
            },
            elevation = 5.dp,
            ){
            Log.d("ButtonClicked","Back arrow is clicked")
        }
    }) {
            paddingValues ->  // Accept Scaffold's paddingValues
        MainContent(data = weather, modifier.padding(paddingValues),isImperial= isImperial)
    }
}


@Composable
fun MainContent(data: WeatherModel, modifier: Modifier, isImperial: Boolean){

    val imageUrl = "https://openweathermap.org/img/wn/${data.list[0].weather[0].icon}.png"

    Column(modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            formatDate(data.list[0].dt), style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.secondary,
            fontWeight = FontWeight.Bold
        )
        Surface(modifier = Modifier.size(200.dp), shape = CircleShape,
            color = Color(0XFFFFC400)
            ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
                ) {
                WeatherStateImage(imageUrl)
                Text(
                    formatDecimals(data.list[0].main.temp)+"°", style =MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.ExtraBold)
                Text(data.list[0].weather[0].main, fontStyle = FontStyle.Italic)
            }
        }
        HumidityWindPressureRow(weather =data,isImperial = isImperial)
        HorizontalDivider(thickness = 0.5.dp,
            modifier = Modifier.padding(start = 5.dp, end = 5.dp))
        SunriseSunsetRow(weather = data)
        Text("Today", style = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        )
        WeekListRow(item = data.list)
    }
}



