package com.example.ic.fixera.Presenter;

import android.content.Context;

import com.example.ic.fixera.Model.RegisterFaceResponse;
import com.example.ic.fixera.Model.UserRegister;
import com.example.ic.fixera.Retrofit.ApiCLint;
import com.example.ic.fixera.Retrofit.Apiinterface;
import com.example.ic.fixera.View.RegisterFaceView;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ic on 9/30/2018.
 */

public class RegisterFace_Presenter {


    RegisterFaceView loginFacevieew;

    public RegisterFace_Presenter(Context context, RegisterFaceView Loginview)
    {
        this.loginFacevieew=Loginview;

    }

    public void RegisterFace(UserRegister user) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("name", user.getFirstName());
        queryMap.put("email", user.getEmail());
        queryMap.put("fb_id", user.getId());
        queryMap.put("api_token", "100");

        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);


        Call<RegisterFaceResponse> call = apiInterface.RegisterFace_Book(queryMap);
        call.enqueue(new Callback<RegisterFaceResponse>() {
            @Override
            public void onResponse(Call<RegisterFaceResponse> call, Response<RegisterFaceResponse> response) {

             if (response.isSuccessful()) {
                    loginFacevieew.openMainFace(response.body().getData().getUserToken());
                }else {
                    loginFacevieew.showErrorFace("");

                }


            }


            @Override
            public void onFailure(Call<RegisterFaceResponse> call, Throwable t) {
                loginFacevieew.showErrorFace("");

            }
        });
    }
}
