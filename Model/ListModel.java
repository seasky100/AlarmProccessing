package com.example.wun.alarmproccessing.Model;

/**
 * Created by WUN、 on 2018/4/21.
 */

enum alarm {
    JDCWT("机动车停放"),LJDF("垃圾堆放"),CDJY("出店经营");
    java.lang.String alarm;
     alarm(java.lang.String alarm) {
        this.alarm=alarm;
    }

    public java.lang.String getAlarm() {
        return alarm;
    }

    public void setAlarm(java.lang.String alarm) {
        this.alarm = alarm;
    }

    public java.lang.String toString(){
        return this.alarm;
    }

}

public class ListModel {
    String alarmType ;  //告警类型
    java.lang.String addressInfo;     //点位信息
    java.lang.String alarmTime;   //告警时间
    boolean processing_status;  //告警状态

    public ListModel(String alarmType, java.lang.String addressInfo, java.lang.String alarmTime, boolean processing_status) {
        this.alarmType = alarmType;
        this.addressInfo = addressInfo;
        this.alarmTime = alarmTime;
        this.processing_status = processing_status;
    }

    public ListModel() {
    }

    public String getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(String alarmType) {
        this.alarmType = alarmType;
    }

    public java.lang.String getAddressInfo() {
        return addressInfo;
    }

    public void setAddressInfo(java.lang.String addressInfo) {
        this.addressInfo = addressInfo;
    }

    public java.lang.String getAlarmTime() {
        return alarmTime;
    }

    public void setAlarmTime(java.lang.String alarmTime) {
        this.alarmTime = alarmTime;
    }

    public boolean isProcessing_status() {
        return processing_status;
    }

    public void setProcessing_status(boolean processing_status) {
        this.processing_status = processing_status;
    }
}
