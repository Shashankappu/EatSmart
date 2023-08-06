package com.shashanksp.eatsmart

class CalorieRepository(private val db:CalorieDatabase) {

    suspend fun insert(items: CalorieItems) = db.getCalorieDao().insert(items)
    suspend fun delete(items: CalorieItems) = db.getCalorieDao().delete(items)

    fun getAllItems() = db.getCalorieDao().getAllCalorieItems()
}