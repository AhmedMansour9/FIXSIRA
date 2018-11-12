package com.example.ic.fixera.Presenter;

import android.content.Context;

import com.example.ic.fixera.Model.CarWashing;
import com.example.ic.fixera.Model.Gallery_Image_Response;
import com.example.ic.fixera.Model.Service;
import com.example.ic.fixera.Model.Service_Response;
import com.example.ic.fixera.Retrofit.ApiCLint;
import com.example.ic.fixera.Retrofit.Apiinterface;
import com.example.ic.fixera.View.Gallery_View;
import com.example.ic.fixera.View.Service_View;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ahmed on 13/10/2018.
 */

public class Service_Presenter {
    Service_View getService;

    public Service_Presenter(Context context, Service_View getService)
    {
        this.getService=getService;

    }

    public void GetService(String lang,String tybe,String vendorid,String user) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("lang", lang);
        queryMap.put("api_token", "100");
        queryMap.put("type", tybe);
        queryMap.put("user_token", user);
        queryMap.put("vendor_id", vendorid);


        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);

        Call<Service_Response> call = apiInterface.getService(queryMap);
        call.enqueue(new Callback<Service_Response>() {
            @Override
            public void onResponse(Call<Service_Response> call, Response<Service_Response> response) {

                if (response.isSuccessful()) {
                    getService.GetService((List<Service>) response.body().getData());
                }
                else {
                    getService.Error();
                }
            }
            @Override
            public void onFailure(Call<Service_Response> call, Throwable t) {
                getService.Error();
            }
        });
    }

}

