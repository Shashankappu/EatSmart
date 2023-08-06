package com.shashanksp.eatsmart

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CalorieItems::class], version = 1)
abstract class CalorieDatabase : RoomDatabase() {

    abstract fun getCalorieDao(): CalorieDao

    companion object {
        @Volatile
        private var instance: CalorieDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance?: synchronized(LOCK) {
            instance ?: createDatabase(context).also{
                instance = it
            }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                CalorieDatabase::class.java,
                "Calorie.db"
            ).build()
    }
}