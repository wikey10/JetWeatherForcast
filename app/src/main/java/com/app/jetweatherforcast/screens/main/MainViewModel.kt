package com.app.jetweatherforcast.screens.main

import androidx.lifecycle.ViewModel
import com.app.jetweatherforcast.data.DataOrException
import com.app.jetweatherforcast.model.WeatherModel
import com.app.jetweatherforcast.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(private val repository : WeatherRepository)
    :ViewModel()
{

        suspend fun getWeatherData(city: String, units: String) :
                DataOrException<WeatherModel,Boolean,Exception>{
            return repository.getWeather(cityQuery = city,units = units)
        }


}