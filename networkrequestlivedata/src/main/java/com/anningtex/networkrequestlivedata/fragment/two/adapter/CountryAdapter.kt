package com.anningtex.networkrequestlivedata.fragment.two.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.anningtex.networkrequestlivedata.R
import com.anningtex.networkrequestlivedata.fragment.two.entity.CountryEntity
import kotlinx.android.synthetic.main.item_pop_list.view.*

/**
 * @Author Song
 * @Date：2023-03-13
 */
class CountryAdapter(countryEntity: List<CountryEntity>, mContext: Context) : BaseAdapter() {
    var countryList: List<CountryEntity>? = null
    var mContext: Context
    var mViewHolder: MyViewHolder? = null

    init {
        countryList = countryEntity
        this.mContext = mContext
    }

    override fun getCount(): Int {
        return countryList?.size ?: 0
    }

    override fun getItem(p0: Int): Any {
        return countryList!![p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var mConvertView = p1
        if (p1 == null) {
            mConvertView = LayoutInflater.from(mContext).inflate(R.layout.item_pop_list, null)
            mViewHolder = mConvertView?.let { MyViewHolder(it) }
            mConvertView!!.tag = mViewHolder
        } else {
            mViewHolder = p1.tag as MyViewHolder
        }
        mViewHolder!!.mContentTv!!.text = countryList!![p0].countryName
        return mConvertView!!
    }

    inner class MyViewHolder(view: View) {
        var mContentTv: TextView? = null

        init {
            mContentTv = view.tv_pop
        }
    }
}
