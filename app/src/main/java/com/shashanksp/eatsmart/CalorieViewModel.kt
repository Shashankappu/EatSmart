package com.shashanksp.eatsmart

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CalorieViewModel(private val repository: CalorieRepository):ViewModel() {
    fun insert(items: CalorieItems) = GlobalScope.launch {
        repository.insert(items)
    }

    fun delete(items: CalorieItems) = GlobalScope.launch {
        repository.delete(items)
    }

    fun getAllCalorieItems() = repository.getAllItems()
}