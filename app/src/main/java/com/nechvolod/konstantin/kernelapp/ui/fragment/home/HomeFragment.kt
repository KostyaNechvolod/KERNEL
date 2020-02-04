package com.nechvolod.konstantin.kernelapp.ui.fragment.home

import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nechvolod.konstantin.kernelapp.BR
import com.nechvolod.konstantin.kernelapp.R
import com.nechvolod.konstantin.kernelapp.base.BaseFragment
import com.nechvolod.konstantin.kernelapp.base.models.ToastModel
import com.nechvolod.konstantin.kernelapp.databinding.FragmentHomeBinding
import com.nechvolod.konstantin.kernelapp.ui.adapter.HomeTtnAdapter
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : BaseFragment<FragmentHomeBinding, HomeVM>(HomeVM::class) {
    override fun getBindingViewModelId(): Int = BR.vm

    override fun getLayoutId(): Int = R.layout.fragment_home

    private val homeAdapter: HomeTtnAdapter by lazy { HomeTtnAdapter() }

    override fun initFragmentViews(savedInstanceState: Bundle?) {
        super.initFragmentViews(savedInstanceState)
        initRecycler()
        setListeners()
    }

    private fun setListeners(){
        fabAddTtn.setOnClickListener {
            showToast(ToastModel(message = "Fab pressed!"))
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
        val linearLayoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        rvTtn.layoutManager = linearLayoutManager
        rvTtn.adapter = homeAdapter
        homeAdapter.replace(listOf("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""))

        val divider = DividerItemDecoration(rvTtn.context, linearLayoutManager.orientation)
        divider.setDrawable(requireContext().getDrawable(R.drawable.rv_divider)!!)
        rvTtn.addItemDecoration(divider)
        homeAdapter.setOnClickListener = {
            showToast(ToastModel(message = it))
        }
    }
}

/*

//creating a popup menu
val wrapper = ContextThemeWrapper(holder.itemView.context, R.style.PopupMenu)
val popup = PopupMenu(wrapper, holder.itemView.textViewOptions)
//inflating menu from xml resource
popup.inflate(R.menu.shopping_cart_item_menu)
//adding click listener
popup.setOnMenuItemClickListener { item ->
    when (item.itemId) {
        R.id.editOrder -> {
            onEditOrderPressed?.invoke(selectEntity.id)
            true
        }
        R.id.deleteOrder -> {
            onDeletePressed?.invoke(selectEntity.id)
            true
        }
        else -> false
    }
}
//displaying the popup

val menuHelper =
    MenuPopupHelper(wrapper, popup.menu as MenuBuilder, holder.itemView.textViewOptions)
menuHelper.show()
*/
