package com.example.wun.alarmproccessing.Presenter;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.example.wun.alarmproccessing.Utils.GlobalConfig;
import com.example.wun.alarmproccessing.Utils.UploadUtil;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by WUN、 on 2018/4/22.
 */

public class ProccessingPresenter {



    public String getImageUri() {
        //todo 这里直接用net的图片，应该应该根据传来的参数动态选取
        String url = "http://img1.dzwww.com:8080/tupian_pl/20150813/16/7858995348613407436.jpg";

        return url;
    }

    //todo 上传文件和时间
    public boolean upload(Uri uri, Date date, Context context){
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor actualimagecursor = context.getContentResolver().query(uri,proj,null,null,null);
        int actual_image_column_index = actualimagecursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        actualimagecursor.moveToFirst();
        String img_path = actualimagecursor.getString(actual_image_column_index);
        File file = new File(img_path);
        UploadUtil.uploadFile(file,GlobalConfig.URL);


        SimpleDateFormat sfd = new SimpleDateFormat("yyyyMMddhhmmss");// 这里我们可以指定可是 例如 MM-DD就是只显示月和日  。。。。

        String time = sfd.format(date); //转换完成的字符串 很简单

        return true;
    }


}
