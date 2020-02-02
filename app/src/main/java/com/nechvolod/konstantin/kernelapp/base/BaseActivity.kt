package com.nechvolod.konstantin.kernelapp.base

import android.app.ActivityManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.ColorInt
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import org.koin.android.viewmodel.ext.android.viewModel
import kotlin.reflect.KClass

abstract class BaseActivity<D : ViewDataBinding, out T : BaseViewModel>(viewModelClass: KClass<T>) :
    AppCompatActivity() {

    lateinit var mViewDataBinding: D

    val mViewModel: T by viewModel(viewModelClass)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        mViewDataBinding.lifecycleOwner = this
        performDataBinding(mViewDataBinding)
        lifecycle.addObserver(mViewModel)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        attachViews()
        initViews(savedInstanceState)
        super.onPostCreate(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycle.removeObserver(mViewModel)
    }

    open fun initViews(savedInstanceState: Bundle?) {}

    open fun attachViews() {}

    /**
     * Override for set binding variable
     *
     * @return variable id
     */
    abstract fun getBindingViewModelId(): Int

    /**
     * @return layout resource id
     */
    @LayoutRes
    abstract fun getLayoutId(): Int

    protected fun performDataBinding(viewDataBinding: D) {
        viewDataBinding.setVariable(getBindingViewModelId(), mViewModel)
    }

    fun showToast(value: String) {
        Toast.makeText(this, value, Toast.LENGTH_SHORT).show()
    }

    fun changeStatusBarColor(@ColorInt color: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = window
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = color
        }
    }


    fun isServiceRunning(serviceClassName: String): Boolean {
        val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val services = activityManager.getRunningServices(Integer.MAX_VALUE)
        for (runningServiceInfo in services) {
            if (runningServiceInfo.service.className == serviceClassName) {
                return true
            }
        }
        return false
    }

}