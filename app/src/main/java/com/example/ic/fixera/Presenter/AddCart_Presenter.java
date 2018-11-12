package com.example.ic.fixera.Presenter;

import android.content.Context;

import com.example.ic.fixera.Model.CartResponse;
import com.example.ic.fixera.Model.Cart_Response;
import com.example.ic.fixera.Model.Categoris_Sparts_Response;
import com.example.ic.fixera.Model.Sparts_Category;
import com.example.ic.fixera.Retrofit.ApiCLint;
import com.example.ic.fixera.Retrofit.Apiinterface;
import com.example.ic.fixera.View.Caetgories_Sparts_View;
import com.example.ic.fixera.View.Cart_View;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ahmed on 13/10/2018.
 */

public class AddCart_Presenter {

    Cart_View cart_view;

    public AddCart_Presenter(Context context, Cart_View cart_view)
    {
        this.cart_view=cart_view;

    }

    public void Add_toCart(String lang,String user,String product_id) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("lang", lang);
        queryMap.put("api_token", "100");
        queryMap.put("user_token", user);
        queryMap.put("products_id", product_id);
        queryMap.put("quantity", "1");
        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);

        Call<Cart_Response> call = apiInterface.AddCart(queryMap);
        call.enqueue(new Callback<Cart_Response>() {
            @Override
            public void onResponse(Call<Cart_Response> call, Response<Cart_Response> response) {

                if (response.isSuccessful()) {
                    cart_view.Success();
                } else {
                    cart_view.Failed();
                }
            }
            @Override
            public void onFailure(Call<Cart_Response> call, Throwable t) {

                cart_view.Failed();

            }
        });
    }
    public void Delete_toCart(String lang,String user,String product_id) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("lang", lang);
        queryMap.put("api_token", "100");
        queryMap.put("user_token", user);
        queryMap.put("products_id", product_id);
        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);

        Call<CartResponse> call = apiInterface.DeleteCart(queryMap);
        call.enqueue(new Callback<CartResponse>() {
            @Override
            public void onResponse(Call<CartResponse> call, Response<CartResponse> response) {

                if (response.isSuccessful()) {
                    cart_view.Success();
                } else {
                    cart_view.Failed();
                }
            }
            @Override
            public void onFailure(Call<CartResponse> call, Throwable t) {
                cart_view.Failed();

            }
        });
    }


}
