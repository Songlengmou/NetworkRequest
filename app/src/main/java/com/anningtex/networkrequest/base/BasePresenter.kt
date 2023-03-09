package com.anningtex.networkrequest.base

import java.lang.ref.WeakReference

open class BasePresenter<T> {

    var weakReference: WeakReference<T>? = null

    fun attachView(t: T) {
        weakReference = WeakReference<T>(t)
    }

    open fun detachView() {
        if (weakReference != null) {
            weakReference!!.clear()
            weakReference = null
        }
    }

    fun getView(): T? {
        return weakReference!!.get()
    }
}