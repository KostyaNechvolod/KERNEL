package com.nechvolod.konstantin.kernelapp.ui.fragment.sign_in

import android.os.Bundle
import androidx.lifecycle.Observer
import com.nechvolod.konstantin.kernelapp.BR
import com.nechvolod.konstantin.kernelapp.R
import com.nechvolod.konstantin.kernelapp.base.BaseFragment
import com.nechvolod.konstantin.kernelapp.databinding.FragmentSighInBinding
import com.nechvolod.konstantin.kernelapp.ui.activity.main.MainActivity

class SignInFragment : BaseFragment<FragmentSighInBinding, SignInVM>(SignInVM::class) {
    override fun getBindingViewModelId(): Int = BR.vm

    override fun getLayoutId(): Int = R.layout.fragment_sigh_in

    override fun initFragmentViews(savedInstanceState: Bundle?) {
        super.initFragmentViews(savedInstanceState)
        mViewModel.loginButtonClickedEvent.observe(this, Observer {
            startActivity(MainActivity.getLaunchInstance(requireContext()))
            requireActivity().finish()
        })
    }
}