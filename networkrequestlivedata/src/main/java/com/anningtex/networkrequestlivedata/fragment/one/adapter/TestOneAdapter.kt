package com.anningtex.networkrequestlivedata.fragment.one.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.anningtex.networkrequestlivedata.R
import com.anningtex.networkrequestlivedata.base.BaseRecyclerViewAdapter
import com.anningtex.networkrequestlivedata.fragment.one.TestOneEntity
import com.syp.library.viewholder.BaseViewHolder

/**
 * @Author Song
 * @Date：2023-03-13
 */
class TestOneAdapter : BaseRecyclerViewAdapter<TestOneEntity?, BaseViewHolder?>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return BaseViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.adapter_test_one, parent, false)
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val entity: TestOneEntity? = data[position]
        holder.setText(R.id.tv_blno, entity?.bLNo)
        holder.setText(R.id.tv_inDate, entity?.inDate)
    }
}