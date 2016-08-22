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
import com.example.administrator.mynews.utils.JokeGSON;
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
public class JokeFragment extends Fragment {

    String url= CommonUtil.HOST+CommonUtil.TOP_URL+CommonUtil.JOKE_ID+CommonUtil.END_URL;
    ArrayList<TopInfo> mlist;
    SuperSwipeRefreshLayout  swipe_joke;
    TextView tv_swipe;
    ImageView img_swipe;
    ProgressBar pb_swipe;
    public  static   RecyclerView recy_joke;


    // Footer View
    private ProgressBar footerProgressBar;
    private TextView footerTextView;
    private ImageView footerImageView;

    public JokeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_joke, container, false);
        swipe_joke= (SuperSwipeRefreshLayout) view.findViewById(R.id.joke_swipe);
        recy_joke= (RecyclerView) view.findViewById(R.id.joke_recy);
        RecyclerView.LayoutManager manager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recy_joke.setLayoutManager(manager);
        recy_joke.setHasFixedSize(true);
        mlist=new ArrayList<>();


     //   new Async().execute(url);
        superswipe();


        return view;

    }




    private void superswipe() {

        swipe_joke.setHeaderView(createHeaderView());
        swipe_joke.setHeaderViewBackgroundColor(0xff888888);
        swipe_joke.setHeaderView(createHeaderView());// add headerView
        swipe_joke.setFooterView(createfooterView());
        swipe_joke.setTargetScrollWithLayout(true);
        swipe_joke.setOnPullRefreshListener(new SuperSwipeRefreshLayout.OnPullRefreshListener() {
            @Override
            public void onRefresh() {
                //开始刷新
                swipe_joke.postDelayed(new Runnable() {
                    @Override
                    public void run() {



                        swipe_joke.setRefreshing(false);


                    }
                },3000);


            }

            @Override
            public void onPullDistance(int distance) {
                //下拉距离
                if (distance<10) {
                    tv_swipe.setText("下拉刷新");
                    img_swipe.setVisibility(View.VISIBLE);

                    pb_swipe.setVisibility(View.GONE);
                }

            }

            @Override
            public void onPullEnable(boolean enable) {
                //下拉过程中,下来的距离是否足够触发刷新

                if (enable){
                    new Async().execute(url);
                    tv_swipe.setText("正在刷新");

                    img_swipe.setVisibility(View.GONE);
                    pb_swipe.setVisibility(View.VISIBLE);
                    // Log.d("onRefresh"+enable, "执行刷新: "+tv_swipe.getText());
                }
            }
        });
        swipe_joke
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
                                swipe_joke.setLoadMore(false);
                            }
                        }, 5000);
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

          recy_joke.setAdapter(new TopAdapter(getContext(), mlist));


      }



    }
}
