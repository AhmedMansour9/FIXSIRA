package com.example.ic.fixera.Activites;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;

import com.crashlytics.android.Crashlytics;
import com.example.ic.fixera.Adapter.Categories_Sparts_Adapter;
import com.example.ic.fixera.Adapter.MyServices_Vendor_Adapter;
import com.example.ic.fixera.Language;
import com.example.ic.fixera.Model.Vendor_Services;
import com.example.ic.fixera.NetworikConntection;
import com.example.ic.fixera.Presenter.Caetgoris_Sparts_Presenter;
import com.example.ic.fixera.Presenter.MyServices_Vendor_Presenter;
import com.example.ic.fixera.View.MyServices_Vendor_View;
import com.fixsira.R;

import java.util.List;

import io.fabric.sdk.android.Fabric;

public class MyServices_vendor extends AppCompatActivity implements MyServices_Vendor_View,SwipeRefreshLayout.OnRefreshListener{
    String vendor_id,user_token,tybe;
    MyServices_Vendor_Presenter categories;
    RecyclerView recyclerView ;
    SwipeRefreshLayout mSwipeRefreshLayout;
    View view;
    MyServices_Vendor_Adapter categories_adapter;
    NetworikConntection networikConntection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_my_services_vendor);
        getData();
        categories=new MyServices_Vendor_Presenter(MyServices_vendor.this,this);
        networikConntection=new NetworikConntection(getApplicationContext());
        Recyclview();
        SwipRefresh();

    }

    public void getData(){
        vendor_id = getIntent().getStringExtra("vendor_id");
        user_token = getIntent().getStringExtra("user_token");
        tybe = getIntent().getStringExtra("tybe");
    }

    @Override
    public void ListServices(List<Vendor_Services> list) {
        categories_adapter = new MyServices_Vendor_Adapter(list,this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(categories_adapter);
        mSwipeRefreshLayout.setRefreshing(false);

    }

    @Override
    public void Error() {
        mSwipeRefreshLayout.setRefreshing(false);
    }
    public void Recyclview(){
        recyclerView =findViewById(R.id.recycler_MyServices);
        recyclerView.setHasFixedSize(true);
    }

    public void SwipRefresh(){
        mSwipeRefreshLayout =findViewById(R.id.swipe_Myservices);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);

        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                if(networikConntection.isNetworkAvailable(MyServices_vendor.this)) {
                    if (Language.isRTL()) {
                        mSwipeRefreshLayout.setRefreshing(true);
                        categories.ServicesVendor(user_token,"ar",vendor_id,tybe);
                    } else {
                        mSwipeRefreshLayout.setRefreshing(true);
                        categories.ServicesVendor(user_token,"en",vendor_id,tybe);
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
            categories.ServicesVendor(user_token,"ar",vendor_id,tybe);
        } else {
            mSwipeRefreshLayout.setRefreshing(true);
            categories.ServicesVendor(user_token,"en",vendor_id,tybe);
        }
    }
}
