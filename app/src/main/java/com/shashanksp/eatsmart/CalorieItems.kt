package com.shashanksp.eatsmart

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName= "calorie_items")
data class CalorieItems (

    @ColumnInfo(name = "itemName")
    var itemName:String,

    @ColumnInfo(name = "itemQuantity")
    var itemQuantity:Int,

    @ColumnInfo(name = "itemCalories")
    var itemCalories:Int

){
    @PrimaryKey(autoGenerate = true)
    var id : Int?=null
}