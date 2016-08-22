package com.example.administrator.mynews.view;



import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.mynews.R;
import com.example.administrator.mynews.adapter.FragmentAdapter;
import com.example.administrator.mynews.ui.MainActivity;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class RigthFragment extends Fragment  {


     public static   ViewPager  mPager;
    ArrayList<Fragment> mList=new ArrayList<>();
    TextView  mTv;
    String[]  str={"登陆界面","注册界面"};
    public RigthFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_rigth, container, false);
          mTv= (TextView) view.findViewById(R.id.rigth_tob_tv);
        mPager= (ViewPager) view.findViewById(R.id.sign_in);
        mList.add(new LogInFragment());
        mList.add(new SigninFragment());
        mPager.setAdapter(new FragmentAdapter(getChildFragmentManager(),mList));
          mPager.setCurrentItem(0);
           mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
               @Override
               public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

               }

               @Override
               public void onPageSelected(int position) {
                   mPager.setCurrentItem(position);
                   mTv.setText(str[position]);
               }

               @Override
               public void onPageScrollStateChanged(int state) {

               }
           });





        return view;
    }

















}
