package com.example.administrator.mynews.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.administrator.mynews.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SigninFragment extends Fragment {


    public SigninFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_signin, container, false);
        Button  btn_call_off= (Button) view.findViewById(R.id.signin_call_off);
               btn_call_off.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {



                   }
               });
        return view;
    }

}
