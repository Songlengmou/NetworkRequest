package com.anningtex.networkrequest.api

/**
 * @Author Song
 * @Desc:参数集合
 * @Date：2023-03-14
 */
open class ApiShowRequestData {

    companion object {
        // login
        fun showRequestLoginData(userName: String, password: String): Map<String, String> {
            val map = HashMap<String, String>()
            map.put("username", userName)
            map.put("password", password)
            map.put("device_id", "0")
            return map
        }

        //foreignRemaining
        fun showRequestForeignRemainingData(num: String): Map<String, String> {
            val map = HashMap<String, String>()
            map.put("country_id", num)
            return map
        }
    }
}