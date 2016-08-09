package com.kosarict.mrs.model;

import android.content.Context;
import android.content.Intent;

/**
 * Created by Sadegh-Pc on 8/2/2016.
 */
public class PublicMethods {

    public static void navToPage(Context onePage, Class twoPage){
        Intent intent = new Intent(onePage, twoPage);
        onePage.startActivity(intent);
    }
}
