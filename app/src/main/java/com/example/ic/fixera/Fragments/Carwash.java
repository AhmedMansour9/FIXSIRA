package com.example.ic.fixera.Fragments;


import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ic.fixera.Activites.TabsLayouts;
import com.example.ic.fixera.Adapter.CarWash_Adapter;
import com.example.ic.fixera.Language;
import com.example.ic.fixera.Model.CarWashing;
import com.example.ic.fixera.Model.Filter_Places;
import com.example.ic.fixera.Model.Servuce_tybe;
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
public class Carwash extends Fragment implements CarWashing_View,SwipeRefreshLayout.OnRefreshListener,Details_Service {


    public Carwash() {
        // Required empty public constructor
    }
    CarWashing_presenter cars;
    RecyclerView recyclerView;
    View view;
    CarWash_Adapter CARS;
    SwipeRefreshLayout mSwipeRefreshLayout;
    List<Filter_Places> filterPlaces=new ArrayList<>();
    NetworikConntection networikConntection;
    String Car_id,Service_id;
    public static String Service;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_carwash, container, false);
        cars=new CarWashing_presenter(getContext(),this);
        networikConntection=new NetworikConntection(getActivity());
        getdata();
     Recyclview();
     SwipRefresh();

   return view;
    }
    public void Recyclview(){
        recyclerView = view.findViewById(R.id.recycler_WashingCar);
        recyclerView.setHasFixedSize(true);
    }
    public void getdata(){
        Bundle args = getArguments();
        if(args!=null){
            Car_id=args.getString("car_id");
            Service_id=args.getString("tybe_id");
            Service=args.getString("service");
        }
    }
    public void SwipRefresh(){
        mSwipeRefreshLayout =  view.findViewById(R.id.swipe_CarWahing);
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
                        cars.GetCarWashong("ar", "car_washing",Car_id,Service_id, Service);
                    } else {
                        mSwipeRefreshLayout.setRefreshing(true);
                        cars.GetCarWashong("en", "car_washing",Car_id,Service_id,Service);
                    }
                }
            }

        });
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

                near_locations.setLatitude(Double.parseDouble(list.get(i).getLat()));
                near_locations.setLongitude(Double.parseDouble(list.get(i).getLng()));
                double distance=selected_location.distanceTo(near_locations);

                Filter_Places filter_places=new Filter_Places();
                filter_places.setAddress(list.get(i).getAddress());
                filter_places.setId(list.get(i).getId());
                filter_places.setVendorName(list.get(i).getVendorName());
                filter_places.setDescription(list.get(i).getDescription());
                filter_places.setUserPhotoUrl(list.get(i).getUserPhotoUrl());
                filter_places.setPhone(list.get(i).getPhone());
                filter_places.setPrice(String.valueOf(list.get(i).getPrice()));
                filter_places.setTotal_Rates(String.valueOf(list.get(i).getRateTotal()));
                filter_places.setRate(list.get(i).getRateAverage());
                filter_places.setLat(String.valueOf(list.get(i).getLat()));
                filter_places.setLng(String.valueOf(list.get(i).getLng()));
                filter_places.setTelephone(list.get(i).getTelephone());
                filter_places.setName(list.get(i).getName());
                filter_places.setDistance(distance);
                filter_places.setVendor_id(list.get(i).getVendorId());
                filter_places.setTotal_Price(list.get(i).getTotalPrice());
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


        CARS = new CarWash_Adapter(filterPlaces,getContext());
        CARS.setClickListener(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(CARS);
        mSwipeRefreshLayout.setRefreshing(false);

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
        filter_places.setLat(String.valueOf(list.get(i).getLat()));
        filter_places.setLng(String.valueOf(list.get(i).getLng()));
        filter_places.setTelephone(list.get(i).getTelephone());
        filter_places.setTotal_Rates(String.valueOf(list.get(i).getRateTotal()));
        filter_places.setName(list.get(i).getName());
        filter_places.setVendor_id(list.get(i).getVendorId());
        filter_places.setPrice(String.valueOf(list.get(i).getPrice()));
        filter_places.setDistance(distance);
        filter_places.setPrice(String.valueOf(list.get(i).getPrice()));
        filter_places.setTybe("car_washing");
        filter_places.setTotal_Price(list.get(i).getTotalPrice());
        filterPlaces.add(filter_places);

    }
    @Override
    public void ErrorAccessories() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        filterPlaces.clear();
        if(Language.isRTL()){
            mSwipeRefreshLayout.setRefreshing(true);
            cars.GetCarWashong("ar","car_washing",Car_id,Service_id,Service);
        }else {
            mSwipeRefreshLayout.setRefreshing(true);
            cars.GetCarWashong("en","car_washing",Car_id,Service_id,Service);
        }
    }

    @Override
    public void listsevice(com.example.ic.fixera.Model.Details_Service list) {
        Profile_Vendor fragmen = new Profile_Vendor();
        Bundle args = new Bundle();
        args.putString("id",String.valueOf(list.getId()));
        args.putString("tybe",list.getTybe());
        args.putString("phone",list.getPhone());
        args.putString("address",list.getAddress());
        args.putString("price",list.getPrice());
        args.putString("image",list.getUserPhotoUrl());
        args.putString("placeName",list.getName());
        args.putString("car_id",Car_id);
        args.putString("tybe_id",Service_id);
        args.putString("rate",String.valueOf(list.getRate()));
        args.putString("Total_rate",list.getTotal_rate());
        args.putString("vendor_id",list.getVendor_id());
        args.putString("tybeservice",Service);
        fragmen.setArguments(args);
        getFragmentManager().beginTransaction()
                .replace(R.id.MenuFrame, fragmen )
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();

    }
}
