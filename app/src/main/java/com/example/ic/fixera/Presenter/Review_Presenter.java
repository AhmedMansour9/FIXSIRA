package com.example.ic.fixera.Presenter;

import android.content.Context;

import com.example.ic.fixera.Model.Review_Response;
import com.example.ic.fixera.Model.UserLoginResponse;
import com.example.ic.fixera.Model.UserRegister;
import com.example.ic.fixera.Retrofit.ApiCLint;
import com.example.ic.fixera.Retrofit.Apiinterface;
import com.example.ic.fixera.View.LoginView;
import com.example.ic.fixera.View.Review_View;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ahmed on 16/10/2018.
 */

public class Review_Presenter {
    Review_View review;

    public Review_Presenter(Context context, Review_View Loginview)
    {
        this.review=Loginview;

    }

    public void Review(String user,String lan,String comment,String vendorid,String rating) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("api_token","100");
        queryMap.put("lang",lan);
        queryMap.put("user_token", user);
        queryMap.put("comment", comment);
        queryMap.put("vendor_id", vendorid);
        queryMap.put("rating", rating);

        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);


        Call<Review_Response> call = apiInterface.Review(queryMap);
        call.enqueue(new Callback<Review_Response>() {
            @Override
            public void onResponse(Call<Review_Response> call, Response<Review_Response> response) {

                if (response.isSuccessful()) {
                    review.review();
                } else {
                    review.Error();
                }
            }
            @Override
            public void onFailure(Call<Review_Response> call, Throwable t) {
                review.Error();
            }
        });
    }

    public void ReviewProduct(String user,String lan,String comment,String product_id,String rating) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("api_token","100");
        queryMap.put("lang",lan);
        queryMap.put("user_token", user);
        queryMap.put("comment", comment);
        queryMap.put("product_id", product_id);
        queryMap.put("rating", rating);
        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);
        Call<Review_Response> call = apiInterface.ReviewProduct(queryMap);
        call.enqueue(new Callback<Review_Response>() {
            @Override
            public void onResponse(Call<Review_Response> call, Response<Review_Response> response) {

                if (response.isSuccessful()) {
                    review.review();
                } else {
                    review.Error();
                }
            }
            @Override
            public void onFailure(Call<Review_Response> call, Throwable t) {
                review.Error();

            }
        });
    }


}
