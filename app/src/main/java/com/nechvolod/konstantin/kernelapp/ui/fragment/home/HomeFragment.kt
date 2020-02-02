package com.nechvolod.konstantin.kernelapp.ui.fragment.home

import android.os.Bundle
import com.nechvolod.konstantin.kernelapp.BR
import com.nechvolod.konstantin.kernelapp.R
import com.nechvolod.konstantin.kernelapp.base.BaseFragment
import com.nechvolod.konstantin.kernelapp.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeVM>(HomeVM::class) {
    override fun getBindingViewModelId(): Int = BR.vm

    override fun getLayoutId(): Int = R.layout.fragment_home


    override fun initFragmentViews(savedInstanceState: Bundle?) {
        super.initFragmentViews(savedInstanceState)
    }
}