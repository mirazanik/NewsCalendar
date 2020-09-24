package com.faiqas.newscalendar.custom;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.faiqas.newscalendar.Article;
import com.faiqas.newscalendar.ArticleAdapter;
import com.faiqas.newscalendar.EnglishWeekBar;
import com.faiqas.newscalendar.R;
import com.faiqas.newscalendar.base.activity.BaseActivity;
import com.faiqas.newscalendar.group.GroupItemDecoration;
import com.faiqas.newscalendar.group.GroupRecyclerView;
import com.faiqas.newscalendar.meizu.MeizuWeekView;
import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarLayout;
import com.haibin.calendarview.CalendarView;

import java.util.HashMap;
import java.util.Map;

public class CustomActivity extends BaseActivity implements
        CalendarView.OnCalendarSelectListener,
        CalendarView.OnYearChangeListener,
        View.OnClickListener {

    TextView mTextMonthDay;

    TextView mTextYear;

    TextView mTextLunar;

    TextView mTextCurrentDay;

    CalendarView mCalendarView;

    RelativeLayout mRelativeTool;
    private int mYear;
    CalendarLayout mCalendarLayout;
    GroupRecyclerView mRecyclerView;

    public static void show(Context context) {
        context.startActivity(new Intent(context, CustomActivity.class));
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_custom;
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void initView() {
        setStatusBarDarkMode();
        mTextMonthDay = findViewById(R.id.tv_month_day);
        mTextYear = findViewById(R.id.tv_year);
        mTextLunar = findViewById(R.id.tv_lunar);
        mTextLunar.setVisibility(View.GONE);
        mRelativeTool = findViewById(R.id.rl_tool);
        mCalendarView =  findViewById(R.id.calendarView);

        mCalendarView.setMonthView(CustomMonthView.class);
        mCalendarView.setWeekView(MeizuWeekView.class);
        mCalendarView.setWeekBar(EnglishWeekBar.class);

        mTextCurrentDay =  findViewById(R.id.tv_current_day);
        mTextMonthDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mCalendarLayout.isExpand()) {
                    mCalendarLayout.expand();
                    return;
                }
                mCalendarView.showYearSelectLayout(mYear);
                mTextLunar.setVisibility(View.GONE);
                mTextYear.setVisibility(View.GONE);
                mTextMonthDay.setText(String.valueOf(mYear));
            }
        });
        findViewById(R.id.fl_current).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCalendarView.scrollToCurrent();
                //mCalendarView.addSchemeDate(getSchemeCalendar(2019, 6, 1, 0xFF40db25, "假"));
//                int year = 2019;
//                int month = 6;
//                Map<String, Calendar> map = new HashMap<>();
//                map.put(getSchemeCalendar(year, month, 3, 0xFF40db25, "假").toString(),
//                        getSchemeCalendar(year, month, 3, 0xFF40db25, "假"));
//                map.put(getSchemeCalendar(year, month, 6, 0xFFe69138, "事").toString(),
//                        getSchemeCalendar(year, month, 6, 0xFFe69138, "事"));
//                map.put(getSchemeCalendar(year, month, 9, 0xFFdf1356, "议").toString(),
//                        getSchemeCalendar(year, month, 9, 0xFFdf1356, "议"));
//                map.put(getSchemeCalendar(year, month, 13, 0xFFedc56d, "记").toString(),
//                        getSchemeCalendar(year, month, 13, 0xFFedc56d, "记"));
//                mCalendarView.addSchemeDate(map);
            }
        });
        mCalendarLayout = findViewById(R.id.calendarLayout);
        mCalendarView.setOnCalendarSelectListener(this);
        mCalendarView.setOnYearChangeListener(this);
        mTextYear.setText(String.valueOf(mCalendarView.getCurYear()));
        mYear = mCalendarView.getCurYear();
        mTextMonthDay.setText( mCalendarView.getCurDay() + "/"+mCalendarView.getCurMonth());
        //mTextLunar.setText("今日");
        mTextCurrentDay.setText(String.valueOf(mCalendarView.getCurDay()));
    }

    @Override
    protected void initData() {
        int year = mCalendarView.getCurYear();
        int month = mCalendarView.getCurMonth();

        Map<String, Calendar> map = new HashMap<>();
        map.put(getSchemeCalendar(year, month, 3, 0xFF40db25, "").toString(),
                getSchemeCalendar(year, month, 3, 0xFF40db25, "1"));
        map.put(getSchemeCalendar(year, month, 6, 0xFFe69138, "").toString(),
                getSchemeCalendar(year, month, 6, 0xFFe69138, "2"));
        map.put(getSchemeCalendar(year, month, 9, 0xFFdf1356, "").toString(),
                getSchemeCalendar(year, month, 9, 0xFFdf1356, "3"));
        map.put(getSchemeCalendar(year, month, 13, 0xFFedc56d, "").toString(),
                getSchemeCalendar(year, month, 13, 0xFFedc56d, "1"));
        map.put(getSchemeCalendar(year, month, 14, 0xFFedc56d, "").toString(),
                getSchemeCalendar(year, month, 14, 0xFFedc56d, "2"));
        map.put(getSchemeCalendar(year, month, 15, 0xFFaacc44, "").toString(),
                getSchemeCalendar(year, month, 15, 0xFFaacc44, "5"));
        map.put(getSchemeCalendar(year, month, 18, 0xFFbc13f0, "").toString(),
                getSchemeCalendar(year, month, 18, 0xFFbc13f0, "2"));
        map.put(getSchemeCalendar(year, month, 25, 0xFF13acf0, "").toString(),
                getSchemeCalendar(year, month, 25, 0xFF13acf0, "4"));
        map.put(getSchemeCalendar(year, month, 27, 0xFF13acf0, "").toString(),
                getSchemeCalendar(year, month, 27, 0xFF13acf0, "2"));
        //此方法在巨大的数据量上不影响遍历性能，推荐使用
        mCalendarView.setSchemeDate(map);


        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new GroupItemDecoration<String, Article>());
        mRecyclerView.setAdapter(new ArticleAdapter(this));
        mRecyclerView.notifyDataSetChanged();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.ll_flyme:
//                CustomActivity.show(this);
//                break;
//            case R.id.ll_simple:
//                SimpleActivity.show(this);
//                break;
//            case R.id.ll_colorful:
//                ColorfulActivity.show(this);
//                break;
//            case R.id.ll_index:
//                IndexActivity.show(this);
//                break;
        }
    }

    private Calendar getSchemeCalendar(int year, int month, int day, int color, String text) {
        Calendar calendar = new Calendar();
        calendar.setYear(year);
        calendar.setMonth(month);
        calendar.setDay(day);
        //calendar.setSchemeColor(color);//如果单独标记颜色、则会使用这个颜色
        //calendar.setScheme(text);
        //calendar.addScheme(new Calendar.Scheme());
        //calendar.addScheme(0xFF008800, "");
        //calendar.addScheme(0xFF008800, "");
        return calendar;
    }

    @Override
    public void onCalendarOutOfRange(Calendar calendar) {

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onCalendarSelect(Calendar calendar, boolean isClick) {
        mTextLunar.setVisibility(View.GONE);
        mTextYear.setVisibility(View.VISIBLE);
        mTextMonthDay.setText(calendar.getDay() + "/"+calendar.getMonth() );
        mTextYear.setText(String.valueOf(calendar.getYear()));
        mTextLunar.setText(calendar.getLunar());
        mYear = calendar.getYear();

        Log.e("onDateSelected", "  -- " + calendar.getYear() +
                "  --  " + calendar.getMonth() +
                "  -- " + calendar.getDay() +
                "  --  " + isClick + "  --   " + calendar.getScheme());
    }

    @Override
    public void onYearChange(int year) {
        mTextMonthDay.setText(String.valueOf(year));
    }


}
