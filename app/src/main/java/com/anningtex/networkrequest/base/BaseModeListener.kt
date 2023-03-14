package com.anningtex.networkrequest.base

/**
 * @author Song
 */
interface BaseModeListener {

    fun onRequest(url: String, map: Map<String, String>, callBackListener: CallBackListener)

    interface CallBackListener {
        fun onDataCallBackListener(result: String)

        fun onError(error: String)
    }
}