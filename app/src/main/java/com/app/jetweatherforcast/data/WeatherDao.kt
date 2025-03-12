package com.app.jetweatherforcast.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.app.jetweatherforcast.model.Favourite
import com.app.jetweatherforcast.model.Unit
import kotlinx.coroutines.flow.Flow


@Dao
interface WeatherDao {

    @Query(value = "SELECT * from fav_tbl")
    fun getFavourites() : Flow<List<Favourite>>

    @Query(value = "SELECT *from fav_tbl where city = :city")
    suspend fun getFavById(city: String) : Favourite

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavourite(favourite: Favourite)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateFavourite(favourite: Favourite)

    @Query("DELETE from fav_tbl")
    suspend fun deleteAllFavourites()

    @Delete
    suspend fun deleteFavourite(favourite: Favourite)

    //Unit Table
    @Query(value = "SELECT * from settings_tbl")
    fun getUnits() : Flow<List<Unit>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUnit(unit: Unit)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateUnit(unit: Unit)

    @Query("DELETE from settings_tbl")
    suspend fun deleteAllUnit()

    @Delete
    suspend fun deleteUnit(unit: Unit)
}