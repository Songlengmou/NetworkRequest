package com.anningtex.networkrequestlivedata.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.anningtex.networkrequestlivedata.api.ApiService
import com.anningtex.networkrequestlivedata.base.BaseViewModel
import com.anningtex.networkrequestlivedata.converter.handler.Request
import com.anningtex.networkrequestlivedata.converter.model.Result
import com.anningtex.networkrequestlivedata.main.entity.Banner
import com.anningtex.networkrequestlivedata.main.entity.WanResponse
import com.google.gson.Gson
import com.google.gson.JsonElement
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

/**
 * @Author Song
 * @Desc:
 * @Date：2023-03-10
 */
class MainModel : BaseViewModel() {
    private val service by lazy { Request.apiService(ApiService::class.java) }

    val liveData = MutableLiveData<WanResponse<List<Banner>>>()

    fun loadDSL() {
        apiDSL {
            onRequest {
                Log.e("Thread-->onRequest", Thread.currentThread().name)
                service.getBanner()
            }

            onResponse {
                Log.e("Thread-->onResponse", Thread.currentThread().name)
                Log.e("onResponse-->", Gson().toJson(it as JsonElement?))
                liveData.value = it
            }

            onStart {
                Log.e("Thread-->onStart", Thread.currentThread().name)
                false
            }

            onError {
                it.printStackTrace()
                Log.e("Thread-->onError", Thread.currentThread().name)
                false
            }
        }
    }

    fun loadCallback() {
        apiCallback({
            Log.e("Thread-->onRequest", Thread.currentThread().name)
            service.getBanner()
        }, {
            Log.e("Thread-->onResponse", Thread.currentThread().name)
            Log.e("onResponse-->", Gson().toJson(it))
            liveData.value = it
        })
    }

    fun loadLiveData(): LiveData<Result<WanResponse<List<Banner>>>> {
        return apiLiveData(
            context = SupervisorJob() + Dispatchers.Main.immediate, timeoutInMs = 2000
        ) {
            service.getBanner()
        }
    }
}