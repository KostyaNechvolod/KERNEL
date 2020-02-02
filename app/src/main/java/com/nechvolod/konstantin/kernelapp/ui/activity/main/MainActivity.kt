package com.nechvolod.konstantin.kernelapp.ui.activity.main

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nechvolod.konstantin.kernelapp.BR
import com.nechvolod.konstantin.kernelapp.R
import com.nechvolod.konstantin.kernelapp.base.BaseActivity
import com.nechvolod.konstantin.kernelapp.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding, MainVM>(MainVM::class) {

    companion object{
        fun getLaunchInstance(context: Context): Intent {
            return Intent(context, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
        }
    }

    override fun getBindingViewModelId(): Int = BR.vm

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun initViews(savedInstanceState: Bundle?) {
        super.initViews(savedInstanceState)
    }


}

