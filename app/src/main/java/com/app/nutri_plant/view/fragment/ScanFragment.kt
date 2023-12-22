package com.app.nutri_plant.view.fragment

import android.app.Activity
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.app.nutri_plant.R
import com.app.nutri_plant.databinding.FragmentScanBinding
import com.app.nutri_plant.helper.viewBinding
import com.app.nutri_plant.model.Status
import com.app.nutri_plant.view.base.BaseFragment
import com.github.dhaval2404.imagepicker.ImagePicker
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class ScanFragment : BaseFragment(R.layout.fragment_scan) {

    private val binding by viewBinding(FragmentScanBinding::bind)

    private var file: File?= null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setView()
        setListener()
    }

    private fun setListener() {
        binding.btnCamera.setOnClickListener {
            ImagePicker.with(this)
                .maxResultSize(640, 640)
                .compress(200) // maks size 250kb
                .cameraOnly()
                .start { resultCode, data ->
                    when (resultCode) {
                        Activity.RESULT_OK -> {
                            file = ImagePicker.getFile(data)!!
                            binding.imgResult.setImageURI(data?.data)

                            uploadImage()
                        }
                        ImagePicker.RESULT_ERROR -> {
                            toast(ImagePicker.getError(data))
                        }
                        else -> toast("Task Cancelled")
                    }
                }

        }

        binding.btnGallery.setOnClickListener {
            ImagePicker.with(this)
                .maxResultSize(640, 640)
                .compress(200) // maks size 250kb
                .galleryOnly()
                .start { resultCode, data ->
                    when (resultCode) {
                        Activity.RESULT_OK -> {
                            file = ImagePicker.getFile(data)!!
                            binding.imgResult.setImageURI(data?.data)

                            uploadImage()
                        }
                        ImagePicker.RESULT_ERROR -> {
                            toast(ImagePicker.getError(data))
                        }
                        else -> toast("Task Cancelled")
                    }
                }

        }
    }

    private fun uploadImage(){
        val builder = MultipartBody.Builder()
        builder.setType(MultipartBody.FORM)

        file?.let {
            builder.addFormDataPart(
                "image_url", file!!.name, file!!
                    .asRequestBody("multipart/form-data".toMediaTypeOrNull())
            )
        }

        val body = builder.build()
        authViewModel.uploadImage(body).observe(viewLifecycleOwner){
            it?.let { resource ->
                when(resource.status){
                    Status.SUCCESS -> {
                        val data = resource.data?.data
                        pLoading.dismissDialog()

                        findNavController().navigate(R.id.resultFragment, bundleOf("image_url" to data))
                    }
                    Status.ERROR -> {
                        toast(resource.data?.message.toString())
                        pLoading.dismissDialog()
                    }
                    Status.LOADING -> {
                        pLoading.showLoading()
                    }
                }
            }
        }
    }

    private fun setView() {
    }
}