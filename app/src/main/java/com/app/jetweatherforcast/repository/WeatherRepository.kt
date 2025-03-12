package com.app.jetweatherforcast.repository

import android.util.Log
import com.app.jetweatherforcast.data.DataOrException
import com.app.jetweatherforcast.model.WeatherModel
import com.app.jetweatherforcast.network.WeatherApi
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val api: WeatherApi) {

    suspend fun getWeather(cityQuery: String,units:String)
            : DataOrException<WeatherModel, Boolean, Exception> {
        val response = try {
            api.getWeather(cityQuery, units = units)
        } catch (e: Exception){
            Log.d("catch","GetWeather :$e")
            return DataOrException(e=e)
        }
        Log.d("INSIDE","GetWeather :$response")
        return DataOrException(data = response)
    }

}