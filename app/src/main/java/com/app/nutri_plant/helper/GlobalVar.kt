package com.app.nutri_plant.helper

import java.util.Random

class GlobalVar {
    companion object {
        const val BASE_URL = "https://golang-backend-bbytzq52eq-uc.a.run.app/"
        const val PREDICTION_URL = "https://nutriplant-model-bbytzq52eq-uc.a.run.app/"

        const val USER = "User"

        fun generateId(): String {
            var r: Random
            val randomNumber: Int = Random().nextInt(61) + 125000
            return randomNumber.toString()
        }

    }
}