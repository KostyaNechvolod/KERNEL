package com.nechvolod.konstantin.kernelapp.ui.activity.auth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.nechvolod.konstantin.kernelapp.BR
import com.nechvolod.konstantin.kernelapp.R
import com.nechvolod.konstantin.kernelapp.base.BaseActivity
import com.nechvolod.konstantin.kernelapp.databinding.ActivityAuthBinding

class AuthActivity : BaseActivity<ActivityAuthBinding, AuthVM>(AuthVM::class) {
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController

    companion object{
        fun getLaunchInstance(context: Context): Intent {
            return Intent(context, AuthActivity::class.java)
        }

        fun getLaunchInstanceClearTask(context: Context): Intent {
            return Intent(context, AuthActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
        }
    }

    override fun getBindingViewModelId(): Int = BR.vm

    override fun getLayoutId(): Int = R.layout.activity_auth

    override fun attachViews() {
        super.attachViews()
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
    }

    override fun initViews(savedInstanceState: Bundle?) {
        super.initViews(savedInstanceState)
        navController = navHostFragment.navController
    }
}