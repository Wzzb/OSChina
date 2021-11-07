package com.example.oschina.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class SpUtils {
    public static HashMap<Integer, Boolean> hashMap = new HashMap<Integer, Boolean>();
    public static void markReaded(int key, boolean value) {
        hashMap.put(key, value);
    }

    public static boolean checkReaded(int key) {
        Boolean aBoolean = hashMap.get(key);
        if (aBoolean == null){
            return false;
        }
        return aBoolean;
    }

    public static void saveReadState(Context context) {
        Set<String> newSet = new HashSet<>();
        Set<Integer> keys = hashMap.keySet();
        for (Integer key : keys
        ) {
            newSet.add(key + ":" + hashMap.get(key).toString());
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("history", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putStringSet("readList", newSet);
        edit.commit();
    }

    /**
     * id:true
     */
    public static void loadReadState(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("history", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        Set<String> readList = sharedPreferences.getStringSet("readList",new HashSet<String>());
        for (String read : readList
        ) {
            Log.d("Test",read);
            String[] split = read.split(":");
            int id = Integer.parseInt(split[0]);
            boolean readed = Boolean.parseBoolean(split[1]);
            hashMap.put(id, readed);
        }
    }
}
