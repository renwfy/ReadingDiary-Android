package com.renwfy.lib.adapter

import android.content.Context
import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import butterknife.ButterKnife
import java.util.*

open abstract class EasyAdapter<T> : BaseAdapter {
    protected var context: Context
    protected var list: MutableList<T>? = null

    constructor(context: Context) {
        this.context = context
    }

    constructor(context: Context, data: MutableList<T>) {
        this.context = context
        list = data
    }

    fun setData(data: MutableList<T>) {
        this.list = data
        this.notifyDataSetChanged()
    }

    fun addData(data: List<T>?) {
        if (this.list == null) {
            this.list = ArrayList()
        }
        if (data != null) {
            this.list!!.addAll(data)
            this.notifyDataSetChanged()
        }
    }

    override fun getCount(): Int {
        return if (list == null) 0 else list!!.size
    }

    override fun getItem(position: Int): T? {
        return if (list == null) null else list!![position]
    }

    override fun getItemId(position: Int): Long {
        return (if (list == null) 0 else position).toLong()
    }

    override fun getView(position: Int, view: View?, viewGroup: ViewGroup): View {
        var convertView = view
        val holder: EasyHolder
        if (convertView == null) {
            holder = getHolder(getItemViewType(position))
            convertView = holder.convertView
            convertView!!.tag = holder
        } else {
            holder = convertView.tag as EasyAdapter<T>.EasyHolder
        }
        holder.setData(position, getItem(position))
        return convertView
    }

    abstract fun getHolder(type: Int): EasyHolder

    open abstract inner class EasyHolder(context: Context) {
        var convertView: View

        @get:LayoutRes
        abstract val layout: Int

        init {
            convertView = LayoutInflater.from(context).inflate(layout, null)
            ButterKnife.bind(this, convertView)
        }

        abstract fun setData(position: Int, data: T?)
    }
}
