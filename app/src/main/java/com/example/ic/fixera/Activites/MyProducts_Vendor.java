package com.example.ic.fixera.Activites;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;

import com.example.ic.fixera.Adapter.MyProducts_vendor_Adapter;
import com.example.ic.fixera.Adapter.MyServices_Vendor_Adapter;
import com.example.ic.fixera.Adapter.SparParts_Adapter;
import com.example.ic.fixera.Language;
import com.example.ic.fixera.Model.Sparts_Details;
import com.example.ic.fixera.NetworikConntection;
import com.example.ic.fixera.Presenter.MyServices_Vendor_Presenter;
import com.example.ic.fixera.Presenter.Sparts_Prsenter;
import com.example.ic.fixera.View.Sparts_View;
import com.fixe.fixera.R;

import java.util.List;

public class MyProducts_Vendor extends AppCompatActivity implements Sparts_View,SwipeRefreshLayout.OnRefreshListener{
    String vendor_id,user_token,tybe;
    Sparts_Prsenter products;
    RecyclerView recyclerView ;
    SwipeRefreshLayout mSwipeRefreshLayout;
    View view;
    MyProducts_vendor_Adapter categories_adapter;
    NetworikConntection networikConntection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_my_products__vendor);
        getData();
        products=new Sparts_Prsenter(MyProducts_Vendor.this,this);
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
        recyclerView =findViewById(R.id.recycler_Products);
        recyclerView.setHasFixedSize(true);
    }

    public void SwipRefresh(){
        mSwipeRefreshLayout =findViewById(R.id.swipe_Products);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);

        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                if(networikConntection.isNetworkAvailable(MyProducts_Vendor.this)) {
                    if (Language.isRTL()) {
                        mSwipeRefreshLayout.setRefreshing(true);
                        products.GetVendorsProducts("ar",vendor_id);
                    } else {
                        mSwipeRefreshLayout.setRefreshing(true);
                        products.GetVendorsProducts("en",vendor_id);
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
            products.GetVendorsProducts("ar",vendor_id);
        } else {
            mSwipeRefreshLayout.setRefreshing(true);
            products.GetVendorsProducts("en",vendor_id);
        }

    }

    @Override
    public void ListSparts(List<Sparts_Details> list) {
        categories_adapter=new MyProducts_vendor_Adapter(list,MyProducts_Vendor.this);

     GridLayoutManager  gridLayoutManager=new GridLayoutManager(MyProducts_Vendor.this,2);
        recyclerView.setLayoutManager(gridLayoutManager);

//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(categories_adapter);
        mSwipeRefreshLayout.setRefreshing(false);

    }

    @Override
    public void ErrorSparts() {
        mSwipeRefreshLayout.setRefreshing(false);
    }
}
