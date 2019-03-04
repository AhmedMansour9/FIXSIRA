package com.example.ic.fixera.Presenter;

import android.content.Context;

import com.example.ic.fixera.Model.Profile_Response;
import com.example.ic.fixera.Model.Profile_Vendor_Response;
import com.example.ic.fixera.Retrofit.ApiCLint;
import com.example.ic.fixera.Retrofit.Apiinterface;
import com.example.ic.fixera.View.Profile_View;
import com.example.ic.fixera.View.Profilevendor_View;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ahmed on 20/10/2018.
 */

public class Profilevendor_Presenter {

    Profilevendor_View profile;

    public Profilevendor_Presenter(Context context, Profilevendor_View registerView)
    {
        this.profile=registerView;

    }

    public void profilevendor(final String user, String lan,String id) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("api_token", "100");
        queryMap.put("lang", lan);
        if(user!=null) {
            queryMap.put("user_token", user);
        }else {
            queryMap.put("user_token", "");
        }
        queryMap.put("location_id",id);

        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);

        Call<Profile_Vendor_Response> call = apiInterface.GetprofileVendor(queryMap);
        call.enqueue(new Callback<Profile_Vendor_Response>() {
            @Override
            public void onResponse(Call<Profile_Vendor_Response> call, Response<Profile_Vendor_Response> response) {

                if (response.isSuccessful()) {
                    if(response.body().getData().getProfile().getLocation()!=null) {
                        profile.getprofile(response.body().getData().getProfile().getLocation());
                    }else {
                        profile.Error();
                    }
                } else {
                    profile.Error();
                }
            }


            @Override
            public void onFailure(Call<Profile_Vendor_Response> call, Throwable t) {
                profile.Error();

            }
        });
    }

}
