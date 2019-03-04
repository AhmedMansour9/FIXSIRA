package com.example.ic.fixera.Fragments;


import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
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
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.ic.fixera.Adapter.MyOrders_Products_Adapter;
import com.example.ic.fixera.Adapter.MyOrders_Service_Adapter;
import com.example.ic.fixera.Language;
import com.example.ic.fixera.Model.My_Order_Services;
import com.example.ic.fixera.NetworikConntection;
import com.example.ic.fixera.Presenter.MyOrdersService_Presenter;
import com.example.ic.fixera.Presenter.MyOrders_Products_Presenter;
import com.example.ic.fixera.Presenter.Review_Presenter;
import com.example.ic.fixera.View.MyOrdersService;
import com.example.ic.fixera.View.MyOrders_Products_View;
import com.example.ic.fixera.View.Review_View;
import com.fixsira.R;
import com.fourhcode.forhutils.FUtilsValidation;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyOrders_Products extends Fragment implements Review_View,SwipeRefreshLayout.OnRefreshListener,MyOrders_Products_View,MyOrdersService {


    public MyOrders_Products() {
        // Required empty public constructor
    }
    MyOrdersService_Presenter orders;
    RecyclerView recyclerView;
    SwipeRefreshLayout mSwipeRefreshLayout;
    MyOrders_Service_Adapter categories_adapter;
    SharedPreferences Shared;
    String user;
    View view;
    MyOrders_Products_Presenter myOrders_products;
    MyOrders_Products_Adapter myOrders_products_adapter;
    String comment;
    Review_Presenter review_presenter;
    EditText commnt;
    String rating;
    Button Review;
    RatingBar ratingBar;
    String lan;
    ProgressBar progrossRating;
    Dialog dialogFragment;
    RelativeLayout dialog_view;
    NetworikConntection networikConntection;
    FrameLayout myoder;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_my_orders_products, container, false);
//        orders=new MyOrdersService_Presenter(getContext(),this);
        Shared=getActivity().getSharedPreferences("login",MODE_PRIVATE);
        user=Shared.getString("logggin",null);
        myoder=view.findViewById(R.id.myoder);
        networikConntection=new NetworikConntection(getActivity());
        myOrders_products=new MyOrders_Products_Presenter(getContext(),this);
        if(Language.isRTL()){
            lan="ar";
        }else {
            lan="en";
        }

        Recyclview();
        SwipRefresh();

        return view;
    }
    public void Recyclview(){
        recyclerView = view.findViewById(R.id.recycler_MyOrdersProducts);
        recyclerView.setHasFixedSize(true);
    }
    public void SwipRefresh(){
        mSwipeRefreshLayout =  view.findViewById(R.id.swipe_MyOrdersProducts);
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
                  myOrders_products.OrderService(user, "ar");
              } else {
                  mSwipeRefreshLayout.setRefreshing(true);
                  myOrders_products.OrderService(user, "en");
              }
          }else{
              Snackbar.make(myoder,getResources().getString(R.string.internet),1500);
          }
            }
        });
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            getFragmentManager().beginTransaction().detach(this).attach(this).commit();
        }
    }
    @Override
    public void onRefresh() {
        if(networikConntection.isNetworkAvailable(getActivity())) {
            if (Language.isRTL()) {
                mSwipeRefreshLayout.setRefreshing(true);
                myOrders_products.OrderService(user, "ar");
            } else {
                mSwipeRefreshLayout.setRefreshing(true);
                myOrders_products.OrderService(user, "en");
            }
        }else {
            Snackbar.make(myoder,getResources().getString(R.string.internet),1500);
        }
    }

    @Override
    public void GetMyOrders(List<com.example.ic.fixera.Model.MyOrders_Products> list) {
        myOrders_products_adapter = new MyOrders_Products_Adapter(list,getContext());
        myOrders_products_adapter.setClickListener(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(myOrders_products_adapter);
        mSwipeRefreshLayout.setRefreshing(false);

    }

    @Override
    public void review() {
        dialogFragment.cancel();
        Toast.makeText(getActivity(), "Thanks For Your Rating", Toast.LENGTH_SHORT).show();
        progrossRating.setVisibility(View.GONE);
    }

    @Override
    public void Error() {
        mSwipeRefreshLayout.setRefreshing(false );
    }

    @Override
    public void reviewProduct() {

    }

    @Override
    public void reviewed() {

    }

    @Override
    public void ErrorProduct() {

    }

    @Override
    public void OrdersService(My_Order_Services my_order_services) {
        MyProducts_Byid fragmen = new MyProducts_Byid();
        Bundle args = new Bundle();
        args.putString("id",String.valueOf(my_order_services.getId()));

        fragmen.setArguments(args);
        getFragmentManager().beginTransaction()
                .replace(R.id.myoder, fragmen )
                .addToBackStack(null)
                .commitAllowingStateLoss();


    }

    @Override
    public void ReviewOrders(final My_Order_Services my_order_services) {
         dialogFragment=new Dialog(getActivity());
        dialogFragment.requestWindowFeature((Window.FEATURE_NO_TITLE));
        dialogFragment.setContentView(R.layout.dialog_review);
        commnt=dialogFragment.findViewById(R.id.comment);
        Review=dialogFragment.findViewById(R.id.Review);
        dialog_view=dialogFragment.findViewById(R.id.dialog_view);
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
                    review_presenter.Review(user, lan, comment, my_order_services.getId(), rating);
                }
            }
        });
      dialogFragment.show();


    }

    @Override
    public void ChangeStatue(My_Order_Services my_order_services) {

    }
}
