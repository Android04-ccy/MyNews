package com.example.administrator.mynews.utils;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2016/8/8.
 */
public class ImgagerGSON {

    /**
     * title : 放过我们吧！
     * thumburl : http://ww3.sinaimg.cn/large/bd759d6djw1f5e6g4j68kj20ft0azdgm.jpg
     * sourceurl : http://down.laifudao.com/images/tupian/2016626152033.jpg
     * height : 395
     * width : 569
     * class : 5
     * url : http://www.laifudao.com/tupian/59149.htm
     */

    private String title;
    private String thumburl;
    private String sourceurl;
    private String height;
    private String width;
    @SerializedName("class")
    private String classX;
    private String url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumburl() {
        return thumburl;
    }

    public void setThumburl(String thumburl) {
        this.thumburl = thumburl;
    }

    public String getSourceurl() {
        return sourceurl;
    }

    public void setSourceurl(String sourceurl) {
        this.sourceurl = sourceurl;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getClassX() {
        return classX;
    }

    public void setClassX(String classX) {
        this.classX = classX;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
