package com.example.ic.fixera.Presenter;

import android.content.Context;

import com.example.ic.fixera.Model.CartResponse;
import com.example.ic.fixera.Model.Cart_Response;
import com.example.ic.fixera.Model.Service_Response;
import com.example.ic.fixera.Retrofit.ApiCLint;
import com.example.ic.fixera.Retrofit.Apiinterface;
import com.example.ic.fixera.View.Service_View;
import com.example.ic.fixera.View.ShowCart_View;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ahmed on 14/10/2018.
 */

public class ShowCart_Presenter {
    ShowCart_View getService;

    public ShowCart_Presenter(Context context, ShowCart_View getService)
    {
        this.getService=getService;

    }

    public void ShowCart(String lang,String user) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("lang", lang);
        queryMap.put("api_token", "100");
        queryMap.put("user_token", user);
        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);

        Call<CartResponse> call = apiInterface.ShowCart(queryMap);
        call.enqueue(new Callback<CartResponse>() {
            @Override
            public void onResponse(Call<CartResponse> call, Response<CartResponse> response) {

                if (response.isSuccessful()) {
                    if(response.body().getData().getProducts()!=null) {
                        getService.ShowCart(response.body().getData().getProducts());
                        getService.ShowTotalprice(String.valueOf(response.body().getData().getTotalPrice()));
                    }else {
                        getService.NoProduct();
                    }
                }
                else {
                    getService.Error();
                }
            }
            @Override
            public void onFailure(Call<CartResponse> call, Throwable t) {
                getService.Error();
            }
        });
    }

}