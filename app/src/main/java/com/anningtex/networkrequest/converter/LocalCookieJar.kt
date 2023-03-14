package com.anningtex.networkrequest.converter

import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl

/**
 * @Author Song
 * @Desc:Cookie
 * @Date：2023-03-08
 */
class LocalCookieJar : CookieJar {
    var cookies: MutableList<Cookie> = arrayListOf()

    override fun loadForRequest(p0: HttpUrl): MutableList<Cookie> {
        return this.cookies
    }

    override fun saveFromResponse(p0: HttpUrl, p1: MutableList<Cookie>) {
        this.cookies = p1
    }
}