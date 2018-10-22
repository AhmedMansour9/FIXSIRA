package com.example.ic.fixera.Fragments;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.ic.fixera.Activites.MainActivity;
import com.example.ic.fixera.Activites.TabsLayouts;
import com.example.ic.fixera.Language;
import com.example.ic.fixera.Model.Profilee;
import com.example.ic.fixera.Presenter.Profile_Presenter;
import com.example.ic.fixera.R;
import com.example.ic.fixera.View.Profile_View;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class Profile extends Fragment implements Profile_View{


    public Profile() {
        // Required empty public constructor
    }
    View view;
    TextView E_Name,E_Email;
    Profile_Presenter profile_presenter;
    String lang;
    ProgressBar progressProfile;
    SharedPreferences Shared;
    String user;
    Button btn_Arabic,Btn_English,Btn_logout;
    SharedPreferences.Editor share;
    SharedPreferences shared;
    SharedPreferences.Editor Sha;
    MyReceiver r;
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_profile, container, false);
        progressProfile=view.findViewById(R.id.progrossprofile);
        Shared=getActivity().getSharedPreferences("login",MODE_PRIVATE);
        Sha=getActivity().getSharedPreferences("login",MODE_PRIVATE).edit();
        share=getActivity().getSharedPreferences("Language",MODE_PRIVATE).edit();
        user=Shared.getString("logggin",null);
        btn_Arabic=view.findViewById(R.id.btn_Arabic);
        Btn_English=view.findViewById(R.id.btn_English);
        Btn_logout=view.findViewById(R.id.btn_logout);
        E_Name=view.findViewById(R.id.T_Name);
        E_Email=view.findViewById(R.id.E_Email);
        if(Language.isRTL()){
            btn_Arabic.setVisibility(View.INVISIBLE);
            Btn_English.setVisibility(View.VISIBLE);
            lang="ar";
        }else {
            Btn_English.setVisibility(View.INVISIBLE);
            btn_Arabic.setVisibility(View.VISIBLE);
            lang="en";
        }
        profile_presenter=new Profile_Presenter(getContext(),this);
        progressProfile.setVisibility(View.VISIBLE);
        profile_presenter.register(user,lang);

        btn_Arabic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                share.putString("Lann","ar");
                share.commit();
            startActivity(new Intent(getContext(),TabsLayouts.class));
            getActivity().finish();


            }
        });
        Btn_English.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                share.putString("Lann","en");
                share.commit();
                startActivity(new Intent(getContext(),TabsLayouts.class));
                getActivity().finish();


            }
        });

        Btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Sha.putString("logggin",null);
                Sha.apply();
             startActivity(new Intent(getActivity(), MainActivity.class));
                getActivity().finish();
            }
        });
        return view;
    }

    @Override
    public void getProfile(String user,String email,String photo) {

        E_Name.setText(user);
        E_Email.setText(email);
        progressProfile.setVisibility(View.GONE);
    }

    @Override
    public void Error() {
        progressProfile.setVisibility(View.GONE);
    }

    public void refresh() {
        //yout code in refresh.

    }

    public void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(r);
    }

    public void onResume() {
        super.onResume();
        r = new MyReceiver();
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(r,
                new IntentFilter("TAG_REFRESH"));
    }

    private class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Profile.this.refresh();
        }
    }
}
