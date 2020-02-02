package com.nechvolod.konstantin.kernelapp.ui.fragment.sign_in

import com.nechvolod.konstantin.kernelapp.BR
import com.nechvolod.konstantin.kernelapp.R
import com.nechvolod.konstantin.kernelapp.base.BaseFragment
import com.nechvolod.konstantin.kernelapp.databinding.FragmentSighInBinding

class SignInFragment : BaseFragment<FragmentSighInBinding, SignInVM>(SignInVM::class) {
    override fun getBindingViewModelId(): Int = BR.vm

    override fun getLayoutId(): Int = R.layout.fragment_sigh_in

}