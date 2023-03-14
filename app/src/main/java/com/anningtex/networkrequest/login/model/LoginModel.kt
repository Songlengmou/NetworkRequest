package com.anningtex.networkrequest.login.model

import com.anningtex.networkrequest.api.ApiManager
import com.anningtex.networkrequest.api.ApiService
import com.anningtex.networkrequest.base.BaseModeListener
import com.anningtex.networkrequest.base.BaseModel
import rx.Subscriber

/**
 * @Author Song
 * @Date：2023-03-07
 */
class LoginModel : BaseModel(), BaseModeListener {
    private var strApiService: ApiService = ApiManager.getInstance().strApiService

    override fun onRequest(
        url: String, map: Map<String, String>, callBackListener: BaseModeListener.CallBackListener
    ) {
        addSubscription(strApiService.getLogin(map), object : Subscriber<String>() {
            override fun onCompleted() {

            }

            override fun onNext(result: String) {
                callBackListener.onDataCallBackListener(result)
            }

            override fun onError(e: Throwable) {
                callBackListener.onError("数据加载失败")
            }
        })
    }
}