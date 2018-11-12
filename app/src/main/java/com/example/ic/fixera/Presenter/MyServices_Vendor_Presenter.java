package com.example.ic.fixera.Presenter;

import android.content.Context;

import com.example.ic.fixera.Model.MyProducts_byId_Response;
import com.example.ic.fixera.Model.Vendor_ServiceResponse;
import com.example.ic.fixera.Retrofit.ApiCLint;
import com.example.ic.fixera.Retrofit.Apiinterface;
import com.example.ic.fixera.View.MyServices_Vendor_View;
import com.example.ic.fixera.View.ProductsById_View;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ahmed on 28/10/2018.
 */

public class MyServices_Vendor_Presenter {

    MyServices_Vendor_View order;

    public MyServices_Vendor_Presenter(Context context, MyServices_Vendor_View order)
    {
        this.order=order;

    }

    public void ServicesVendor(String user,String lan,String id,String tybe) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("lang", lan);
        queryMap.put("api_token","100");
        queryMap.put("user_token", user);
        queryMap.put("vendor_id", id);
        queryMap.put("type", tybe);
        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);


        Call<Vendor_ServiceResponse> call = apiInterface.GetServices(queryMap);
        call.enqueue(new Callback<Vendor_ServiceResponse>() {
            @Override
            public void onResponse(Call<Vendor_ServiceResponse> call, Response<Vendor_ServiceResponse> response) {

                if (response.isSuccessful()) {
                    if(response.body().getData()!=null) {
//                    order.GetDetailsOrder(response.body().getData().getInfo());
                        order.ListServices(response.body().getData());
                    }else {
                        order.Error();
                    }
                } else {
                    order.Error();
                }
            }


            @Override
            public void onFailure(Call<Vendor_ServiceResponse> call, Throwable t) {
                order.Error();

            }
        });
    }


}
