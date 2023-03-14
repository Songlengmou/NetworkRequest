package com.anningtex.networkrequestlivedata.base

import androidx.lifecycle.MutableLiveData
import com.anningtex.networkrequestlivedata.converter.model.RequestViewModel

/**
 * @Author Song
 */
open class BaseViewModel : RequestViewModel() {
    internal val toastLiveData = MutableLiveData<String>()

    private fun showLoading() {
        apiLoading.postValue(true)
    }

    private fun hideLoading() {
        apiLoading.postValue(false)
    }

    fun showToast(message: String) {
        toastLiveData.postValue(message)
    }

    override fun onApiStart() {
        super.onApiStart()
    }

    override fun onApiError(e: Exception?) {
        super.onApiError(e)
    }

    override fun onApiFinally() {
        super.onApiFinally()
    }
}
