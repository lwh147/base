package com.lwh147.practice.bean;

import com.lwh147.practice.util.ObjectAnalyzer;

/**
 * @author lwh
 * @description 简单的用户类
 * @date 2020/11/3
 **/
public class User {
    public static String test = "test";
    private String name;
    private int age;
    private String sex;
    private String hobbies;
    private boolean online;
    private String profile;
    private String[] arr;


    public User(String name, int age, String sex, String hobbies, boolean online, String profile) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.hobbies = hobbies;
        this.online = online;
        this.profile = profile;
        this.arr = new String[2];
        this.arr[0] = "dsfa";
        this.arr[1] = "sdff";
    }

    public User() {

    }

    public static String getString() {
        return "dsfsad";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getHobbies() {
        return hobbies;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    @Override
    public String toString() {
        return new ObjectAnalyzer().toString(this);
    }
}
