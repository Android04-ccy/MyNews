package com.example.administrator.mynews.common;

/**
 * Created by Administrator on 2016/8/8.
 */
public class CommonUtil {
   public  static  int PAGE_SIZE=20;
    public static String HOST = "http://c.m.163.com/";

    public static String END_URL = "/0-" + PAGE_SIZE + ".html";
    public static String END_DETAIL_URL = "/full.html";
    //头条
    public static final String TOP_URL = "nc/article/headline/";
    public static final String TOP_ID = "T1348647909107";
    //新闻详情
    public static final String NEW_DETAIL = "nc/article";
    public static final String COMMON_URL = "nc/article/list/";
   //汽车
   public static final String CAR_ID = "T1348654060988";
    //笑话
   public static final String JOKE_ID = "T1350383429665";
    //NBA
   public static final String NBA_ID = "T1348649145984";
    //图片
   public static final String IMAGES_URL = "http://api.laifudao.com/open/tupian.json";
    //天气预报
   public static final String WEATHER = "http://wthrcdn.etouch.cn/weather_mini?city=";



}
