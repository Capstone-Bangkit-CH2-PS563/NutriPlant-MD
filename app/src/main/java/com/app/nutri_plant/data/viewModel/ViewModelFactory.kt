package com.app.nutri_plant.data.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.nutri_plant.data.api.ApiInterface
import com.app.nutri_plant.data.api.ApiRepository

class ViewModelFactory (private val apiInterface: ApiInterface) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            return AuthViewModel(ApiRepository(apiInterface)) as T
        } else if (modelClass.isAssignableFrom(PredictionViewModel::class.java)) {
            return PredictionViewModel(ApiRepository(apiInterface)) as T
        }

        throw IllegalArgumentException("Unknown class name")
    }
}