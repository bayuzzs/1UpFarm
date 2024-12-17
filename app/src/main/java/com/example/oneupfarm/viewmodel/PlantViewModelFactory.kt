package com.example.oneupfarm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.oneupfarm.data.api.PlantApi

class PlantViewModelFactory(private val plantApi: PlantApi): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PlantViewModel::class.java)) {
            return PlantViewModel(plantApi) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}