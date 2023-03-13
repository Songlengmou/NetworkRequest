package com.anningtex.networkrequestlivedata.base

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

    protected abstract fun initViewModel(): T
    protected abstract fun layoutId(): Int
    protected abstract fun initData(savedInstanceState: Bundle?)
    protected abstract fun initLivedata(viewModel: T)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        viewModel = initViewModel()
        viewModel.apiLoading.observe(viewLifecycleOwner, Observer<Boolean> {
            if (it == null) {
                return@Observer
            }
            if (it) showLoading() else hideLoading()
        })

        viewModel.toastLiveData.observe(viewLifecycleOwner, Observer<String> {
            it?.apply {
                showToast(it)
            }
        })

        viewModel.apiException.observe(viewLifecycleOwner, Observer<Throwable> { throwable ->
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

    private fun initProgressDialog(): LoadingDialog {
        val progressDialog = LoadingDialog(requireContext())
        progressDialog.setCanceledOnTouchOutside(false)
        progressDialog.setCancelable(false)
        return progressDialog
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

    override fun handleLogin() {
        (activity as BaseActivity<*>).handleLogin()
    }

    override fun handleException(throwable: Throwable): Boolean {
        Log.e("BaseViewModel--> ", throwable?.toString() ?: "did not get detail exception")
        return false
    }
}
