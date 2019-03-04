package com.example.ic.fixera.Presenter;

import android.content.Context;

import com.example.ic.fixera.Model.Info;
import com.example.ic.fixera.Model.MyOrders_Service_Response;
import com.example.ic.fixera.Model.MyProducts_byId_Response;
import com.example.ic.fixera.Retrofit.ApiCLint;
import com.example.ic.fixera.Retrofit.Apiinterface;
import com.example.ic.fixera.View.MyOrdersService_View;
import com.example.ic.fixera.View.ProductsById_View;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ahmed on 17/10/2018.
 */

public class MyProductsById_Presenter {
    ProductsById_View order;

    public MyProductsById_Presenter(Context context, ProductsById_View order)
    {
        this.order=order;

    }

    public void ProductsByid(String user,String lan,String id) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("lang", lan);
        queryMap.put("api_token","100");
//        queryMap.put("user_token", user);
        queryMap.put("order_id", id);

        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);


        Call<MyProducts_byId_Response> call = apiInterface.ProductsByid(queryMap);
        call.enqueue(new Callback<MyProducts_byId_Response>() {
            @Override
            public void onResponse(Call<MyProducts_byId_Response> call, Response<MyProducts_byId_Response> response) {

                if (response.isSuccessful()) {
                    if(response.body().getData()!=null) {
//                    order.GetDetailsOrder(response.body().getData().getInfo());
                        order.GetProducts(response.body().getData().getInfo().getOrderedItems());
                    }else {
                        order.Error();
                    }
                } else {
                    order.Error();
                }
            }


            @Override
            public void onFailure(Call<MyProducts_byId_Response> call, Throwable t) {
                order.Error();

            }
        });
    }


}
