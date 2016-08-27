package com.myapp.glenwin.sproouut.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Glenwin18 on 7/11/2016.
 */
public class Preferences {
    private static SharedPreferences prefs;
    private static final String storage = "configs";


    public static void setTreePoints(Context context, int value){
        prefs = context.getSharedPreferences(storage, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("tree_points", 0);
        editor.apply();
    }
    public static int getTreePoints(Context context){
        prefs = context.getSharedPreferences(storage, Context.MODE_PRIVATE);
        return prefs.getInt("tree_points", 20);
    }
    public static void putDefaultPoints(Context context){
        prefs = context.getSharedPreferences(storage, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("tree_points",20);
        editor.apply();
    }
    public static boolean ifHasPoints(Context context){
        prefs = context.getSharedPreferences(storage, Context.MODE_PRIVATE);
        int points = prefs.getInt("tree_points",20);
        return points >= 20 ? true : false;
    }

}
