package com.example.dimicapstone.mygl;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import com.unity3d.player.UnityPlayer;

import java.util.List;

//갤러리부분코드 작성 17:26
public class gallery extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        open();
    }

    //갤러리를 진짜로 띄우는 것
    private void open(){

        /* 되긴 됨 => 보는 거 외에 되는 거 없음
        new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        //startActivityForResult(intent,0); <- 경로불러올때? 원래 코드
        startActivity(intent);
        */

        /*오류
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivity(intent);*/

         /*오류
        Intent intent = getPackageManager().getLaunchIntentForPackage("com.funppy.mozzi_lavender.gallery3d");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);*/

        /* 되긴 됨 파란화면, 역시 보는 거 외에 되는 거 없음
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivity(intent);*/

        //성공!!!!!!!!!!!!!
        Intent intent = new Intent(getPackageManager().getLaunchIntentForPackage("com.sec.android.gallery3d"));
        startActivity(intent);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==0 && resultCode==RESULT_OK){

            Uri path = data.getData();
            String changeUri = abs_path(path);
            //abs_path의 의미 절대경로로 형변환을 시킨다.

            //유니티에 뿌려줄 파트 29:54
            UnityPlayer.UnitySendMessage("androidPlugin,","getImage",changeUri);
        }
        finish();   //이미지선택하면 유니티화면으로 돌아가는 코드
    }

    //절대경로로 바꿔주는 코드?
    private String abs_path(Uri uri){
        String[] m_data ={MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(uri,m_data,null,null,null);
        startManagingCursor(cursor);
        int columIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(columIndex);
    }

}
