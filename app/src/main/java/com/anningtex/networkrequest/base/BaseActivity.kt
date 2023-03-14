package com.anningtex.networkrequest.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * @Author Song
 */
abstract class BaseActivity<V, T : BasePresenter<V>> : AppCompatActivity() {
    var presenter: T? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(setMainLayout())
        presenter = createPresenter()
        presenter!!.attachView(this as V)
        initView()
        initBeforeData()
    }

    abstract fun setMainLayout(): Int

    abstract fun createPresenter(): T

    abstract fun initView()

    abstract fun initBeforeData()

    override fun onDestroy() {
        //销毁---避免内存泄漏
        presenter!!.detachView()
        super.onDestroy()
    }
}