package com.anningtex.networkrequest.api

open class ApiConstants {
    //    "https://anlog.anningtex.vip";
//    "http://192.168.0.155:8080/";
//    "http://192.168.0.125:9093/";
    companion object {
        var baseUrl: String = "http://192.168.0.155:8080"

        fun showRequestLoginData(userName: String, password: String): Map<String, String> {
            val map = HashMap<String, String>()
            map.put("username", userName)
            map.put("password", password)
            map.put("device_id", "0")
            return map
        }

        fun showRequestForeignRemainingData(num: String): Map<String, String> {
            val map = HashMap<String, String>()
            map.put("country_id", num)
            return map
        }
    }
}