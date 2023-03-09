package com.anningtex.networkrequest.test

import android.util.Log
import com.anningtex.networkrequest.R
import com.anningtex.networkrequest.api.ApiConstants
import com.anningtex.networkrequest.base.BaseView
import com.anningtex.networkrequest.base.BaseActivity
import com.anningtex.networkrequest.second.SecondActivity
import com.anningtex.networkrequest.test.entity.TestEntity
import com.anningtex.networkrequest.test.presenter.TestPresenter
import com.anningtex.networkrequest.utils.MessageEvent
import com.anningtex.networkrequest.utils.SPUtils
import com.google.gson.Gson
import org.greenrobot.eventbus.EventBus
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import java.util.*
import kotlin.concurrent.schedule

class TestActivity : BaseActivity<BaseView, TestPresenter>(), BaseView {
    override fun setMainLayout(): Int {
        return R.layout.activity_test
    }

    override fun createPresenter(): TestPresenter {
        return TestPresenter()
    }

    override fun initView() {
        val getToke = SPUtils[this, "token", ""]
        Log.e("TAG", "getToke: $getToke")
    }

    override fun initBeforeData() {
        presenter!!.onRequest(ApiConstants.showRequestForeignRemainingData("2"))
    }

    override fun onLoadContributorStart() {
        toast("加载中......")
    }

    override fun onLoadContributorComplete(result: String) {
        Log.e("result", result)
        //{"code":1,"msg":"success","data":[{"ContainerNo":"CRSU1061250","InDate":"2021-12-11","BLNo":"GOSUNGB9760650","ContainerList":[{"ID":"2457","BGLID":"2457","InDate":"2021-12-11","BLNo":"GOSUNGB9760650","ContainerNo":"CRSU1061250","OrderNo":"AN210504T","ToCountryName":"刚果","QBales":4,"MetersPerBale":"600.0000","MetersPerBaleUnitID":"1","VolumeUnit":".1280","WeightUnit":"94.5000","UnitName":"码","UnitNameEN":"y"},{"ID":"2458","BGLID":"2458","InDate":"2021-12-11","BLNo":"GOSUNGB9760650","ContainerNo":"CRSU1061250","OrderNo":"AN210511C","ToCountryName":"刚果","QBales":2,"MetersPerBale":"600.0000","MetersPerBaleUnitID":"1","VolumeUnit":".1280","WeightUnit":"93.0000","UnitName":"码","UnitNameEN":"y"},{"ID":"2460","BGLID":"2460","InDate":"2021-12-11","BLNo":"GOSUNGB9760650","ContainerNo":"CRSU1061250","OrderNo":"DJ210415A","ToCountryName":"刚果","QBales":3,"MetersPerBale":"900.0000","MetersPerBaleUnitID":"1","VolumeUnit":".1450","WeightUnit":"108.0000","UnitName":"码","UnitNameEN":"y"},{"ID":"2461","BGLID":"2461","InDate":"2021-12-11","BLNo":"GOSUNGB9760650","ContainerNo":"CRSU1061250","OrderNo":"DJ210423A","ToCountryName":"刚果","QBales":6,"MetersPerBale":"900.0000","MetersPerBaleUnitID":"1","VolumeUnit":".1450","WeightUnit":"109.0000","UnitName":"码","UnitNameEN":"y"},{"ID":"2462","BGLID":"2462","InDate":"2021-12-11","BLNo":"GOSUNGB9760650","ContainerNo":"CRSU1061250","OrderNo":"IB210411C","ToCountryName":"刚果","QBales":9,"MetersPerBale":"600.0000","MetersPerBaleUnitID":"1","VolumeUnit":".1280","WeightUnit":"95.0000","UnitName":"码","UnitNameEN":"y"},{"ID":"2463","BGLID":"2463","InDate":"2021-12-11","BLNo":"GOSUNGB9760650","ContainerNo":"CRSU1061250","OrderNo":"PB210611C","ToCountryName":"刚果","QBales":162,"MetersPerBale":"300.0000","MetersPerBaleUnitID":"1","VolumeUnit":".0550","WeightUnit":"37.0000","UnitName":"码","UnitNameEN":"y"},{"ID":"2464","BGLID":"2464","InDate":"2021-12-11","BLNo":"GOSUNGB9760650","ContainerNo":"CRSU1061250","OrderNo":"PJX210112A","ToCountryName":"刚果","QBales":50,"MetersPerBale":"900.0000","MetersPerBaleUnitID":"1","VolumeUnit":".1500","WeightUnit":"109.0000","UnitName":"码","UnitNameEN":"y"}]},{"ContainerNo":"PCIU1965040","InDate":"2021-12-11","BLNo":"NGNT10085000","ContainerList":[{"ID":"2465","BGLID":"2465","InDate":"2021-12-11","BLNo":"NGNT10085000","ContainerNo":"PCIU1965040","OrderNo":"IB210506C","ToCountryName":"刚果","QBales":82,"MetersPerBale":"600.0000","MetersPerBaleUnitID":"1","VolumeUnit":".1280","WeightUnit":"93.5000","UnitName":"码","UnitNameEN":"y"},{"ID":"2466","BGLID":"2466","InDate":"2021-12-11","BLNo":"NGNT10085000","ContainerNo":"PCIU1965040","OrderNo":"IB210507C","ToCountryName":"刚果","QBales":96,"MetersPerBale":"600.0000","MetersPerBaleUnitID":"1","VolumeUnit":".1280","WeightUnit":"93.5000","UnitName":"码","UnitNameEN":"y"},{"ID":"2467","BGLID":"2467","InDate":"2021-12-11","BLNo":"NGNT10085000","ContainerNo":"PCIU1965040","OrderNo":"PSJ210618C","ToCountryName":"刚果","QBales":57,"MetersPerBale":"180.0000","MetersPerBaleUnitID":"1","VolumeUnit":".0500","WeightUnit":"28.0000","UnitName":"码","UnitNameEN":"y"},{"ID":"2468","BGLID":"2468","InDate":"2021-12-11","BLNo":"NGNT10085000","ContainerNo":"PCIU1965040","OrderNo":"PSJ210619C","ToCountryName":"刚果","QBales":55,"MetersPerBale":"180.0000","MetersPerBaleUnitID":"1","VolumeUnit":".0450","WeightUnit":"20.5000","UnitName":"码","UnitNameEN":"y"},{"ID":"2469","BGLID":"2469","InDate":"2021-12-11","BLNo":"NGNT10085000","ContainerNo":"PCIU1965040","OrderNo":"PSY210526Z","ToCountryName":"刚果","QBales":10,"MetersPerBale":"900.0000","MetersPerBaleUnitID":"1","VolumeUnit":".1500","WeightUnit":"104.5000","UnitName":"码","UnitNameEN":"y"}]}]}
        val resultBean = Gson().fromJson(result, TestEntity::class.javaObjectType)
        if (resultBean.code == 1) {
            val dataList = resultBean.data;
//        Log.e("TAG dataList", dataList.toString())
            for (element in dataList) {
                val containerNo = element.ContainerNo
//            Log.e("TAG containerNo", containerNo)
                val containerList = element.ContainerList
                for (element in containerList) {
                    val toCountryName = element.ToCountryName
//                Log.e("TAG toCountryName", toCountryName)
                }
            }

            startActivity<SecondActivity>()
            Timer().schedule(3000) {
                EventBus.getDefault().post(MessageEvent("跳转了"))
            }
        }
    }

    override fun onNetWork() {
        toast("网络未连接")
    }

    override fun onError(error: String) {
        toast(error)
    }
}