package com.example.ic.fixera.Presenter;

import android.content.Context;

import com.example.ic.fixera.Model.CarWashing;
import com.example.ic.fixera.Model.CarWashing_Response;
import com.example.ic.fixera.Model.Categoris_Sparts_Response;
import com.example.ic.fixera.Model.Sparts_Category;
import com.example.ic.fixera.Retrofit.ApiCLint;
import com.example.ic.fixera.Retrofit.Apiinterface;
import com.example.ic.fixera.View.Caetgories_Sparts_View;
import com.example.ic.fixera.View.CarWashing_View;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ahmed on 11/10/2018.
 */

public class CarWashing_presenter {
    CarWashing_View getCars;
    public CarWashing_presenter(Context context, CarWashing_View getAccessories)
    {
        this.getCars=getAccessories;

    }

    public void GetCarWashong(String lang,String car,String Car_id,String service_id,String service){
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("lang", lang);
        queryMap.put("api_token", "100");
        queryMap.put("type", car);
//        if(!Car_id.equals("")) {
//            queryMap.put("car_model_id", Car_id);
//            queryMap.put("type_id", service_id);
//                queryMap.put("type_vist", service);
//        }
        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);

        Call<CarWashing_Response> call = apiInterface.GetCars(queryMap);
        call.enqueue(new Callback<CarWashing_Response>() {
            @Override
            public void onResponse(Call<CarWashing_Response> call, Response<CarWashing_Response> response) {

                if (response.isSuccessful()) {
                    if(response.body().getData()!=null) {
                        getCars.GetAccessories((List<CarWashing>) response.body().getData());
                    }else {
                        getCars.EmptyBranches();
                    }
                } else {
                    getCars.ErrorAccessories();
                }
            }
            @Override
            public void onFailure(Call<CarWashing_Response> call, Throwable t) {
                getCars.ErrorAccessories();

            }
        });
    }

}

