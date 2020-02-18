package com.nechvolod.konstantin.kernelapp.ui.fragment.create_ttn

import android.os.Bundle
import com.nechvolod.konstantin.kernelapp.BR
import com.nechvolod.konstantin.kernelapp.R
import com.nechvolod.konstantin.kernelapp.base.BaseFragment
import com.nechvolod.konstantin.kernelapp.databinding.FragmentCreateTtnBinding
import kotlinx.android.synthetic.main.toolbar_create_ttn.*

class CreateTtnFragment : BaseFragment<FragmentCreateTtnBinding, CreateTtnVM>(CreateTtnVM::class) {
    override fun getBindingViewModelId(): Int = BR.vm

    override fun getLayoutId(): Int = R.layout.fragment_create_ttn

    override fun initFragmentViews(savedInstanceState: Bundle?) {
        super.initFragmentViews(savedInstanceState)
        tvCreateItem.setOnClickListener {
            mViewModel.additemToDb()
        }
    }
}