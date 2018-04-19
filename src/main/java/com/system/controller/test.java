package com.system.controller;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * Created by wlh on 2018/4/19.
 */
public class test {
    public static void main(String[] args) {

        Md5Hash md5Hash=new Md5Hash("admin","admin",3);
        System.out.println(md5Hash);
    }
}
