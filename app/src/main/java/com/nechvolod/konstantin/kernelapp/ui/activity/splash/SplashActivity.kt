package com.nechvolod.konstantin.kernelapp.ui.activity.splash

import android.os.Bundle
import com.nechvolod.konstantin.kernelapp.BR
import com.nechvolod.konstantin.kernelapp.R
import com.nechvolod.konstantin.kernelapp.base.BaseActivity
import com.nechvolod.konstantin.kernelapp.databinding.ActivitySplashBinding
import com.nechvolod.konstantin.kernelapp.ui.activity.auth.AuthActivity
import com.nechvolod.konstantin.kernelapp.ui.activity.main.MainActivity
import java.util.*
import kotlin.concurrent.schedule

class SplashActivity : BaseActivity<ActivitySplashBinding, SplashVM>(SplashVM::class) {

    override fun getBindingViewModelId(): Int = BR.vm

    override fun getLayoutId(): Int = R.layout.activity_splash

    override fun initViews(savedInstanceState: Bundle?) {
        super.initViews(savedInstanceState)
        Timer("Splash", false).schedule(1500) {
            runOnUiThread {
                mViewModel.isAuthenticated.observe(this@SplashActivity, androidx.lifecycle.Observer {
                    if (it) {
                        startActivity(MainActivity.getLaunchInstance(applicationContext))
                    } else {
                        startActivity(AuthActivity.getLaunchInstance(applicationContext))
                    }
                })
            }
//            startActivity(AuthActivity.getLaunchInstance(applicationContext))
            finish()
        }
    }
}