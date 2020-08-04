package com.example.dimicapstone.mygl;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

public class Uplugin {

    private static Uplugin m_instance;
    public Context context;
    public static Uplugin instance(){

        if(m_instance==null){
         m_instance = new Uplugin();}
         return m_instance;
        }

private void setContext(Context ct){
        context=ct;
}

//갤러리 파트 제일중요

    private static void showGallery(Activity activity){
        Intent intent = new Intent(activity,gallery.class);
        activity.startActivity(intent);
    }

    private static void updateGallery(Activity activity){
        Intent intent = new Intent(activity,update.class);
        activity.startActivity(intent);
    }
}
