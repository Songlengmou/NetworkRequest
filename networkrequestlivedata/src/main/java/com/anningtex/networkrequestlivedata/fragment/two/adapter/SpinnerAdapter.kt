package com.anningtex.networkrequestlivedata.fragment.two.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.anningtex.networkrequestlivedata.R
import com.anningtex.networkrequestlivedata.fragment.two.entity.LoginUrlEntity
import kotlinx.android.synthetic.main.item_common_spinner.view.*

/**
 * @Author Song
 * @Date：2023-03-13
 */
class SpinnerAdapter(urlEntity: List<LoginUrlEntity>, mContext: Context) : BaseAdapter() {
    var urlEntities: List<LoginUrlEntity>? = null
    var mContext: Context
    var mViewHolder: MyViewHolder? = null

    init {
        this.urlEntities = urlEntity
        this.mContext = mContext
    }

    override fun getCount(): Int {
        return urlEntities?.size ?: 0
    }

    override fun getItem(p0: Int): Any {
        return urlEntities!![p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var mConvertView = p1
        if (p1 == null) {
            mConvertView = LayoutInflater.from(mContext).inflate(R.layout.item_common_spinner, null)
            mViewHolder = mConvertView?.let { MyViewHolder(it) }
            mConvertView!!.tag = mViewHolder
        } else {
            mViewHolder = p1.tag as MyViewHolder
        }
        mViewHolder!!.tvInput!!.text = urlEntities!![p0].name
        mViewHolder!!.cl?.setBackgroundResource(R.color.purple_200)
        mViewHolder!!.tvInput?.setTextColor(ContextCompat.getColor(mContext, R.color.white))
        return mConvertView!!
    }

    inner class MyViewHolder(view: View) {
        var tvInput: TextView? = null
        var cl: ConstraintLayout? = null

        init {
            tvInput = view.tv_input
            cl = view.cl
        }
    }
}
