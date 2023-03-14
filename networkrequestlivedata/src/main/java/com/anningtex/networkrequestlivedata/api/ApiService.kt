package com.anningtex.networkrequestlivedata.api

import com.anningtex.networkrequestlivedata.base.BaseResponse
import com.anningtex.networkrequestlivedata.fragment.one.TestOneEntity
import com.anningtex.networkrequestlivedata.login.entity.LoginEntity
import com.anningtex.networkrequestlivedata.main.entity.Banner
import com.anningtex.networkrequestlivedata.main.entity.WanResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * @Author Song
 */
interface ApiService {
    /**
     * 测试接口
     */
    @GET("/banner/json")
    suspend fun getBanner(): WanResponse<List<Banner>>

    /**
     * login
     */
    @FormUrlEncoded
    @POST("/login_in")
    suspend fun getLogin(
        @Field("username") userName: String,
        @Field("password") password: String,
        @Field("device_id") device_id: String
    ): BaseResponse<LoginEntity>

    /**
     * login后的下一个测试接口
     */
    @FormUrlEncoded
    @POST("/api/v1/getRemainingGoods")
    suspend fun getForeignRemaining(
        @Field("country_id") countryId: String
//    ): BaseResponse<Object>
    ): BaseResponse<List<TestOneEntity>>
}