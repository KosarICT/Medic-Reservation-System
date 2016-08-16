package com.kosarict.mrs.view;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout.LayoutParams;
import android.widget.TextView;

import com.kosarict.mrs.tools.JalaliCalendar;
import com.kosarict.wheel.ArrayWheelAdapter;
import com.kosarict.wheel.OnWheelChangedListener;
import com.kosarict.wheel.OnWheelScrollListener;
import com.kosarict.wheel.WheelView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;



public class DatePickerDialog extends Dialog {

    private Context Mcontex;

    private int NoOfYear = 64;
    private JalaliCalendar Jalali;
    private String months[] = new String[]{"فروردین", "اردیبهشت", "خرداد",
            "تیر", " مرداد", "شهریور", "مهر", "آبان", "آذر",
            "دی", "بهمن", "اسفند"};

    private int curMonth;
    private String[] year_array;
    private String[] day_array;
    private Typeface typeface;

    public DatePickerDialog(final Context context, Calendar calendar, final DatePickerListner dtp) {

        super(context);
        Mcontex = context;
        typeface = Typeface.createFromAsset(context.getAssets(), "fonts/iransans.ttf");

        LinearLayout lytmain = new LinearLayout(Mcontex);
        lytmain.setOrientation(LinearLayout.VERTICAL);
        LinearLayout lyttitle = new LinearLayout(Mcontex);
        LinearLayout lytdate = new LinearLayout(Mcontex);
        LinearLayout lytbutton = new LinearLayout(Mcontex);


        Button btnset = new Button(Mcontex);
        btnset.setBackgroundColor(Color.parseColor("#1799ff"));
        btnset.setTextColor(Color.WHITE);


        Button btncancel = new Button(Mcontex);
        btncancel.setBackgroundColor(Color.parseColor("#1799ff"));
        btncancel.setTextColor(Color.WHITE);

        btnset.setText("انتخاب شود ");
        btnset.setTypeface(typeface);

        btncancel.setText("لغو");
        btncancel.setTypeface(typeface);

        final TextView lblTitle = new TextView(Mcontex);
        lblTitle.setGravity(Gravity.CENTER);
        lblTitle.setTextSize(20f);
        lblTitle.setTextColor(Color.WHITE);
        lblTitle.setTypeface(typeface);

        final WheelView month = new WheelView(Mcontex);
        final WheelView year = new WheelView(Mcontex);
        final WheelView day = new WheelView(Mcontex);


        requestWindowFeature(Window.FEATURE_NO_TITLE);


        lyttitle.addView(lblTitle, new LayoutParams(android.view.ViewGroup.LayoutParams.FILL_PARENT, 220));
        lyttitle.setBackgroundColor(Color.parseColor("#1799ff"));

        lytdate.addView(year, new LayoutParams(
                android.view.ViewGroup.LayoutParams.FILL_PARENT,
                android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 0.85f));


        lytdate.addView(month, new LayoutParams(
                android.view.ViewGroup.LayoutParams.FILL_PARENT,
                android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 0.7f));


        lytdate.addView(day, new LayoutParams(
                android.view.ViewGroup.LayoutParams.FILL_PARENT,
                android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 0.85f));


        LayoutParams btnSendLayoutParams = new LayoutParams(
                android.view.ViewGroup.LayoutParams.FILL_PARENT,
                android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
        btnSendLayoutParams.setMargins(0, 0, 10, 0);
        lytbutton.addView(btnset, btnSendLayoutParams);

        LayoutParams btnCancelLayoutParams = new LayoutParams(
                android.view.ViewGroup.LayoutParams.FILL_PARENT,
                android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
        btnCancelLayoutParams.setMargins(0, 0, 10, 0);
        lytbutton.addView(btncancel, btnCancelLayoutParams);


        lytbutton.setPadding(20, 20, 20, 20);
        lytmain.addView(lyttitle);
        lytmain.addView(lytdate);
        lytmain.addView(lytbutton);

        setContentView(lytmain);

        getWindow().setLayout(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);

        OnWheelChangedListener listener = new OnWheelChangedListener() {

            public void onChanged(WheelView wheel, int oldValue, int newValue) {


                updateDays(year, month, day);


            }
        };


        Jalali = new JalaliCalendar();


        // month
        curMonth = Jalali.get(Calendar.MONTH);


        month.setViewAdapter(new ArrayWheelAdapter<String>(context, months));


        month.setCurrentItem(curMonth);
        month.addChangingListener(listener);
        month.setScrollbarFadingEnabled(true);
        month.setCyclic(true);

        // year
        int curYear = Jalali.get(Calendar.YEAR);
        int Year = Jalali.get(Calendar.YEAR);


        ArrayList<String> years = new ArrayList<String>();

        int Yearstart = Year - NoOfYear;
        int Yearend = Year + NoOfYear;

        while (Yearstart < Yearend) {
            years.add(String.valueOf(number2farsi(Yearstart)));

            Yearstart++;
        }

        year_array = new String[years.size()];
        years.toArray(year_array);


        year.setViewAdapter(new ArrayWheelAdapter<String>(context, year_array));


        year.setCurrentItem(curYear - (Year - NoOfYear));
        year.addChangingListener(listener);


        // day
        updateDays(year, month, day);
        day.setCurrentItem(Jalali.get(Calendar.DAY_OF_MONTH) - 1);
        day.setCyclic(true);

        btnset.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                dtp.OnDoneButton(DatePickerDialog.this, number2farsi(Integer.parseInt(year_array[year.getCurrentItem()])) + "/"
                        + (formatNumber(month.getCurrentItem()+ 1)) + "/"
                        + formatNumber(Integer.parseInt(day_array[day.getCurrentItem()])));

            }
        });
        btncancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dtp.OnCancelButton(DatePickerDialog.this);

            }
        });

        day.addScrollingListener(new OnWheelScrollListener() {
            @Override
            public void onScrollingStarted(WheelView wheel) {
            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                lblTitle.setText(year_array[year.getCurrentItem()] + "/" + formatNumber(month.getCurrentItem()+ 1) + "/" + formatNumber(Integer.parseInt(day_array[day.getCurrentItem()])));
            }
        });

        month.addScrollingListener(new OnWheelScrollListener() {
            @Override
            public void onScrollingStarted(WheelView wheel) {
            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                lblTitle.setText(year_array[year.getCurrentItem()] + "/" + formatNumber(month.getCurrentItem()+ 1) + "/" + formatNumber(Integer.parseInt(day_array[day.getCurrentItem()])));
            }
        });

        year.addScrollingListener(new OnWheelScrollListener() {
            @Override
            public void onScrollingStarted(WheelView wheel) {
            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                lblTitle.setText(year_array[year.getCurrentItem()] + "/" + formatNumber(month.getCurrentItem()+ 1) + "/" + day_array[day.getCurrentItem()]);
            }
        });

        lblTitle.setText(year_array[year.getCurrentItem()] + "/" + formatNumber(month.getCurrentItem()+ 1) + "/" + day_array[day.getCurrentItem()]);
    }


    public void updateDays(WheelView year, WheelView month, WheelView day) {

        Jalali.set(Calendar.YEAR,
                Jalali.get(Calendar.YEAR) + (year.getCurrentItem() - NoOfYear));
        Jalali.set(Calendar.MONTH, month.getCurrentItem());
        curMonth = Jalali.get(Calendar.MONTH);
        int maxDays = JalaliCalendar.jalaliDaysInMonth[curMonth];

        boolean isLeapYear = ((Integer.valueOf(year_array[year.getCurrentItem()]) % 4 == 3) && (Integer.valueOf(year_array[year.getCurrentItem()]) % 100 != 3) || (Integer.valueOf(year_array[year.getCurrentItem()]) % 400 == 3));

        ArrayList<String> days = new ArrayList<String>();

        int firstday = 1;
        int lastday = maxDays;


        if (isLeapYear) {

            if (months[month.getCurrentItem()] == "اسفند") {
                lastday++;
            }

        }


        while (firstday <= lastday) {
            days.add(String.valueOf(number2farsi(firstday)));

            firstday++;
        }

        day_array = new String[days.size()];
        days.toArray(day_array);

        day.setViewAdapter(new ArrayWheelAdapter<String>(Mcontex, day_array));

        int curDay = Math.min(maxDays, day.getCurrentItem() + 1);
        day.setCurrentItem(curDay - 1, true);

    }


    public interface DatePickerListner {
        public void OnDoneButton(Dialog datedialog, String c);

        public void OnCancelButton(Dialog datedialog);

        //public void OnYearScrollChanged(TextView lblTitle, String data);
    }


    public String number2English(String i) {
        String b = i;

        Map<String, String> replaceMap = new HashMap<String, String>();
        replaceMap.put("۰", "0");
        replaceMap.put("۱", "1");
        replaceMap.put("۲", "2");
        replaceMap.put("۳", "3");
        replaceMap.put("۴", "4");
        replaceMap.put("۵", "5");
        replaceMap.put("۶", "6");
        replaceMap.put("۷", "7");
        replaceMap.put("۸", "8");
        replaceMap.put("۹", "9");

        for (Map.Entry<String, String> entry : replaceMap.entrySet()) {
            b = b.replaceAll(entry.getKey(), entry.getValue());
        }

        return b;
    }


    public String number2farsi(int i) {
        String b = Integer.toString(i);

        Map<String, String> replaceMap = new HashMap<String, String>();
        replaceMap.put("0", "۰");
        replaceMap.put("1", "۱");
        replaceMap.put("2", "۲");
        replaceMap.put("3", "۳");
        replaceMap.put("4", "۴");
        replaceMap.put("5", "۵");
        replaceMap.put("6", "۶");
        replaceMap.put("7", "۷");
        replaceMap.put("8", "۸");
        replaceMap.put("9", "۹");

        for (Map.Entry<String, String> entry : replaceMap.entrySet()) {
            b = b.replaceAll(entry.getKey(), entry.getValue());
        }

        return b;
    }

    private float convertPxToDp(int px) {
        Resources r = Mcontex.getResources();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 14, r.getDisplayMetrics());
    }

    private String dateString() {
        JalaliCalendar jalaliCalendar = new JalaliCalendar();
        int datOdWeek = jalaliCalendar.get(Calendar.DAY_OF_WEEK);
        String name = "";

        switch (datOdWeek) {
            case 1:
                name = "یکشنبه";
                break;
            case 2:
                name = "دوشنبه";
                break;
            case 3:
                name = "سه شنبه";
                break;
            case 4:
                name = "چهارشنبه";
                break;
            case 5:
                name = "پنجشنبه";
                break;
            case 6:
                name = "جمعه";
                break;
            case 7:
                name = "شنبه";
                break;
        }

        return name;
    }

    private String formatNumber(int number){
        String numberFormated;

        if (number < 10){
            numberFormated = number2farsi(0) + number2farsi(number);
        }else{
            numberFormated = number2farsi(number);
        }

        return numberFormated;
    }
}
