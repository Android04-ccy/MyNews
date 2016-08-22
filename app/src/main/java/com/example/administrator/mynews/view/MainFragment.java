package com.example.administrator.mynews.view;


import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.FrameLayout;

import com.example.administrator.mynews.R;
import com.example.administrator.mynews.adapter.FragmentAdapter;
import com.example.administrator.mynews.adapter.MainTopAdapter;
import com.example.administrator.mynews.common.FragTofa;
import com.example.administrator.mynews.common.RecyclerViewClickListener;
import com.example.administrator.mynews.ui.MainActivity;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {
  public  static   RecyclerView rv_top;
    String[] strings = {"头条", "NBA", "汽车", "笑话", "图片", "新闻"};
    ViewPager vp;
    ArrayList<Fragment> mfrag = new ArrayList<>();
    FragmentAdapter mFraA;
    FloatingActionButton mFloat;
        int  weizhi=0;
    ArrayList<String> mlist;
    private AdView mAdView;
    private FrameLayout mMfra;
    private AlphaAnimation mAlpha;
    private MainTopAdapter mAdapter;
    private AlphaAnimation mAlpha1;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
             rv_top= (RecyclerView) view.findViewById(R.id.fra_main_top_recy);
         vp= (ViewPager) view.findViewById(R.id.fra_main);

        mlist=new ArrayList<>();
//        for (int i = 0; i < strings.length; i++) {
//
//            mlist.add(strings[i]);
//        }
        mlist.add("头条");
        mlist.add("NBA");
        mlist.add("汽车");
        mlist.add("笑话");
        mlist.add("图片");
        mlist.add("新闻");



        RecyclerView.LayoutManager  manager= new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false);
        rv_top.setLayoutManager(manager);
        mAdapter = new MainTopAdapter(getContext(), mlist);

        rv_top.setAdapter(mAdapter);
        rv_top.addOnItemTouchListener(new RecyclerViewClickListener(getContext(), new RecyclerViewClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                vp.setCurrentItem(position);

                   MainTopAdapter.MyViewHodler  myViewHodler= (MainTopAdapter.MyViewHodler) rv_top.getChildViewHolder(view);
                         dianji(position);
                         myViewHodler.tv_top.setTextColor(Color.YELLOW);
                       // itemShow(position);

            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        }));

         viewpager();
           mFloat= (FloatingActionButton) view.findViewById(R.id.main_float);
           float1();
        mAlpha = new AlphaAnimation(0.3f,0.3f);

            mFloat.setAnimation(mAlpha);
           mAlpha.setFillAfter(true);
           mAlpha.start();



        mMfra = (FrameLayout) view.findViewById(R.id.fra_main_fra);
        if (mAdView != null) {
            mMfra.removeView(mAdView);
            mAdView.destroy();
        }

        mAdView = new AdView(getActivity());
        mAdView.setAdSize(AdSize.BANNER);
        mAdView.setAdUnitId(getString(R.string.admob_banner_ad_unit_id));
        mMfra.addView(mAdView);

        mAdView.loadAd(new AdRequest.Builder().build());

        return  view;

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



        FragmentAdapter adapter = new FragmentAdapter(getChildFragmentManager(), mfrag);

        vp.setAdapter(adapter);
        vp.setCurrentItem(0);

        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float offset, int positionOffsetPixels) {


            }


            @Override
            public void onPageSelected(int position) {

                 if (weizhi<position){

                     if (position<strings.length-2){
                         rv_top.scrollToPosition(position+2);}
                 }else {

                     if (position<strings.length+2){
                         rv_top.scrollToPosition(position-2);}
                 }


                vp.setCurrentItem(position);
                  itemShow(position);

                weizhi=position;

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    public  void dianji(int k){
        if (mlist!=null){
        for (int i = 0; i < mlist.size(); i++) {


            View childAt = rv_top.getChildAt(i);
            if (childAt!=null) {
                MainTopAdapter.MyViewHodler holder = (MainTopAdapter.MyViewHodler) rv_top.getChildViewHolder(childAt);
                holder.tv_top.setTextColor(Color.WHITE);

            }else if (k>3){
                View childAt2 = rv_top.getChildAt(2);
                View childAt3 = rv_top.getChildAt(3);
                MainTopAdapter.MyViewHodler holder2 = (MainTopAdapter.MyViewHodler) rv_top.getChildViewHolder(childAt2);
                MainTopAdapter.MyViewHodler holder3 = (MainTopAdapter.MyViewHodler) rv_top.getChildViewHolder(childAt3);
                holder2.tv_top.setTextColor(Color.WHITE);
                holder3.tv_top.setTextColor(Color.WHITE);
            }

        }}



    }
    public  void  itemShow(int i){
        Log.d("itemShow", "  itemShow);:点击了"+i );

        dianji(i);

        View childAt1 = rv_top.getChildAt(0);
        int p = rv_top.getChildAdapterPosition(childAt1);
          View childAt = rv_top.getChildAt(i-p);

          if (childAt!=null) {

              MainTopAdapter.MyViewHodler holder = (MainTopAdapter.MyViewHodler) rv_top.getChildViewHolder(childAt);


              holder.tv_top.setTextColor(Color.YELLOW);


          }

    }

}