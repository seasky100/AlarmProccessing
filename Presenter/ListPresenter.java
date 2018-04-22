package com.example.wun.alarmproccessing.Presenter;

import com.example.wun.alarmproccessing.Utils.GlobalConfig;
import com.example.wun.alarmproccessing.Model.ListModel;
import com.example.wun.alarmproccessing.View.LoginActivity;
import com.example.wun.alarmproccessing.View.MainActivity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
/**
 * Created by WUN、 on 2018/4/22.
 */

public class ListPresenter {
    private MainActivity mainActivity;
    LoginActivity loginActivity;
    private String initservletName="DisplayGoodOrder";
    private String servletIP;
    private List<ListModel> list =new ArrayList<ListModel>();

    public ListPresenter(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void dispalyInitList() {
        servletIP= GlobalConfig.URL+initservletName;
        //sendRequestWithOkHttpInit(servletIP,user);
        //sendRequestWithOkHttpInit(servletIP);
        getData();
    }

    /**
     * 获取测试数据
     */
    private void getData() {
        for (int i = 0; i < 6; i++) {

            list.add(new ListModel("垃圾堆放","工大服务区",(new Date()).toString(),false));
        }
        mainActivity.displayList(list);
    }


    //todo 这里之后会根据传入的用户user，向服务器请求不同的数据
    private void sendRequestWithOkHttpInit(final String servletIP) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //转换成JSON格式
                    Gson gson = new Gson();
                    //String userdata = gson.toJson(user);//用户信息

//                    RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8")
//                            , userdata);
                    Request request = new Request.Builder().
                            url(servletIP).
//                            post(requestBody).
                            build();

                    OkHttpClient client = new OkHttpClient();
                    Response response = client.newCall(request).execute();

                    String jsonData= response.body().string().toString();
                    parseJSONWithJONObject(jsonData);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void parseJSONWithJONObject(String jsonData) {
        Gson gson = new Gson();
        list= gson.fromJson( jsonData, new TypeToken<List<ListModel>>(){}.getType());
       mainActivity.displayList(list);


    }
}
