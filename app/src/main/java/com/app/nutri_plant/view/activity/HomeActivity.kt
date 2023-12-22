package com.app.nutri_plant.view.activity

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.app.nutri_plant.MyApplication.Companion.last_opened_tab
import com.app.nutri_plant.R
import com.app.nutri_plant.databinding.ActivityHomeBinding
import com.app.nutri_plant.helper.viewBinding
import com.app.nutri_plant.view.base.BaseActivity
import com.app.nutri_plant.view.fragment.HomeFragment

class HomeActivity : BaseActivity() {
    private val binding by viewBinding(ActivityHomeBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setListener()
    }

    private fun setListener() {
        binding.bottomBar.setActiveItem(0)
        binding.bottomBar.onItemSelected = {
            last_opened_tab = it
            when (it) {
                0 -> findNavController(R.id.nav_host_fragment).navigate(R.id.homeFragment)
                1 -> findNavController(R.id.nav_host_fragment).navigate(R.id.scanFragment)
                2 -> findNavController(R.id.nav_host_fragment).navigate(R.id.profileFragment)
            }
        }

    }

    fun dialogLogout() {
        val builder1: AlertDialog.Builder = AlertDialog.Builder(this)
        builder1.setMessage("Yakin ingin keluar dari Aplikasi?")
        builder1.setCancelable(true)
        builder1.setPositiveButton(
            "Ya"
        ) { dialog, id ->
            finish()
        }
        builder1.setNegativeButton(
            "Tidak"
        ) { dialog, id -> dialog.cancel() }
        val alert11: AlertDialog = builder1.create()
        alert11.show()
    }

    // to check current active fragment
    fun getForegroundFragment(): Fragment? {
        val navHostFragment: Fragment? =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
        return navHostFragment?.childFragmentManager?.fragments?.get(0)
    }

    override fun onBackPressed() {
        when(getForegroundFragment()){
            is HomeFragment -> {
                finish()
            }

            else -> {
                super.onBackPressed()
            }
        }

    }

}