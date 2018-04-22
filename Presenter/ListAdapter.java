package com.example.wun.alarmproccessing.Presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.allen.library.SuperTextView;
import com.example.wun.alarmproccessing.Utils.GlobalConfig;
import com.example.wun.alarmproccessing.Model.ListModel;
import com.example.wun.alarmproccessing.R;
import com.example.wun.alarmproccessing.View.ProccessingActivity;

import java.util.List;

/**
 * Created by WUN、 on 2018/4/22.
 */

public  class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder>{
    private List<ListModel> list ;
    private Context mContext;

    public ListAdapter(Context context,List<ListModel> list) {
        this.list = list;
        this.mContext=context;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        SuperTextView stv;
        public ViewHolder(View itemView) {
            super(itemView);
            stv= itemView.findViewById(R.id.list_stv);
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item ,parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        ListModel lm = list.get(position);
        boolean status =lm.isProcessing_status();
        holder.stv.setCenterBottomString(lm.getAlarmTime());
        holder.stv.setCenterTopString(lm.getAlarmType());
        holder.stv.setCenterString(lm.getAddressInfo());
        holder.stv.setRightString(status?"已处理":"未处理");
        if(!status)
            holder.stv.setRightTextColor(mContext.getResources().getColor(R.color.colorAccent));
        holder.stv.setOnSuperTextViewClickListener(new SuperTextView.OnSuperTextViewClickListener() {
            @Override
            public void onClickListener(SuperTextView superTextView) {
                Bundle mBundle = new Bundle();
                Intent intent =new Intent(mContext, ProccessingActivity.class);
                intent.putExtra("flag", !status?GlobalConfig.ALARM_SHOW:GlobalConfig.ALARM_FEEDBACK);
                mBundle.putString("time", lm.getAlarmTime());
                mBundle.putString("address", lm.getAddressInfo());
                mBundle.putString("type", lm.getAlarmType());
                mBundle.putBoolean("status",lm.isProcessing_status());
                intent.putExtras(mBundle);
                mContext.startActivity(intent);

            }
        });
    }
}
