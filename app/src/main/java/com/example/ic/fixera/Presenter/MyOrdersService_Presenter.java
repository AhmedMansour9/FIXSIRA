package com.example.ic.fixera.Presenter;

import android.content.Context;

import com.example.ic.fixera.Model.MyOrders_Service_Response;
import com.example.ic.fixera.Model.UserLoginResponse;
import com.example.ic.fixera.Model.UserRegister;
import com.example.ic.fixera.Retrofit.ApiCLint;
import com.example.ic.fixera.Retrofit.Apiinterface;
import com.example.ic.fixera.View.LoginView;
import com.example.ic.fixera.View.MyOrdersService_View;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ahmed on 15/10/2018.
 */

public class  MyOrdersService_Presenter {

    MyOrdersService_View order;

    public MyOrdersService_Presenter(Context context, MyOrdersService_View order)
    {
        this.order=order;

    }

    public void OrderService(String user,String lan) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("lang", lan);
        queryMap.put("api_token","100");
        queryMap.put("user_token", user);

        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);


        Call<MyOrders_Service_Response> call = apiInterface.getOrderService(queryMap);
        call.enqueue(new Callback<MyOrders_Service_Response>() {
            @Override
            public void onResponse(Call<MyOrders_Service_Response> call, Response<MyOrders_Service_Response> response) {

                if (response.isSuccessful()) {
                    if(response.body().getData()!=null) {
                        order.ShowMyOrdersService(response.body().getData());
                    }else {
                        order.Error();
                    }
                } else {
                    order.Error();
                }
            }


            @Override
            public void onFailure(Call<MyOrders_Service_Response> call, Throwable t) {
                order.Error();

            }
        });
    }


}
