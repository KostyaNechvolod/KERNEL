package com.nechvolod.konstantin.kernelapp.ui.fragment.create_ttn

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nechvolod.konstantin.kernelapp.BR
import com.nechvolod.konstantin.kernelapp.R
import com.nechvolod.konstantin.kernelapp.base.BaseFragment
import com.nechvolod.konstantin.kernelapp.base.models.ToastModel
import com.nechvolod.konstantin.kernelapp.data.model.TtnModel
import com.nechvolod.konstantin.kernelapp.databinding.FragmentCreateTtnBinding
import com.nechvolod.konstantin.kernelapp.ui.adapter.SecListAdapter
import com.nechvolod.konstantin.kernelapp.utils.LoadingState
import kotlinx.android.synthetic.main.fragment_create_ttn.*
import kotlinx.android.synthetic.main.toolbar_create_ttn.*

class CreateTtnFragment : BaseFragment<FragmentCreateTtnBinding, CreateTtnVM>(CreateTtnVM::class) {
    override fun getBindingViewModelId(): Int = BR.vm

    override fun getLayoutId(): Int = R.layout.fragment_create_ttn

    private val secListAdapter: SecListAdapter by lazy { SecListAdapter() }

    override fun initFragmentViews(savedInstanceState: Bundle?) {
        super.initFragmentViews(savedInstanceState)
        tvCreateItem.setOnClickListener {
            mViewModel.codeList.value = secListAdapter.getItems()
            mViewModel.additemToDb()
        }
        initRecycler()

        mViewModel.loadingState.observe(this, Observer {
            when(it){
                LoadingState.LOADED-> showToast(ToastModel("Success"))
            }
        })
    }

    private fun initRecycler() {
        val linearLayoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        rvSec.layoutManager = linearLayoutManager
        rvSec.adapter = secListAdapter
        tvAddZPU.setOnClickListener {
            secListAdapter.add(TtnModel.Code("", false))
            rvSec.smoothScrollToPosition(secListAdapter.itemCount-1)
        }
        val divider = DividerItemDecoration(rvSec.context, linearLayoutManager.orientation)
        divider.setDrawable(requireContext().getDrawable(R.drawable.rv_divider)!!)
        rvSec.addItemDecoration(divider)
//        secListAdapter.setOnClickListener = {
//        }
    }
}