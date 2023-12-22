package com.app.nutri_plant.data.api

import com.app.nutri_plant.model.request.PredictRequest
import okhttp3.RequestBody

class ApiRepository (private val apiInterface: ApiInterface) {

    suspend fun register(
        name: String,
        email: String,
        password: String,
        confirm_password: String
    ) = apiInterface.register(
        name, email, password, confirm_password
    )

    suspend fun login(
        email: String,
        password: String
    ) = apiInterface.login(
        email, password
    )

    suspend fun uploadImage(
        body: RequestBody
    ) = apiInterface.uploadImage(
        body
    )

    suspend fun prediction(
        image_url: String
    ) = apiInterface.prediction(
        PredictRequest(image_url)
    )
}