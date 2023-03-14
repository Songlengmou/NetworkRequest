package com.anningtex.networkrequestlivedata.second

import android.os.Bundle
import com.anningtex.networkrequestlivedata.R
import com.anningtex.networkrequestlivedata.base.BaseActivity
import com.anningtex.networkrequestlivedata.dialog.get
import com.anningtex.networkrequestlivedata.fragment.one.TestOneFragment
import com.anningtex.networkrequestlivedata.fragment.two.TestTwoFragment
import kotlinx.android.synthetic.main.activity_second.*

/**
 * @Author Song
 */
class SecondActivity : BaseActivity<SecondModel>() {

    override fun initViewModel(): SecondModel {
        return get(SecondModel::class.java)
    }

    override fun layoutId(): Int {
        return R.layout.activity_second
    }

    override fun initPage(savedInstanceState: Bundle?) {
        val oneFragment = TestOneFragment()
        val twoFragment = TestTwoFragment()
        supportFragmentManager.beginTransaction().replace(R.id.fl_content, oneFragment).commit()
        rg!!.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.rb1 -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fl_content, oneFragment)
                        .commit()
                }
                R.id.rb2 -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fl_content, twoFragment)
                        .commit()
                }
            }
        }
    }

    override fun initLivedata(viewModel: SecondModel) {
    }
}