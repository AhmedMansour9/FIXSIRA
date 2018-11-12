package com.example.ic.fixera.Presenter;

import android.content.Context;

import com.example.ic.fixera.Model.Spinner_CarMoel_Response;
import com.example.ic.fixera.Model.Spinner_Service_Respons;
import com.example.ic.fixera.Retrofit.ApiCLint;
import com.example.ic.fixera.Retrofit.Apiinterface;
import com.example.ic.fixera.View.CarModels_View;
import com.example.ic.fixera.View.Spinner_Service_tybe_View;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ahmed on 20/10/2018.
 */

public class Servicetybe_Presenter {
    Spinner_Service_tybe_View getIamges;

    public Servicetybe_Presenter(Context context, Spinner_Service_tybe_View getAccessories)
    {
        this.getIamges=getAccessories;

    }

    public void GetServices(String lang,String tybe,String user,String Cart_id) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("lang", lang);
        queryMap.put("api_token", "100");
        queryMap.put("user_token", user);
        queryMap.put("type", tybe);
        queryMap.put("car_id", Cart_id);
        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);

        Call<Spinner_Service_Respons> call = apiInterface.Services(queryMap);
        call.enqueue(new Callback<Spinner_Service_Respons>() {
            @Override
            public void onResponse(Call<Spinner_Service_Respons> call, Response<Spinner_Service_Respons> response) {

                if (response.isSuccessful()) {
                    if(response.body().getData()!=null) {
                        getIamges.services(response.body().getData().getServicesType());
                    }else {
                        getIamges.ErrorService();
                    }
                }
                else {
                    getIamges.ErrorService();
                }
            }
            @Override
            public void onFailure(Call<Spinner_Service_Respons> call, Throwable t) {
                getIamges.ErrorService();
            }
        });
    }

}
