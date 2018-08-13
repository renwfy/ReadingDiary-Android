package com.renwfy.readingdiary.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.renwfy.readingdiary.R
import com.renwfy.readingdiary.activity.DiaryDetailsActivity
import com.renwfy.readingdiary.model.LessonEntity
import java.util.*

/**
 * Created by LSD on 16/6/7.
 */
class DairyRecyclerAdapter(private val context: Context) : RecyclerView.Adapter<DairyRecyclerAdapter.MViewHolder>() {
    private var list: List<LessonEntity>? = null

    fun setData(list: List<LessonEntity>) {
        this.list = list
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MViewHolder {
        return MViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_m_dairy_subitem, parent, false))
    }

    override fun onBindViewHolder(holder: MViewHolder, position: Int) {
        val ety = getItem(position) as LessonEntity?
        if (ety != null) {
            holder.setData(position, ety)
        }
    }

    override fun getItemCount(): Int {
        return if (list == null) 0 else list!!.size
    }

    fun getItem(position: Int): Any? {
        return if (list == null) null else list!![position]
    }

    open inner class MViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        lateinit var view: View
        @BindView(R.id.tvDay)
        lateinit var tvDay: TextView
        @BindView(R.id.tvTitle)
        lateinit var tvTitle: TextView
        @BindView(R.id.dirLeft)
        lateinit var dirLeft: View

        init {
            this.view = view
            ButterKnife.bind(this, view)
        }

        fun setData(position: Int, data: LessonEntity) {
            if (0 == position) {
                dirLeft.visibility = View.VISIBLE
            } else {
                dirLeft.visibility = View.GONE
            }
            var dayString = "${data.date_by_day}"
            dayString = dayString.substring(6)
            var day = dayString.toInt()
            tvDay.text = "$day"
            tvTitle.text = data.title
            view.setOnClickListener {
                context.startActivity(Intent(context, DiaryDetailsActivity::class.java).putExtra("entity",data))
            }
        }
    }
}
