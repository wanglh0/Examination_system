package com.system.controller.mobile;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.StringUtils;

import java.util.Date;

/**
 * Created by wlh on 2018/4/26.
 */
public class WeChatUtil {
    //URL验证使用的token
    public static final String TOKEN="wlh";

    public static final String APPID="wx1f862281fc8d754d";
    public static final String SECRET="c0b1e6cb9f2843e7bde8b12b9064080b";

    //创建菜单的地址
    public static final String CREATE_MENU_URL="https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
    //获取菜单的地址
    public static final String GET_MENU_URL="https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";
    //删除菜单的地址
    public static final String DELETE_MENU_URL="https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";

    //获取access_token的地址
    public static final String GET_ACCESSTOKEN_URL="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";


    private static String accessToken;
    private static Long expiresTime;
    /**
     * 获取access_token
     * @return
     */
    public static String getAccessToken(){
        JSONObject jsonObject = HttpClientUtils.httpGet(GET_ACCESSTOKEN_URL.replace("APPID", APPID).replace("APPSECRET", SECRET));

        if(accessToken==null||expiresTime<System.currentTimeMillis()){
            accessToken = jsonObject.getString("access_token");
            Long expires_in = jsonObject.getLong("expires_in");
            expiresTime=System.currentTimeMillis()+(expires_in-60)*1000;
            System.out.println(expiresTime);
        }
        return accessToken;
    }

    /**
     * 创建菜单
     * @param menu
     */
    public static void creatMenu(String menu){
        JSONObject result = HttpClientUtils.httpPost(CREATE_MENU_URL.replace("ACCESS_TOKEN", getAccessToken()), menu);
        System.out.println(result);
    }

    /**
     * 获取自定义菜单
     */
    public static void getMenu(){
        JSONObject menu = HttpClientUtils.httpGet(GET_MENU_URL.replace("ACCESS_TOKEN", getAccessToken()));
        System.out.println(menu);
    }

    /**
     * 删除自定义菜单
     */
    public static void delMenu(){
        JSONObject result = HttpClientUtils.httpGet(DELETE_MENU_URL.replace("ACCESS_TOKEN", getAccessToken()));
        System.out.println(result);
    }

    public static void main(String[] args) {
//        delMenu();
        getMenu();
//        getAccessToken();
        /*creatMenu("{\n" +
                "     \"button\":[\n" +
                "     {    \n" +
                "          \"type\":\"click\",\n" +
                "          \"name\":\"开班信息\",\n" +
                "          \"key\":\"classinfo\"\n" +
                "      },\n" +
                "     {    \n" +
                "          \"type\":\"click\",\n" +
                "          \"name\":\"校区地址\",\n" +
                "          \"key\":\"address\"\n" +
                "      },\n" +
                "      {\n" +
                "           \"name\":\"学科介绍\",\n" +
                "           \"sub_button\":[\n" +
                "           {    \n" +
                "               \"type\":\"view\",\n" +
                "               \"name\":\"Java课程\",\n" +
                "               \"url\":\"http://www.wolfcode.cn/zt/java/index.html\"\n" +
                "            },\n" +
                "           {    \n" +
                "               \"type\":\"view\",\n" +
                "               \"name\":\"Python课程\",\n" +
                "               \"url\":\"http://www.wolfcode.cn/zt/python/index.html\"\n" +
                "            }]\n" +
                "       }]\n" +
                " }\n" +
                "\n" );*/
    }

}
