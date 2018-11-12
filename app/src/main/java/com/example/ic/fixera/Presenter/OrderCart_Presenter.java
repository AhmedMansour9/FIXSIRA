package com.example.ic.fixera.Presenter;

import android.content.Context;

import com.example.ic.fixera.Model.OrderCart_Response;
import com.example.ic.fixera.Model.Order_Service_Response;
import com.example.ic.fixera.Retrofit.ApiCLint;
import com.example.ic.fixera.Retrofit.Apiinterface;
import com.example.ic.fixera.View.OrderCart_View;
import com.example.ic.fixera.View.OrderService_View;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ahmed on 17/10/2018.
 */

public class OrderCart_Presenter {
    OrderCart_View orderService_view;

    public OrderCart_Presenter(Context context, OrderCart_View registerView)
    {
        this.orderService_view=registerView;

    }


    public void Order_Service(String lang,String user,String order_note,String name,String email,String phone
            ,String adddress) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("lang", lang);
        queryMap.put("api_token", "100");
        queryMap.put("user_token", user);
        if(order_note!=null) {
            queryMap.put("order_note", order_note);
        }else {
            queryMap.put("order_note", "null");
        }
        queryMap.put("company_name", "");
        queryMap.put("name", name);
        queryMap.put("email", email);
        queryMap.put("phone", phone);
        queryMap.put("fax", "");

        queryMap.put("adddress", adddress);
        queryMap.put("city", "");
        queryMap.put("postcode", "null");


        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);

        Call<OrderCart_Response> call = apiInterface.Order_Cart(queryMap);
        call.enqueue(new Callback<OrderCart_Response>() {
            @Override
            public void onResponse(Call<OrderCart_Response> call, Response<OrderCart_Response> response) {

                if (response.isSuccessful()) {
                    orderService_view.order(String.valueOf(response.body().getData().getOrderId()));
                } else {
                    orderService_view.Error();
                }
            }
            @Override
            public void onFailure(Call<OrderCart_Response> call, Throwable t) {
                orderService_view.Error();

            }
        });
    }

}
