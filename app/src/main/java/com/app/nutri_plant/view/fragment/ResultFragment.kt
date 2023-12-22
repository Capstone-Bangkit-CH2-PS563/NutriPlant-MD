package com.app.nutri_plant.view.fragment

import android.os.Bundle
import android.view.View
import com.app.nutri_plant.R
import com.app.nutri_plant.databinding.FragmentResultBinding
import com.app.nutri_plant.helper.viewBinding
import com.app.nutri_plant.model.Status
import com.app.nutri_plant.view.base.BaseFragment
import com.bumptech.glide.Glide

class ResultFragment : BaseFragment(R.layout.fragment_result) {

    private val binding by viewBinding(FragmentResultBinding::bind)

    private var image_url = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        image_url = arguments?.getString("image_url")?: ""
        if (image_url.isNotEmpty()){
            Glide.with(requireContext())
                .load(image_url)
                .into(binding.imgResult)

            loadPredict()
        }

        setView()
        setListener()
    }

    private fun setListener(){

    }

    private fun setView(){

    }

    private fun loadPredict(){
        predictionViewModel.prediction(image_url).observe(viewLifecycleOwner){
            it?.let { resource ->
                when(resource.status){
                    Status.SUCCESS -> {
                        val data = resource.data?.data

                        if (data != null){
                            binding.tvName.text = data.vegetable_prediction
                            binding.tvDesc.text = data.calories

                            val sbManfaat = StringBuilder()

                            if (data.benefit.isNotEmpty()){
                                for(i in data.benefit){
                                    sbManfaat.append(i + "\n")
                                }
                            }

                            binding.tvManfaat.text = sbManfaat.toString()
                        }

                        pLoading.dismissDialog()
                    }
                    Status.ERROR -> {
                        toast("Not Found")
                        pLoading.dismissDialog()
                    }
                    Status.LOADING -> {
                        pLoading.showLoading()
                    }
                }
            }
        }
    }
}