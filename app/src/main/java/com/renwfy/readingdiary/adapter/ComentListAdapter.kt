package com.renwfy.readingdiary.adapter

import android.content.Context
import android.text.TextUtils
import android.widget.TextView
import butterknife.BindView
import butterknife.OnClick
import com.renwfy.lib.adapter.EasyAdapter
import com.renwfy.lib.net.NSCallback
import com.renwfy.readingdiary.IAppliction
import com.renwfy.readingdiary.R
import com.renwfy.readingdiary.api.Api
import com.renwfy.readingdiary.model.CommentEntity
import com.renwfy.readingdiary.model.SuccessComm
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

/**
 * Created by LSD on 2018/9/14.
 * Desc
 */
class ComentListAdapter(context: Context) : EasyAdapter<CommentEntity>(context) {
    override fun getHolder(type: Int): EasyHolder {
        return ViewHolder()
    }

    inner class ViewHolder : EasyHolder() {
        @BindView(R.id.avstar)
        lateinit var avstar: CircleImageView
        @BindView(R.id.tvNickName)
        lateinit var tvNickName: TextView
        @BindView(R.id.tvContent)
        lateinit var tvContent: TextView
        @BindView(R.id.tvMsg)
        lateinit var tvMsg: TextView
        @BindView(R.id.tvLike)
        lateinit var tvLike: TextView

        lateinit var data: CommentEntity
        var isLike = false;

        override fun setData(position: Int, data: CommentEntity?) {
            if (data != null) {
                this.data = data
                if(!TextUtils.isEmpty(data.user!!.avatar)){
                    Picasso.with(context).load(data.user!!.avatar).into(avstar)
                }
                tvNickName.text = data.user!!.nickname
                tvContent.text = data.content
                tvMsg.text = "${data.sub_comment_count}"
                tvLike.text = "${data.like_count}"
                setDrawable(data.my_like)
            }
        }

        override fun getLayout(): Int {
            return R.layout.layout_comment_item
        }

        private fun setDrawable(isLike: Boolean) {
            this.isLike = isLike
            var drawable = context.resources.getDrawable(R.drawable.ic_like_s1)
            if (isLike) {
                drawable = context.resources.getDrawable(R.drawable.ic_like_s2)
            }
            drawable.setBounds(0, 0, drawable.minimumWidth, drawable.minimumHeight)
            tvLike.setCompoundDrawables(drawable, null, null, null)
        }

        @OnClick(R.id.tvLike)
        fun like() {
            Api.comentLike(IAppliction.instance.getUser()!!.id!!, data.id, !isLike, object : NSCallback<SuccessComm>(context, SuccessComm::class.java) {
                override fun onSuccess(t: SuccessComm?) {
                    if (isLike) {
                        data.my_like = false
                        data.like_count = data.like_count - 1
                    } else {
                        data.my_like = true
                        data.like_count = data.like_count + 1
                    }
                    notifyDataSetChanged()
                }
            })
        }
    }
}