package com.android.androiddialog.interfaces

import android.view.View

interface OnColorItemClickListener {
    fun setOnColorItemClick(view: View, color: Int, position: Int)
}