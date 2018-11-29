package com.renwfy.readingdiary.activity

import android.widget.TextView
import butterknife.BindView
import butterknife.OnClick
import com.renwfy.lib.net.NSCallback
import com.renwfy.readingdiary.IAppliction
import com.renwfy.readingdiary.R
import com.renwfy.readingdiary.adapter.ComentListAdapter
import com.renwfy.readingdiary.api.Api
import com.renwfy.readingdiary.model.CommentEntity
import com.renwfy.readingdiary.model.LessonEntity
import com.renwfy.readingdiary.view.AutoLoadListView


/**
 * Created by LSD on 2018/8/2.
 * Desc 观点评论
 */
class ViewsActivity : CommonActivity() {
    @BindView(R.id.tvTitle)
    lateinit var tvTitle: TextView
    @BindView(R.id.autoListView)
    lateinit var autoListView: AutoLoadListView

    var page = 0;
    val size = 40;
    var lesson: LessonEntity? = null
    lateinit var adapter: ComentListAdapter


    override fun getContentView(): Int {
        return R.layout.activity_views
    }

    override fun onViewCreated() {
        super.onViewCreated()
        init()
        loadData()
    }

    fun init() {
        lesson = intent.getSerializableExtra("lesson") as LessonEntity?
        adapter = ComentListAdapter(mActivity)
        autoListView.adapter = adapter
        autoListView.setOnLoadListener {
            page++
            loadData()
        }
    }

    fun loadData() {
        if (lesson == null) {
            return
        }
        tvTitle.text = lesson!!.title
        var start = page * size
        var userId = ""
        if (IAppliction.isLogin()) {
            userId = IAppliction.instance.getUser()!!.id!!
        }
        Api.comentList(userId, lesson!!.id, start, size, object : NSCallback<CommentEntity>(mActivity, CommentEntity::class.java) {
            override fun onSuccess(t: List<CommentEntity>, total: Int) {
                adapter.setData(t as MutableList<CommentEntity>)
                autoListView.onLoadMoreComplete()
                autoListView.isCanLoadMore = !(t.size < size)
            }
        })
    }

    @OnClick(R.id.ivEdit)
    fun edit() {
        finish()
    }

    @OnClick(R.id.ivBack)
    fun back() {
        finish()
    }
}