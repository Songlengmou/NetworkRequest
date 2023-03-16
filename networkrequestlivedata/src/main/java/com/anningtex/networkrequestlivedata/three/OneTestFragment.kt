package com.anningtex.networkrequestlivedata.three

import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.anningtex.networkrequestlivedata.R

/**
 * @Author Song
 * @Desc:
 * @Date：2023-03-14
 */
class OneTestFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return LayoutInflater.from(context).inflate(R.layout.fragment_test_one, container, false)
    }
}