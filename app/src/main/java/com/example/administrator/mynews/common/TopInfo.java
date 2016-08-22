package com.example.administrator.mynews.common;

/**
 * Created by Administrator on 2016/8/8.
 */
public class TopInfo {

    public String  img;
    public    String  text;
    public    String  ptime;
    public    String  sou;
    public   String   url;
    public TopInfo(String i,String t){
        img=i;
        text=t;


    }
    public TopInfo(String i,String t,String p,String s, String   u){
        img=i;
        text=t;
       ptime=p;
        sou=s;
        url=u;
    }

    public TopInfo(String i,String t,String p,String s){
        img=i;
        text=t;
        ptime=p;
        sou=s;

    }

}
