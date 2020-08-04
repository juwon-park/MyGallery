package com.example.dimicapstone.mygl;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import com.unity3d.player.UnityPlayer;

//갤러리부분코드 작성 17:26
public class gallery extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        open();
    }

    //갤러리를 진짜로 띄우는 것
    private void open(){

        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(intent,0);

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
