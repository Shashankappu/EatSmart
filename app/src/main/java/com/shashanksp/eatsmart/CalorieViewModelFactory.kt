package com.shashanksp.eatsmart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CalorieViewModelFactory(private val repository: CalorieRepository):ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CalorieViewModel(repository) as T
    }
}