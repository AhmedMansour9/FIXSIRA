package com.example.ic.fixera.Presenter;

import android.content.Context;

import com.example.ic.fixera.Model.Gallery_Image_Response;
import com.example.ic.fixera.Retrofit.ApiCLint;
import com.example.ic.fixera.Retrofit.Apiinterface;
import com.example.ic.fixera.View.Gallery_View;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ahmed on 10/10/2018.
 */

public class Gallery_Image_Presenter {

    Gallery_View getIamges;

    public Gallery_Image_Presenter(Context context, Gallery_View getAccessories)
    {
        this.getIamges=getAccessories;

    }

    public void GetImages(String lang,String id) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("lang", lang);
        queryMap.put("api_token", "100");
        queryMap.put("user_token", "100");
        queryMap.put("product_id", id);
        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);

        Call<Gallery_Image_Response> call = apiInterface.GetIamges(queryMap);
        call.enqueue(new Callback<Gallery_Image_Response>() {
            @Override
            public void onResponse(Call<Gallery_Image_Response> call, Response<Gallery_Image_Response> response) {

                if (response.isSuccessful()) {
                    if(response.body().getData()!=null) {
                        getIamges.listimages(response.body().getData().getGalleryImages());
                    }else {
                        getIamges.Error();
                    }
                }
                else {
                    getIamges.Error();
                }
            }
            @Override
            public void onFailure(Call<Gallery_Image_Response> call, Throwable t) {
                getIamges.Error();
            }
        });
    }

}
