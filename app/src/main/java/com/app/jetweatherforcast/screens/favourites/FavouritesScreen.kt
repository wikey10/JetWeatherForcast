package com.app.jetweatherforcast.screens.favourites

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.app.jetweatherforcast.model.Favourite
import com.app.jetweatherforcast.navigation.WeatherScreens
import com.app.jetweatherforcast.widgets.WeatherAppBar

@Composable

fun FavouritesScreen(navController: NavController,modifier: Modifier,favouriteViewModel: FavouriteViewModel){
   Scaffold(topBar = {
       WeatherAppBar(
           title = "Favourite Cities",
           icon = Icons.AutoMirrored.Filled.ArrowBack,
           isMainScreen = false,
           navController = navController
       ){
           navController.popBackStack()
       }
   }) {
       paddingValues ->  FavouriteScreenContent(modifier.padding(paddingValues),favouriteViewModel,
           navController)
   }
}

@Composable

fun FavouriteScreenContent(modifier: Modifier,favouriteViewModel: FavouriteViewModel,
                           navController: NavController
                           ){
    Column(modifier.fillMaxWidth().padding(5.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
        ) {
        val lists = favouriteViewModel.favList.collectAsState().value
        LazyColumn {
            items(items = lists){
                FavouritesRow(
                    favourite = it,
                    navController = navController,
                    favouriteViewModel
                )
            }
        }
    }
}

@Composable

fun FavouritesRow(favourite: Favourite,navController: NavController,
                  favouriteViewModel: FavouriteViewModel){
    Surface(
        modifier = Modifier.padding(12.dp)
            .fillMaxWidth()
            .height(50.dp)
            .clickable {
                navController.navigate(WeatherScreens.MainScreen.name +"/${favourite.city}")
            },
        shape = CircleShape.copy(topEnd = CornerSize(6.dp),),
        color = Color(0XFFB2DFDB)
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(favourite.city
            )
            Surface(
                modifier = Modifier.padding(1.dp),
                shape = CircleShape,
                color = Color(0XFFD1E3E1)
            ) {
                Text(favourite.country, modifier = Modifier.padding(2.dp),
                    style = MaterialTheme.typography.titleMedium)

            }
            IconButton(onClick = {
                favouriteViewModel.deleteFavourite(favourite)
            }) {
                Icon(imageVector = Icons.Rounded.Delete, contentDescription = "Delete Icon",
                    tint = Color.Red.copy(0.3f)
                    )
            }
        }
    }
}