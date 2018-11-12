package com.example.ic.fixera.Presenter;

import android.content.Context;

import com.example.ic.fixera.Model.CarWashing;
import com.example.ic.fixera.Model.CarWashing_Response;
import com.example.ic.fixera.Model.Hours_Vendor_Response;
import com.example.ic.fixera.Retrofit.ApiCLint;
import com.example.ic.fixera.Retrofit.Apiinterface;
import com.example.ic.fixera.View.CarWashing_View;
import com.example.ic.fixera.View.Vendor_Hours_View;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ahmed on 29/10/2018.
 */

public class Vendor_Hours_Presenter {
    Vendor_Hours_View getHours;

    public Vendor_Hours_Presenter(Context context, Vendor_Hours_View getHours)
    {
        this.getHours=getHours;
    }

    public void VendorHours(String lang,String id,String usertoken){
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("lang", lang);
        queryMap.put("api_token", "100");
        queryMap.put("location_id", id);
        queryMap.put("user_token", usertoken);
        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);

        Call<Hours_Vendor_Response> call = apiInterface.GetHours(queryMap);
        call.enqueue(new Callback<Hours_Vendor_Response>() {
            @Override
            public void onResponse(Call<Hours_Vendor_Response> call, Response<Hours_Vendor_Response> response) {

                if (response.isSuccessful()) {
                    if(response.body().getData()!=null) {
                        getHours.GetHours(response.body().getData());
                    }else {
                        getHours.Error();
                    }
                } else {
                    getHours.Error();
                }
            }
            @Override
            public void onFailure(Call<Hours_Vendor_Response> call, Throwable t) {
                getHours.Error();

            }
        });
    }
}
