package com.kosarict.mrs.tools;

import android.content.Context;
import android.content.Intent;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sadegh-Pc on 8/2/2016.
 */
public class PublicMethods {

    public static void navToPage(Context onePage, Class twoPage){
        Intent intent = new Intent(onePage, twoPage);
        onePage.startActivity(intent);
    }

    public static String getCurrentPersianDate(){
        PersianCalendar persianCalendar = new PersianCalendar();

        return number2farsi(persianCalendar.getIranianYear()) + "/"
                + formatNumber(persianCalendar.getIranianMonth()) + "/"
                + formatNumber(persianCalendar.getIranianDay());
    }

    public static String getCurrentTime(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        Calendar calendar = Calendar.getInstance();

        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        return formatNumber(hour) + ":" + formatNumber(minute);
    }

    public static String number2farsi(int i) {
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

    public static String formatNumber(int number){
        String numberFormated;

        if (number < 10){
            numberFormated = number2farsi(0) + number2farsi(number);
        }else{
            numberFormated = number2farsi(number);
        }

        return numberFormated;
    }
}
