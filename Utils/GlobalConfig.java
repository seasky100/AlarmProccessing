package com.example.wun.alarmproccessing.Utils;

import android.content.res.Resources;

import com.example.wun.alarmproccessing.R;

/**
 * Created by WUN、 on 2018/4/21.
 */

public class GlobalConfig {
    public static String serviceURL = "";
    public static final String  URL="http://192.168.43.121:8080/FiveCrowdsourcing-Server/";//wch

    public static final String[] appTitle= Resources.getSystem().getStringArray(R.array.title);
    public static final int ALARM_SHOW=0;
    public static final int ALARM_FEEDBACK=1;

}
