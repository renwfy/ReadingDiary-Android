package com.renwfy.readingdiary;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;

import com.renwfy.readingdiary.activity.CommonActivity;
import com.renwfy.readingdiary.utils.TimerUtil;
import com.yarolegovich.slidingrootnav.SlideGravity;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;
import com.yarolegovich.slidingrootnav.callback.DragListener;
import com.yarolegovich.slidingrootnav.callback.DragStateListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by LSD on 2018/7/21.
 * Desc
 */
public class TestAcitivty extends CommonActivity {
    TimerUtil timerUtil = new TimerUtil(new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    });

    @Override
    protected int getContentView() {
        return R.layout.menu_drawer;
    }

    @Override
    protected void onViewCreated() {
        super.onViewCreated();

        Calendar calendar = Calendar.getInstance();
        new SlidingRootNavBuilder(this)
                .withMenuLayout(R.layout.menu_drawer)
                .withGravity(SlideGravity.RIGHT)
                .addDragListener(new DragListener() {
                    @Override
                    public void onDrag(float progress) {

                    }
                })
                .addDragStateListener(new DragStateListener() {
                    @Override
                    public void onDragStart() {

                    }

                    @Override
                    public void onDragEnd(boolean isMenuOpened) {

                    }
                })
                .inject();

    }

    private void cutDown() {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        try {
            int tSecond = 1000;
            int tMinute = 60 * tSecond;
            int tHour = 60 * tMinute;

            Date weeHour = format.parse("24:00:00");
            Date now = new Date();
            long time = weeHour.getTime() - now.getTime();
            long hour = time % tHour;
            long min = (time - hour * tHour) % tMinute;
            long second = (time - hour * tHour - min * tMinute) % tSecond;

            String txt = (hour > 9 ? "" + hour : "0" + hour) + (min > 9 ? "" + min : "0" + min) + (second > 9 ? "" + second : "0" + second);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        StringBuffer buffer = new StringBuffer();
        String str = "123456789";//定义的字符串
        for (int i = 0; i < str.length(); i++) {
            buffer.append(str.charAt(i));
        }

        str.replaceAll("133","11");

        String.valueOf(123);

        String dest = "";
        if (str!=null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }

        startActivity(new Intent(mActivity,MainActivity.class));


        String[] stringNames = {"零","一","二","三","四","五","六","七","八","九"};

        long abc = 3000;
        int tt = (int) (abc +1);

        List<String> aga = new ArrayList<>();

    }
}
