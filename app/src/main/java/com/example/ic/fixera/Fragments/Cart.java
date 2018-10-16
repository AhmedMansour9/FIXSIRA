package com.example.ic.fixera.Fragments;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.ic.fixera.Activites.TabsLayouts;
import com.example.ic.fixera.Adapter.Cart_Adapter;
import com.example.ic.fixera.Adapter.Categories_Sparts_Adapter;
import com.example.ic.fixera.Language;
import com.example.ic.fixera.Presenter.AddCart_Presenter;
import com.example.ic.fixera.Presenter.Caetgoris_Sparts_Presenter;
import com.example.ic.fixera.Presenter.ShowCart_Presenter;
import com.example.ic.fixera.R;
import com.example.ic.fixera.View.Cart_View;
import com.example.ic.fixera.View.Count_View;
import com.example.ic.fixera.View.ShowCart_View;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;
import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * A simple {@link Fragment} subclass.
 */
public class Cart extends Fragment implements ShowCart_View ,SwipeRefreshLayout.OnRefreshListener,Count_View,Cart_View {


    public Cart() {
        // Required empty public constructor
    }

    ShowCart_Presenter showCart_presenter;
    RecyclerView recyclerView;
    SwipeRefreshLayout mSwipeRefreshLayout;
    View view;
    Cart_Adapter cart_adapter;
    SharedPreferences Shared;
    String user;
    AddCart_Presenter addCart;
    TextView T_Price;
    Button order;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_cart, container, false);
        showCart_presenter=new ShowCart_Presenter(getContext(),this);
        Shared=getActivity().getSharedPreferences("login",MODE_PRIVATE);
        user=Shared.getString("logggin",null);
        T_Price=view.findViewById(R.id.T_Price);
        addCart=new AddCart_Presenter(getContext(),this);
        TabsLayouts.banner.setVisibility(View.GONE);
        order=view.findViewById(R.id.servicerequest);
        Recyclview();
        SwipRefresh();

        return view;
    }
    public void Recyclview(){
        recyclerView = view.findViewById(R.id.recycler_Cart);
        recyclerView.setHasFixedSize(true);
    }
    public void SwipRefresh(){
        mSwipeRefreshLayout =  view.findViewById(R.id.swipe_Cart);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);

        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                if (user != null) {
                    if (Language.isRTL()) {
                        mSwipeRefreshLayout.setRefreshing(true);
                        showCart_presenter.ShowCart("ar", user);
                    } else {
                        mSwipeRefreshLayout.setRefreshing(true);
                        showCart_presenter.ShowCart("en", user);
                    }
                }
            }

        });
    }
    @Override
    public void ShowCart(List<com.example.ic.fixera.Model.Cart> list) {
        if(list!=null) {
            cart_adapter = new Cart_Adapter(list, getContext());
            cart_adapter.count(this);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(cart_adapter);
            mSwipeRefreshLayout.setRefreshing(false);
        }
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void ShowTotalprice(String price) {
            T_Price.setText(price + "LE");
            order.setVisibility(View.VISIBLE);

    }

    @Override
    public void Error() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        if (user != null) {
            if (Language.isRTL()) {
                mSwipeRefreshLayout.setRefreshing(true);
                showCart_presenter.ShowCart("ar", user);
            } else {
                mSwipeRefreshLayout.setRefreshing(true);
                showCart_presenter.ShowCart("en", user);
            }
        }
    }
    @Override
    public void count_plus(String id) {
        mSwipeRefreshLayout.setRefreshing(true);
        if(Language.isRTL()){
            addCart.Add_toCart("ar",user,id);
        }else {
            addCart.Add_toCart("en",user,id);
        }
    }

    @Override
    public void count_minus(String id) {

        mSwipeRefreshLayout.setRefreshing(true);
        if(Language.isRTL()){
            addCart.Delete_toCart("ar",user,id);
        }else {
            addCart.Delete_toCart("en",user,id);
        }
    }

    @Override
    public void Success() {

        if(Language.isRTL()){
            mSwipeRefreshLayout.setRefreshing(true);
            showCart_presenter.ShowCart("ar",user);
        }else {
            mSwipeRefreshLayout.setRefreshing(true);
            showCart_presenter.ShowCart("en",user);
        }
    }

    @Override
    public void Failed() {
        mSwipeRefreshLayout.setRefreshing(false);
    }
}
