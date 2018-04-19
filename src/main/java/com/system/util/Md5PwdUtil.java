package com.system.util;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * Created by wlh on 2018/4/19.
 */
public class Md5PwdUtil {
    private static final int hashIterations=3;

    public static String getMd5Passwd(String pwd,String salt,int hashIterations){
        Md5Hash md5Hash=new Md5Hash(pwd,salt,hashIterations);
        return md5Hash.toString();
    }
    public static String getMd5Passwd(String pwd,String salt){
        Md5Hash md5Hash=new Md5Hash(pwd,salt,hashIterations);
        return md5Hash.toString();
    }

    public static void main(String[] args) {

        Md5Hash md5Hash=new Md5Hash("admin","admin",3);
        System.out.println(md5Hash);
    }
}
