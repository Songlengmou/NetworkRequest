package com.anningtex.networkrequestlivedata.main.entity

import java.io.Serializable

/**
 * @Author Song
 * @Desc:
 * @Date：2023-03-11
 */
data class WanResponse<out T>(val errorCode: Int, val errorMsg: String, val data: T) : Serializable