package com.lwh147.practice.bean;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;

/**
 * TODO
 *
 * @author lwh
 * @date 2022/5/16 16:11
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class SubUser extends User {
    private String name = "subUser";
    private String subName = "subname";

    public static void main(String[] args) {
        User user = new SubUser();
        System.out.println(user);
        {
        }
        new ArrayList<>().stream().filter(o -> false);
    }

    public void test() {
    }
}


