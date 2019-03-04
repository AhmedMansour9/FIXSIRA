package com.example.ic.fixera.Fragments;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
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
import com.example.ic.fixera.SharedPrefManager;
import com.example.ic.fixera.View.Profile_View;
import com.fixsira.R;

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
    TextView E_Name,E_Email,T_CarModel,T_CarYear,T_ModileNumber;
    Profile_Presenter profile_presenter;
    String lang;
    ProgressBar progressProfile;
    SharedPreferences Shared;
    String user;
    Button btn_Arabic,Btn_English,Btn_logout,Btn_ChangePassword,btn_login;
    SharedPreferences.Editor share;
    SharedPreferences shared;
    SharedPreferences.Editor Sha;
    MyReceiver r;
    TextView Edit_Profil;
    String Name,CarModel,CarYear,phone;
    String role;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_profile, container, false);
        String greeting = (savedInstanceState != null) ? savedInstanceState.getString("greeting") : "null";
         role= SharedPrefManager.getInstance(getActivity()).getRole();
        btn_login=view.findViewById(R.id.btn_login);
        init();
        visablty();
        Lan_Arabic();
        Lan_English();
        Log_out();
        Change_password();
        EditProfile();
        progressProfile=view.findViewById(R.id.progrossprofile);
        profile_presenter=new Profile_Presenter(getContext(),this);
        progressProfile.setVisibility(View.VISIBLE);
        profile_presenter.register(user,lang);





        return view;
    }
    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        String greeting = (savedInstanceState != null) ? savedInstanceState.getString("greeting") : "null";
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("greeting", "Hello");
    }
    public void EditProfile(){
        Edit_Profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Edit_Profile fragmen = new Edit_Profile();
                Bundle args = new Bundle();
                args.putString("name",E_Name.getText().toString());
                args.putString("CarModel",T_CarModel.getText().toString());
                args.putString("CarYear",T_CarYear.getText().toString());
                args.putString("phone",T_ModileNumber.getText().toString());
                fragmen.setArguments(args);
                getFragmentManager().beginTransaction()
                        .replace(R.id.Frame_Profile, fragmen )
                        .addToBackStack(null)
                        .commit();

            }
        });
    }
    public void Change_password(){
        Btn_ChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.Frame_Profile,new Change_Password())
                        .addToBackStack(null).commitAllowingStateLoss();

            }
        });
    }
    public void Lan_Arabic(){
        btn_Arabic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                share.putString("Lann","ar");
                share.commit();
                startActivity(new Intent(getContext(),TabsLayouts.class));
                getActivity().finish();


            }
        });
    }
    public void Lan_English(){
        Btn_English.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                share.putString("Lann","en");
                share.commit();
                startActivity(new Intent(getContext(),TabsLayouts.class));
                getActivity().finish();


            }
        });

    }
    public void Log_out(){
        Btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Sha.putString("logggin",null);
                Sha.apply();
                startActivity(new Intent(getActivity(), MainActivity.class));
                getActivity().finish();
            }
        });
    }
     public void init(){
         Shared=getActivity().getSharedPreferences("login",MODE_PRIVATE);
         Sha=getActivity().getSharedPreferences("login",MODE_PRIVATE).edit();
         share=getActivity().getSharedPreferences("Language",MODE_PRIVATE).edit();
         user=Shared.getString("logggin",null);
         btn_Arabic=view.findViewById(R.id.btn_Arabic);
         Btn_English=view.findViewById(R.id.btn_English);
         Btn_logout=view.findViewById(R.id.btn_logout);
         Btn_ChangePassword=view.findViewById(R.id.btn_changepassword);
         E_Name=view.findViewById(R.id.T_Name);
         E_Email=view.findViewById(R.id.E_Email);
         T_CarModel=view.findViewById(R.id.T_CarModel);
         T_CarYear=view.findViewById(R.id.T_CarYear);
         T_ModileNumber=view.findViewById(R.id.T_ModileNumber);
         Edit_Profil=view.findViewById(R.id.Edit_Prof);
         btn_login=view.findViewById(R.id.btn_login);
         if(role!=null){
          Btn_logout.setVisibility(View.GONE);
             Btn_ChangePassword.setVisibility(View.GONE);
             btn_login.setVisibility(View.VISIBLE);
             btn_login.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     startActivity(new Intent(getActivity(), MainActivity.class));
                     getActivity().finish();
                 }
             });
         }
     }
     public void visablty(){
         if(Language.isRTL()){
             btn_Arabic.setVisibility(View.INVISIBLE);
             Btn_English.setVisibility(View.VISIBLE);
             lang="ar";
         }else {
             Btn_English.setVisibility(View.INVISIBLE);
             btn_Arabic.setVisibility(View.VISIBLE);
             lang="en";
         }
     }
    @Override
    public void getProfile(String user,String email,String photo,String phones,String carymodel,String caryear) {
        E_Name.setText(user);
        E_Email.setText(email);
        T_ModileNumber.setText(phones);
        if(carymodel!=null) {
            T_CarModel.setText(carymodel);
        } if(caryear!=null) {
            T_CarYear.setText(caryear);
        }
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
