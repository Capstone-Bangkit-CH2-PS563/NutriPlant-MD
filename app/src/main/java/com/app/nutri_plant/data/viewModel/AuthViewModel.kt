package com.app.nutri_plant.data.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.app.nutri_plant.data.api.ApiRepository
import com.app.nutri_plant.model.Resource
import kotlinx.coroutines.Dispatchers
import okhttp3.RequestBody

class AuthViewModel(private val apiRepository: ApiRepository) : ViewModel() {

    fun register(
        name: String,
        email: String,
        password: String,
        confirm_password: String
    ) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = apiRepository.register(name, email, password, confirm_password)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun login(
        email: String,
        password: String
    ) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = apiRepository.login(email, password)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun uploadImage(
        body: RequestBody
    ) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = apiRepository.uploadImage(body)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun prediction(
        image_url: String
    ) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = apiRepository.prediction(image_url)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}
