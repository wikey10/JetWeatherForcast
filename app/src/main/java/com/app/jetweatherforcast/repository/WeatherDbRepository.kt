package com.app.jetweatherforcast.repository

import com.app.jetweatherforcast.data.WeatherDao
import com.app.jetweatherforcast.model.Favourite
import com.app.jetweatherforcast.model.Unit
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WeatherDbRepository @Inject constructor(private val weatherDao: WeatherDao) {

    fun getFavourites() : Flow<List<Favourite>> = weatherDao.getFavourites()

    suspend fun insertFavourite(favourite: Favourite) = weatherDao.insertFavourite(favourite)

    suspend fun updateFavourite(favourite: Favourite) = weatherDao.updateFavourite(favourite)

    suspend fun deleteAllFavourite() = weatherDao.deleteAllFavourites()

    suspend fun deleteFavourite(favourite: Favourite) = weatherDao.deleteFavourite(favourite)

    suspend fun getFavById(city:String) :Favourite = weatherDao.getFavById(city)


    //Units

    fun getUnits() : Flow<List<Unit>> = weatherDao.getUnits()

    suspend fun insertUnits(unit: Unit) = weatherDao.insertUnit(unit)

    suspend fun updateUnits(unit: Unit) = weatherDao.updateUnit(unit)

    suspend fun deleteAllUnit() = weatherDao.deleteAllUnit()

    suspend fun deleteUnit(unit: Unit) = weatherDao.deleteUnit(unit)



}