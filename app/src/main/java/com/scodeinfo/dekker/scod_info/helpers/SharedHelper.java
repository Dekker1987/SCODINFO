package com.scodeinfo.dekker.scod_info.helpers;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Sergii on 06.05.2018.
 */

public class SharedHelper {

    static SharedHelper instance;
    static SharedPreferences prefs;
    private static final String prefsName = "scode_ad_prefs";

   public void init(Context context){
        prefs = context.getSharedPreferences(prefsName, Context.MODE_PRIVATE);
    }

    public static SharedHelper getInstance(){
        if(instance==null){
            instance = new SharedHelper();
        }
        return instance;
    }

    public void setShareCounter(int shareCounter){
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("share_counter",shareCounter);
        editor.apply();
    }

    public int getShareCounter(){
        return prefs.getInt("share_counter",0);
    }

    public void setSCODEtouchCounter(int scodeTouchCounter){
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("scode_press_counter",scodeTouchCounter);
        editor.apply();
    }

    public int getSCODEtouchCounter(){
        return prefs.getInt("scode_press_counter",0);
    }

    public void resetCounters(){
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("share_counter",0);
        editor.putInt("scode_press_counter",0);
        editor.apply();
    }
}
