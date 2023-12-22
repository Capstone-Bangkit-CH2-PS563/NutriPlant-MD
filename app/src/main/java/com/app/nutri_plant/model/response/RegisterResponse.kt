package com.app.nutri_plant.model.response

data class RegisterResponse(
    val message: String,
    val status: String,
    val `data`: Data
) {
    data class Data(
        val user_id: Int,
        val email: String
    )
}