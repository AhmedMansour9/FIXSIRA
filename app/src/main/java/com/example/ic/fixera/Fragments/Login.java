package com.example.ic.fixera.Fragments;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ic.fixera.Presenter.LoginPresenter;
import com.example.ic.fixera.R;
import com.example.ic.fixera.Model.UserRegister;
import com.example.ic.fixera.View.LoginView;
import com.fourhcode.forhutils.FUtilsValidation;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class Login extends Fragment implements LoginView {


    public Login() {
        // Required empty public constructor
    }
    EditText E_Email,E_Password;
   View view;
    Button Sign_IN;
    TextView Sign_UP;
    SharedPreferences.Editor Shared;
    NetworikConntection Network;
    ProgressBar progressBar;
    String email;
    LoginPresenter logiin;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_login, container, false);
        Shared=getActivity().getSharedPreferences("login",MODE_PRIVATE).edit();
        logiin=new LoginPresenter(getActivity(),this);
        progressBar=view.findViewById(R.id.progressBarlogin);
        Network=new NetworikConntection(getContext());
        Sign_IN=view.findViewById(R.id.Sign_IN);
        Sign_UP=view.findViewById(R.id.textsignup);
        E_Email=view.findViewById(R.id.E_Email);
        E_Password=view.findViewById(R.id.E_Password);
        GotToTabsLayout();
        GoToRegister();


   return view;
    }

    public void GotToTabsLayout(){
        Sign_IN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!ValidateEmail()){
                    return;
                }

                if(Network.isNetworkAvailable(getContext())){
                    FUtilsValidation.isEmpty(E_Email,getResources().getString(R.string.insertemail));
                    FUtilsValidation.isEmpty(E_Password, getResources().getString(R.string.insertpassword));

                    if (!E_Email.getText().toString().equals("") && !E_Password.getText().toString().equals("")) {
                        UserRegister user = new UserRegister();
                        user.setEmail(E_Email.getText().toString());
                        user.setPassword(E_Password.getText().toString());
                        progressBar.setVisibility(View.VISIBLE);
                        email=E_Email.getText().toString();
                        logiin.Login(user);
                    }
                }else {
                    Toast.makeText(getContext(),getResources().getString(R.string.internet), Toast.LENGTH_LONG).show();
                }
                }

//                getFragmentManager().beginTransaction().replace(R.id.flContent,new TabsLayouts()).commit();

        });
    }
    public void GoToRegister(){
        Sign_UP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.flContent,new Register()).commit();
            }
        });
    }
    private Boolean ValidateEmail(){
        String EMAIL=E_Email.getText().toString().trim();
        if (EMAIL.isEmpty()||!isValidEmail(EMAIL)){
            E_Email.setError(getResources().getString(R.string.invalidemail));

            return false;
        }else if(!E_Email.getText().toString().matches("[a-zA-Z0-9._-]+@[a-z-Z0-9._-]+\\.+[a-z]+")){
            E_Email.setError(getResources().getString(R.string.invalidemail));
            return false;
        }
        return true;
    }
    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    @Override
    public void openMain(String a) {
        Shared.putString("logggin",a);
        Shared.apply();
        progressBar.setVisibility(View.GONE);
        getFragmentManager().beginTransaction().replace(R.id.flContent,new TabsLayouts()).commit();
    }

    @Override
    public void showError(String error) {
        progressBar.setVisibility(View.GONE);
    }
}

