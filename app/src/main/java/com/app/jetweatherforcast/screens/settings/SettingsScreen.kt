package com.app.jetweatherforcast.screens.settings
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.app.jetweatherforcast.model.Unit
import com.app.jetweatherforcast.widgets.WeatherAppBar

@Composable

fun SettingScreen(navController: NavController,modifier: Modifier,
                  settingsViewModel: SettingsViewModel
                  ){

    var unitToggleState by remember { mutableStateOf(false) }

    val measurementUnits = listOf("Imperial (F)","Metric (C)")



    val choiceFromDB = settingsViewModel.unitList.collectAsState().value

        val defaultChoice = if(choiceFromDB.isEmpty()) measurementUnits[0]

              else choiceFromDB[0].unit

    var choiceState by remember {
        mutableStateOf(defaultChoice)
    }



   Scaffold(topBar = {
       WeatherAppBar(
           title = "Settings Screen",
           icon = Icons.AutoMirrored.Filled.ArrowBack,
           isMainScreen = false,
           navController = navController,
           onButtonClicked = {
               navController.popBackStack()
           }
       )
   }) {
         innerPadding -> Surface(
       modifier.fillMaxWidth().padding(innerPadding)
           .fillMaxHeight().padding(10.dp)
   ) {
       Column(
           verticalArrangement = Arrangement.Center,
           horizontalAlignment = Alignment.CenterHorizontally
       ) {
           Text("Change Units of Measurement",
               modifier = Modifier.padding(bottom = 15.dp)
           )

           IconToggleButton(checked = !unitToggleState,
               onCheckedChange = {
                   unitToggleState = !it
                   if(unitToggleState){
                       choiceState="Imperial (F)"
                   }
                   else{
                       choiceState ="Metric (C)"
                   }
               }, modifier = Modifier.fillMaxWidth(0.5f)
                   .clip(shape = RectangleShape)
                   .padding(5.dp)
                   .background(Color.Magenta.copy(alpha = 0.4f))
           ) {
               Text(text = if(unitToggleState)"Fahrenheit °F" else "Celsius °C")

           }

           Button(
               modifier = Modifier.padding(3.dp),
               shape = RoundedCornerShape(34.dp),
               colors = ButtonDefaults.buttonColors(
                   containerColor = Color(0XFFEFBE42)
               ),
               onClick = {
                   settingsViewModel.deleteAllUnit()
                   settingsViewModel.insertUnit(Unit(
                       unit = choiceState
                   ))
               },
           ) {
               Text("Save")
           }
       }
   }
   }
}
