package com.renwfy.readingdiary.view.dialog

import android.content.Context
import android.graphics.Bitmap
import butterknife.OnClick
import com.renwfy.readingdiary.R
import com.renwfy.readingdiary.utils.BitmapUitl
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX
import com.tencent.mm.opensdk.modelmsg.WXImageObject
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage
import com.tencent.mm.opensdk.openapi.WXAPIFactory

class ShareDialog(context: Context, var bitmap: Bitmap) : AbsDialog(context, R.layout.dialog_share, R.style.shareInDialog) {

    @OnClick(R.id.item_wechat)
    fun shareToWeixincircle() {
        val imgObj = WXImageObject(bitmap)

        val msg = WXMediaMessage()
        msg.mediaObject = imgObj

        val thumbBmp = Bitmap.createScaledBitmap(bitmap, 150, 150, true)
        bitmap.recycle()
        msg.thumbData = BitmapUitl.bmpToByteArray(thumbBmp, true)

        val req = SendMessageToWX.Req()
        req.transaction = buildTransaction("img")
        req.message = msg
        req.scene = SendMessageToWX.Req.WXSceneTimeline
        var api = WXAPIFactory.createWXAPI(context, "APP_ID")
        api.sendReq(req)
    }

    @OnClick(R.id.item_wxcircle)
    fun shareToWechat() {
        val imgObj = WXImageObject(bitmap)

        val msg = WXMediaMessage()
        msg.mediaObject = imgObj

        val thumbBmp = Bitmap.createScaledBitmap(bitmap, 150, 150, true)
        bitmap.recycle()
        msg.thumbData = BitmapUitl.bmpToByteArray(thumbBmp, true)

        val req = SendMessageToWX.Req()
        req.transaction = buildTransaction("img")
        req.message = msg
        req.scene = SendMessageToWX.Req.WXSceneSession
        var api = WXAPIFactory.createWXAPI(context, "APP_ID")
        api.sendReq(req)
    }

    private fun buildTransaction(type: String?): String {
        return if (type == null) System.currentTimeMillis().toString() else type + System.currentTimeMillis()
    }


    @OnClick(R.id.ll_content, R.id.tv_cancle)
    fun doClose() {
        dismiss()
    }
}
