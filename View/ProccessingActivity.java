package com.example.wun.alarmproccessing.View;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.allen.library.SuperButton;
import com.allen.library.SuperTextView;
import com.bumptech.glide.Glide;
import com.example.wun.alarmproccessing.Presenter.ProccessingPresenter;
import com.example.wun.alarmproccessing.Utils.GlobalConfig;
import com.example.wun.alarmproccessing.Model.ListModel;
import com.example.wun.alarmproccessing.R;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.sql.Time;
import java.util.Date;
import java.util.List;


/**
 * Created by WUN、 on 2018/4/21.
 */

public class ProccessingActivity extends AppCompatActivity  {
    ImageView imageView;
    ImageView imageCamera;
    Toolbar toolbar;
    SuperTextView superTextView1;
    SuperTextView superTextView2;
    SuperTextView superTextView3;
    SuperButton superButton;
    TextView title;
    TextInputEditText inputEditText;
    LinearLayout proccessingShow;
    LinearLayout proccessingFeedback;
    ProccessingPresenter proccessingPresenter = new ProccessingPresenter();
    private int flag;
    private ListModel listModel ;
    List<Uri> mSelected;

    private static final int REQUEST_CODE_CHOOSE = 23;

    public ProccessingActivity() {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        flag = getIntent().getIntExtra("flag", 0);
        listModel=new ListModel(bundle.getString("type"),bundle.getString("address"),bundle.getString("time"),bundle.getBoolean("status"));
        setContentView(R.layout.activity_proccessing);
        proccessingShow = findViewById(R.id.include_show);
        proccessingFeedback = findViewById(R.id.include_feedback);
        initView(flag);
        init();
    }

    private void init() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //todo 这里应该根据不同用户，不同告警取得不同图片
        String imageUrl = proccessingPresenter.getImageUri();
        Glide.with(this).load(imageUrl).into(imageView);


        superButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (flag){
                    case GlobalConfig.ALARM_FEEDBACK: {
                        //todo 这里上传图片和时间,备注最好关联在USER中
                        proccessingPresenter.upload(mSelected.get(0),new Date(),ProccessingActivity.this);
                    }
                    case GlobalConfig.ALARM_SHOW: {
                        Intent intent = getIntent();
                        intent.putExtra("flag",GlobalConfig.ALARM_FEEDBACK);
                        overridePendingTransition(0, 0);
                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(intent);
                    }
                }
            }
        });
    }

    private void initView(int flag) {
        imageView = findViewById(R.id.proccessing_image);
        title = findViewById(R.id.title);
        superButton = findViewById(R.id.proccessing_action);
        toolbar=findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_action_back);

        switch (flag) {
            case GlobalConfig.ALARM_FEEDBACK: {
                proccessingShow.setVisibility(View.GONE);
                proccessingFeedback.setVisibility(View.VISIBLE);
                inputEditText = findViewById(R.id.feedback_input);
                superButton.setText(R.string.proccessing_submit);
                imageCamera=findViewById(R.id.proccessing_feedback_camera);
                toolbar.setTitle("处理反馈");
                imageCamera.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Matisse.from(ProccessingActivity.this)
                                .choose(MimeType.of(MimeType.JPEG,MimeType.PNG)) // 选择 mime 的类型
                                .countable(true)
                                .maxSelectable(1) // 图片选择的最多数量
                                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                                .thumbnailScale(0.85f) // 缩略图的比例
                                .capture(true) //是否提供拍照功能
                                .captureStrategy(new CaptureStrategy(true,"com.example.wun.alarmproccessing.fileprovider"))//存储到哪里
                                .imageEngine(new GlideEngine()) // 使用的图片加载引擎
                                .forResult(REQUEST_CODE_CHOOSE); // 设置作为标记的请求码



                    }
                });

                break;
            }
            case GlobalConfig.ALARM_SHOW: {
                proccessingShow.setVisibility(View.VISIBLE);
                proccessingFeedback.setVisibility(View.GONE);
                superButton.setText(R.string.proccessing_deal);

                imageView=findViewById(R.id.proccessing_image);
                superTextView1=findViewById(R.id.show_alarm_type);
                superTextView2=findViewById(R.id.show_alarm_address);
                superTextView3=findViewById(R.id.show_alarm_time);

                superTextView1.setCenterString(listModel.getAlarmType());
                superTextView2.setCenterString(listModel.getAddressInfo());
                superTextView3.setCenterString(listModel.getAlarmTime());
                toolbar.setTitle("告警信息");

                break;
            }
            default:
                break;
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            mSelected = Matisse.obtainResult(data);
            Log.d("Matisse", "mSelected: " + mSelected);
            imageCamera.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        }
    }



}
