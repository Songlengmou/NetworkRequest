package com.anningtex.networkrequest.test.model

import com.anningtex.networkrequest.api.ApiManager
import com.anningtex.networkrequest.api.ApiService
import com.anningtex.networkrequest.base.BaseModeListener
import com.anningtex.networkrequest.base.BaseModel
import rx.Subscriber

/**
 * @Author Song
 * @Date：2023-03-08
 */
class TestModel : BaseModel(), BaseModeListener {
    private var strApiService: ApiService = ApiManager.getInstance().strApiService

    override fun onRequest(
        url: String, map: Map<String, String>, callBackListener: BaseModeListener.CallBackListener
    ) {
        addSubscription(strApiService.getForeignRemaining(map), object : Subscriber<String>() {
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