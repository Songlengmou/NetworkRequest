package com.anningtex.networkrequestlivedata.base

interface IBaseView {
    fun showToast(message: String)

    fun hideLoading()

    fun showLoading()

    fun handleLogin()

    fun handleException(throwable: Throwable): Boolean
}