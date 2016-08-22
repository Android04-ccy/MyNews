package com.example.administrator.mynews.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.administrator.mynews.R;
import com.example.administrator.mynews.ui.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class LogInFragment extends Fragment {

    EditText et_name;
    EditText et_press;
    Button  bnt_login;
    Button  bnt_signin;
    public LogInFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_log_in, container, false);
        et_name= (EditText) view.findViewById(R.id.used_name);
        et_press= (EditText) view.findViewById(R.id.edit_passed);
        bnt_login= (Button) view.findViewById(R.id.btn_login);

        bnt_signin= (Button) view.findViewById(R.id.button2);

        bnt_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               RigthFragment.mPager.setCurrentItem(1);
            }
        });


        return view;
    }

}
