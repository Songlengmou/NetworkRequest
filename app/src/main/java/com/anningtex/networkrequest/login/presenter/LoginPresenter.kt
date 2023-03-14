package com.anningtex.networkrequest.login.presenter

import android.util.Log
import com.anningtex.networkrequest.api.ApiConstants
import com.anningtex.networkrequest.base.BaseModeListener
import com.anningtex.networkrequest.login.model.LoginModel
import com.anningtex.networkrequest.base.BaseView
import com.anningtex.networkrequest.base.BasePresenter

/**
 * @Author Song
 * @Date：2023-03-07
 */
class LoginPresenter : BasePresenter<BaseView>() {
    var model: LoginModel? = null

    init {
        model = LoginModel()
    }

    fun onRequest(map: Map<String, String>) {
        Log.e("TAG", "onRequest: $map")
        val iView = getView()
        if (model != null && iView != null) {
            //验证网络  无网络不加载
            if (false) {
                iView.onNetWork()
                return
            }
            //初始化
            iView.onLoadContributorStart()
            model!!.onRequest(ApiConstants.baseUrl,
                map,
                object : BaseModeListener.CallBackListener {
                    override fun onDataCallBackListener(result: String) {
                        iView.onLoadContributorComplete(result)
                    }

                    override fun onError(error: String) {
                        iView.onError(error)
                    }
                })
        }
    }

    override fun detachView() {
        //重写 销毁
        super.detachView()
        if (model != null) {
            model!!.detachModel()
        }
    }
}