package com.example.ic.fixera.Presenter;

import android.content.Context;

import com.example.ic.fixera.Model.UserLoginResponse;
import com.example.ic.fixera.Model.UserRegister;
import com.example.ic.fixera.Retrofit.ApiCLint;
import com.example.ic.fixera.Retrofit.Apiinterface;
import com.example.ic.fixera.View.LoginView;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by HP on 04/09/2018.
 */

public class LoginPresenter {

    LoginView loginvieew;

    public LoginPresenter(Context context, LoginView Loginview)
    {
        this.loginvieew=Loginview;

    }

    public void Login(UserRegister user) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("email", user.getEmail());
        queryMap.put("password", user.getPassword());
        queryMap.put("api_token", "100");

        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);


        Call<UserLoginResponse> call = apiInterface.Login(queryMap);
        call.enqueue(new Callback<UserLoginResponse>() {
            @Override
            public void onResponse(Call<UserLoginResponse> call, Response<UserLoginResponse> response) {

                if (response.isSuccessful()) {


                    if(response.body().getData().getMessage().equals("login success")){
                        if(response.body().getData().getRoleId()!=null) {
                            loginvieew.OpenRole(response.body().getData().getUserToken());

                        }
                        else {
                            loginvieew.openMain(response.body().getData().getUserToken());
                        }
                    } else {
                        loginvieew.Invalidemail("");
                    }

                }

            }


            @Override
            public void onFailure(Call<UserLoginResponse> call, Throwable t) {
                loginvieew.showError("");

            }
        });
    }
}
