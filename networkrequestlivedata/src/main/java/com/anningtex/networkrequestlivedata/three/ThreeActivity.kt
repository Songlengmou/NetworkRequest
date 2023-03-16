package com.anningtex.networkrequestlivedata.three

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.anningtex.networkrequestlivedata.R
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.anningtex.networkrequestlivedata.base.BaseActivity
import com.anningtex.networkrequestlivedata.dialog.get
import com.anningtex.networkrequestlivedata.fragment.two.TestTwoFragment
import com.google.android.material.button.MaterialButton
import kotlinx.android.synthetic.main.activity_three.*

/**
 * @author Song
 */
class ThreeActivity : BaseActivity<ThreeModel>() {
    private var oneTestFragment: OneTestFragment? = null
    private var twoTestFragment: TestTwoFragment? = null
    private var fManager: FragmentManager? = null

    //用于避免fragment重合到一起
    private var showFragment: Fragment? = null

    override fun initViewModel(): ThreeModel {
        return get(ThreeModel::class.java)
    }

    override fun layoutId(): Int {
        return R.layout.activity_three
    }

    override fun initPage(savedInstanceState: Bundle?) {
        //1.第一种
//        fManager = supportFragmentManager
//        rb1.setOnClickListener { v: View? -> setChoiceItem(1) }
//        rb2.setOnClickListener { v: View? -> setChoiceItem(2) }

        //2.第二种
        tab_group.addOnButtonCheckedListener { group, checkedId, isChecked ->
            //拿到制作了按钮的数量
            val childCount = group.childCount
            //index用于后面对fragment操作
            var index = 0
            for (i in 0 until childCount) {
                val childAt = group.getChildAt(i) as MaterialButton
                //让被选中的按钮改变颜色
                if (childAt.id == checkedId) {
                    index = i
                    childAt.setTextColor(Color.GREEN)
                    childAt.iconTint = ColorStateList.valueOf(Color.GREEN)
                } else {
                    childAt.setTextColor(Color.WHITE)
                    childAt.iconTint = ColorStateList.valueOf(Color.WHITE)
                }
            }
            switchFragment(index)
        }
        //使其默认指向第一个按钮
        tab_group.check(R.id.tab_1)
    }

    //将按钮与fragment绑定
    private fun switchFragment(index: Int) {
        //让返回值变为fragment
        val fragment = when (index) {
            0 -> {
                if (oneTestFragment == null) {
                    oneTestFragment = OneTestFragment()
                }
                oneTestFragment
            }
            1 -> {
                if (twoTestFragment == null) {
                    twoTestFragment = TestTwoFragment()
                }
                twoTestFragment
            }
            else -> {
                return
            }
        } ?: return
        //开启fragment事务管理
        val ft = supportFragmentManager.beginTransaction()
        //判断返回的fragment中是否被绑定
        if (!fragment.isAdded) {
            ft.add(R.id.container, fragment)
        }
        //显示被选中的fragment
        ft.show(fragment)
        //避免所有fragment重合
        if (showFragment != null) {
            ft.hide(showFragment!!)
        }
        showFragment = fragment
        //提交事务
        ft.commitAllowingStateLoss()
    }

//    private fun setChoiceItem(index: Int) {
//        val transaction = fManager!!.beginTransaction()
//        hideFragments(transaction)
//        when (index) {
//            1 -> if (oneTestFragment == null) {
//                oneTestFragment = OneTestFragment()
//                transaction.add(R.id.main_frame_layout, oneTestFragment!!)
//            } else {
//                transaction.show(oneTestFragment!!)
//            }
//            2 -> if (twoTestFragment == null) {
//                twoTestFragment = TwoTestFragment()
//                transaction.add(R.id.main_frame_layout, twoTestFragment!!)
//            } else {
//                transaction.show(twoTestFragment!!)
//            }
//            else -> {}
//        }
//        transaction.commit()
//    }

    private fun hideFragments(transaction: FragmentTransaction) {
        if (oneTestFragment != null) {
            transaction.hide(oneTestFragment!!)
        }
        if (twoTestFragment != null) {
            transaction.hide(twoTestFragment!!)
        }
    }

    override fun initLivedata(viewModel: ThreeModel) {

    }
}