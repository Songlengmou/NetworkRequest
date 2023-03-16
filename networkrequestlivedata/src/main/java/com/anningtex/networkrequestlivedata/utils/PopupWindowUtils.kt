package com.anningtex.networkrequestlivedata.utils

import android.content.Context
import android.graphics.Rect
import android.widget.PopupWindow
import android.os.Build
import android.view.View

/**
 * @author Song
 */
class PopupWindowUtils(context: Context?) : PopupWindow(context, null) {
    override fun showAsDropDown(anchor: View) {
        if (Build.VERSION.SDK_INT == 24) {
            val rect = Rect()
            anchor.getGlobalVisibleRect(rect)
            val h = anchor.resources.displayMetrics.heightPixels - rect.bottom
            height = h
        }
        super.showAsDropDown(anchor)
    }
}