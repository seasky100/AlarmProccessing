package com.example.wun.alarmproccessing.View;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.example.wun.alarmproccessing.Model.ListModel;
import com.example.wun.alarmproccessing.Presenter.ListAdapter;
import com.example.wun.alarmproccessing.Presenter.ListPresenter;
import com.example.wun.alarmproccessing.R;

import java.util.List;

/**
 * Created by WUN、 on 2018/4/21.
 */

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{
    private TextView title;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeLayout;

    ListPresenter listPresenter = new ListPresenter(this);


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        initView();
    }


    private void initView() {
        title=findViewById(R.id.title);
        mRecyclerView=findViewById(R.id.list_recyclerview);

        mSwipeLayout=findViewById(R.id.list_swipeLayout);
        listPresenter.dispalyInitList();
        title.setText("消息列表");
        //findViewById(R.id.back_step_image).setVisibility(View.INVISIBLE);
       //Log.e("title",GlobalConfig.appTitle[0]);
        mSwipeLayout.setOnRefreshListener(this);



    }

    public void displayList(List<ListModel> list) {
        Log.e("Mainactivity","displayList");
        Log.e("Mainactivity list",list.toString());
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            mRecyclerView.setLayoutManager(layoutManager);
            ListAdapter adpater = new ListAdapter(this,list);
            mRecyclerView.setAdapter(adpater);

    }

    @Override
    public void onRefresh() {
        Log.e("Mainactivity","onRefresh");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               listPresenter.dispalyInitList();
                // 加载完数据设置为不刷新状态，将下拉进度收起来
                mSwipeLayout.setRefreshing(false);
            }
        }, 2000);
    }


}
