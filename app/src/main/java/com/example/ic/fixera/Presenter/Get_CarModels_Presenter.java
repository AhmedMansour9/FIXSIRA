package com.example.ic.fixera.Presenter;

import android.content.Context;

import com.example.ic.fixera.Model.Gallery_Image_Response;
import com.example.ic.fixera.Model.Spinner_CarModel;
import com.example.ic.fixera.Model.Spinner_CarMoel_Response;
import com.example.ic.fixera.Retrofit.ApiCLint;
import com.example.ic.fixera.Retrofit.Apiinterface;
import com.example.ic.fixera.View.CarModels_View;
import com.example.ic.fixera.View.Gallery_View;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ahmed on 20/10/2018.
 */

public class Get_CarModels_Presenter {

    CarModels_View getIamges;

    public Get_CarModels_Presenter(Context context, CarModels_View getAccessories)
    {
        this.getIamges=getAccessories;

    }

    public void GetCarModel(String lang,String user) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("lang", lang);
        queryMap.put("api_token", "100");
        queryMap.put("user_token", user);

        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);

        Call<Spinner_CarMoel_Response> call = apiInterface.CarModels(queryMap);
        call.enqueue(new Callback<Spinner_CarMoel_Response>() {
            @Override
            public void onResponse(Call<Spinner_CarMoel_Response> call, Response<Spinner_CarMoel_Response> response) {

                if (response.isSuccessful()) {
                    if(response.body().getData()!=null) {
                        getIamges.GetCarModels(response.body().getData().getCarModel());
                    }else {
                        getIamges.Error();
                    }
                }
                else {
                    getIamges.Error();
                }
            }
            @Override
            public void onFailure(Call<Spinner_CarMoel_Response> call, Throwable t) {
                getIamges.Error();
            }
        });
    }

}
