package com.example.ic.fixera.Activites;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;

import com.crashlytics.android.Crashlytics;
import com.example.ic.fixera.Adapter.Vendor_Hours_Adapter;
import com.example.ic.fixera.Language;
import com.example.ic.fixera.NetworikConntection;
import com.example.ic.fixera.Presenter.Vendor_Hours_Presenter;
import com.example.ic.fixera.View.Vendor_Hours_View;
import com.fixsira.R;

import java.util.List;

import io.fabric.sdk.android.Fabric;

public class Hours_Vendor extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener,Vendor_Hours_View{
    String vendor_id,user_token,tybe;
    Vendor_Hours_Presenter products;
    RecyclerView recyclerView ;
    SwipeRefreshLayout mSwipeRefreshLayout;
    View view;
    Vendor_Hours_Adapter categories_adapter;
    NetworikConntection networikConntection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_hours__vendor);
        getData();
        products=new Vendor_Hours_Presenter(Hours_Vendor.this,this);
        networikConntection=new NetworikConntection(getApplicationContext());
        Recyclview();
        SwipRefresh();
    }
    public void getData(){
        vendor_id = getIntent().getStringExtra("vendor_id");
        user_token = getIntent().getStringExtra("user_token");
        tybe = getIntent().getStringExtra("tybe");
    }
    public void Recyclview(){
        recyclerView =findViewById(R.id.recycler_Hours);
        recyclerView.setHasFixedSize(true);
    }

    public void SwipRefresh(){
        mSwipeRefreshLayout =findViewById(R.id.swipe_Hours);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);

        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                if(networikConntection.isNetworkAvailable(Hours_Vendor.this)) {
                    if (Language.isRTL()) {
                        mSwipeRefreshLayout.setRefreshing(true);
                        products.VendorHours("ar",vendor_id,user_token);
                    } else {
                        mSwipeRefreshLayout.setRefreshing(true);
                        products.VendorHours("en",vendor_id,user_token);
                    }
                }else {
//                    Snackbar.make(Categories,getResources().getString(R.string.internet),1500).show();
                }
            }


        });
    }

    @Override
    public void onRefresh() {
        if (Language.isRTL()) {
            mSwipeRefreshLayout.setRefreshing(true);
            products.VendorHours("ar",vendor_id,user_token);
        } else {
            mSwipeRefreshLayout.setRefreshing(true);
            products.VendorHours("en",vendor_id,user_token);
        }
    }

    @Override
    public void GetHours(List<com.example.ic.fixera.Model.Hours_Vendor> list) {
        categories_adapter=new Vendor_Hours_Adapter(list,Hours_Vendor.this);

//        GridLayoutManager gridLayoutManager=new GridLayoutManager(Hours_Vendor.this,2);
//        recyclerView.setLayoutManager(gridLayoutManager);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(categories_adapter);
        mSwipeRefreshLayout.setRefreshing(false);

    }

    @Override
    public void Error() {
        mSwipeRefreshLayout.setRefreshing(false);
    }
}
