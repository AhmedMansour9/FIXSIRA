package com.example.ic.fixera.Presenter;

import android.content.Context;

import com.example.ic.fixera.Model.CartResponse;
import com.example.ic.fixera.Model.Order_Service_Response;
import com.example.ic.fixera.Retrofit.ApiCLint;
import com.example.ic.fixera.Retrofit.Apiinterface;
import com.example.ic.fixera.View.OrderService_View;
import com.example.ic.fixera.View.RegisterView;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ahmed on 15/10/2018.
 */

public class Order_Service_Presenter {
    OrderService_View orderService_view;

    public Order_Service_Presenter(Context context, OrderService_View registerView)
    {
        this.orderService_view=registerView;

    }


    public void Order_Service(String lang,String user,String Service_id,String Service_type,String Phone,String Car_Name,String Car_Model
             ,String year,String Date,String lat,String lon,String modelid,String tybeid,String Services) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("lang", lang);
        queryMap.put("api_token", "100");
        queryMap.put("user_token", user);
        queryMap.put("location_id", Service_id);
        queryMap.put("services_type", Service_type);
        queryMap.put("phone", Phone);
        queryMap.put("car_name", Car_Name);
        queryMap.put("car_model", Car_Model);
        queryMap.put("car_model_year", year);
        queryMap.put("schedule_date", Date);
        queryMap.put("lat", lat);
        queryMap.put("lng", lon);
        queryMap.put("car_model_id",modelid);
        queryMap.put("type_id", tybeid);
        queryMap.put("type_services",Services);
        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);

        Call<com.example.ic.fixera.Model.Order_Service_Response> call = apiInterface.Order_Service(queryMap);
        call.enqueue(new Callback<com.example.ic.fixera.Model.Order_Service_Response>() {
            @Override
            public void onResponse(Call<com.example.ic.fixera.Model.Order_Service_Response> call, Response<com.example.ic.fixera.Model.Order_Service_Response> response) {

                if (response.isSuccessful()) {
                        orderService_view.Orders(response.body().getData().getOrderId(),response.body().getData().getOrderPrice());
                } else {
                    orderService_view.Error();
                }
            }
            @Override
            public void onFailure(Call<com.example.ic.fixera.Model.Order_Service_Response> call, Throwable t) {
                orderService_view.Error();

            }
        });
    }

}
