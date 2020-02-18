package com.nechvolod.konstantin.kernelapp.ui.fragment.ttn_deatails

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nechvolod.konstantin.kernelapp.BR
import com.nechvolod.konstantin.kernelapp.R
import com.nechvolod.konstantin.kernelapp.base.BaseFragment
import com.nechvolod.konstantin.kernelapp.databinding.FragmentTtnDetailsBinding
import com.nechvolod.konstantin.kernelapp.ui.adapter.SecListAdapter
import kotlinx.android.synthetic.main.fragment_ttn_details.*

class TtnDetailsFragment :
    BaseFragment<FragmentTtnDetailsBinding, TtnDetailsVM>(TtnDetailsVM::class) {

    companion object {
        const val ID_KEY = "ttn_id"
    }

    var id1: Int = -1

    override fun getBindingViewModelId(): Int = BR.vm

    override fun getLayoutId(): Int = R.layout.fragment_ttn_details

    private val secListAdapter: SecListAdapter by lazy { SecListAdapter() }

    override fun initFragmentViews(savedInstanceState: Bundle?) {
        super.initFragmentViews(savedInstanceState)
        var id = arguments?.getInt(ID_KEY)
        id?.let {
            mViewModel.getData(it)
            id1 = it
        }
        initRecycler()
    }

    private fun initRecycler() {
        val linearLayoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        rvSec.layoutManager = linearLayoutManager
        rvSec.adapter = secListAdapter

        mViewModel.data?.observe(this, Observer {item ->
            secListAdapter.replace(item.codeList)
            etNumberTTN.editText?.setText(item?.ttnNumber)
            etDateTTN.editText?.setText(item?.ttnDate)
            etAutoNumber.editText?.setText(item?.trackPlate)
            etTrailerNumber.editText?.setText(item?.trailerPlate)
            etDriver.editText?.setText(item?.driverName)
            etSender.editText?.setText(item?.senderName)
        })

        val divider = DividerItemDecoration(rvSec.context, linearLayoutManager.orientation)
        divider.setDrawable(requireContext().getDrawable(R.drawable.rv_divider)!!)
        rvSec.addItemDecoration(divider)
        secListAdapter.setOnClickListener = {
        }
    }
}