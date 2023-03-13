package com.anningtex.networkrequestlivedata.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.anningtex.networkrequestlivedata.base.BaseResponse
import com.anningtex.networkrequestlivedata.api.ApiService
import com.anningtex.networkrequestlivedata.base.BaseViewModel
import com.anningtex.networkrequestlivedata.converter.handler.Request
import com.anningtex.networkrequestlivedata.converter.model.Result
import com.anningtex.networkrequestlivedata.login.entity.LoginEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

/**
 * @Author Song
 * @Desc:
 * @Date：2023-03-10
 */
class LoginModel : BaseViewModel() {
    private val service by lazy { Request.apiService(ApiService::class.java) }

    val liveData = MutableLiveData<BaseResponse<LoginEntity>>()

    fun loadLiveData(
        userName: String, enCodesPwd: String
    ): LiveData<Result<BaseResponse<LoginEntity>>> {
        return apiLiveData(
            context = SupervisorJob() + Dispatchers.Main.immediate, timeoutInMs = 2000
        ) {
            service.getLogin(userName, enCodesPwd, "0")
        }
    }
}