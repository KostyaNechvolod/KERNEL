package com.nechvolod.konstantin.kernelapp.ui.fragment.home

import android.os.Bundle
import androidx.appcompat.view.ContextThemeWrapper
import androidx.appcompat.view.menu.MenuBuilder
import androidx.appcompat.view.menu.MenuPopupHelper
import androidx.appcompat.widget.PopupMenu
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nechvolod.konstantin.kernelapp.BR
import com.nechvolod.konstantin.kernelapp.R
import com.nechvolod.konstantin.kernelapp.base.BaseFragment
import com.nechvolod.konstantin.kernelapp.base.models.ToastModel
import com.nechvolod.konstantin.kernelapp.databinding.FragmentHomeBinding
import com.nechvolod.konstantin.kernelapp.ui.adapter.HomeTtnAdapter
import com.nechvolod.konstantin.kernelapp.utils.LoadingState
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.toolbar_home.*


class HomeFragment : BaseFragment<FragmentHomeBinding, HomeVM>(HomeVM::class) {

    companion object {
        const val ID_KEY = "ttn_id"
    }

    override fun getBindingViewModelId(): Int = BR.vm

    override fun getLayoutId(): Int = R.layout.fragment_home

    private val homeAdapter: HomeTtnAdapter by lazy { HomeTtnAdapter() }

    override fun initFragmentViews(savedInstanceState: Bundle?) {
        super.initFragmentViews(savedInstanceState)
        initRecycler()
        setListeners()
        mViewModel.loadingState.observe(this, Observer {
            when (it.status) {
                LoadingState.Status.FAILED -> showToast(ToastModel(it.msg))
                LoadingState.Status.RUNNING -> showToast(ToastModel("Loading"))
                LoadingState.Status.SUCCESS -> showToast(ToastModel("Success"))
            }
        })
    }

    private fun setListeners() {
        ivFilter.setOnClickListener {
            val wrapper = ContextThemeWrapper(requireContext(), R.style.Widget_AppCompat_PopupMenu)
            val popup = PopupMenu(wrapper, it)
            popup.inflate(R.menu.sort_menu)
            popup.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.filterByTtn -> {
                        showToast(ToastModel("TTN"))
                        true
                    }
                    R.id.filterByNumberPlate -> {
                        showToast(ToastModel("NUMBERPLATE"))
                        true
                    }
                    R.id.filterByDate -> {
                        showToast(ToastModel("DATE"))
                        showDatePickerDialog()
                        true
                    }
                    else -> false
                }
            }
            val menuHelper =
                MenuPopupHelper(wrapper, popup.menu as MenuBuilder, it)
            menuHelper.show()
        }
        fabAddTtn.setOnClickListener {
            mViewModel.navigateToCreateTtnFragment()
        }
        rvTtn.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0 || dy < 0 && fabAddTtn.isShown)
                    fabAddTtn.hide()
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE)
                    fabAddTtn.show()
                super.onScrollStateChanged(recyclerView, newState)
            }
        })
    }

    private fun initRecycler() {
        val linearLayoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        rvTtn.layoutManager = linearLayoutManager
        rvTtn.adapter = homeAdapter

        mViewModel.data.observe(this, Observer {
            homeAdapter.replace(it)
        })

        val divider = DividerItemDecoration(rvTtn.context, linearLayoutManager.orientation)
        divider.setDrawable(requireContext().getDrawable(R.drawable.rv_divider)!!)
        rvTtn.addItemDecoration(divider)
        homeAdapter.setOnClickListener = {
            mViewModel.navigateToTtnDetailsFragment(it.id!!)
        }
    }
}