package com.app.jetweatherforcast.screens.search

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.app.jetweatherforcast.navigation.WeatherScreens
import com.app.jetweatherforcast.widgets.WeatherAppBar


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SearchScreen(navController: NavController,modifier: Modifier){
    Scaffold(
        topBar = {
            WeatherAppBar(navController = navController,
                icon = Icons.AutoMirrored.Filled.ArrowBack,
                isMainScreen = false,
                title = "Search"
            ){
                navController.popBackStack()
            }
        }
    ) { innerPadding ->  // innerPadding is automatically provided by Scaffold
        Surface(
            modifier = Modifier.padding(innerPadding) // Apply innerPadding here
        ) {
            Column(modifier = Modifier.padding(6.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
                ) {
                SearchBar(
                    onSearch = {mCity->
                        navController.navigate(WeatherScreens.MainScreen.name +
                                "/$mCity")
                    },
                    modifier = modifier.fillMaxWidth().padding(16.dp)
                    .align(Alignment.CenterHorizontally))
            }
        }
    }
}

@Composable
fun SearchBar(
    onSearch: (String) ->Unit ={},modifier: Modifier){

    val searchQuery = rememberSaveable {
        mutableStateOf("")
    }

    val keyboardController = LocalSoftwareKeyboardController.current

    val valid = remember(searchQuery.value) {
        searchQuery.value.trim().isNotEmpty()
    }

    Column {
        CommonTextFiled(valueState = searchQuery,
            placeholder = "Seattle",
            onAction = KeyboardActions {
                if(!valid)return@KeyboardActions
                onSearch(searchQuery.value.trim())
                searchQuery.value = ""
                keyboardController?.hide()
            }
            )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommonTextFiled(
    valueState : MutableState<String>, placeholder : String,
    onAction : KeyboardActions = KeyboardActions.Default,
    imeAction: ImeAction = ImeAction.Next,
    keyboardType : KeyboardType = KeyboardType.Text,
){
    OutlinedTextField(
        value = valueState.value,
        onValueChange = {
            valueState.value = it
        },
        label = {
            Text(placeholder)
        },
        maxLines = 1,
        singleLine =   true,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType,
            imeAction = imeAction),
        keyboardActions = onAction,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Blue,
            cursorColor = Color.Black
        ),
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier.fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp)
    )
}