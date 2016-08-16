package com.kosarict.mrs.view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.kosarict.wheel.ArrayWheelAdapter;
import com.kosarict.wheel.OnWheelChangedListener;
import com.kosarict.wheel.OnWheelScrollListener;
import com.kosarict.wheel.WheelView;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by Sadegh-Pc on 2/10/2016.
 */
public class TimePickerDialog extends Dialog {

    private Context Mcontex;
    private Typeface typeface;

    private String minuteArray[] = new String[]{"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17",
            "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40",
            "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"};

    private String hourArray[] = new String[]{"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"};
    private Calendar calendar = Calendar.getInstance();

    public TimePickerDialog(final Context context, final DatePickerListner dtp) {

        super(context);
        Mcontex = context;
        typeface = Typeface.createFromAsset(context.getAssets(), "fonts/iransans.ttf");

        LinearLayout lytmain = new LinearLayout(Mcontex);
        lytmain.setOrientation(LinearLayout.VERTICAL);
        LinearLayout lyttitle = new LinearLayout(Mcontex);
        LinearLayout lyttime = new LinearLayout(Mcontex);
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

        final WheelView minute = new WheelView(Mcontex);
        final WheelView hour = new WheelView(Mcontex);


        requestWindowFeature(Window.FEATURE_NO_TITLE);


        lyttitle.addView(lblTitle, new TableLayout.LayoutParams(android.view.ViewGroup.LayoutParams.FILL_PARENT, 220));
        lyttitle.setBackgroundColor(Color.parseColor("#1799ff"));

        lyttime.addView(hour, new TableLayout.LayoutParams(
                android.view.ViewGroup.LayoutParams.FILL_PARENT,
                android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 0.85f));


        lyttime.addView(minute, new TableLayout.LayoutParams(
                android.view.ViewGroup.LayoutParams.FILL_PARENT,
                android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 0.8f));

        TableLayout.LayoutParams btnSendLayoutParams = new TableLayout.LayoutParams(
                android.view.ViewGroup.LayoutParams.FILL_PARENT,
                android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
        btnSendLayoutParams.setMargins(0, 0, 10, 0);
        lytbutton.addView(btnset, btnSendLayoutParams);

        TableLayout.LayoutParams btnCancelLayoutParams = new TableLayout.LayoutParams(
                android.view.ViewGroup.LayoutParams.FILL_PARENT,
                android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
        btnCancelLayoutParams.setMargins(10, 0, 0, 0);
        lytbutton.addView(btncancel, btnCancelLayoutParams);


        lytbutton.setPadding(20, 20, 20, 20);
        lytmain.addView(lyttitle);
        lytmain.addView(lyttime);
        lytmain.addView(lytbutton);

        setContentView(lytmain);

        getWindow().setLayout(TableLayout.LayoutParams.WRAP_CONTENT,
                TableLayout.LayoutParams.WRAP_CONTENT);

        OnWheelChangedListener listener = new OnWheelChangedListener() {

            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                //updateDays(hour, minute, day);
            }
        };

        minute.setViewAdapter(new ArrayWheelAdapter<String>(context, minuteArray));

        minute.setCurrentItem(calendar.get(Calendar.MINUTE));
        minute.addChangingListener(listener);
        minute.setScrollbarFadingEnabled(true);
        minute.setCyclic(true);


        hour.setViewAdapter(new ArrayWheelAdapter<String>(context, hourArray));


        hour.setCurrentItem(calendar.get(Calendar.HOUR_OF_DAY));
        hour.addChangingListener(listener);
        hour.setCyclic(true);


        btnset.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dtp.OnDoneButton(TimePickerDialog.this, formatNumber(Integer.parseInt(hourArray[hour.getCurrentItem()])),formatNumber(Integer.parseInt(minuteArray[minute.getCurrentItem()])));
            }
        });
        btncancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dtp.OnCancelButton(TimePickerDialog.this);
            }
        });

        minute.addScrollingListener(new OnWheelScrollListener() {
            @Override
            public void onScrollingStarted(WheelView wheel) {
            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                lblTitle.setText(formatNumber(Integer.parseInt(hourArray[hour.getCurrentItem()])) + ":" + formatNumber(Integer.parseInt(minuteArray[minute.getCurrentItem()])));
            }
        });

        hour.addScrollingListener(new OnWheelScrollListener() {
            @Override
            public void onScrollingStarted(WheelView wheel) {
            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                lblTitle.setText(formatNumber(Integer.parseInt(hourArray[hour.getCurrentItem()])) + ":" + formatNumber(Integer.parseInt(minuteArray[minute.getCurrentItem()])));
            }
        });

        lblTitle.setText(formatNumber(Integer.parseInt(hourArray[hour.getCurrentItem()])) + ":" + formatNumber(Integer.parseInt(minuteArray[minute.getCurrentItem()])));
    }


    public interface DatePickerListner {
        public void OnDoneButton(Dialog datedialog, String hour, String minute);

        public void OnCancelButton(Dialog datedialog);
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
