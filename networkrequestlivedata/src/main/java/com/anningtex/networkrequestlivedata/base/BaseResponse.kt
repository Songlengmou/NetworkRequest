package com.anningtex.networkrequestlivedata.base

import java.io.Serializable

/**
 * @Author Song
 * @Desc:
 * @Date：2023-03-10
 */
data class BaseResponse<out T>(val code: Int, val msg: String, val data: T) :
    Serializable