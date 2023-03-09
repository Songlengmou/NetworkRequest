package com.anningtex.networkrequest.login

import android.content.Intent
import android.util.Log
import com.anningtex.networkrequest.R
import com.anningtex.networkrequest.api.ApiConstants
import com.anningtex.networkrequest.login.entity.LoginEntity
import com.anningtex.networkrequest.login.presenter.LoginPresenter
import com.anningtex.networkrequest.base.BaseView
import com.anningtex.networkrequest.base.BaseActivity
import com.anningtex.networkrequest.test.TestActivity
import com.anningtex.networkrequest.utils.Md5Utils
import com.anningtex.networkrequest.utils.SPUtils
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.toast

class LoginActivity : BaseActivity<BaseView, LoginPresenter>(), BaseView {
    override fun setMainLayout(): Int {
        return R.layout.activity_login
    }

    override fun createPresenter(): LoginPresenter {
        return LoginPresenter()
    }

    override fun initView() {

    }

    override fun initBeforeData() {
        btn_login.setOnClickListener {
            val Md5Utils = Md5Utils()
            val encodes = Md5Utils.encodes("1234567")
            presenter!!.onRequest(ApiConstants.showRequestLoginData("panp2", encodes))
        }
    }

    override fun onLoadContributorStart() {
        toast("加载中......")
    }

    var name: String? = null

    override fun onLoadContributorComplete(data: String) {
        Log.e("data", data)

        //json解析
//        val entityTitle = JSON.parseObject(data, LoginEntity::class.java)
//        if (entityTitle.data != null) {
//            Log.e("TAG", "data: " + entityTitle.data.toString())
//            val menu = entityTitle.data?.menu
//            Log.e("TAG", "menu: " + menu.toString())
//        }

        //gson解析
//{"code":1,"msg":"登录成功","data":{"role":"国外同事","menu":{"module":[{"name":"报表","id":41}],"urlList":[]},"IsSimplePWD":0,"token":"db73b8b30e661055d4c69f023bf4a777","IsHeader":"0","CountryID":"1","UserName":"PANP1"}}
        val resultBean = Gson().fromJson(data, LoginEntity::class.java)
        val data = resultBean.data
        val menu = data?.menu
        val module = menu?.module
        if (module != null) {
            for (moduleBean in module) {
                name = moduleBean.name
                Log.e("TAG", "name: " + name.toString())
            }
        }
        Log.e("TAG", "data: " + data.toString())
        Log.e("TAG", "menu: " + menu.toString())
        Log.e("TAG", "module: " + module.toString())
        Log.e("TAG", "name23: " + name.toString())

        val token = data?.token
        if (token != null) {
            SPUtils.put(this, "token", token)
        }
        if (resultBean.code == 1) {
            resultBean.msg?.let { toast(it) }
            startActivity(Intent(this, TestActivity::class.java))
        } else {
            resultBean.msg?.let { toast(it) }
        }
    }

    override fun onNetWork() {
        toast("网络未连接")
    }

    override fun onError(error: String) {
        toast(error)
    }
}