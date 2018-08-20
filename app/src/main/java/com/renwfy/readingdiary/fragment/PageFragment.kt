package com.renwfy.readingdiary.fragment

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.TextUtils
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import butterknife.BindView
import butterknife.OnClick
import com.google.gson.Gson
import com.renwfy.lib.net.NSCallback
import com.renwfy.lib.utils.AppLog
import com.renwfy.readingdiary.R
import com.renwfy.readingdiary.api.Api
import com.renwfy.readingdiary.model.LessonEntity
import com.renwfy.readingdiary.model.StatsEntity
import com.renwfy.readingdiary.utils.ColorShades
import com.renwfy.readingdiary.utils.StringUtil
import com.renwfy.readingdiary.utils.ViewToImageUtil
import com.renwfy.readingdiary.view.ObservableScrollView
import com.renwfy.readingdiary.view.dialog.ShareDialog
import java.util.regex.Pattern

/**
 * Created by LSD on 2018/7/21.
 * Desc 内容页
 */
class PageFragment : NativeFragment() {
    @BindView(R.id.titleBar)
    lateinit var titleBar: RelativeLayout
    @BindView(R.id.tvTitle)
    lateinit var tvTitle: TextView
    @BindView(R.id.tvDate)
    lateinit var tvDate: TextView
    @BindView(R.id.llTitleRight)
    lateinit var llTitleRight: LinearLayout
    @BindView(R.id.scrollView)
    lateinit var scrollView: ObservableScrollView
    @BindView(R.id.rlCtitleView)
    lateinit var rlCtitleView: RelativeLayout
    @BindView(R.id.tvCdate)
    lateinit var tvCdate: TextView
    @BindView(R.id.tvCtitle)
    lateinit var tvCtitle: TextView
    @BindView(R.id.tvCcontent)
    lateinit var tvCcontent: TextView
    @BindView(R.id.tvArticleTitle)
    lateinit var tvArticleTitle: TextView
    @BindView(R.id.tvLike)
    lateinit var tvLike: TextView
    @BindView(R.id.tvReply)
    lateinit var tvReply: TextView
    @BindView(R.id.llBottomBar)
    lateinit var llBottomBar:LinearLayout

    internal var shades = ColorShades()

    var lesson: LessonEntity? = null

    companion object {
        fun getInstance(lesson: LessonEntity?): Fragment {
            val homePageFragment = PageFragment()
            val args = Bundle()
            args.putSerializable("lesson", lesson)
            homePageFragment.setArguments(args)
            return homePageFragment
        }
    }

    override fun onViewCreated() {
        super.onViewCreated()
        if (arguments != null) {
            lesson = arguments!!.getSerializable("lesson") as LessonEntity?
        }
        initView()
        setData()
        //getStatus()
    }

    override fun getContentView(): Int {
        return R.layout.fragment_page
    }

    fun initView() {
        scrollView.setOnScrollListener(object : ObservableScrollView.OnScrollListener {
            override fun onScrollStateChanged(view: ObservableScrollView?, scrollState: Int) {
            }

            override fun onScroll(view: ObservableScrollView?, isTouchScroll: Boolean, l: Int, t: Int, oldl: Int, oldt: Int) {
                val hight = 200
                if (t >= hight) {
                    llTitleRight.alpha = 1.0f
                    rlCtitleView.alpha = 0.0f
                    titleBar.setBackgroundColor(Color.parseColor("#efffffff"))
                } else {
                    llTitleRight.alpha = t / 200.0f
                    rlCtitleView.alpha = 1.0f - (t / 200.0f)
                    val offset = t / 200.0f
                    shades.setFromColor(Color.parseColor("#00ffffff")).setToColor(Color.parseColor("#efffffff")).setShade(offset)
                    titleBar.setBackgroundColor(shades.generate())
                }
            }
        })
    }

    fun setData() {
        if (lesson != null) {
            val dateString = StringUtil.toDateStringCN(lesson!!.date_by_day.toString())
            tvTitle.text = lesson!!.title
            tvDate.text = dateString
            tvCtitle.text = lesson!!.title
            tvCdate.text = dateString
            var content = lesson!!.article
            content = content.replace(" ".toRegex(), "\n")//换行
            tvCcontent.text = content
            tvArticleTitle.text = "《" + lesson!!.provenance + "》，" + if (lesson!!.author != null) lesson!!.author!!.name else ""
        }
    }

    fun getStatus() {
        if (lesson != null) {
            Api.getActivityStats(lesson!!.date_by_day.toString(), object : NSCallback<StatsEntity>(mContext, StatsEntity::class.java) {
                override fun onResponse(response: String?, id: Int) {
                    if (!TextUtils.isEmpty(response)) {
                        var t: StatsEntity = Gson().fromJson(response, StatsEntity::class.java)
                        tvLike.text = if (t.favourite_count > 0) "${t.favourite_count}" else ""
                        tvReply.text = if (t.comment_count > 0) "${t.comment_count}" else ""
                    }
                }
            })
        }
    }

    @OnClick(R.id.ivShare)
    fun share(){
        llBottomBar.visibility = View.INVISIBLE
        llTitleRight.alpha = 0.0f
        rlCtitleView.alpha = 1.0f
        var bitmap = ViewToImageUtil.scrollViewToBitmap(scrollView)
        ShareDialog(mContext,bitmap).show()
        llBottomBar.visibility = View.VISIBLE
        llTitleRight.alpha = 1.0f
        rlCtitleView.alpha = 0.0f
    }
}