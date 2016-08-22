package com.example.administrator.mynews.view;


import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.administrator.mynews.R;
import com.example.administrator.mynews.adapter.TopAdapter;
import com.example.administrator.mynews.common.CommonUtil;
import com.example.administrator.mynews.common.SuperSwipeRefreshLayout;
import com.example.administrator.mynews.common.TopInfo;
import com.example.administrator.mynews.utils.NewsGSON;
import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import lumenghz.com.pullrefresh.PullToRefreshView;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopFragment extends Fragment {
     PullToRefreshView pv;
    String url= CommonUtil.HOST+CommonUtil.TOP_URL+CommonUtil.TOP_ID+CommonUtil.END_URL;
    String url1= "http://c.m.163.com/nc/article/list/T1350383429665/0-20.html";
    public  static   RecyclerView  recy_top;
    SuperSwipeRefreshLayout seipe_top;
    TextView tv_swipe;
    ImageView img_swipe;
    ProgressBar pb_swipe;
          ArrayList<TopInfo>  mlist;

    // Footer View
    private ProgressBar footerProgressBar;
    private TextView footerTextView;
    private ImageView footerImageView;

    public TopFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_top, container, false);

        recy_top= (RecyclerView) view.findViewById(R.id.top_recy);
        seipe_top= (SuperSwipeRefreshLayout) view.findViewById(R.id.top_swipe);
        RecyclerView.LayoutManager manager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
           recy_top.setLayoutManager(manager);
            recy_top.findViewHolderForLayoutPosition(0);
             mlist=new ArrayList<>();


        superswipe();
       // new Async().execute(url);



        return view;

    }

    private void superswipe() {

        seipe_top.setHeaderView(createHeaderView());
        seipe_top.setHeaderViewBackgroundColor(0xff888888);
        seipe_top.setHeaderView(createHeaderView());// add headerView
        seipe_top.setFooterView(createfooterView());
        seipe_top.setTargetScrollWithLayout(true);
        seipe_top.setOnPullRefreshListener(new SuperSwipeRefreshLayout.OnPullRefreshListener() {
            @Override
            public void onRefresh() {
              //开始刷新
                seipe_top.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                seipe_top.setRefreshing(false);

                    }
                }, 3000);
                 }

            @Override
            public void onPullDistance(int distance) {
                //下拉距离
                if (distance < 10) {
                    tv_swipe.setText("下拉刷新");
                    img_swipe.setVisibility(View.VISIBLE);
                    //  pb_swipe.setProgress(0);
                    pb_swipe.setVisibility(View.GONE);
                }
            }

                @Override
                public void onPullEnable ( boolean enable){
                    //下拉过程中,下来的距离是否足够触发刷新
                    //    Log.d("onRefresh"+enable, "run4: "+tv_swipe.getText());
                    if (enable) {
                        tv_swipe.setText("正在刷新");
                        img_swipe.setVisibility(View.GONE);
                        pb_swipe.setVisibility(View.VISIBLE);
                   //     new Async().execute(url);

                    }
                }

    });
        seipe_top
                .setOnPushLoadMoreListener(new SuperSwipeRefreshLayout.OnPushLoadMoreListener() {

                    @Override
                    public void onLoadMore() {
                        footerTextView.setText("正在加载...");
                        footerImageView.setVisibility(View.GONE);
                        footerProgressBar.setVisibility(View.VISIBLE);
                        new Handler().postDelayed(new Runnable() {

                            @Override
                            public void run() {
                                footerImageView.setVisibility(View.VISIBLE);
                                footerProgressBar.setVisibility(View.GONE);
                                seipe_top.setLoadMore(false);
                            }
                        }, 5000);
                    }

                    @Override
                    public void onPushEnable(boolean enable) {
                     //   footerTextView.setText(enable ? "松开加载" : "上拉加载");
                        footerImageView.setVisibility(View.VISIBLE);
                        footerImageView.setRotation(enable ? 0 : 180);
                    }

                    @Override
                    public void onPushDistance(int distance) {
                        // TODO Auto-generated method stub

                    }

                });

    }
    private View createfooterView() {
        View footerView = LayoutInflater.from(getContext())
                .inflate(R.layout.main_footer, null);
        footerProgressBar = (ProgressBar) footerView
                .findViewById(R.id.footer_pb_view);
        footerImageView = (ImageView) footerView
                .findViewById(R.id.footer_image_view);
        footerTextView = (TextView) footerView
                .findViewById(R.id.footer_text_view);
        footerProgressBar.setVisibility(View.GONE);
        footerImageView.setVisibility(View.VISIBLE);
        footerImageView.setImageResource(R.drawable.down_arrow);
        footerTextView.setText("上拉加载更多...");
        return footerView;
    }
    private View createHeaderView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.main_top, null);
        tv_swipe= (TextView) view.findViewById(R.id.swipe_tv);
        img_swipe= (ImageView) view.findViewById(R.id.swipe_img);
        pb_swipe= (ProgressBar) view.findViewById(R.id.swipe_pb);
        img_swipe.setVisibility(View.VISIBLE);
        pb_swipe.setVisibility(View.GONE);
        // pb_swipe.setProgress(0);
        tv_swipe.setVisibility(View.VISIBLE);
        tv_swipe.setText("下拉刷新");
        return view;

    }



    class Async extends AsyncTask<String,Void,String> {

        public   String s = null;
        @Override
        protected String doInBackground(String... params) {

            Request request=new Request.Builder().url(params[0]).build();

            try {
                Response response=new OkHttpClient().newCall(request).execute();
                s=response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return s;


        }

        @Override
        protected void onPostExecute(String s) {
            gson(s);
        }
    }
    public  void gson(  String s){

        Gson gson=new Gson();

        Gson gson2 = new Gson();
              NewsGSON gson1 = gson2.fromJson(s, NewsGSON.class);
        List<NewsGSON.T1348647909107Bean> list=null;
        if (gson1!=null){
          list = gson1.getT1348647909107();}

           if (list!=null){

                             for (int i = 2; i < list.size(); i++) {
                                 String u =  list.get(i).getUrl_3w();

                                 String title = list.get(i).getTitle();
                                 String imgsrc = list.get(i).getImgsrc();
                                 String ptime = list.get(i).getPtime();
                                 String source = list.get(i).getSource();
                               //  String url =  ads.get(i).getUrl();
                                 mlist.add(new TopInfo(imgsrc,title,ptime,source,u));

                             }




                   recy_top.setAdapter(new TopAdapter(getContext(),mlist));

               }










           }

//     public  void gson1(String s){
//         Gson gson = new Gson();
//         NewsGSON gson1 = gson.fromJson(s, NewsGSON.class);
//         List<NewsGSON.T1348647909107Bean> list = gson1.getT1348647909107();
//
//
//                          for (int i = 0; i < list.size(); i++) {
//
//                                 String title = list.get(i).getTitle();
//                                 String imgsrc = list.get(i).getImgsrc();
//                                 String ptime = list.get(i).getPtime();
//                                 String source = list.get(i).getSource();
//                                    String u =  list.get(i).getUrl();
//                            Log.d("taiy hmtl", "gson: ");
//                               mlist.add(new TopInfo(imgsrc,title,ptime,source));
//
//                             }
//
//
//
//         }






}
