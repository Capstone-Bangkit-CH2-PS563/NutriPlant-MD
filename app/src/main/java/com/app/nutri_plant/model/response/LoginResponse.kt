package com.app.nutri_plant.model.response

data class LoginResponse(
    val message: String,
    val status: String,
    val `data`: Data
) {
    data class Data(
        val user_id: Int,
        val email: String,
        val token: String
    )
}