package com.app.jetweatherforcast.widgets

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size

import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCompositionContext
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.app.jetweatherforcast.model.Favourite
import com.app.jetweatherforcast.navigation.WeatherScreens
import com.app.jetweatherforcast.screens.favourites.FavouriteViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherAppBar(
    elevation: Dp = 0.dp,
    title:String ="Title",
    icon: ImageVector? =null,
    isMainScreen : Boolean =true,
    navController: NavController,
    favViewModel: FavouriteViewModel = hiltViewModel(),
    onAddActionClicked :() ->Unit ={},
    onButtonClicked : () ->Unit={}
                  ){

    val showDialog = remember {
        mutableStateOf(false)
    }

    if(showDialog.value){
        ShowSettingDropDownMenu(showDialog=showDialog,navController=navController)
    }

    val context = LocalContext.current


    val showIt = remember {
        mutableStateOf(false)
    }

    TopAppBar(
        modifier = Modifier.shadow(elevation),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent,
            titleContentColor = Color.Black,
            actionIconContentColor = Color.Black,
            scrolledContainerColor = Color.Transparent,
            navigationIconContentColor = Color.Gray
        ),
        title = {
            Text(title, style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            ))
        },
        actions = {
            if(isMainScreen){

                IconButton(
                    onClick = {
                        onAddActionClicked.invoke()
                    }
                ) {
                    Icon(Icons.Default.Search, contentDescription = "Search Icon")
                }
                IconButton(onClick = {
                   showDialog.value =true
                }) {
                    Icon(Icons.Default.MoreVert, contentDescription = "More Icon")
                }
            }else Box{

            }
        },
        navigationIcon = {
             if(icon!=null){
                 Icon(imageVector = icon,"icons", tint = MaterialTheme.colorScheme.secondary,
                     modifier = Modifier.clickable {
                         onButtonClicked.invoke()
                     },
                     )
             }
            if(isMainScreen){

                val isAlreadyFavList = favViewModel
                    .favList.collectAsState().value.filter {
                            favourite -> (favourite.city == title.split(",")[0])
                    }

                if (isAlreadyFavList.isNullOrEmpty()){
                    IconButton(
                        onClick = {
                            val dataList = title.split(",")
                            favViewModel.insertFavourite(Favourite(
                                city = dataList[0], //cityName
                                country =  dataList[1]  //countryCode
                            ))
                            showIt.value=true

                        }
                    ) {
                        Icon(
                            tint = Color.Red.copy(alpha = 0.6f),
                            imageVector =   Icons.Default.Favorite, contentDescription = "Fav Icon", modifier = Modifier.scale(0.9f))
                    }
                }
                else {
                    Box {}
                }
                ShowToast(context = context,showIt)
            }
        },
    )
}


@Composable
fun ShowSettingDropDownMenu(showDialog: MutableState<Boolean>, navController: NavController) {
    var expanded by remember { mutableStateOf(true) }

    val items = listOf("About", "Favourites", "Settings")

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.TopEnd)
            .absolutePadding(top = 55.dp, right = 20.dp)
    ) {
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(140.dp)
                .background(Color.White, shape = RoundedCornerShape(8.dp))
        ) {
            items.forEach { text ->
                DropdownMenuItem(
                    onClick = {
                        expanded = false
                        showDialog.value = false
                        when (text) {
                            "About" -> navController.navigate(WeatherScreens.AboutScreen.name)
                            "Favourites" -> navController.navigate(WeatherScreens.FavouriteScreen.name)
                            "Settings" -> navController.navigate(WeatherScreens.SettingsScreen.name)
                        }
                    },
                    leadingIcon = {
                        Icon(imageVector = when (text){
                            "About" -> Icons.Default.Info
                            "Favourites" -> Icons.Default.FavoriteBorder
                            else -> Icons.Default.Settings
                        },null,
                            tint = Color.Gray
                            )
                    },
                    text = { Text(text) }
                )
            }
        }
    }
}

@Composable
fun ShowToast(context: Context, showIt: MutableState<Boolean>) {
    if(showIt.value){
        Toast.makeText(context,"City Added to Favourites",
            Toast.LENGTH_SHORT
            ).show()
        showIt.value=false
    }
}


