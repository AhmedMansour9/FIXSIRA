package com.example.ic.fixera.Presenter;

import android.content.Context;

import com.example.ic.fixera.Model.Profile_Response;
import com.example.ic.fixera.Model.Profilee;
import com.example.ic.fixera.Model.RegisterResponse;
import com.example.ic.fixera.Model.UserRegister;
import com.example.ic.fixera.Retrofit.ApiCLint;
import com.example.ic.fixera.Retrofit.Apiinterface;
import com.example.ic.fixera.View.Profile_View;
import com.example.ic.fixera.View.RegisterView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ahmed on 15/10/2018.
 */

public class Profile_Presenter {
    Profile_View profile;

    public Profile_Presenter(Context context, Profile_View registerView)
    {
        this.profile=registerView;

    }

    public void register(final String user, String lan) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("api_token", "100");
        queryMap.put("lang", lan);
        queryMap.put("user_token",user);

        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);
        Call<Profile_Response> call = apiInterface.Getprofile(queryMap);
        call.enqueue(new Callback<Profile_Response>() {
            @Override
            public void onResponse(Call<Profile_Response> call, Response<Profile_Response> response) {

                if (response.isSuccessful()) {
                    if(response.body().getData()!=null) {
                        profile.getProfile(response.body().getData().getDisplayName(), response.body().getData().getEmail(),
                                response.body().getData().getUserPhotoUrl(),response.body().getData().getPhone()
                        ,response.body().getData().getCarModel(),response.body().getData().getCarYear());
                    }else {
                        profile.Error();
                    }
                } else {
                    profile.Error();
                }
            }


            @Override
            public void onFailure(Call<Profile_Response> call, Throwable t) {
                profile.Error();

            }
        });
    }
}
