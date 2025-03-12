package com.app.jetweatherforcast.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.jetweatherforcast.model.Favourite
import com.app.jetweatherforcast.model.Unit


@Database(entities = [Favourite::class, Unit::class], version = 2, exportSchema = false)
abstract class WeatherDatabase : RoomDatabase() {

    abstract fun weatherDao() : WeatherDao


}