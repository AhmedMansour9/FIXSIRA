package com.example.ic.fixera.Retrofit;

import com.example.ic.fixera.Model.CarWashing_Response;
import com.example.ic.fixera.Model.CartResponse;
import com.example.ic.fixera.Model.Cart_Response;
import com.example.ic.fixera.Model.Categoris_Sparts_Response;
import com.example.ic.fixera.Model.Counter_Response;
import com.example.ic.fixera.Model.Gallery_Image_Response;
import com.example.ic.fixera.Model.Hours_Vendor_Response;
import com.example.ic.fixera.Model.MyOrders_Products_Response;
import com.example.ic.fixera.Model.MyOrders_Service;
import com.example.ic.fixera.Model.MyOrders_Service_Response;
import com.example.ic.fixera.Model.MyProducts_byId_Response;
import com.example.ic.fixera.Model.OrderCart_Response;
import com.example.ic.fixera.Model.Order_Service_Response;
import com.example.ic.fixera.Model.Profile_Response;
import com.example.ic.fixera.Model.Profile_Vendor_Response;
import com.example.ic.fixera.Model.RegisterFaceResponse;
import com.example.ic.fixera.Model.RegisterResponse;
import com.example.ic.fixera.Model.Review;
import com.example.ic.fixera.Model.Review_Response;
import com.example.ic.fixera.Model.Service_Response;
import com.example.ic.fixera.Model.Sparts_Response;
import com.example.ic.fixera.Model.Spinner_CarMoel_Response;
import com.example.ic.fixera.Model.Spinner_Service_Respons;
import com.example.ic.fixera.Model.UserLoginResponse;
import com.example.ic.fixera.Model.Vendor_ServiceResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Created by HP on 04/09/2018.
 */

public interface Apiinterface {


    @POST("signupMobile")
    Call<RegisterResponse> register(@QueryMap Map<String, String> queryMab);

    @POST("login")
    Call<UserLoginResponse> Login(@QueryMap Map<String, String> queryMab);
    @POST("signupMobileGoogle")
    Call<UserLoginResponse> RegisterGoogle(@QueryMap Map<String,String> queryMab);
    @POST("signupMobileFacebook")
    Call<RegisterFaceResponse> RegisterFace_Book(@QueryMap Map<String,String> queryMab);


    @POST("productsList")
    Call<Sparts_Response> Get_Sparts(@QueryMap Map<String,String> queryMab);

    @POST("categoryList")
    Call<Categoris_Sparts_Response> GetCategroiesResponse(@QueryMap Map<String,String> queryMab);

    @POST("productImages")
    Call<Gallery_Image_Response> GetIamges(@QueryMap Map<String,String> queryMab);

    @POST("locationList")
    Call<CarWashing_Response> GetCars(@QueryMap Map<String,String> queryMab);

    @POST("addToCart")
    Call<Cart_Response> AddCart(@QueryMap Map<String,String> queryMab);

    @POST("getServices")
    Call<Service_Response> getService(@QueryMap Map<String,String> queryMab);

    @POST("showCart")
    Call<CartResponse> ShowCart(@QueryMap Map<String,String> queryMab);

    @POST("deleteFromCart")
    Call<CartResponse> DeleteCart(@QueryMap Map<String,String> queryMab);

    @POST("orderServices")
    Call<Order_Service_Response> Order_Service(@QueryMap Map<String,String> queryMab);

    @POST("profile")
    Call<Profile_Response> Getprofile(@QueryMap Map<String,String> queryMab);

    @POST("getVendorProfile")
    Call<Profile_Vendor_Response> GetprofileVendor(@QueryMap Map<String,String> queryMab);


    @POST("myOrderServices")
    Call<MyOrders_Service_Response> getOrderService(@QueryMap Map<String,String> queryMab);

    @POST("reviewProduct")
    Call<Review_Response> ReviewProduct(@QueryMap Map<String,String> queryMab);

    @POST("reviewVendor")
    Call<Review_Response> Review(@QueryMap Map<String,String> queryMab);

    @POST("counterCart")
    Call<Counter_Response> Counter(@QueryMap Map<String,String> queryMab);

    @POST("orderShop")
    Call<OrderCart_Response> Order_Cart(@QueryMap Map<String,String> queryMab);

    @POST("myOrderShop")
    Call<MyOrders_Products_Response> MyOrdersProducts(@QueryMap Map<String,String> queryMab);

    @POST("getDetelisOrderShop")
    Call<MyProducts_byId_Response> ProductsByid(@QueryMap Map<String,String> queryMab);

    @POST("carModelList")
    Call<Spinner_CarMoel_Response> CarModels(@QueryMap Map<String,String> queryMab);

    @POST("servicesType")
    Call<Spinner_Service_Respons> Services(@QueryMap Map<String,String> queryMab);

    @POST("getServices")
    Call<Vendor_ServiceResponse> GetServices(@QueryMap Map<String,String> queryMab);

    @POST("getDays")
    Call<Hours_Vendor_Response> GetHours(@QueryMap Map<String,String> queryMab);

}

