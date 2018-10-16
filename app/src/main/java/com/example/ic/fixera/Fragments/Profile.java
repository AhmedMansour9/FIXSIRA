package com.example.ic.fixera.Fragments;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_profile, container, false);
        progressProfile=view.findViewById(R.id.progrossprofile);
        Shared=getActivity().getSharedPreferences("login",MODE_PRIVATE);
        user=Shared.getString("logggin",null);

        E_Name=view.findViewById(R.id.T_Name);
        E_Email=view.findViewById(R.id.E_Email);
        if(Language.isRTL()){
            lang="ar";
        }else {
            lang="en";
        }
        profile_presenter=new Profile_Presenter(getContext(),this);
        progressProfile.setVisibility(View.VISIBLE);
        profile_presenter.register(user,lang);

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
}
