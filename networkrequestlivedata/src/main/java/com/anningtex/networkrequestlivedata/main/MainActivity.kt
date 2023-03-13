package com.anningtex.networkrequestlivedata.main

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import com.anningtex.networkrequestlivedata.R
import com.anningtex.networkrequestlivedata.base.BaseActivity
import com.anningtex.networkrequestlivedata.converter.handler.Request
import com.anningtex.networkrequestlivedata.converter.model.Result
import com.google.gson.Gson
import androidx.lifecycle.Observer
import com.anningtex.networkrequestlivedata.dialog.get
import com.anningtex.networkrequestlivedata.login.LoginActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

/**
 *desc: 测试数据
 */
class MainActivity : BaseActivity<MainModel>(), OnClickListener {

    override fun initViewModel(): MainModel {
        return get(MainModel::class.java)
    }

    override fun layoutId(): Int {
        return R.layout.activity_main
    }

    override fun initPage(savedInstanceState: Bundle?) {
        Request.init(this, "https://www.wanandroid.com") {
//        Request.init(this, ApiConstants.baseUrl) {
            okHttp {
                //配置okhttp
                it
            }
            retrofit {
                //配置retrofit
                it
            }
        }
        test.setOnClickListener(this)
        test2.setOnClickListener(this)
        test3.setOnClickListener(this)
        test4.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        //1.
        when (v) {
            test -> viewModel.loadDSL()
            test2 -> viewModel.loadCallback()
            test3 -> viewModel.loadLiveData().observe(this, Observer {
                when (it) {
                    is Result.Error -> {
                        hideLoading()
                    }
                    is Result.Response -> {
                        hideLoading()
                        it.response.apply {
                            Log.e("onResponse-->", Gson().toJson(this))
                            showToast(Gson().toJson(this))
                        }
                    }
                    is Result.Start -> {
                        Log.e("Start-->", Thread.currentThread().name)
                        showLoading()
                    }
                    is Result.Finally -> {
                        Log.e("Finally-->", Thread.currentThread().name)
                    }
                    else -> {

                    }
                }
            })
            test4 -> startActivity<LoginActivity>()
        }
        //2.
//        when(v!!.id){
//          R.id.test -> viewModel.loadDSL()
//          R.id.test2 -> viewModel.loadCallback()
//          R.id.test3 -> showToast("121212121")
//        }
    }

    override fun initLivedata(viewModel: MainModel) {
        viewModel.liveData.observe(this, Observer {
            showToast(Gson().toJson(it))
        })
    }
}