package com.example.ic.fixera.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.example.ic.fixera.Model.UserRegister;
import com.example.ic.fixera.NetworikConntection;
import com.example.ic.fixera.Presenter.Register_Presenter;
import com.example.ic.fixera.View.RegisterView;
import com.fixsira.R;
import com.fourhcode.forhutils.FUtilsValidation;

import io.fabric.sdk.android.Fabric;


/**
 * A simple {@link Fragment} subclass.
 */
public class Register extends Fragment implements RegisterView {


    public Register() {
        // Required empty public constructor
    }
    View view;
   Button Sign_Up;
    EditText E_FirstName,E_LastName,E_Emai,E_Phone,E_Password,E_CarModel,E_CarYear;
    ProgressBar Progrossregister;
    NetworikConntection checkgbsAndNetwork;
    Register_Presenter register;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_register, container, false);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE|
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        register=new Register_Presenter(getContext(),this);
        E_FirstName=view.findViewById(R.id.E_FirstName);
        E_LastName=view.findViewById(R.id.E_LastName);
        Progrossregister=view.findViewById(R.id.progressBarRegister);
        E_Emai=view.findViewById(R.id.E_Email);
        E_Phone=view.findViewById(R.id.E_Phone);
        E_Password=view.findViewById(R.id.E_Password);
        E_CarModel=view.findViewById(R.id.E_CarModel);
        E_CarYear=view.findViewById(R.id.E_CarYear);
        Sign_Up=view.findViewById(R.id.Sign_Up);
        checkgbsAndNetwork=new NetworikConntection(getActivity());
        GoToLogin();
        Sign_Up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkgbsAndNetwork.isNetworkAvailable(getActivity())) {
                    FUtilsValidation.isEmpty(E_Emai, getResources().getString(R.string.insertemail));
                    FUtilsValidation.isEmpty(E_FirstName, getResources().getString(R.string.insertfirstname));
                    FUtilsValidation.isEmpty(E_LastName, getResources().getString(R.string.insertlastname));
                    FUtilsValidation.isEmpty(E_Phone, getResources().getString(R.string.insertphone));
                    FUtilsValidation.isEmpty(E_Password, getResources().getString(R.string.insertpassword));

                    FUtilsValidation.isEmpty(E_CarModel, getResources().getString(R.string.insertcarmodel));
                    FUtilsValidation.isEmpty(E_CarYear, getResources().getString(R.string.insertcaryear));

                    FUtilsValidation.isLengthCorrect(E_Password.getText().toString(), 8, 16);

                    if (!FUtilsValidation.isLengthCorrect(E_Password.getText().toString(), 8, 16))
                        E_Password.setError("password min 8 char");


                    if (!E_Emai.getText().toString().equals("") && !E_FirstName.getText().toString().equals("") && !E_LastName.getText().toString().equals("") && !E_Phone.getText().toString().equals("") &&
                            (FUtilsValidation.isLengthCorrect(E_Password.getText().toString(), 8, 16))) {
                        UserRegister user = new UserRegister();


                        user.setEmail(E_Emai.getText().toString());
                        user.setFirstName(E_FirstName.getText().toString());
                        user.setLastName(E_LastName.getText().toString());
                        user.setPhone(E_Phone.getText().toString());
                        user.setPassword(E_Password.getText().toString());
                        user.setCarmodel(E_CarModel.getText().toString());
                        user.setCaryear(E_CarYear.getText().toString());

                        Progrossregister.setVisibility(View.VISIBLE);
                        register.register(user);
                    }
                } else {
                    Toast.makeText(getActivity(), getResources().getString(R.string.internet), Toast.LENGTH_LONG).show();
                }

            }
        });


        return view;
    }
   public void GoToLogin(){
       Sign_Up=view.findViewById(R.id.Sign_Up);
       Sign_Up.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               getFragmentManager().beginTransaction().replace(R.id.flContent,
                       new Login()).commit();
           }
       });
   }

    @Override
    public void openMain() {
        Toast.makeText(getActivity(), getResources().getString(R.string.Successfull), Toast.LENGTH_SHORT).show();
        Progrossregister.setVisibility(View.GONE);
        getFragmentManager().beginTransaction().replace(R.id.flContent,new Login()).commit();
    }

    @Override
    public void showError(String error) {
        Progrossregister.setVisibility(View.GONE);
        Toast.makeText(getActivity(), getResources().getString(R.string.emailfailed), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStart() {
        super.onStart();

    }
}
