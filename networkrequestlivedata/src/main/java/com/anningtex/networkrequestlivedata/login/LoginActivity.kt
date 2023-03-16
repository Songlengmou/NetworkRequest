package com.anningtex.networkrequestlivedata.login

import android.Manifest
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView
import androidx.lifecycle.Observer
import com.anningtex.networkrequestlivedata.R
import com.anningtex.networkrequestlivedata.api.ApiConstants
import com.anningtex.networkrequestlivedata.api.AppUrlManger
import com.anningtex.networkrequestlivedata.base.BaseActivity
import com.anningtex.networkrequestlivedata.converter.handler.Request
import com.anningtex.networkrequestlivedata.converter.model.Result
import com.anningtex.networkrequestlivedata.dialog.get
import com.anningtex.networkrequestlivedata.fragment.two.adapter.CountryAdapter
import com.anningtex.networkrequestlivedata.fragment.two.adapter.SpinnerAdapter
import com.anningtex.networkrequestlivedata.second.SecondActivity
import com.anningtex.networkrequestlivedata.utils.Md5Utils
import com.anningtex.networkrequestlivedata.utils.PopupWindowUtils
import com.anningtex.networkrequestlivedata.utils.SPUtils
import com.google.gson.Gson
import com.permissionx.saltedfish.PermissionX
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.btn_test
import kotlinx.android.synthetic.main.activity_login.et_select
import org.jetbrains.anko.startActivity

/**
 * @Author Song
 */
class LoginActivity : BaseActivity<LoginModel>(), View.OnClickListener {
    lateinit var userName: String
    lateinit var enCodesPwd: String
    private var countryIndex = -1
    var popupWindow: PopupWindowUtils? = null

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
        countryIndex = SPUtils[this, ApiConstants.KEY_COUNTRY_INDEX, -1] as Int
        if (countryIndex != -1) {
            et_select.setText(AppUrlManger.countryEntity[countryIndex].countryName)
        }
        popupWindow = PopupWindowUtils(this)
        initSpinner()

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
        et_select.setOnClickListener(this)
        btn_test.setOnClickListener(this)
        btn_login.setOnClickListener(this)
    }

    private fun initSpinner() {
        val adapter = SpinnerAdapter(AppUrlManger.urlEntities, this)
        sp_chooses.adapter = adapter
        sp_chooses.setSelection(AppUrlManger.indexChoose.getIndex() as Int)
        // 默认
        AppUrlManger.indexChoose.setUrl(AppUrlManger.urlEntities[0].url)
        sp_chooses.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?, view: View, position: Int, id: Long
            ) {
                Log.e("TAG", "url:" + AppUrlManger.urlEntities[position].toString())
                AppUrlManger.indexChoose.setUrl(AppUrlManger.urlEntities[position].url)
                AppUrlManger.indexChoose.setIndex(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun showCountryPop() {
        val popView: View =
            LayoutInflater.from(this).inflate(R.layout.popup_window_list, null, false)
        val popList = popView.findViewById<ListView>(R.id.pop_list)
        popupWindow!!.contentView = popView
        popupWindow!!.width = ViewGroup.LayoutParams.MATCH_PARENT
        popupWindow!!.height = ViewGroup.LayoutParams.MATCH_PARENT
        popupWindow!!.isFocusable = true
        popupWindow!!.isOutsideTouchable = true
        popList.divider = ColorDrawable(Color.BLACK)
        popList.dividerHeight = 1
        val countryAdapter = CountryAdapter(AppUrlManger.countryEntity, this)
        popList.adapter = countryAdapter

        popList.onItemClickListener =
            AdapterView.OnItemClickListener { parent: AdapterView<*>?, view: View?, position: Int, id: Long ->
                countryIndex = position
                val countryEntity = AppUrlManger.countryEntity[position]
                et_select.setText(countryEntity.countryName)
                popupWindow!!.dismiss()
            }
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
            et_select -> {
                showCountryPop()
                popupWindow?.showAsDropDown(et_select)
            }
            btn_test -> {
                if (countryIndex == -1) {
                    showToast("请选择")
                    return
                }
                val countryEntity = AppUrlManger.countryEntity[countryIndex]
                Log.e("TAG", "countryId: " + countryEntity.countryId)
            }
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