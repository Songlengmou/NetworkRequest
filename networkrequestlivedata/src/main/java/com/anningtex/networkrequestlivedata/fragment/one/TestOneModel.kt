package com.anningtex.networkrequestlivedata.fragment.one

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.anningtex.networkrequestlivedata.api.ApiService
import com.anningtex.networkrequestlivedata.base.BaseResponse
import com.anningtex.networkrequestlivedata.base.BaseViewModel
import com.anningtex.networkrequestlivedata.converter.handler.Request
import com.anningtex.networkrequestlivedata.converter.model.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

/**
 * @Author Song
 * @Desc:
 * @Date：2023-03-10
 */
class TestOneModel : BaseViewModel() {
    private val service by lazy { Request.apiService(ApiService::class.java) }

    val liveData = MutableLiveData<BaseResponse<Any>>()

    fun loadLiveData(
        countryId: String
    ): LiveData<Result<BaseResponse<List<TestOneEntity>>>> {
        return apiLiveData(
            context = SupervisorJob() + Dispatchers.Main.immediate, timeoutInMs = 2000
        ) {
            service.getForeignRemaining(countryId)
        }
    }
}