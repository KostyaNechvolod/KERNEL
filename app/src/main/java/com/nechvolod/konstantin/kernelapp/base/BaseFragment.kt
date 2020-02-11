package com.nechvolod.konstantin.kernelapp.base

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
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
import java.util.*
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

    protected fun showDialog(){
        val builder = AlertDialog.Builder(requireContext())
        // Set the alert dialog title
        builder.setTitle("App background color")
        // Display a message on alert dialog
        builder.setMessage("Are you want to set the app background color to RED?")
        // Set a positive button and its click listener on alert dialog
        builder.setPositiveButton("YES"){dialog, which ->
            // Do something when user press the positive button
            Toast.makeText(requireContext(),"Ok, we change the app background.",Toast.LENGTH_SHORT).show()
        }
        // Display a negative button on alert dialog
        builder.setNegativeButton("No"){dialog,which ->
            Toast.makeText(requireContext(),"You are not agree.",Toast.LENGTH_SHORT).show()
        }
        // Display a neutral button on alert dialog
        builder.setNeutralButton("Cancel"){_,_ ->
            Toast.makeText(requireContext(),"You cancelled the dialog.",Toast.LENGTH_SHORT).show()
        }
        // Finally, make the alert dialog using builder
        val dialog: AlertDialog = builder.create()
        // Display the alert dialog on app interface
        dialog.show()
    }

    protected fun showDatePickerDialog(){ // todo return date via lambda
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(requireContext(), DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

             val date = GregorianCalendar(year + 1900, monthOfYear, dayOfMonth)

        }, year, month, day)

        dpd.show()
    }
}