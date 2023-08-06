package com.shashanksp.eatsmart

import androidx.lifecycle.LiveData
import androidx.room.*



@Dao
interface CalorieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: CalorieItems)

    @Delete
    suspend fun delete(item :CalorieItems)

    @Query("SELECT * FROM calorie_items")
    fun getAllCalorieItems() : LiveData<List<CalorieItems>>

}