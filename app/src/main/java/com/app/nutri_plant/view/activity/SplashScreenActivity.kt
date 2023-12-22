package com.app.nutri_plant.view.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.app.nutri_plant.databinding.ActivitySplashScreenBinding
import com.app.nutri_plant.helper.viewBinding
import com.app.nutri_plant.view.base.BaseActivity

class SplashScreenActivity : BaseActivity() {
    private val binding by viewBinding(ActivitySplashScreenBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        Handler().postDelayed({
            if (session.isLogin()){
                val i = Intent(this@SplashScreenActivity, HomeActivity::class.java)
                i.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(i)

            } else {
                val i = Intent(this@SplashScreenActivity, LoginActivity::class.java)
                i.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(i)
            }

        }, SPLASH_TIME_OUT)
    }

    companion object {
        // Splash screen timer
        private const val SPLASH_TIME_OUT = 1000L
    }

}