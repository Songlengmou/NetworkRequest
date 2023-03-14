package com.anningtex.networkrequest.api

import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import rx.Observable

/**
 * @Author Song
 */
interface ApiService {
    @FormUrlEncoded
    @POST("/login_in")
    fun getLogin(@FieldMap params: Map<String, String>): Observable<String>

    @FormUrlEncoded
    @POST("/api/v1/getRemainingGoods")
    fun getForeignRemaining(@FieldMap params: Map<String, String>): Observable<String>
}