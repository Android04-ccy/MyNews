package com.example.administrator.mynews.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.mynews.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/8/8.
 */
public class MainTopAdapter extends RecyclerView.Adapter<MainTopAdapter.MyViewHodler> {
        ArrayList<String> mlist;
        Context mcontext;

    public  MainTopAdapter(Context c,ArrayList<String> s){
        mlist=s;
        mcontext=c;


    }

    @Override
    public MyViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.main_top_recy, parent, false);

        return new MyViewHodler(view);
    }

    @Override
    public void onBindViewHolder(MyViewHodler holder, final int position) {

          holder.tv_top.setText(mlist.get(position));



                  }






    @Override
    public int getItemCount() {

        if (mlist!=null){
            return  mlist.size();

        }
        return 0;
    }

     public   class MyViewHodler extends RecyclerView.ViewHolder{
       public   TextView  tv_top;
      LinearLayout  line;
        public MyViewHodler(View itemView) {
            super(itemView);
           tv_top= (TextView) itemView.findViewById(R.id.main_top_tv);
        line= (LinearLayout) itemView.findViewById(R.id.main_top_line);
        }
    }
}
