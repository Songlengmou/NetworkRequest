package com.anningtex.networkrequest.base

/**
 * @Author Song
 * @Desc:
 * @Date：2023-03-07
 */
interface BaseView {
    fun onLoadContributorStart()

    fun onLoadContributorComplete(result: String)

    fun onNetWork()

    fun onError(error: String)
}