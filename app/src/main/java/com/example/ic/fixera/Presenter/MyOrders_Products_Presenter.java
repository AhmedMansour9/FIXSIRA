package com.example.ic.fixera.Presenter;

import android.content.Context;

import com.example.ic.fixera.Model.MyOrders_Products_Response;
import com.example.ic.fixera.Model.MyOrders_Service_Response;
import com.example.ic.fixera.Retrofit.ApiCLint;
import com.example.ic.fixera.Retrofit.Apiinterface;
import com.example.ic.fixera.View.MyOrdersService_View;
import com.example.ic.fixera.View.MyOrders_Products_View;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ahmed on 17/10/2018.
 */

public class MyOrders_Products_Presenter {

    MyOrders_Products_View order;

    public MyOrders_Products_Presenter(Context context, MyOrders_Products_View order)
    {
        this.order=order;

    }

    public void OrderService(String user,String lan) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("lang", lan);
        queryMap.put("api_token","100");
        queryMap.put("user_token", user);

        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);


        Call<MyOrders_Products_Response> call = apiInterface.MyOrdersProducts(queryMap);
        call.enqueue(new Callback<MyOrders_Products_Response>() {
            @Override
            public void onResponse(Call<MyOrders_Products_Response> call, Response<MyOrders_Products_Response> response) {

                if (response.isSuccessful()) {
                    if(response.body().getData().getOrders()!=null) {
                        order.GetMyOrders(response.body().getData().getOrders());
                    }else {
                        order.Error();
                    }
                } else {
                    order.Error();
                }
            }


            @Override
            public void onFailure(Call<MyOrders_Products_Response> call, Throwable t) {
                order.Error();

            }
        });
    }


}
