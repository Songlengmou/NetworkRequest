package com.anningtex.networkrequestlivedata.login

import android.Manifest
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import com.anningtex.networkrequestlivedata.R
import com.anningtex.networkrequestlivedata.api.ApiConstants
import com.anningtex.networkrequestlivedata.base.BaseActivity
import com.anningtex.networkrequestlivedata.converter.handler.Request
import com.anningtex.networkrequestlivedata.converter.model.Result
import com.anningtex.networkrequestlivedata.dialog.get
import com.anningtex.networkrequestlivedata.second.SecondActivity
import com.anningtex.networkrequestlivedata.utils.Md5Utils
import com.google.gson.Gson
import com.permissionx.saltedfish.PermissionX
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity

/**
 * @Author Song
 */
class LoginActivity : BaseActivity<LoginModel>(), View.OnClickListener {
    lateinit var userName: String
    lateinit var enCodesPwd: String

    override fun initViewModel(): LoginModel {
        return get(LoginModel::class.java)
    }

    override fun layoutId(): Int {
        return R.layout.activity_login
    }

    override fun onStart() {
        super.onStart()
        permissionMgr()
    }

    private fun permissionMgr() {
        PermissionX.requestPermissions(
            this,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) { map, b ->
            if (b) {
                //权限全部授予执行的操作
                showToast("You granted all permissions")
            } else {
                map.entries.forEach { entry ->
                    if (!entry.value) {
                        //未授予的权限执行的操作 这里弹出未授予的权限名
                        showToast("You denied ${entry.key.substringAfterLast(".")} permission")
                    }
                }
            }
        }
    }

    override fun initPage(savedInstanceState: Bundle?) {
        Request.init(this, ApiConstants.baseUrl) {
            okHttp {
                //配置okhttp
                it
            }
            retrofit {
                //配置retrofit
                it
            }
        }
        btn_login.setOnClickListener(this)
    }

    override fun initLivedata(viewModel: LoginModel) {
        viewModel.liveData.observe(this) {
            showToast(Gson().toJson(it))
        }
    }

    var name: String = ""

    override fun onClick(p0: View?) {
        val md5 = Md5Utils()
        userName = "panp2"
        enCodesPwd = md5.encodes("1234567")
//        userName = et_userName.text.toString()
//        enCodesPwd = md5.encodes(et_password.text.toString())
        when (p0) {
            btn_login -> viewModel.loadLiveData(userName, enCodesPwd).observe(this, Observer {
                when (it) {
                    is Result.Error -> {
                        hideLoading()
                    }
                    is Result.Response -> {
                        hideLoading()
                        it.response.apply {
                            Log.e("onResponse-->", Gson().toJson(this))
                            if (code == 1) {
                                showToast(msg)
                                Log.e("TAG role: ", data.role)
                                val module = data.menu.module
                                for (moduleBean in module) {
                                    name = moduleBean.name
                                    Log.e("TAG name: ", name)
                                }
                                startActivity<SecondActivity>()
                                finish()
                            } else {
                                showToast(msg)
                            }
                        }
                    }
                    is Result.Start -> {
                        showLoading()
                    }
                    is Result.Finally -> {
                    }
                    else -> {
                    }
                }
            })
        }
    }
}