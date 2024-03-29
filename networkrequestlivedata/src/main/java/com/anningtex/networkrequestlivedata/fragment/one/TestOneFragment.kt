package com.anningtex.networkrequestlivedata.fragment.one

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import com.anningtex.networkrequestlivedata.R
import com.anningtex.networkrequestlivedata.api.ApiConstants
import com.anningtex.networkrequestlivedata.base.BaseFragment
import com.anningtex.networkrequestlivedata.converter.handler.Request
import com.anningtex.networkrequestlivedata.converter.model.Result
import com.anningtex.networkrequestlivedata.dialog.get
import com.anningtex.networkrequestlivedata.fragment.one.adapter.TestOneAdapter
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_test_one.*

/**
 * @Author Song
 */
class TestOneFragment : BaseFragment<TestOneModel>() {

    override fun initViewModel(): TestOneModel {
        return get(TestOneModel::class.java)
    }

    override fun layoutId(): Int {
        return R.layout.fragment_test_one
    }

    override fun initLayoutInflaterView(inflater: View) {

    }

    override fun initDataConfig(savedInstanceState: Bundle?) {
        Request.init(requireContext(), ApiConstants.baseUrl) {
            okHttp {
                it
            }
            retrofit {
                it
            }
        }

        viewModel.loadLiveData("2").observe(this@TestOneFragment, Observer {
            when (it) {
                is Result.Error -> {
                    hideLoading()
                }
                is Result.Response -> {
                    hideLoading()
                    it.response.apply {
                        Log.e("onResponse-->", Gson().toJson(this))
                        if (code == 1) {
                            //adapter
                            val testOneAdapter = TestOneAdapter()
                            testOneAdapter.setData(data)
                            rv_one.adapter = testOneAdapter

                            //LogShow
                            for (testOneEntity in data) {
                                val containerList = testOneEntity.containerList
                                for (container in containerList) {
                                    Log.e("TAG", "weightUnit: " + container.weightUnit)
                                }
                            }
                        } else {
                            showToast(msg)
                        }
                    }
                }
                is Result.Start -> {
                    showLoading()
                    Log.e("Start-->", Thread.currentThread().name)
                }
                is Result.Finally -> {
                    Log.e("Finally-->", Thread.currentThread().name)
                }
                else -> {
                }
            }
        })
    }

    override fun initLivedata(viewModel: TestOneModel) {
        viewModel.liveData.observe(this@TestOneFragment) {
            showToast(Gson().toJson(it))
        }
    }
}