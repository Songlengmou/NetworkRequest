package com.anningtex.networkrequestlivedata.main.entity

/**
 * @Author Song
 * @Desc:
 * @Date：2023-03-11
 */
data class Banner(
    val desc: String,
    val id: Int,
    val imagePath: String,
    val isVisible: Int,
    val order: Int,
    val title: String,
    val type: Int,
    val url: String
)
