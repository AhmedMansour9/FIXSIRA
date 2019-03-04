package com.example.ic.fixera.Presenter;

import android.content.Context;

import com.example.ic.fixera.Model.Sparts_Response;
import com.example.ic.fixera.Retrofit.ApiCLint;
import com.example.ic.fixera.Retrofit.Apiinterface;
import com.example.ic.fixera.View.Sparts_View;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ic on 10/7/2018.
 */

public class SpartsProducts_Prsenter {
    Sparts_View sparts_view;

    public SpartsProducts_Prsenter(Context context, Sparts_View sparts)
    {
        this.sparts_view=sparts;

    }
    public void GetOffers(String lan,String page) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("api_token","100");
        queryMap.put("lang",lan);
        queryMap.put("page", page);
        queryMap.put("user_token", "1123");


        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);


        Call<Sparts_Response> call = apiInterface.Get_Offers(queryMap);
        call.enqueue(new Callback<Sparts_Response>() {
            @Override
            public void onResponse(Call<Sparts_Response> call, Response<Sparts_Response> response) {

                if (response.isSuccessful()) {
                    if(response.body().getData().getProducts()!=null) {
                        sparts_view.ListSparts(response.body().getData().getProducts().getData());
                    }else {
                        sparts_view.ErrorSparts();
                    }
                } else {
                    sparts_view.ErrorSparts();
                }
            }


            @Override
            public void onFailure(Call<Sparts_Response> call, Throwable t) {
                sparts_view.ErrorSparts();

            }
        });
    }
    public void GetSparts(String lan,String page,String id) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("api_token","100");
        queryMap.put("lang",lan);
        queryMap.put("page", page);
        queryMap.put("user_token", "1123");
        queryMap.put("cat_id",id);

        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);


        Call<Sparts_Response> call = apiInterface.Get_Sparts(queryMap);
        call.enqueue(new Callback<Sparts_Response>() {
            @Override
            public void onResponse(Call<Sparts_Response> call, Response<Sparts_Response> response) {

                if (response.isSuccessful()) {
                    if(response.body().getData().getProducts()!=null) {
                        sparts_view.ListSparts(response.body().getData().getProducts().getData());
                    }else {
                        sparts_view.EmptySparts();
                    }
                } else {
                    sparts_view.ErrorSparts();
                }
            }


            @Override
            public void onFailure(Call<Sparts_Response> call, Throwable t) {
                sparts_view.ErrorSparts();

            }
        });
    }
    public void GetSearchSparts(String lan,String search) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("api_token","100");
        queryMap.put("lang",lan);
        queryMap.put("srch_term", search);

        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);


        Call<Sparts_Response> call = apiInterface.Get_Sparts(queryMap);
        call.enqueue(new Callback<Sparts_Response>() {
            @Override
            public void onResponse(Call<Sparts_Response> call, Response<Sparts_Response> response) {

                if (response.isSuccessful()) {
                    if(response.body().getData().getProducts()!=null) {
                        sparts_view.ListSparts(response.body().getData().getProducts().getData());
                    }else {
                        sparts_view.EmptySparts();
                    }
                } else {
                    sparts_view.ErrorSparts();
                }
            }


            @Override
            public void onFailure(Call<Sparts_Response> call, Throwable t) {
                sparts_view.ErrorSparts();

            }
        });
    }
    public void GetVendorsProducts(String lan,String vendorid) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("api_token","100");
        queryMap.put("lang",lan);
        queryMap.put("vendor_id", vendorid);

        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);


        Call<Sparts_Response> call = apiInterface.Get_Sparts(queryMap);
        call.enqueue(new Callback<Sparts_Response>() {
            @Override
            public void onResponse(Call<Sparts_Response> call, Response<Sparts_Response> response) {

                if (response.isSuccessful()) {
                    if(response.body().getData().getProducts()!=null) {
                        sparts_view.ListSparts(response.body().getData().getProducts().getData());
                    }else {
                        sparts_view.ErrorSparts();
                    }
                } else {
                    sparts_view.ErrorSparts();
                }
            }


            @Override
            public void onFailure(Call<Sparts_Response> call, Throwable t) {
                sparts_view.ErrorSparts();

            }
        });
    }
}