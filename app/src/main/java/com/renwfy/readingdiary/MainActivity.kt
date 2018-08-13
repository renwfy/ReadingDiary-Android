package com.renwfy.readingdiary

import android.content.Intent
import android.os.Handler
import android.os.Message
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.orhanobut.hawk.Hawk
import com.renwfy.lib.utils.DensityUtil
import com.renwfy.readingdiary.activity.AboutActivity
import com.renwfy.readingdiary.activity.CommonActivity
import com.renwfy.readingdiary.activity.DiaryActivity
import com.renwfy.readingdiary.activity.DiaryNotificationActivity
import com.renwfy.readingdiary.adapter.HomePageAdapter
import com.renwfy.readingdiary.data.DataFactory
import com.renwfy.readingdiary.utils.TimerUtil
import com.renwfy.readingdiary.view.dialog.ICenterDialog
import com.yarolegovich.slidingrootnav.SlideGravity
import com.yarolegovich.slidingrootnav.SlidingRootNav
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder
import com.yarolegovich.slidingrootnav.callback.DragStateListener
import java.text.ParseException
import java.util.*


class MainActivity : CommonActivity() {
    @BindView(R.id.mViewPage)
    lateinit var mViewPage: ViewPager
    @BindView(R.id.mainContent)
    lateinit var mainContent: RelativeLayout

    lateinit var memuDrawer: MenuView
    lateinit var slidingRootNav: SlidingRootNav

    lateinit var floatView: FloatView

    lateinit var adapter: HomePageAdapter

    var screetW = 0

    //static
    companion object {
        var FRONT = false
    }

    override fun getContentView(): Int {
        return R.layout.activity_main
    }

    override fun onViewCreated() {
        super.onViewCreated()
        FRONT = true;
        initView()
        loadData()
    }

    override fun onDestroy() {
        super.onDestroy()
        FRONT = false;
    }

    override fun onBackPressed() {
        if (floatView.show) {
            floatView.hide()
        } else {
            super.onBackPressed()
        }
    }

    fun initView() {
        floatView = FloatView()
        screetW = DensityUtil.screenWidthInPx(mActivity)

        memuDrawer = MenuView()
        slidingRootNav = SlidingRootNavBuilder(this)
                .withMenuView(memuDrawer.contentView)
                .withGravity(SlideGravity.RIGHT)
                .withRootViewElevation(3)
                .withRootViewScale(0.8f)
                .withContentClickableWhenMenuOpened(false)
                .withDragDistance(DensityUtil.px2dip(mActivity, screetW * 7.5f / 10f))
                .addDragListener {
                    memuDrawer.updatePosition(it)
                }
                .addDragStateListener(object : DragStateListener {
                    override fun onDragEnd(isMenuOpened: Boolean) {
                        if (isMenuOpened) {
                            memuDrawer.startCutDown()
                        } else {
                            memuDrawer.stopCutDown()
                        }
                    }

                    override fun onDragStart() {
                    }
                })
                .inject()
        mViewPage.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                slidingRootNav.isMenuLocked = position < adapter.count - 1
            }
        })
        adapter = HomePageAdapter(mActivity.supportFragmentManager)
        mViewPage.adapter = adapter
    }

    fun loadData() {
        if (DataFactory.lessonData != null) {
            adapter.setData(DataFactory.lessonData!!)
            mViewPage.setCurrentItem(DataFactory.lessonData!!.size - 1, false)
            if (Hawk.get("firstLoad", true)) {
                slidingRootNav.openMenu()
                Hawk.put("firstLoad", false)
            }
        }
    }

    @OnClick(R.id.ivMoreMenu)
    fun showMenu() {
        floatView.show()
    }

    //菜单View
    inner class MenuView {
        var contentView: View
        @BindView(R.id.menuContent)
        lateinit var menuContent: LinearLayout
        @BindView(R.id.tvMenuMonth)
        lateinit var tvMenuMonth: TextView
        @BindView(R.id.tvMenuDay)
        lateinit var tvMenuDay: TextView
        @BindView(R.id.tvCounDown)
        lateinit var tvCounDown: TextView

        var timerUtil = TimerUtil(object : Handler() {
            override fun handleMessage(msg: Message) {
                updateCutDown()
                startCutDown()
            }
        })

        constructor() {
            contentView = LayoutInflater.from(mActivity).inflate(R.layout.menu_drawer, null)
            ButterKnife.bind(this, contentView)

            val calendar = Calendar.getInstance()
            val day = calendar.get(Calendar.DATE)
            calendar.set(Calendar.DATE, day + 1)
            var smonth = calendar.get(Calendar.MONTH) + 1
            var sday = calendar.get(Calendar.DAY_OF_MONTH)
            tvMenuMonth.text = if (smonth > 9) "$smonth" else "0$smonth"
            tvMenuDay.text = if (sday > 9) "$sday" else "0$sday"

            updateCutDown()
        }

        fun updatePosition(it: Float) {
            menuContent.x = screetW * (1 - it) + screetW / 3 * 1.4f
        }

        fun startCutDown() {
            timerUtil.startTimer(1000)
        }

        fun stopCutDown() {
            timerUtil.stopTimer()
        }

        private fun updateCutDown() {
            try {
                val tSecond = 1000
                val tMinute = 60 * tSecond
                val tHour = 60 * tMinute

                val weeHour = Date()
                weeHour.hours = 24
                weeHour.minutes = 0
                weeHour.seconds = 0

                val now = Date()
                val time = weeHour.time - now.time
                val hour = time / tHour
                val min = (time - hour * tHour) / tMinute
                val second = (time - hour * tHour - min * tMinute) / tSecond

                val txt = (if (hour > 9) "${hour}时 " else "0${hour}时 ") + (if (min > 9) "${min}分 " else "0${min}分 ") + if (second > 9) "${second}秒" else "0${second}秒"
                tvCounDown.text = txt
            } catch (e: ParseException) {
                e.printStackTrace()
            }
        }
    }

    //浮层View
    inner class FloatView {
        var contentView: View
        var show = false;

        init {
            contentView = LayoutInflater.from(mActivity).inflate(R.layout.dialog_icenter, null)
            ButterKnife.bind(this, contentView)
        }

        fun show() {
            show = true
            val LAYOUT_PARAMS = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            mainContent.addView(contentView, LAYOUT_PARAMS)
            val anim: Animation = AnimationUtils.loadAnimation(mActivity, R.anim.slide_in_up)
            contentView.startAnimation(anim)
        }

        @OnClick(R.id.ivCloseDialog)
        fun hide() {
            show = false
            val anim: Animation = AnimationUtils.loadAnimation(mActivity, R.anim.slide_out_down)
            contentView.startAnimation(anim)
            mainContent.removeView(contentView)
        }

        @OnClick(R.id.itemCalendar)
        fun itemCalendar() {
            startActivity(Intent(mActivity, DiaryActivity::class.java))
        }

        @OnClick(R.id.itemTip)
        fun itemTip() {
            startActivity(Intent(mActivity, DiaryNotificationActivity::class.java))
        }

        @OnClick(R.id.itemAbout)
        fun itemAbout() {
            startActivity(Intent(mActivity, AboutActivity::class.java))
        }
    }
}
