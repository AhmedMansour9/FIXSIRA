package com.example.ic.fixera.Presenter;

import android.content.Context;

import com.example.ic.fixera.Model.Categoris_Sparts_Response;
import com.example.ic.fixera.Model.Sparts_Category;
import com.example.ic.fixera.Retrofit.ApiCLint;
import com.example.ic.fixera.Retrofit.Apiinterface;
import com.example.ic.fixera.View.Caetgories_Sparts_View;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ic on 10/9/2018.
 */

public class Caetgoris_Sparts_Presenter {
    Caetgories_Sparts_View getAccessories;

    public Caetgoris_Sparts_Presenter(Context context, Caetgories_Sparts_View getAccessories)
    {
        this.getAccessories=getAccessories;

    }

    public void GetCategoriesSpart(String lang) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("lang", lang);
        queryMap.put("api_token", "100");
        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);

        Call<Categoris_Sparts_Response> call = apiInterface.GetCategroiesResponse(queryMap);
        call.enqueue(new Callback<Categoris_Sparts_Response>() {
            @Override
            public void onResponse(Call<Categoris_Sparts_Response> call, Response<Categoris_Sparts_Response> response) {

                if (response.isSuccessful()) {
                    if(response.body().getData()!=null) {
                        getAccessories.GetAccessories((List<Sparts_Category>) response.body().getData());
                    }else {
                        getAccessories.ErrorAccessories();
                    }
                } else {
                    getAccessories.ErrorAccessories();
                }
            }
            @Override
            public void onFailure(Call<Categoris_Sparts_Response> call, Throwable t) {
                getAccessories.ErrorAccessories();

            }
        });
    }

}

