package com.app.jetweatherforcast.network

import com.app.jetweatherforcast.model.WeatherModel
import com.app.jetweatherforcast.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton


@Singleton
interface WeatherApi  {

    @GET(value = "data/2.5/forecast")
    suspend fun getWeather(
        @Query("q") query :String,
        @Query("appid") appid: String = Constants.ApiKey,
        @Query("units") units : String
    ) : WeatherModel

}