package com.example.ic.fixera.Fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.ic.fixera.Activites.Navigation;
import com.example.ic.fixera.Adapter.Service_Adapter;
import com.example.ic.fixera.Language;
import com.example.ic.fixera.Model.Filter_Places;
import com.example.ic.fixera.Model.Service;
import com.example.ic.fixera.NetworikConntection;
import com.example.ic.fixera.Presenter.Service_Presenter;
import com.example.ic.fixera.View.Details_Service;
import com.example.ic.fixera.View.Service_View;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.fixsira.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;
import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * A simple {@link Fragment} subclass.
 */
public class Services extends Fragment implements SwipeRefreshLayout.OnRefreshListener,Service_View,Details_Service {


    public Services() {
        // Required empty public constructor
    }
    Service_Presenter service_presenter;
    RecyclerView recyclerView;
    Service_Adapter service_adapter;
    SwipeRefreshLayout mSwipeRefreshLayout;
    List<Filter_Places> filterPlaces=new ArrayList<>();
    String tybe,phone;
    View view;
    SharedPreferences Shared;
    String user;
    private String id,title,price,stock,image,description;
    ImageView imgphone;
    NetworikConntection networikConntection;
    FrameLayout ServiceFrame;
    private ShimmerFrameLayout mShimmerViewContainer;
    Toolbar toolbars;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_services, container, false);
        mShimmerViewContainer =view.findViewById(R.id.shimmer_view_container);
        service_presenter=new Service_Presenter(getContext(),this);
        Shared=getActivity().getSharedPreferences("login",MODE_PRIVATE);
        user=Shared.getString("logggin",null);
        networikConntection=new NetworikConntection(getApplicationContext());
        ServiceFrame=view.findViewById(R.id.ServiceFrame);
        toolbars=view.findViewById(R.id.toolbar);
        Navigation.toolbar.setVisibility(View.GONE);
        Navigation.toggle = new ActionBarDrawerToggle(
                getActivity(), Navigation.drawer, toolbars,R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        Navigation.drawer.addDrawerListener(Navigation.toggle);
        Navigation.toggle.syncState();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            toolbars.setNavigationOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (Navigation.drawer.isDrawerOpen(GravityCompat.START)) {
                        Navigation.drawer.closeDrawer(GravityCompat.START);
                    } else {
                        Navigation.drawer.openDrawer(GravityCompat.START);
                    }
                }
            });
        }

        GetData();
        Recyclview();
        SwipRefresh();


        return view;
    }

    public void Recyclview(){
        recyclerView = view.findViewById(R.id.recycler_Service);
        recyclerView.setHasFixedSize(true);
    }
    public void SwipRefresh(){
        mSwipeRefreshLayout =  view.findViewById(R.id.swipe_Service);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);

        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                if (networikConntection.isNetworkAvailable(getContext())) {
                    mShimmerViewContainer.startShimmerAnimation();
                    mShimmerViewContainer.setVisibility(View.VISIBLE);
                    if (Language.isRTL()) {
//                        mSwipeRefreshLayout.setRefreshing(true);
                        service_presenter.GetService("ar", tybe, id, user);
                    } else {
//                        mSwipeRefreshLayout.setRefreshing(true);
                        service_presenter.GetService("en", tybe, id, user);
                    }

                } else {
                    Snackbar.make(ServiceFrame, getResources().getString(R.string.internet), 1500).show();
                }
            }
        });
    }
    public void GetData(){
        Bundle args = getArguments();
        if (args != null) {
            id=args.getString("id");
            tybe=args.getString("tybe");
            phone=args.getString("phone");

        }
    }
    @Override
    public void onRefresh() {
        if (networikConntection.isNetworkAvailable(getContext())) {
            if (Language.isRTL()) {
                mSwipeRefreshLayout.setRefreshing(true);
                service_presenter.GetService("ar", tybe, id, user);
            } else {
                mSwipeRefreshLayout.setRefreshing(true);
                service_presenter.GetService("en", tybe, id, user);
            }
        } else {
            Snackbar.make(ServiceFrame, getResources().getString(R.string.internet), 1500).show();
        }
    }

    @Override
    public void GetService(List<Service> list) {

        service_adapter = new Service_Adapter(list,getContext());
        service_adapter.setClickListener(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(gridLayoutManager);

        recyclerView.setAdapter(service_adapter);
//        mSwipeRefreshLayout.setRefreshing(false);
        mShimmerViewContainer.stopShimmerAnimation();
        mShimmerViewContainer.setVisibility(View.GONE);


    }

    @Override
    public void Error() {
        mSwipeRefreshLayout.setRefreshing(false);
        mShimmerViewContainer.stopShimmerAnimation();
        mShimmerViewContainer.setVisibility(View.GONE);

    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Navigation.Visablty = false;
        Navigation.toolbar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onStop() {
        super.onStop();
        Navigation.Visablty = true;
    }

    @Override
    public void listsevice(com.example.ic.fixera.Model.Details_Service list) {
        Details_Services_fragment fragmen = new Details_Services_fragment();
        Bundle args = new Bundle();
        args.putString("id",String.valueOf(list.getId()));
        args.putString("services_id",String.valueOf(list.getServices_id()));
        args.putString("tybe",tybe);
        args.putString("phone",phone);
        args.putString("discription",list.getDescription());
        args.putString("image",list.getUserPhotoUrl());
        args.putString("placeName",list.getName());
        args.putString("address",list.getAddress());
        args.putString("rate",list.getPhone());
        args.putString("price",list.getPrice());
        args.putString("lati",list.getLat());
        args.putString("lon",list.getLng());

        fragmen.setArguments(args);
        getFragmentManager().beginTransaction()
                .replace(R.id.Frame_PullCar, fragmen )
                .addToBackStack(null)
                .commitAllowingStateLoss();


    }

    @Override
    public void onDetach() {
        super.onDetach();
//        Navigation.toolbar.setVisibility(View.GONE);
    }
}
