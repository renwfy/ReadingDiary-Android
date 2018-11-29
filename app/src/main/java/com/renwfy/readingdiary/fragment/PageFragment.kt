package com.renwfy.readingdiary.fragment

import android.content.Intent
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
import com.renwfy.readingdiary.IAppliction
import com.renwfy.readingdiary.R
import com.renwfy.readingdiary.activity.LoginActivity
import com.renwfy.readingdiary.activity.ViewsActivity
import com.renwfy.readingdiary.api.Api
import com.renwfy.readingdiary.model.LessonEntity
import com.renwfy.readingdiary.model.LikeEntity
import com.renwfy.readingdiary.model.StatsEntity
import com.renwfy.readingdiary.model.SuccessComm
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
    lateinit var llBottomBar: LinearLayout

    internal var shades = ColorShades()

    var lesson: LessonEntity? = null
    var isLike = false

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
        getStatus()
    }

    override fun onResume() {
        super.onResume()
        if (IAppliction.isLogin()) {
            if (lesson != null) {
                getIsLike()
            }
        }
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

    private fun setDrawable(isLike: Boolean) {
        var drawable = resources.getDrawable(R.drawable.ic_like1)
        if (isLike) {
            drawable = resources.getDrawable(R.drawable.ic_like2)
        }
        drawable.setBounds(0, 0, drawable.minimumWidth, drawable.minimumHeight)
        tvLike.setCompoundDrawables(drawable, null, null, null)
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
                override fun onSuccess(t: StatsEntity?) {
                    if (t != null) {
                        tvLike.text = if (t.favourite_count > 0) "${t.favourite_count}" else ""
                        tvReply.text = if (t.comment_count > 0) "${t.comment_count}" else ""
                    }
                }
            })
        }
    }

    fun getIsLike() {
        Api.likeStatus(lesson!!.id, IAppliction.instance.getUser()!!.id!!, object : NSCallback<LikeEntity>(mContext, LikeEntity::class.java) {
            override fun onSuccess(t: LikeEntity?) {
                if (t != null) {
                    isLike = t.status
                    setDrawable(isLike)
                }
            }
        })
    }

    @OnClick(R.id.ivShare)
    fun share() {
        llBottomBar.visibility = View.INVISIBLE
        llTitleRight.alpha = 0.0f
        rlCtitleView.alpha = 1.0f
        var bitmap = ViewToImageUtil.scrollViewToBitmap(scrollView)
        ShareDialog(mContext, bitmap).show()
        llBottomBar.visibility = View.VISIBLE
        llTitleRight.alpha = 1.0f
        rlCtitleView.alpha = 0.0f
    }

    @OnClick(R.id.tvLike)
    fun like() {
        if (!IAppliction.isLogin()) {
            startActivity(Intent(mContext, LoginActivity::class.java))
            return
        }
        Api.doFavor(lesson!!.id, IAppliction.instance.getUser()!!.id!!, "${lesson!!.date_by_day}", "${!isLike}", object : NSCallback<LikeEntity>(mContext, LikeEntity::class.java) {
            override fun onSuccess(t: LikeEntity?) {
                if (t != null) {
                    isLike = t.status
                    setDrawable(isLike)
                    getStatus()
                }
            }
        })
    }

    @OnClick(R.id.tvReply)
    fun comment() {
        if (!IAppliction.isLogin()) {
            startActivity(Intent(mContext, LoginActivity::class.java))
            return
        }
        startActivity(Intent(mContext, ViewsActivity::class.java).putExtra("lesson", lesson));
    }
}