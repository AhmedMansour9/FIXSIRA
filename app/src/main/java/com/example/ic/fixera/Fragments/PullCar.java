package com.example.ic.fixera.Fragments;


import android.content.SharedPreferences;
import android.location.Location;
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
import android.widget.RelativeLayout;

import com.example.ic.fixera.Activites.TabsLayouts;
import com.example.ic.fixera.Adapter.CarWash_Adapter;
import com.example.ic.fixera.Adapter.PullCar_Adapter;
import com.example.ic.fixera.Language;
import com.example.ic.fixera.Model.CarWashing;
import com.example.ic.fixera.Model.Filter_Places;
import com.example.ic.fixera.NetworikConntection;
import com.example.ic.fixera.Presenter.CarWashing_presenter;
import com.example.ic.fixera.View.CarWashing_View;
import com.example.ic.fixera.View.Details_Service;
import com.fixe.fixera.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PullCar extends Fragment implements CarWashing_View,SwipeRefreshLayout.OnRefreshListener,Details_Service {


    public PullCar() {
        // Required empty public constructor
    }


    RecyclerView recyclerView;
    SharedPreferences shared;
    PullCar_Adapter adapter;
    CarWashing_presenter cars;
    SwipeRefreshLayout mSwipeRefreshLayout;
    NetworikConntection checknetwork;
    FrameLayout FramePullcar;
   View view;
    List<Filter_Places> filterPlaces=new ArrayList<>();
    NetworikConntection networikConntection;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_pull_car, container, false);
        cars=new CarWashing_presenter(getContext(),this);
       init();
        SwipRefresh();

        return view;
    }
     public void init(){
         recyclerView=view.findViewById(R.id.recycler_pullCar);
         checknetwork=new NetworikConntection(getActivity());
         mSwipeRefreshLayout=view.findViewById(R.id.swipe_pullCar);
         FramePullcar=view.findViewById(R.id.Frame_PullCar);
     }
    public void SwipRefresh(){
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);

        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                if(checknetwork.isNetworkAvailable(getActivity())){
                    mSwipeRefreshLayout.setRefreshing(true);
                    mSwipeRefreshLayout.setEnabled(true);
                    if(Language.isRTL()) {
                        mSwipeRefreshLayout.setRefreshing(true);
                        cars.GetCarWashong("ar","pull_washing","","","");
                    }else{
                        mSwipeRefreshLayout.setRefreshing(true);
                        cars.GetCarWashong("en","pull_washing","","","");
                    }

                }else {
                    Snackbar.make(FramePullcar,R.string.internet,1000).show();
                }
            }
        });
    }

    @Override
    public void onRefresh() {
        if(checknetwork.isNetworkAvailable(getActivity())){
            mSwipeRefreshLayout.setRefreshing(true);
            mSwipeRefreshLayout.setEnabled(true);
            if(Language.isRTL()) {
                mSwipeRefreshLayout.setRefreshing(true);
                cars.GetCarWashong("ar","pull_washing","","","");
            }else{
                mSwipeRefreshLayout.setRefreshing(true);
                cars.GetCarWashong("en","pull_washing","","","");

            }

        }else {
            Snackbar.make(FramePullcar,R.string.internet,1000).show();
        }

    }

    @Override
    public void listsevice(com.example.ic.fixera.Model.Details_Service list) {
        Services fragmen = new Services();
        Bundle args = new Bundle();
        args.putString("id",String.valueOf(list.getId()));
        args.putString("tybe",list.getTybe());
        args.putString("phone",list.getPhone());
        fragmen.setArguments(args);
        getFragmentManager().beginTransaction()
                .replace(R.id.MenuFrame, fragmen )
                .addToBackStack(null)
                .commitAllowingStateLoss();

    }
    public void setData(List<CarWashing> list,int i,double distance){
        Filter_Places filter_places=new Filter_Places();
        filter_places.setAddress(list.get(i).getAddress());
        filter_places.setId(list.get(i).getId());
        filter_places.setVendorName(list.get(i).getVendorName());
        filter_places.setDescription(list.get(i).getDescription());
        filter_places.setUserPhotoUrl(list.get(i).getUserPhotoUrl());
        filter_places.setPhone(list.get(i).getPhone());
        filter_places.setRate(list.get(i).getRateAverage());
        filter_places.setPrice(list.get(i).getPrice());
        filter_places.setTotal_Rates(String.valueOf(list.get(i).getRateTotal()));
        filter_places.setLat(String.valueOf(list.get(i).getLat()));
        filter_places.setLng(String.valueOf(list.get(i).getLng()));
        filter_places.setTelephone(list.get(i).getTelephone());
        filter_places.setName(list.get(i).getName());
        filter_places.setDistance(distance);
        filter_places.setTybe("car_washing");
        filterPlaces.add(filter_places);

    }
    @Override
    public void GetAccessories(List<CarWashing> list) {
        filterPlaces.clear();
        if(TabsLayouts.latitude!=0){
            for(int i=0;i<list.size();i++){
                Location selected_location=new Location("locationA");
                selected_location.setLatitude(TabsLayouts.latitude);
                selected_location.setLongitude(TabsLayouts.longitude);
                Location near_locations=new Location("locationB");
                near_locations.setLatitude(Double.parseDouble(String.valueOf(list.get(i).getLat())));
                near_locations.setLongitude(Double.parseDouble(String.valueOf(list.get(i).getLng())));
                double distance=selected_location.distanceTo(near_locations);

                Filter_Places filter_places=new Filter_Places();
                filter_places.setAddress(list.get(i).getAddress());
                filter_places.setId(list.get(i).getId());
                filter_places.setVendorName(list.get(i).getVendorName());
                filter_places.setDescription(list.get(i).getDescription());
                filter_places.setUserPhotoUrl(list.get(i).getUserPhotoUrl());
                filter_places.setTotal_Rates(String.valueOf(list.get(i).getRateTotal()));
                filter_places.setPhone(list.get(i).getPhone());
                filter_places.setPrice(list.get(i).getPrice());
                filter_places.setRate(list.get(i).getRateAverage());
                filter_places.setLat(String.valueOf(list.get(i).getLat()));
                filter_places.setLng(String.valueOf(list.get(i).getLng()));
                filter_places.setTelephone(list.get(i).getTelephone());
                filter_places.setName(list.get(i).getName());
                filter_places.setDistance(distance);
                filter_places.setTybe("car_washing");
                filterPlaces.add(filter_places);

            }
            Collections.sort(filterPlaces, new Comparator<Filter_Places>() {

                @Override
                public int compare(Filter_Places o1, Filter_Places o2) {
                    return Double.compare(o1.getDistance(), o2.getDistance());
                }
            });
//            Second_filterPlaces=filterPlaces;
//            Collections.sort(filterPlaces, new Comparator<Filter_Places>() {
//
//                @Override
//                public int compare(Filter_Places o1, Filter_Places o2) {
//                    return Double.compare(o1.getRate(),o2.getRate());
//                }
//
//            });
//            Collections.reverse(filterPlaces);
        }else {
            for(int i=0;i<list.size();i++){
                setData(list,i,0);
            }

        }


        adapter = new PullCar_Adapter(filterPlaces,getContext());
        adapter.setClickListener(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        mSwipeRefreshLayout.setRefreshing(false);

    }

    @Override
    public void ErrorAccessories() {
        mSwipeRefreshLayout.setRefreshing(false);
    }
}
