package com.example.steel.SharedPreferences;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by steel on 2017/6/13.
 */

public class SharedPreferencesUsage extends Activity {
    public final static String COLUMN_NAME = "name";
    public final static String COLUMN_MOBLIE = "mobile";
    SharedPreferencesHelper sp;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        sp = new SharedPreferencesHelper(this, "contacts");
        sp.putValue(COLUMN_NAME, "我爱故乡明月");
        sp.putValue(COLUMN_MOBLIE, "1234567890");

        String name = sp.getValue(COLUMN_NAME);
        String mobile = sp.getValue(COLUMN_MOBLIE);

        TextView textView = new TextView(this);
        textView.setText("NAME:"+name+"\n"+"MOBILE:"+mobile);
        setContentView(textView);
    }
}
