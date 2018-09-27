package com.example.ic.fixera.Fragments;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.ic.fixera.MainActivity;
import com.example.ic.fixera.R;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class language extends Fragment {


    public language() {
        // Required empty public constructor
    }
   Button btn_Arabic,btn_English;
   View view;
    SharedPreferences.Editor share;
    SharedPreferences shared;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_language, container, false);
        shared=getActivity().getSharedPreferences("Language",MODE_PRIVATE);
        String Lan=shared.getString("Lann",null);
        share=getActivity().getSharedPreferences("Language",MODE_PRIVATE).edit();
        if(Lan!=null){
            getFragmentManager().beginTransaction().replace(R.id.flContent,new Login()).commit();
        }
        btn_Arabic=view.findViewById(R.id.btn_Arabic);
        btn_English=view.findViewById(R.id.btn_English);

        btn_Arabic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                share.putString("Lann","ar");
                share.commit();
                getFragmentManager().beginTransaction().replace(R.id.flContent,new Login()).commit();

            }
        });

        btn_English.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                share.putString("Lann","en");
                share.commit();
                getFragmentManager().beginTransaction().replace(R.id.flContent,new Login()).commit();
            }
        });


        return view;
    }

}
