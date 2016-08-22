package com.example.administrator.mynews.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.mynews.R;
import com.example.administrator.mynews.common.TopInfo;
import com.example.administrator.mynews.ui.ShowActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/8/8.
 */
public class TopAdapter extends RecyclerView.Adapter<TopAdapter.MyViewHodler> {


    ArrayList<TopInfo> mlist;
    Context mcontext;
    public  TopAdapter(Context c,ArrayList<TopInfo> s){
        mlist=s;
        mcontext=c;


    }

    @Override
    public MyViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mcontext).inflate(R.layout.top_recy, parent, false);

        return new MyViewHodler(view);
    }

    @Override
    public void onBindViewHolder(MyViewHodler holder, final int position) {
        Picasso.with(mcontext).load(  mlist.get(position).img).into(holder.top_img);
         holder.top_tv.setText(mlist.get(position).text);
        if (mlist.get(position).ptime!=null&&mlist.get(position).sou!=null) {
            holder.top_ptime.setText(mlist.get(position).ptime);
            holder.top_source.setText(mlist.get(position).sou);

        }

        holder.line.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mcontext, ShowActivity.class);
                if (mlist.get(position).url!=null){

                     intent.putExtra("url",mlist.get(position).url);
                     intent.putExtra("title",mlist.get(position).text);
                     intent.putExtra("img",mlist.get(position).img);


                }else {
                    intent.putExtra("url","无法加载");

                }
                mcontext.startActivity(intent);
            }
        });





    }

    @Override
    public int getItemCount() {
        if (mlist!=null){
            return  mlist.size();

        }
        return 0;
    }

    class MyViewHodler extends RecyclerView.ViewHolder{
        TextView top_ptime;
        TextView top_source;
        TextView top_tv;
       ImageView top_img;
     LinearLayout  line;
        public MyViewHodler(View itemView) {
            super(itemView);
            top_tv= (TextView) itemView.findViewById(R.id.top_recy_tv);
            top_ptime= (TextView) itemView.findViewById(R.id.top_recy_ptime);
            top_source= (TextView) itemView.findViewById(R.id.top_recy_source);
            top_img= (ImageView) itemView.findViewById(R.id.top_recy_iv);
             line= (LinearLayout) itemView.findViewById(R.id.recy_line);
        }
    }
}
