package com.anningtex.networkrequestlivedata.fragment.two

import android.os.Bundle
import com.anningtex.networkrequestlivedata.R
import com.anningtex.networkrequestlivedata.base.BaseFragment
import com.anningtex.networkrequestlivedata.dialog.get

class TestTwoFragment : BaseFragment<TestTwoModel>() {

    override fun initViewModel(): TestTwoModel {
        return get(TestTwoModel::class.java)
    }

    override fun layoutId(): Int {
        return R.layout.fragment_test_two
    }

    override fun initData(savedInstanceState: Bundle?) {

    }

    override fun initLivedata(viewModel: TestTwoModel) {

    }
}