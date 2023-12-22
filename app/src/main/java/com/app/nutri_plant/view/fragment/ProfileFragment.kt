package com.app.nutri_plant.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.app.nutri_plant.R
import com.app.nutri_plant.databinding.FragmentProfileBinding
import com.app.nutri_plant.helper.viewBinding
import com.app.nutri_plant.view.activity.LoginActivity
import com.app.nutri_plant.view.base.BaseFragment

class ProfileFragment : BaseFragment(R.layout.fragment_profile) {

    private val binding by viewBinding(FragmentProfileBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setView()
        setListener()
    }

    private fun setListener() {
        binding.lyLogout.setOnClickListener {
            session.isLogin(false)
            session.dataUser = null

            val i = Intent(requireContext(), LoginActivity::class.java)
            i.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(i)
        }
    }

    private fun setView() {
    }
}