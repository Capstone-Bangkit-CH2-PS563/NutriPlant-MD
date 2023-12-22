package com.app.nutri_plant.view.base

import android.content.Context
import android.os.Bundle
import android.util.DisplayMetrics
import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.app.nutri_plant.R
import com.app.nutri_plant.data.api.ApiClient
import com.app.nutri_plant.data.api.ApiClientPrediction
import com.app.nutri_plant.data.api.ApiInterface
import com.app.nutri_plant.data.viewModel.AuthViewModel
import com.app.nutri_plant.data.viewModel.PredictionViewModel
import com.app.nutri_plant.data.viewModel.ViewModelFactory
import com.app.nutri_plant.helper.Loading
import com.app.nutri_plant.helper.SessionManager
import io.reactivex.disposables.CompositeDisposable

open class BaseFragment(fragment: Int) : Fragment(fragment) {

    lateinit var session: SessionManager
    var disposable: CompositeDisposable? = null

    private var toast: Toast? = null
    lateinit var pLoading: Loading
    lateinit var progressBar: ProgressBar
    lateinit var apiInterface: ApiInterface

    lateinit var apiPredictionInterface: ApiInterface

    lateinit var authViewModel: AuthViewModel
    lateinit var predictionViewModel: PredictionViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        pLoading = Loading(requireContext())
        disposable = CompositeDisposable()
        session = SessionManager(requireContext())
        progressBar = ProgressBar(requireContext())
        apiInterface = ApiClient.client.create(ApiInterface::class.java)
        apiPredictionInterface = ApiClientPrediction.client.create(ApiInterface::class.java)

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
        disposable?.clear()
    }

    fun toast(toastMessage: String?) {
        if (toastMessage != null && toastMessage.isNotEmpty()) {
            if (toast != null) toast!!.cancel()
            toast = Toast.makeText(activity, toastMessage, Toast.LENGTH_LONG)
            toast!!.show()

        }
    }

    fun showProgress(status: Boolean) {
        if (status) {
            pLoading.showLoading(resources.getString(R.string.label_loading_title_dialog), false)
        } else {
            pLoading.dismissDialog()
        }
    }

    fun calculateNoOfColumns(
        context: Context,
        columnWidthDp: Float
    ): Int { // For example columnWidthdp=180
        val displayMetrics: DisplayMetrics = context.getResources().getDisplayMetrics()
        val screenWidthDp = displayMetrics.widthPixels / displayMetrics.density
        return (screenWidthDp / columnWidthDp + 0.5).toInt()
    }

}