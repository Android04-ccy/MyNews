package com.example.administrator.mynews.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.webkit.WebView;
import android.widget.TextView;

import com.example.administrator.mynews.R;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.onekeyshare.OnekeyShareTheme;
import cn.sharesdk.sina.weibo.SinaWeibo;


public class ShowActivity extends AppCompatActivity {

    TextView tv;
    WebView web;
    private String mUrl;
    private String mTitle;
    private String mImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        web= (WebView) findViewById(R.id.show_wed);
        mUrl = getIntent().getStringExtra("url");
        mTitle = getIntent().getStringExtra("title");
        mImg = getIntent().getStringExtra("img");
            tv= (TextView) findViewById(R.id.show_tob_tv);
        //      Picasso.with(this).load(url).into(tv);
      //  Log.d("ShowActivity", "onCreate: "+url);
           web.loadUrl(mUrl);
        ShareSDK.initSDK(this);
            tv.setText(mTitle);
            web.getSettings().setUseWideViewPort(true);
            web.getSettings().setBuiltInZoomControls(true);
            web.getSettings().setJavaScriptEnabled(true);
            web.getSettings().setSupportZoom(true);
        FloatingActionButton   fb= (FloatingActionButton) findViewById(R.id.show_float);
        AlphaAnimation alpha=new AlphaAnimation(0.3f,0.3f);
        fb.setAnimation(alpha);
        alpha.setFillAfter(true);
        alpha.start();

        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShare();
             //   showShare(getApplicationContext(), null, false);
            }
        });



    }


    public void beak(View view) {
        finish();


    }



    private void showShare() {
        ShareSDK.initSDK(this);
        cn.sharesdk.onekeyshare.OnekeyShare oks = new cn.sharesdk.onekeyshare.OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        oks.setTheme(OnekeyShareTheme.CLASSIC);
// 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle(mTitle);
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl(mUrl);
        // text是分享文本，所有平台都需要这个字段
        //oks.setText(mUrl);
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
       // oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        oks.setImageUrl(mImg);

        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl(mUrl);
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        //oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(mUrl);
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl(mUrl);

// 启动分享GUI
        oks.show(this);
    }



    public static void showShare(Context context, String platformToShare, boolean showContentEdit) {
        OnekeyShare oks = new OnekeyShare();
        oks.setSilent(!showContentEdit);
        if (platformToShare != null) {
            oks.setPlatform(platformToShare);
        }
        //ShareSDK快捷分享提供两个界面第一个是九宫格 CLASSIC  第二个是SKYBLUE
         oks.setTheme(OnekeyShareTheme.CLASSIC);
        // 令编辑页面显示为Dialog模式
        oks.setDialogMode();
        // 在自动授权时可以禁用SSO方式
        oks.disableSSOWhenAuthorize();
        //oks.setAddress("12345678901"); //分享短信的号码和邮件的地址
        oks.setTitle("ShareSDK--Title");
        oks.setTitleUrl("http://mob.com");
        oks.setText("ShareSDK--文本");
        //oks.setImagePath("/sdcard/test-pic.jpg");  //分享sdcard目录下的图片
        oks.setImageUrl(randomPic()[0]);
        oks.setUrl("http://www.mob.com"); //微信不绕过审核分享链接
        //oks.setFilePath("/sdcard/test-pic.jpg");  //filePath是待分享应用程序的本地路劲，仅在微信（易信）好友和Dropbox中使用，否则可以不提供
        oks.setComment("分享"); //我对这条分享的评论，仅在人人网和QQ空间使用，否则可以不提供
        oks.setSite("ShareSDK");  //QZone分享完之后返回应用时提示框上显示的名称
        oks.setSiteUrl("http://mob.com");//QZone分享参数
        oks.setVenueName("ShareSDK");
        oks.setVenueDescription("This is a beautiful place!");
        // 将快捷分享的操作结果将通过OneKeyShareCallback回调
        //oks.setCallback(new OneKeyShareCallback());
        // 去自定义不同平台的字段内容
        //oks.setShareContentCustomizeCallback(new ShareContentCustomizeDemo());
        // 在九宫格设置自定义的图标
        Bitmap logo = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher);
        String label = "ShareSDK";
        View.OnClickListener listener = new View.OnClickListener() {
            public void onClick(View v) {

            }
        };
        oks.setCustomerLogo(logo, label, listener);

        // 为EditPage设置一个背景的View
       // oks.setEditPageBackground(getPage());
        // 隐藏九宫格中的新浪微博
         oks.addHiddenPlatform(SinaWeibo.NAME);

         String[] AVATARS = {
         		"http://99touxiang.com/public/upload/nvsheng/125/27-011820_433.jpg",
         		"http://img1.2345.com/duoteimg/qqTxImg/2012/04/09/13339485237265.jpg",
         		"http://diy.qqjay.com/u/files/2012/0523/f466c38e1c6c99ee2d6cd7746207a97a.jpg",
         		"http://diy.qqjay.com/u2/2013/0422/fadc08459b1ef5fc1ea6b5b8d22e44b4.jpg",
         		"http://img1.2345.com/duoteimg/qqTxImg/2012/04/09/13339510584349.jpg",
         		"http://diy.qqjay.com/u2/2013/0401/4355c29b30d295b26da6f242a65bcaad.jpg" };
         oks.setImageArray(AVATARS);              //腾讯微博和twitter用此方法分享多张图片，其他平台不可以

        // 启动分享
        oks.show(context);
    }

    public static String[] randomPic() {
        String url = "http://git.oschina.net/alexyu.yxj/MyTmpFiles/raw/master/kmk_pic_fld/";
        String urlSmall = "http://git.oschina.net/alexyu.yxj/MyTmpFiles/raw/master/kmk_pic_fld/small/";
        String[] pics = new String[] {
                "120.JPG",
                "127.JPG",
                "130.JPG",
                "18.JPG",
                "184.JPG",
                "22.JPG",
                "236.JPG",
                "237.JPG",
                "254.JPG",
                "255.JPG",
                "263.JPG",
                "265.JPG",
                "273.JPG",
                "37.JPG",
                "39.JPG",
                "IMG_2219.JPG",
                "IMG_2270.JPG",
                "IMG_2271.JPG",
                "IMG_2275.JPG",
                "107.JPG"
        };
        int index = (int) (System.currentTimeMillis() % pics.length);
        return new String[] {
                url + pics[index],
                urlSmall + pics[index]
        };
    }












}
