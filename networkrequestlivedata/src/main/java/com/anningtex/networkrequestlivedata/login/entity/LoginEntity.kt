package com.anningtex.networkrequestlivedata.login.entity

import com.google.gson.annotations.SerializedName

/**
 * @Author Song
 */
data class LoginEntity(
    /**
     * code : 1
     * msg : 登录成功
     * data : {"role":"国外同事","menu":{"module":[{"name":"报表","id":41}],"urlList":[]},"IsSimplePWD":0,"token":"db73b8b30e661055d4c69f023bf4a777","IsHeader":"0","CountryID":"1","UserName":"PANP1"}
     */
    @SerializedName("CountryID")
    val countryID: String,
    @SerializedName("IsHeader")
    val isHeader: String,
    @SerializedName("IsSimplePWD")
    val isSimplePWD: Int,
    @SerializedName("menu")
    val menu: MenuBean,
    @SerializedName("role")
    val role: String,
    @SerializedName("token")
    val token: String,
    @SerializedName("UserName")
    val userName: String

) {
    override fun toString(): String {
        return "LoginEntity(countryID='$countryID', isHeader='$isHeader', isSimplePWD=$isSimplePWD, menu=$menu, role='$role', token='$token', userName='$userName')"
    }
}

data class MenuBean(
    /**
     * name : 报表
     * id : 41
     */
    @SerializedName("module")
    val module: List<ModuleBean>,
    @SerializedName("urlList")
    val urlList: List<Any>

) {
    override fun toString(): String {
        return "MenuBean(module=$module, urlList=$urlList)"
    }
}

data class ModuleBean(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
) {
    override fun toString(): String {
        return "ModuleBean(id=$id, name='$name')"
    }
}
