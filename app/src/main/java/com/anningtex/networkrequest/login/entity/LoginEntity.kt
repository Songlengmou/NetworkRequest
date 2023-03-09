package com.anningtex.networkrequest.login.entity

/**
 * @Author Song
 */
class LoginEntity {
    /**
     * code : 1
     * msg : 登录成功
     * data : {"role":"国外同事","menu":{"module":[{"name":"报表","id":41}],"urlList":[]},"IsSimplePWD":0,"token":"db73b8b30e661055d4c69f023bf4a777","IsHeader":"0","CountryID":"1","UserName":"PANP1"}
     */
    var code = 0
    var msg: String? = null
    var data: DataBean? = null

    class DataBean {
        var role: String? = null
        var menu: MenuBean? = null
        var isSimplePWD = 0
        var token: String? = null
        var isHeader: String? = null
        var countryID: String? = null
        var userName: String? = null

        class MenuBean {
            /**
             * name : 报表
             * id : 41
             */
            var module: List<ModuleBean>? = null
            var urlList: List<*>? = null

            class ModuleBean {
                var name: String? = null
                var id = 0
                override fun toString(): String {
                    return "ModuleBean(name=$name, id=$id)"
                }
            }
        }

        override fun toString(): String {
            return "DataBean(role=$role, menu=$menu, isSimplePWD=$isSimplePWD, token=$token, isHeader=$isHeader, countryID=$countryID, userName=$userName)"
        }

    }

    override fun toString(): String {
        return "LoginEntity(code=$code, msg=$msg, data=$data)"
    }
}