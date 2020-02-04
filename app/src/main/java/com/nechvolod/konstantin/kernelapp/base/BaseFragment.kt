package com.nechvolod.konstantin.kernelapp.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.nechvolod.konstantin.kernelapp.R
import com.nechvolod.konstantin.kernelapp.base.models.NavigationModel
import com.nechvolod.konstantin.kernelapp.base.models.ToastModel
import org.koin.android.viewmodel.ext.android.viewModel
import kotlin.reflect.KClass

abstract class BaseFragment <D : ViewDataBinding, out T : BaseViewModel>(viewModelClass: KClass<T>) : Fragment() {
    protected val mViewModel: T by viewModel(viewModelClass)

    lateinit var mActivity: BaseActivity<*,*>
    private lateinit var mViewDataBinding: D
    protected lateinit var navController: NavController

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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mViewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        mViewDataBinding.lifecycleOwner = viewLifecycleOwner
        performDataBinding(mViewDataBinding)
        mViewModel.navControllerLiveEvent.observe(viewLifecycleOwner, Observer { onNavigateTo(it) })
        mViewModel.toastLiveEvent.observe(viewLifecycleOwner, Observer { showToast(it) })
        return mViewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycle.addObserver(mViewModel)
        navController = NavHostFragment.findNavController(this)
        attachFragmentViews(view)
        initFragmentViews(savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.mActivity = context as BaseActivity<*,*>
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel.addObserver(lifecycle)
    }
    override fun onDestroyView() {
        mViewModel.removeObserver(lifecycle)
        super.onDestroyView()
    }

    override fun onDetach() {
        super.onDetach()
    }

    protected fun performDataBinding(viewDataBinding: D) {
        viewDataBinding.setVariable(getBindingViewModelId(), mViewModel)
        mViewDataBinding?.executePendingBindings()
    }

    open fun attachFragmentViews(view: View) {
        mViewDataBinding.root.findViewById<View>(R.id.ivBack)?.setOnClickListener {
            mActivity.onBackPressed()
        }
    }

    protected fun onNavigateTo(navigationModel: NavigationModel) {
        if (navigationModel.popBack) {
            navController.navigateUp()
        } else if (navigationModel.direction != null) {
            navController.navigate(navigationModel.direction)
        } else {
            navController.navigate(navigationModel.actionId, navigationModel.bundle)
        }
    }

    open fun initFragmentViews(savedInstanceState: Bundle?) {

    }

    protected fun showToast(toastModel: ToastModel) {
        val message: String
        if (toastModel.idResMessage != null) {
            message = getString(toastModel.idResMessage)
        } else {
            message = toastModel.message!!
        }
        Toast.makeText(mActivity, message, Toast.LENGTH_SHORT).show()
    }
}