package com.example.administrator.mynews.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.mynews.R;
import com.example.administrator.mynews.adapter.FragmentAdapter;
import com.example.administrator.mynews.adapter.MainTopAdapter;
import com.example.administrator.mynews.common.FragTofa;
import com.example.administrator.mynews.common.RecyclerViewClickListener;
import com.example.administrator.mynews.view.AdMobBannerSizesFragment;
import com.example.administrator.mynews.view.CarFragment;
import com.example.administrator.mynews.view.ImagerFragment;
import com.example.administrator.mynews.view.JokeFragment;
import com.example.administrator.mynews.view.MainFragment;
import com.example.administrator.mynews.view.NBAFragment;
import com.example.administrator.mynews.view.NawsFragment;
import com.example.administrator.mynews.view.RigthFragment;
import com.example.administrator.mynews.view.TopFragment;
import com.squareup.okhttp.internal.Platform;
import com.squareup.picasso.Picasso;

import net.youmi.android.AdManager;

import java.util.ArrayList;
import java.util.HashMap;

import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,NavigationView.OnNavigationItemSelectedListener,PlatformActionListener {

    ImageView rigth_im;
    TextView rigth_tv;
    RecyclerView rv_top;
    String[]  strings={"头条","NBA","汽车","笑话","图片","新闻"};
    ViewPager vp;
    ArrayList<Fragment> mfrag=new ArrayList<>();

    FloatingActionButton mFloat;
    public  static  TextView main_tob_tv;
    ArrayList<String> mlist;

    DrawerLayout mDrawerLayout;
    NavigationView  mNavi_rigth;
    NavigationView  mNavi_legth;
    ActionBarDrawerToggle mToggle;
        ImageButton  ib_weixin;
        ImageButton  ib_qq;
        ImageButton  ib_xinlang;
        ImageButton  ib_renren;
      LinearLayout  rigth_line;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AdManager.getInstance(this).init("66e129f7ce6651c7","931f408fd03e933a",true , true);
        mDrawerLayout= (DrawerLayout) findViewById(R.id.rigth_draw);
        mToggle=new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
      //  main_tob_tv= (TextView) findViewById(R.id.tob_tv);
        mNavi_rigth= (NavigationView) findViewById(R.id.nav_rigth);
        mNavi_legth= (NavigationView) findViewById(R.id.nav_legth);

         mNavi_rigth.setNavigationItemSelectedListener( this);
         mNavi_legth.setNavigationItemSelectedListener( this);

        View view =  mNavi_rigth.getHeaderView(0);
         naviRight(view);
        ShareSDK.initSDK(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_rela, new MainFragment()).commit();
        rv_top= (RecyclerView) findViewById(R.id.main_top_recy);
        vp= (ViewPager) findViewById(R.id.main_fra);
        mFloat= (FloatingActionButton) findViewById(R.id.main_float);
       //float1();
     //   viewpager();

        try {
            Thread.sleep(3000);
            new MainFragment().itemShow(0);











        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    public void naviRight(View view){
        rigth_line= (LinearLayout) view.findViewById(R.id.rigth_line);
        rigth_tv= (TextView) view.findViewById(R.id.rigth_tv);
        rigth_im= (ImageView) view.findViewById(R.id.rigth_im);
        rigth_line.setOnClickListener(this);
        rigth_tv.setOnClickListener(this);
        rigth_im.setOnClickListener(this);
        ib_weixin= (ImageButton)  view.findViewById(R.id.weixin);
        ib_qq= (ImageButton)  view.findViewById(R.id.qq);
        ib_xinlang= (ImageButton)  view.findViewById(R.id.xinlang);
        ib_renren= (ImageButton)  view.findViewById(R.id.renren);
        ib_renren.setOnClickListener(this);
        ib_qq.setOnClickListener(this);
        ib_weixin.setOnClickListener(this);
        ib_xinlang.setOnClickListener(this);


    }

      public  void reay(){

          mlist=new ArrayList<>();
          for (int i = 0; i < strings.length; i++) {

              mlist.add(strings[i]);
          }
          RecyclerView.LayoutManager  manager= new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
          rv_top.setLayoutManager(manager);

          rv_top.setAdapter(new MainTopAdapter(this,mlist));



          rv_top.addOnItemTouchListener(new RecyclerViewClickListener(this, new RecyclerViewClickListener.OnItemClickListener() {
              @Override
              public void onItemClick(View view, int position) {

                  vp.setCurrentItem(position);

              }

              @Override
              public void onItemLongClick(View view, int position) {

              }
          }));



      }


    public void float1(){

        mFloat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.main_float:
                        switch (vp.getCurrentItem()) {
                            case 0:
                                TopFragment.recy_top.scrollToPosition(0);
                                break;

                            case 1:
                                NBAFragment.recy_nba.scrollToPosition(0);
                                break;

                            case 2:
                                CarFragment.recy_car.scrollToPosition(0);
                                break;

                            case 3:
                                JokeFragment.recy_joke.scrollToPosition(0);
                                break;

                            case 4:
                                ImagerFragment.recy_img.scrollToPosition(0);
                                break;
                            case 5:
                                NawsFragment.recy_news.scrollToPosition(0);
                                break;
                        }
                        break;
                }}
        });
    }



    public void  viewpager(){
        mfrag.add(new TopFragment());
        mfrag.add(new NBAFragment());
        mfrag.add(new CarFragment());
        mfrag.add(new JokeFragment());
        mfrag.add(new ImagerFragment());
        mfrag.add(new NawsFragment());


        FragmentAdapter adapter = new FragmentAdapter(this.getSupportFragmentManager(), mfrag);

        vp.setAdapter(adapter);
        vp.setCurrentItem(0);
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float offset, int positionOffsetPixels) {

            }


            @Override
            public void onPageSelected(int position) {

                vp.setCurrentItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

       //返回顶部
     @Override
    public void onClick(View v) {
         switch (v.getId()){
             case  R.id.rigth_im:

                 break;
             case  R.id.rigth_tv:


                 getSupportFragmentManager().beginTransaction().replace(R.id.main_rela, new RigthFragment()).commit();

                 Toast.makeText(this,"dianjitv",Toast.LENGTH_SHORT).show();
                 break;
             case  R.id.weixin:
                 cn.sharesdk.framework.Platform weixin= ShareSDK.getPlatform(Wechat.NAME);
                 weixin.SSOSetting(false);
                 weixin.setPlatformActionListener( this);
                 weixin.authorize();
                 break;
             case  R.id.xinlang:
                 cn.sharesdk.framework.Platform xinkang= ShareSDK.getPlatform(Wechat.NAME);
                 xinkang.SSOSetting(false);
                 xinkang.setPlatformActionListener( this);
                 xinkang.authorize();
                 break;
             case  R.id.renren:
                 cn.sharesdk.framework.Platform renren= ShareSDK.getPlatform(Wechat.NAME);
                 renren.SSOSetting(false);
                 renren.setPlatformActionListener( this);
                 renren.authorize();
                 break;
             case  R.id.qq:
                 cn.sharesdk.framework.Platform qq= ShareSDK.getPlatform(QQ.NAME);
                 qq.SSOSetting(false);
                 qq.setPlatformActionListener( this);
                 qq.authorize();
                 break;



         }


         }





    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        Toast.makeText(this,"dianji"+item.getTitle(),Toast.LENGTH_SHORT).show();

        switch (item.getItemId()){

            case R.id.navi_home:

                getSupportFragmentManager().beginTransaction().replace(R.id.main_rela, new AdMobBannerSizesFragment()).commit();
                TopFragment.recy_top.scrollToPosition(0);
                break;






        }





        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return      mToggle.onOptionsItemSelected(item)||super.onOptionsItemSelected(item);
    }


    @Override
    public void onComplete(cn.sharesdk.framework.Platform platform, int i, HashMap<String, Object> hashMap) {


            PlatformDb platDB = platform.getDb();//获取数平台数据DB
            //通过DB获取各种数据
        String platformNname = platDB.getPlatformNname();
        String token = platDB.getToken();
        String userGender = platDB.getUserGender();
        String userIcon = platDB.getUserIcon();
        String userId = platDB.getUserId();
        String userName = platDB.getUserName();
        Log.d("onComplete", "token: "+token);
        Log.d("onComplete", "userGender: "+userGender);
        Log.d("onComplete", "userIcon: "+userIcon);
        Log.d("onComplete", "userId: "+userId);
        Log.d("onComplete", "userName: "+userName);
        Log.d("onComplete", "platformNname: "+platformNname);
      //  Picasso.with(this).load(userIcon).into(rigth_im);
          rigth_tv.setText(userName);
    }

    @Override
    public void onError(cn.sharesdk.framework.Platform platform, int i, Throwable throwable) {

    }

    @Override
    public void onCancel(cn.sharesdk.framework.Platform platform, int i) {

    }
}

