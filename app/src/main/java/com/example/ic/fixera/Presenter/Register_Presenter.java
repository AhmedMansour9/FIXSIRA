package com.example.ic.fixera.Presenter;

import android.content.Context;

import com.example.ic.fixera.Model.RegisterResponse;
import com.example.ic.fixera.Model.UserRegister;
import com.example.ic.fixera.Retrofit.ApiCLint;
import com.example.ic.fixera.Retrofit.Apiinterface;
import com.example.ic.fixera.View.RegisterView;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by HP on 04/09/2018.
 */

public class Register_Presenter {

    RegisterView registerView;

    public Register_Presenter(Context context, RegisterView registerView)
    {
        this.registerView=registerView;

    }

    public void register(UserRegister user) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("api_token", "100");
        queryMap.put("firstName", user.getFirstName());
        queryMap.put("lastName", user.getLastName());
        queryMap.put("password", user.getPassword());
        queryMap.put("email", user.getEmail());
        queryMap.put("phone", user.getPhone());
        queryMap.put("car_model", user.getCarmodel());
        queryMap.put("car_year", user.getCaryear());

        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);
        Call<RegisterResponse> call = apiInterface.register(queryMap);
        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {

                if (response.isSuccessful()) {
                    registerView.openMain();
                } else {
                    registerView.showError("");
                }
            }


            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                registerView.showError("");

            }
        });
    }
}
