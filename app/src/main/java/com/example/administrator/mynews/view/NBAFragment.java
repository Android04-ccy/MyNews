package com.example.administrator.mynews.view;


import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.mynews.R;
import com.example.administrator.mynews.adapter.TopAdapter;
import com.example.administrator.mynews.common.CommonUtil;
import com.example.administrator.mynews.common.FragTofa;
import com.example.administrator.mynews.common.RecyclerViewClickListener;
import com.example.administrator.mynews.common.SuperSwipeRefreshLayout;
import com.example.administrator.mynews.common.TopInfo;
import com.example.administrator.mynews.ui.MainActivity;
import com.example.administrator.mynews.utils.JokeGSON;
import com.example.administrator.mynews.utils.NBAGSON;
import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class NBAFragment extends Fragment implements FragTofa{
    String url= CommonUtil.HOST+CommonUtil.TOP_URL+CommonUtil.NBA_ID+CommonUtil.END_URL;
    String url1 = CommonUtil.HOST+CommonUtil.TOP_URL+CommonUtil.JOKE_ID+CommonUtil.END_URL;
     FragTofa mFrag;
    ArrayList<TopInfo> mlist;
    SuperSwipeRefreshLayout nba_swipe;
    public  static     RecyclerView recy_nba;
    TextView tv_swipe;
    ImageView img_swipe;
    ProgressBar pb_swipe;
    View view;
    // Footer View
    private ProgressBar footerProgressBar;
    private TextView footerTextView;
    private ImageView footerImageView;

    TopAdapter topAdapter;
    private  String  DATA=null;
     Button  bnt;
    public NBAFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_nba, container, false);

        recy_nba = (RecyclerView) view.findViewById(R.id.nba_recy);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recy_nba.setLayoutManager(manager);

        recy_nba.setHasFixedSize(true);
        mlist = new ArrayList<>();
     //    new Async().execute(url);
        nba_swipe = (SuperSwipeRefreshLayout) view.findViewById(R.id.nba_swipe);
        superswipe();
       // new Async1().execute(url1);
       // recy_nba.setScrollingTouchSlop(1);
      //
        topAdapter = new TopAdapter(getContext(), mlist);
        //topAdapter.notifyItemChanged(0);
       // recy_nba.setScrollingTouchSlop();


//                recy_nba.scrollToPosition(0);
//
//            }
        return view;


    }




    private void superswipe() {

        nba_swipe.setHeaderView(createHeaderView());
        nba_swipe.setHeaderViewBackgroundColor(0xff888888);
        nba_swipe.setHeaderView(createHeaderView());// add headerView
        nba_swipe.setFooterView(createfooterView());
        nba_swipe.setTargetScrollWithLayout(true);
        nba_swipe.setOnPullRefreshListener(new SuperSwipeRefreshLayout.OnPullRefreshListener() {
            @Override
            public void onRefresh() {
                //开始刷新
               nba_swipe.postDelayed(new Runnable() {
                   @Override
                   public void run() {

                     //  Log.d("onRefresh", "run1: "+tv_swipe.getText());


                       nba_swipe.setRefreshing(false);


                   }
               },3000);

             //   Log.d("onRefresh", "run2: "+tv_swipe.getText());
            }



            @Override
            public void onPullDistance(int distance) {
                //下拉距离
                if (distance<10) {
                    tv_swipe.setText("下拉刷新");
                    img_swipe.setVisibility(View.VISIBLE);

                    pb_swipe.setVisibility(View.GONE);
                }
               // Log.d("onRefresh"+distance, "run3: "+tv_swipe.getText());
            }

            @Override
            public void onPullEnable(boolean enable) {
                //下拉过程中,下来的距离是否足够触发刷新
              //  Log.d("onRefresh"+enable, "run4: "+tv_swipe.getText());
                if (enable){
                    new Async().execute(url);
                    tv_swipe.setText("正在刷新");

                    img_swipe.setVisibility(View.GONE);
                    pb_swipe.setVisibility(View.VISIBLE);
                   // Log.d("onRefresh"+enable, "执行刷新: "+tv_swipe.getText());
                }
            }
        });
        nba_swipe
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
                                nba_swipe.setLoadMore(false);
                            }
                        }, 3000);
                    }

                    @Override
                    public void onPushEnable(boolean enable) {
                        footerTextView.setText(enable ? "松开加载" : "上拉加载");
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

    @Override
    public void sendNBA(String s) {
        if (s.equals("nihao")) {

            DATA=s;
            Log.d("hhhhhhhhhh", "onCreateView: "+DATA);

        }

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

          //  Log.d("onRefresh"+DATA, "run7: ");
          //  Log.d("onRefresh", "run15: "+tv_swipe.getText());

         if (!s.equals(DATA)){
             Log.d("88"+DATA, "run7: ");
            gson(s);}else {
             Toast.makeText(getContext(),"已经是最新新闻",Toast.LENGTH_SHORT).show();
         }


           }


        }

    public  void gson(  String s){

        Log.d("88"+DATA, "gson: ");
        Log.d("88", "gson: ");

        Gson gson=new Gson();

        NBAGSON nbagson = gson.fromJson(s, NBAGSON.class);
        List<NBAGSON.T1348649145984Bean> list=null;
        if (nbagson!=null){
        list = nbagson.getT1348649145984();}
        if (list!=null){


            for (int i = 4; i < list.size(); i++) {

                String title = list.get(i).getTitle();
                String imgsrc = list.get(i).getImgsrc();
                String ptime = list.get(i).getPtime();
                String source = list.get(i).getSource();
                String url = list.get(i).getUrl();
                String u = list.get(i).getUrl_3w();

                mlist.add(0,new TopInfo(imgsrc,title,ptime,source,u));

            }

            recy_nba.setAdapter(topAdapter);

        }










    }



    class Async1 extends AsyncTask<String,Void,String> {

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
            DATA=s;
            gson1(s);
        }
    }
    public  void gson1(  String s){

       // Log.d("NawsFragment", "gson: "+s);
     //   Log.d("NawsFragment", "gson: "+url);


        Gson gson=new Gson();

        JokeGSON jokeGSON = gson.fromJson(s, JokeGSON.class);

        List<JokeGSON.T1350383429665Bean> list = null;
        if (jokeGSON!=null) {
            list = jokeGSON.getT1350383429665();
        }
        if (list!=null) {

            for (int i = 0; i < list.size(); i++) {

                String title = list.get(i).getTitle();
                String imgsrc = list.get(i).getImgsrc();
                String ptime = list.get(i).getPtime().substring(10);
                String source = list.get(i).getSource();
                String url = list.get(i).getUrl_3w();
                mlist.add(new TopInfo(imgsrc, title, ptime, source, url));


            }

            recy_nba.setAdapter(topAdapter);


        }



    }



}
