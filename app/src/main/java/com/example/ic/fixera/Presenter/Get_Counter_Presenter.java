package com.example.ic.fixera.Presenter;

import android.content.Context;

import com.example.ic.fixera.Model.Counter_Response;
import com.example.ic.fixera.Model.Review_Response;
import com.example.ic.fixera.Retrofit.ApiCLint;
import com.example.ic.fixera.Retrofit.Apiinterface;
import com.example.ic.fixera.View.Counter_View;
import com.example.ic.fixera.View.Review_View;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ahmed on 17/10/2018.
 */

public class Get_Counter_Presenter {

    Counter_View review;

    public Get_Counter_Presenter(Context context, Counter_View Loginview)
    {
        this.review=Loginview;

    }

    public void GetCounter(String user,String lan) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("api_token","100");
        queryMap.put("lang",lan);
        queryMap.put("user_token", user);


        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);


        Call<Counter_Response> call = apiInterface.Counter(queryMap);
        call.enqueue(new Callback<Counter_Response>() {
            @Override
            public void onResponse(Call<Counter_Response> call, Response<Counter_Response> response) {

                if (response.isSuccessful()) {
                    if(response.body().getData()!=null) {
                        review.Counter(String.valueOf(response.body().getData().getCountCart()));
                    }else {
                        review.Error();
                    }
                } else {
                    review.Error();
                }
            }


            @Override
            public void onFailure(Call<Counter_Response> call, Throwable t) {
                review.Error();

            }
        });
    }
}
