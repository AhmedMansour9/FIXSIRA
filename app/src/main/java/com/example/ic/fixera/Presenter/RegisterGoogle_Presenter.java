package com.example.ic.fixera.Presenter;

import android.content.Context;

import com.example.ic.fixera.Model.UserLoginResponse;
import com.example.ic.fixera.Model.UserRegister;
import com.example.ic.fixera.Retrofit.ApiCLint;
import com.example.ic.fixera.Retrofit.Apiinterface;
import com.example.ic.fixera.View.RegistergoogleView;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ic on 9/30/2018.
 */

public class RegisterGoogle_Presenter {
    RegistergoogleView logingooglevieew;

    public RegisterGoogle_Presenter(Context context, RegistergoogleView Loginview)
    {
        this.logingooglevieew=Loginview;

    }

    public void Registergoogle(UserRegister user) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("name", user.getFirstName());
        queryMap.put("email", user.getEmail());
        queryMap.put("google_id", user.getId());
        queryMap.put("api_token", "100");

        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);


        Call<UserLoginResponse> call = apiInterface.RegisterGoogle(queryMap);
        call.enqueue(new Callback<UserLoginResponse>() {
            @Override
            public void onResponse(Call<UserLoginResponse> call, Response<UserLoginResponse> response) {

                if (response.isSuccessful()) {
                    logingooglevieew.openMainGoogle(response.body().getData().getUserToken());

                }
            }


            @Override
            public void onFailure(Call<UserLoginResponse> call, Throwable t) {
                logingooglevieew.showErrorGoogle("");

            }
        });
    }
}
