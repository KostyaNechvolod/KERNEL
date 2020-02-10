package com.nechvolod.konstantin.kernelapp.ui.fragment.ttn_deatails

import com.nechvolod.konstantin.kernelapp.BR
import com.nechvolod.konstantin.kernelapp.R
import com.nechvolod.konstantin.kernelapp.base.BaseFragment
import com.nechvolod.konstantin.kernelapp.databinding.FragmentTtnDetailsBinding

class TtnDetailsFragment : BaseFragment<FragmentTtnDetailsBinding, TtnDetailsVM>(TtnDetailsVM::class) {
    override fun getBindingViewModelId(): Int = BR.vm

    override fun getLayoutId(): Int = R.layout.fragment_ttn_details


}