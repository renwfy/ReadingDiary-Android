package com.renwfy.readingdiary.wxapi

import android.content.Intent
import com.renwfy.lib.utils.AppTips

import com.renwfy.readingdiary.activity.CommonActivity
import com.tencent.mm.opensdk.modelbase.BaseReq
import com.tencent.mm.opensdk.modelbase.BaseResp
import com.tencent.mm.opensdk.openapi.IWXAPI
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler
import com.tencent.mm.opensdk.openapi.WXAPIFactory

class WXEntryActivity : CommonActivity(), IWXAPIEventHandler {
    private var api: IWXAPI? = null

    override fun getContentView(): Int {
        return 0
    }

    override fun onViewCreated() {
        super.onViewCreated()
        api = WXAPIFactory.createWXAPI(this, "wxf03aa1d5c2536673", false)
        api!!.registerApp("wxf03aa1d5c2536673")

        try {
            api!!.handleIntent(intent, this)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)

        setIntent(intent)
        api!!.handleIntent(intent, this)
    }

    override fun onReq(req: BaseReq) {}

    override fun onResp(resp: BaseResp) {
        when (resp.errCode) {
            BaseResp.ErrCode.ERR_OK -> {
                AppTips.showToast("分享成功")
            }
            BaseResp.ErrCode.ERR_USER_CANCEL -> {
                AppTips.showToast("分享取消")
            }
            BaseResp.ErrCode.ERR_AUTH_DENIED -> {
                AppTips.showToast("分享失败")
            }
        }
        finish()
    }
}