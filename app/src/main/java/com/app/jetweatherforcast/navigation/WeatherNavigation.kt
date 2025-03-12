package com.app.jetweatherforcast.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.app.jetweatherforcast.screens.about.AboutScreen
import com.app.jetweatherforcast.screens.favourites.FavouriteViewModel
import com.app.jetweatherforcast.screens.favourites.FavouritesScreen
import com.app.jetweatherforcast.screens.main.MainViewModel
import com.app.jetweatherforcast.screens.main.WeatherMainScreen
import com.app.jetweatherforcast.screens.search.SearchScreen
import com.app.jetweatherforcast.screens.settings.SettingScreen
import com.app.jetweatherforcast.screens.settings.SettingsViewModel
import com.app.jetweatherforcast.screens.splash.WeatherSplashScreen


@Composable
fun WeatherNavigation(modifier: Modifier) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = WeatherScreens.SplashScreen.name,){
        composable(WeatherScreens.SplashScreen.name){
            WeatherSplashScreen(navController=navController,modifier)
        }

        val route = WeatherScreens.MainScreen.name

        composable(route = "$route/{city}",
            arguments = listOf(
                navArgument(name = "city"){
                    type= NavType.StringType
                })){
            navBack -> navBack.arguments?.getString("city").let {
                city ->
            val mainViewModel = hiltViewModel<MainViewModel>()
            WeatherMainScreen(navController = navController,modifier, mainViewModel,
                city = city) }
        }

        composable(WeatherScreens.SearchScreen.name){
//            val mainViewModel = hiltViewModel<MainViewModel>()
            SearchScreen(navController = navController,modifier,)
        }

        composable(WeatherScreens.AboutScreen.name){
            AboutScreen(navController =navController, modifier = Modifier)
        }

        composable(WeatherScreens.FavouriteScreen.name){
            val favouriteViewModel = hiltViewModel<FavouriteViewModel>()
            FavouritesScreen(navController =navController,modifier =  Modifier, favouriteViewModel = favouriteViewModel)
        }

        composable(WeatherScreens.SettingsScreen.name){
            val settingsViewModel = hiltViewModel<SettingsViewModel>()
            SettingScreen(navController =navController,modifier = Modifier,settingsViewModel = settingsViewModel)
        }
    }

}