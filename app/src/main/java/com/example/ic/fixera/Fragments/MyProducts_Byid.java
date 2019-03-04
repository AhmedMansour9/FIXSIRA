package com.example.ic.fixera.Fragments;


import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.ic.fixera.Adapter.MyOrders_Products_Adapter;
import com.example.ic.fixera.Adapter.MyOrders_Service_Adapter;
import com.example.ic.fixera.Adapter.Products_Byid_Adapter;
import com.example.ic.fixera.Language;
import com.example.ic.fixera.Model.Info;
import com.example.ic.fixera.Model.My_Order_Services;
import com.example.ic.fixera.Model.OrdersProducts;
import com.example.ic.fixera.NetworikConntection;
import com.example.ic.fixera.Presenter.MyOrders_Products_Presenter;
import com.example.ic.fixera.Presenter.MyProductsById_Presenter;
import com.example.ic.fixera.Presenter.Review_Presenter;
import com.example.ic.fixera.View.MyOrdersService;
import com.example.ic.fixera.View.ProductsById_View;
import com.example.ic.fixera.View.Review_View;
import com.fixsira.R;
import com.fourhcode.forhutils.FUtilsValidation;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyProducts_Byid extends Fragment implements Review_View,MyOrdersService,ProductsById_View,SwipeRefreshLayout.OnRefreshListener{


    public MyProducts_Byid() {
        // Required empty public constructor
    }
    RecyclerView recyclerView;
    SwipeRefreshLayout mSwipeRefreshLayout;
    MyOrders_Service_Adapter categories_adapter;
    SharedPreferences Shared;
    String user;
    MyProductsById_Presenter myOrders_products;
    Products_Byid_Adapter myOrders_products_adapter;
    String   id;
    View view;
    Review_Presenter review_presenter;
    EditText commnt;
    String comment;
    NetworikConntection networikConntection;
    String rating;
    Button Review;
    RatingBar ratingBar;
    String lan;
    ProgressBar progrossRating;
    Dialog dialogFragment;
    FrameLayout myoderproductsbyid;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_my_products__byid, container, false);
        Shared=getActivity().getSharedPreferences("login",MODE_PRIVATE);
        user=Shared.getString("logggin",null);
        networikConntection=new NetworikConntection(getActivity());
        myoderproductsbyid=view.findViewById(R.id.myoderproductsbyid);
        myOrders_products=new MyProductsById_Presenter(getContext(),this);
        Recyclview();
        SwipRefresh();
        Bundle args = getArguments();
        if (args != null) {
            id=args.getString("id");
        }
        if(Language.isRTL()) {
            lan = "ar";
        }else {
            lan="en";
        }


        return view;
    }
    public void Recyclview(){
        recyclerView = view.findViewById(R.id.recycler_MyOrdersProductsByid);
        recyclerView.setHasFixedSize(true);
    }
    public void SwipRefresh(){
        mSwipeRefreshLayout =  view.findViewById(R.id.swipe_MyOrdersProductsByid);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);

        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
           if(networikConntection.isNetworkAvailable(getContext())) {
               if (Language.isRTL()) {
                   mSwipeRefreshLayout.setRefreshing(true);
                   myOrders_products.ProductsByid(user, "ar", id);
               } else {
                   mSwipeRefreshLayout.setRefreshing(true);
                   myOrders_products.ProductsByid(user, "en", id);
               }
           }else {
               Snackbar.make(myoderproductsbyid,getResources().getString(R.string.internet),1500);
           }
            }
        });
    }
    @Override
    public void GetDetailsOrder(Info list) {

    }

    @Override
    public void GetProducts(List<OrdersProducts> list) {
        myOrders_products_adapter = new Products_Byid_Adapter(list,getContext());
        myOrders_products_adapter.setClickL(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(myOrders_products_adapter);
        mSwipeRefreshLayout.setRefreshing(false);

    }

    @Override
    public void review() {
    }

    @Override
    public void Error() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void reviewProduct() {
        dialogFragment.cancel();
        Toast.makeText(getActivity(),getResources().getString(R.string.ratedsuccess), Toast.LENGTH_SHORT).show();
        progrossRating.setVisibility(View.GONE);

    }

    @Override
    public void reviewed() {
        dialogFragment.cancel();
        Toast.makeText(getActivity(),getResources().getString(R.string.rated), Toast.LENGTH_SHORT).show();
        progrossRating.setVisibility(View.GONE);

    }

    @Override
    public void ErrorProduct() {
        mSwipeRefreshLayout.setRefreshing(false);
        progrossRating.setVisibility(View.GONE);

    }

    @Override
    public void onRefresh() {
        if(networikConntection.isNetworkAvailable(getContext())) {
            if (Language.isRTL()) {
                mSwipeRefreshLayout.setRefreshing(true);
                myOrders_products.ProductsByid(user, "ar", id);
            } else {
                mSwipeRefreshLayout.setRefreshing(true);
                myOrders_products.ProductsByid(user, "en", id);
            }
        }else {
            Snackbar.make(myoderproductsbyid,getResources().getString(R.string.internet),1500);
        }
    }

    @Override
    public void OrdersService(My_Order_Services my_order_services) {

    }

    @Override
    public void ReviewOrders(final My_Order_Services my_order_services) {
        dialogFragment=new Dialog(getActivity());
        dialogFragment.requestWindowFeature((Window.FEATURE_NO_TITLE));
        dialogFragment.setContentView(R.layout.dialog_review);
        commnt=dialogFragment.findViewById(R.id.comment);
        Review=dialogFragment.findViewById(R.id.Review);

        review_presenter=new Review_Presenter(getContext(),this);
        ratingBar=dialogFragment.findViewById(R.id.ratingBar);

        progrossRating=dialogFragment.findViewById(R.id.progrossRating);
        Review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rating=String.valueOf(ratingBar.getRating());
                comment=commnt.getText().toString();


                if(commnt.getText().toString().equals("")){
                    FUtilsValidation.isEmpty(commnt,"Insert Comment");
                }else {
                    progrossRating.setVisibility(View.VISIBLE);
                    review_presenter.ReviewProduct(user, lan, comment, my_order_services.getId(), rating);
                }
            }
        });
        dialogFragment.show();


    }

    @Override
    public void ChangeStatue(My_Order_Services my_order_services) {

    }
}
