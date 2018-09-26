package com.example.ic.fixera.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.ic.fixera.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Register extends Fragment {


    public Register() {
        // Required empty public constructor
    }
    View view;
   Button Sign_Up;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_register, container, false);
        Sign_Up=view.findViewById(R.id.Sign_Up);
        GoToLogin();


        return view;
    }
   public void GoToLogin(){
       Sign_Up=view.findViewById(R.id.Sign_Up);
       Sign_Up.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               getFragmentManager().beginTransaction().replace(R.id.flContent,new Login()).commit();
           }
       });
   }
}
