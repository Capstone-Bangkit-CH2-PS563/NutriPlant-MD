package com.app.nutri_plant.data.api

import com.app.nutri_plant.model.request.PredictRequest
import com.app.nutri_plant.model.response.LoginResponse
import com.app.nutri_plant.model.response.PredictResponse
import com.app.nutri_plant.model.response.RegisterResponse
import com.app.nutri_plant.model.response.UploadImageResponse
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiInterface {
    @FormUrlEncoded
    @POST("register")
    suspend fun register(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("confirm_password") confirm_password: String
    ): RegisterResponse

    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String,
    ): LoginResponse

    @POST("upload/image")
    suspend fun uploadImage(
        @Body request: RequestBody
    ): UploadImageResponse

    @POST("prediction")
    suspend fun prediction(
        @Body request: PredictRequest
    ): PredictResponse
}