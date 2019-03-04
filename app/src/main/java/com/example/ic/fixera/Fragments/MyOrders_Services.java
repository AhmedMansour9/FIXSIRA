package com.example.ic.fixera.Fragments;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.ic.fixera.Adapter.Categories_Sparts_Adapter;
import com.example.ic.fixera.Adapter.MyOrders_Service_Adapter;
import com.example.ic.fixera.Language;
import com.example.ic.fixera.Model.MyOrders_Service;
import com.example.ic.fixera.Model.My_Order_Services;
import com.example.ic.fixera.NetworikConntection;
import com.example.ic.fixera.Presenter.Caetgoris_Sparts_Presenter;
import com.example.ic.fixera.Presenter.MyOrdersService_Presenter;
import com.example.ic.fixera.View.MyOrdersService;
import com.example.ic.fixera.View.MyOrdersService_View;
import com.fixsira.R;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;
import static com.facebook.FacebookSdk.getApplicationContext;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyOrders_Services extends Fragment implements MyOrdersService_View,SwipeRefreshLayout.OnRefreshListener,MyOrdersService {


    public MyOrders_Services() {
        // Required empty public constructor
    }

    MyOrdersService_Presenter orders;
    RecyclerView recyclerView;
    SwipeRefreshLayout mSwipeRefreshLayout;
    MyOrders_Service_Adapter categories_adapter;
    SharedPreferences Shared;
    String user;
    NetworikConntection networikConntection;
   View view;
   FrameLayout myodersmaintenence;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_my_orders__maintenence, container, false);
        orders=new MyOrdersService_Presenter(getContext(),this);
        Shared=getActivity().getSharedPreferences("login",MODE_PRIVATE);
        myodersmaintenence=view.findViewById(R.id.myodersmaintenence);
        user=Shared.getString("logggin",null);
        networikConntection=new NetworikConntection(getApplicationContext());
        Recyclview();
        SwipRefresh();

        return view;
    }

    @Override
    public void ShowMyOrdersService(List<MyOrders_Service> list) {
        categories_adapter = new MyOrders_Service_Adapter(list,getContext());
        categories_adapter.setClickListener(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(gridLayoutManager);

        recyclerView.setAdapter(categories_adapter);
        mSwipeRefreshLayout.setRefreshing(false);

    }
    public void Recyclview(){
        recyclerView = view.findViewById(R.id.recycler_MyOrdersService);
        recyclerView.setHasFixedSize(true);
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            getFragmentManager().beginTransaction().detach(this).attach(this).commit();
        }
    }

    public void SwipRefresh(){
        mSwipeRefreshLayout =  view.findViewById(R.id.swipe_MyOrdersService);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);

        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                if (networikConntection.isNetworkAvailable(getContext())) {
                    if (Language.isRTL()) {
                        mSwipeRefreshLayout.setRefreshing(true);
                        orders.OrderService(user, "ar");
                    } else {
                        mSwipeRefreshLayout.setRefreshing(true);
                        orders.OrderService(user, "en");
                    }
                } else {
                Snackbar.make(myodersmaintenence,getResources().getString(R.string.internet),1500);
            }
            }
        });
    }
    @Override
    public void Error() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        if(networikConntection.isNetworkAvailable(getApplicationContext())) {
            if (Language.isRTL()) {
                mSwipeRefreshLayout.setRefreshing(true);
                orders.OrderService(user, "ar");
            } else {
                mSwipeRefreshLayout.setRefreshing(true);
                orders.OrderService(user, "en");
            }
        }else{
            Snackbar.make(myodersmaintenence,getResources().getString(R.string.internet),1500);
        }
    }

    @Override
    public void OrdersService(My_Order_Services my_order_services) {
        Rating_Service fragmen = new Rating_Service();
        Bundle args = new Bundle();
        args.putString("id",String.valueOf(my_order_services.getId()));
        args.putString("tybe",my_order_services.getServicetybe());
        args.putString("vendorid",my_order_services.getVendorid());
        args.putString("carname",my_order_services.getCarname());
        args.putString("carmodel",my_order_services.getCarmodel());
        args.putString("price",my_order_services.getPrice());
        args.putString("img",my_order_services.getImg());
        args.putString("date",my_order_services.getDate());
        args.putString("status",my_order_services.getStatus());

        fragmen.setArguments(args);
        getFragmentManager().beginTransaction()
                .replace(R.id.myodersmaintenence, fragmen )
                .addToBackStack(null)
                .commitAllowingStateLoss();



    }

    @Override
    public void ReviewOrders(My_Order_Services my_order_services) {

    }

    @Override
    public void ChangeStatue(My_Order_Services my_order_services) {

    }
}
