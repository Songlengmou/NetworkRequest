package com.anningtex.networkrequestlivedata.base

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.anningtex.networkrequestlivedata.dialog.LoadingDialog

abstract class BaseFragment<T : BaseViewModel> : Fragment(), IBaseView {
    lateinit var viewModel: T
    private lateinit var loadingDialog: Dialog
    protected abstract fun initViewModel(): T
    protected abstract fun layoutId(): Int
    protected abstract fun initData(savedInstanceState: Bundle?)
    protected abstract fun initLivedata(viewModel: T)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        viewModel = initViewModel()
        loadingDialog = initLoadingDialog()
        viewModel.apiLoading.observe(viewLifecycleOwner, Observer {
            if (it == null) {
                return@Observer
            }
            if (it) showLoading() else hideLoading()
        })

        viewModel.toastLiveData.observe(viewLifecycleOwner) {
            it?.apply {
                showToast(it)
            }
        }

        viewModel.apiException.observe(viewLifecycleOwner, Observer { throwable ->
            if (throwable == null) {
                return@Observer
            }
            if (handleException(throwable)) {
                return@Observer
            }
        })

        initData(savedInstanceState)
        initLivedata(viewModel)

        return inflater.inflate(layoutId(), container, false)
    }

    private fun initLoadingDialog(): LoadingDialog {
        return LoadingDialog(requireContext())
    }

    override fun showToast(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    override fun hideLoading() {
        (activity as BaseActivity<*>).hideLoading()
    }

    override fun showLoading() {
        (activity as BaseActivity<*>).showLoading()
    }

    override fun handleException(throwable: Throwable): Boolean {
        throwable.toString().let { Log.e("BaseViewModel--> ", it) }
        return false
    }
}
