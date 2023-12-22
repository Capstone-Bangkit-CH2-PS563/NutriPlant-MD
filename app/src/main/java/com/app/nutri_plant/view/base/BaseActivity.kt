package com.app.nutri_plant.view.base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.app.nutri_plant.data.api.ApiClient
import com.app.nutri_plant.data.api.ApiClientPrediction
import com.app.nutri_plant.data.api.ApiInterface
import com.app.nutri_plant.data.viewModel.AuthViewModel
import com.app.nutri_plant.data.viewModel.PredictionViewModel
import com.app.nutri_plant.data.viewModel.ViewModelFactory
import com.app.nutri_plant.helper.Loading
import com.app.nutri_plant.helper.SessionManager
import io.github.inflationx.viewpump.ViewPumpContextWrapper

open class BaseActivity : AppCompatActivity(){

    private var toast: Toast? = null
    lateinit var pLoading : Loading

    lateinit var session: SessionManager
    lateinit var progressBar: ProgressBar
    lateinit var apiInterface: ApiInterface

    lateinit var apiPredictionInterface: ApiInterface

    lateinit var authViewModel: AuthViewModel
    lateinit var predictionViewModel: PredictionViewModel

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        pLoading = Loading(this)
        session = SessionManager(this)
        apiInterface = ApiClient.client.create(ApiInterface::class.java)
        apiPredictionInterface = ApiClientPrediction.client.create(ApiInterface::class.java)

        progressBar = ProgressBar(this)

        authViewModel = ViewModelProviders.of(
            this, ViewModelFactory(apiInterface)
        ).get(AuthViewModel::class.java)

        predictionViewModel = ViewModelProviders.of(
            this, ViewModelFactory(apiPredictionInterface)
        ).get(PredictionViewModel::class.java)
    }

    fun toast(@StringRes message: Int) {
        toast(getString(message))
    }

    override fun onDestroy() {
        super.onDestroy()
    }


    fun setOveridePendingTransisi(context: Activity) {
        try {
            context.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    fun toast(toastMessage: String?) {
        if (toastMessage != null && !toastMessage.isEmpty()) {
            if (toast != null) toast!!.cancel()
            toast = Toast.makeText(this.applicationContext, toastMessage, Toast.LENGTH_LONG)
            toast!!.show()

        }
    }

}
