package com.anningtex.networkrequestlivedata.base

/**
 * @Author Song
 */
interface IBaseView {
    fun showToast(message: String)

    fun hideLoading()

    fun showLoading()

    fun handleException(throwable: Throwable): Boolean
}