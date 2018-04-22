package com.example.wun.alarmproccessing.Model;

/**
 * Created by WUN、 on 2018/4/22.
 */

public class User {

    //todo 目前还没有多用户判断，每个用户都显示全部信息
    String name;
    String email;
    String password;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
