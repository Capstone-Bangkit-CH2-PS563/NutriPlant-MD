package com.app.nutri_plant.model.response

data class PredictResponse(
    val `data`: Data,
    val status: Status
) {
    data class Data(
        val benefit: List<String>,
        val calories: String,
        val confidence: Double,
        val vegetable_prediction: String
    )

    data class Status(
        val code: Int,
        val message: String
    )
}