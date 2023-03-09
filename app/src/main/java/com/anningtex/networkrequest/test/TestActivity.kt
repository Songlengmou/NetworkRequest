package com.anningtex.networkrequest.test

import android.util.Log
import com.anningtex.networkrequest.R
import com.anningtex.networkrequest.api.ApiConstants
import com.anningtex.networkrequest.base.BaseView
import com.anningtex.networkrequest.base.BaseActivity
import com.anningtex.networkrequest.test.presenter.TestPresenter
import com.anningtex.networkrequest.utils.SPUtils
import org.jetbrains.anko.toast

class TestActivity : BaseActivity<BaseView, TestPresenter>(), BaseView {
    override fun setMainLayout(): Int {
        return R.layout.activity_test
    }

    override fun createPresenter(): TestPresenter {
        return TestPresenter()
    }

    override fun initView() {
        val getToke = SPUtils[this, "token", ""]
        Log.e("TAG", "getToke: $getToke")
    }

    override fun initBeforeData() {
        presenter!!.onRequest(ApiConstants.showRequestForeignRemainingData("2"))
    }

    override fun onLoadContributorStart() {
        toast("加载中......")
    }

    override fun onLoadContributorComplete(result: String) {
        Log.e("result", result)
    }

    override fun onNetWork() {
        toast("网络未连接")
    }

    override fun onError(error: String) {
        toast(error)
    }
}