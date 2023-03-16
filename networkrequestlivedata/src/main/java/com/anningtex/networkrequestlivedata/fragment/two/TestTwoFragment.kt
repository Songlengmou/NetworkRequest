package com.anningtex.networkrequestlivedata.fragment.two

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatSpinner
import com.anningtex.networkrequestlivedata.R
import com.anningtex.networkrequestlivedata.api.ApiConstants
import com.anningtex.networkrequestlivedata.api.AppUrlManger
import com.anningtex.networkrequestlivedata.base.BaseFragment
import com.anningtex.networkrequestlivedata.dialog.get
import com.anningtex.networkrequestlivedata.fragment.two.adapter.CountryAdapter
import com.anningtex.networkrequestlivedata.fragment.two.adapter.SpinnerAdapter
import com.anningtex.networkrequestlivedata.utils.PopupWindowUtils
import com.anningtex.networkrequestlivedata.utils.SPUtils

/**
 * @Author Song
 * desc: 使用继承BaseAdapter
 */
class TestTwoFragment : BaseFragment<TestTwoModel>(), View.OnClickListener {
    private var spChoose: AppCompatSpinner? = null
    private var etSelect: AppCompatEditText? = null
    private var btnTest: AppCompatButton? = null
    private var popupWindow: PopupWindowUtils? = null
    private var countryIndex = -1

    override fun initViewModel(): TestTwoModel {
        return get(TestTwoModel::class.java)
    }

    override fun layoutId(): Int {
        return R.layout.fragment_test_two
    }

    override fun initLayoutInflaterView(inflater: View) {
        spChoose = inflater.findViewById(R.id.spChoose)
        etSelect = inflater.findViewById(R.id.etSelect)
        btnTest = inflater.findViewById(R.id.btnTest)
        countryIndex = SPUtils[requireContext(), ApiConstants.KEY_COUNTRY_INDEX, -1] as Int
        if (countryIndex != -1) {
            etSelect?.setText(AppUrlManger.countryEntity[countryIndex].countryName)
        }
        popupWindow = PopupWindowUtils(requireContext())
        initSpinner()
        etSelect?.setOnClickListener(this)
        btnTest?.setOnClickListener(this)
    }

    override fun initDataConfig(savedInstanceState: Bundle?) {

    }

    private fun initSpinner() {
        val adapter = SpinnerAdapter(AppUrlManger.urlEntities, requireContext())
        spChoose?.adapter = adapter
        spChoose?.setSelection(AppUrlManger.indexChoose.getIndex() as Int)
        // 默认
        AppUrlManger.indexChoose.setUrl(AppUrlManger.urlEntities[0].url)
        spChoose?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
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

    override fun onClick(p0: View?) {
        when (p0) {
            etSelect -> {
                showCountryPop()
                etSelect?.let { popupWindow?.showAsDropDown(it) }
            }
            btnTest -> {
                if (countryIndex == -1) {
                    showToast("请选择")
                    return
                }
                val countryEntity = AppUrlManger.countryEntity[countryIndex]
                showToast("countryId: " + countryEntity.countryId)
            }
        }
    }

    override fun initLivedata(viewModel: TestTwoModel) {

    }

    private fun showCountryPop() {
        val popView: View =
            LayoutInflater.from(requireContext()).inflate(R.layout.popup_window_list, null, false)
        val popList = popView.findViewById<ListView>(R.id.pop_list)
        popupWindow!!.contentView = popView
        popupWindow!!.width = ViewGroup.LayoutParams.MATCH_PARENT
        popupWindow!!.height = ViewGroup.LayoutParams.MATCH_PARENT
        popupWindow!!.isFocusable = true
        popupWindow!!.isOutsideTouchable = true
        popList.divider = ColorDrawable(Color.BLACK)
        popList.dividerHeight = 1
        val countryAdapter = CountryAdapter(AppUrlManger.countryEntity, requireContext())
        popList.adapter = countryAdapter
        popList.onItemClickListener =
            AdapterView.OnItemClickListener { parent: AdapterView<*>?, view: View?, position: Int, id: Long ->
                countryIndex = position
                val countryEntity = AppUrlManger.countryEntity[position]
                etSelect?.setText(countryEntity.countryName)
                popupWindow!!.dismiss()
            }
    }
}