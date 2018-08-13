package com.renwfy.readingdiary.view.dialog

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater

import com.renwfy.readingdiary.R

import butterknife.ButterKnife

open class AbsDialog @JvmOverloads constructor(internal var context: Context, layout: Int, style: Int = R.style.dialog) : Dialog(context, style) {
    init {
        var view = LayoutInflater.from(context).inflate(layout, null)
        setContentView(view)
        ButterKnife.bind(this, view)
    }
}
