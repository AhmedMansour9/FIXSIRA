package com.example.ic.fixera.Fragments;


import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.ic.fixera.Activites.TabsLayouts;
import com.example.ic.fixera.Adapter.CarWash_Adapter;
import com.example.ic.fixera.Adapter.PullCar_Adapter;
import com.example.ic.fixera.Language;
import com.example.ic.fixera.Model.CarWashing;
import com.example.ic.fixera.Model.Filter_Places;
import com.example.ic.fixera.NetworikConntection;
import com.example.ic.fixera.Presenter.CarWashing_presenter;
import com.example.ic.fixera.Presenter.PullCar_Presenter;
import com.example.ic.fixera.View.CarWashing_View;
import com.example.ic.fixera.View.Details_Service;
import com.example.ic.fixera.View.PullCar_View;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.fixsira.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PullCar extends Fragment implements PullCar_View,SwipeRefreshLayout.OnRefreshListener,Details_Service {


    public PullCar() {
        // Required empty public constructor
    }

    final int REQUEST_LOCATION_CODE = 99;
    RecyclerView recyclerView;
    SharedPreferences shared;
    PullCar_Adapter adapter;
    PullCar_Presenter cars;
    SwipeRefreshLayout mSwipeRefreshLayout;
    NetworikConntection checknetwork;
    FrameLayout FramePullcar;
   View view;
    List<Filter_Places> filterPlaces=new ArrayList<>();
    NetworikConntection networikConntection;
    Button sortRate,sortDistance;
    private ShimmerFrameLayout mShimmerViewContainer;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_pull_car, container, false);
        cars=new PullCar_Presenter(getContext(),this);
        mShimmerViewContainer =view.findViewById(R.id.shimmer_view_container);
       init();
        SwipRefresh();
        setSortRate();
        setDistance();

        return view;
    }
     public void init(){
         recyclerView=view.findViewById(R.id.recycler_pullCar);
         checknetwork=new NetworikConntection(getActivity());
         mSwipeRefreshLayout=view.findViewById(R.id.swipe_pullCar);
         FramePullcar=view.findViewById(R.id.Frame_PullCar);
         sortRate=view.findViewById(R.id.sortrate);
         sortDistance=view.findViewById(R.id.sortdestance);
         adapter = new PullCar_Adapter(filterPlaces,getContext());
         adapter.setClickListener(this);
         LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
         linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
         recyclerView.setLayoutManager(linearLayoutManager);
         recyclerView.setItemAnimator(new DefaultItemAnimator());
         recyclerView.setAdapter(adapter);

     }
    public void setSortRate(){
        sortRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checknetwork.isNetworkAvailable(getContext())) {
                    sortDistance.setTextColor(getResources().getColor(R.color.text));
                    sortRate.setTextColor(getResources().getColor(R.color.orange));


                    Collections.sort(filterPlaces, new Comparator<Filter_Places>() {

                        @Override
                        public int compare(Filter_Places o1, Filter_Places o2) {
                            return Double.compare(o1.getRate(),o2.getRate());
                        }
                    });
                    Collections.reverse(filterPlaces);
                    adapter.notifyDataSetChanged();

                }

            }
        });
    }
    public void setDistance(){
        sortDistance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TabsLayouts.latitude != 0) {
                    mShimmerViewContainer.stopShimmerAnimation();
                    mShimmerViewContainer.setVisibility(View.GONE);
                    sortDistance.setTextColor(view.getResources().getColor(R.color.orange));
                    sortRate.setTextColor(view.getResources().getColor(R.color.text));
                    if(Language.isRTL()) {
//                        mSwipeRefreshLayout.setRefreshing(true);
                        cars.GetCarWashong("ar","pull_washing","","","");
                    }else{
//                        mSwipeRefreshLayout.setRefreshing(true);
                        cars.GetCarWashong("en","pull_washing","","","");
                    }
                }
                else {
                    checkLocationPermission();
                }
            }
        });
    }
    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        REQUEST_LOCATION_CODE);



            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        REQUEST_LOCATION_CODE);
            }
            return false;
        } else {
            return true;
        }
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
//                        mSwipeRefreshLayout.setRefreshing(true);
                        mShimmerViewContainer.startShimmerAnimation();
                        mShimmerViewContainer.setVisibility(View.VISIBLE);
                        cars.GetCarWashong("ar","pull_washing","","","");
                    }else{
                        mShimmerViewContainer.startShimmerAnimation();
                        mShimmerViewContainer.setVisibility(View.VISIBLE);

//                        mSwipeRefreshLayout.setRefreshing(true);
                        cars.GetCarWashong("en","pull_washing","","","");
                    }

                }else {
//                    Snackbar.make(FramePullcar,R.string.internet,1000).show();
                }
            }
        });
    }

    @Override
    public void onRefresh() {
        if(checknetwork.isNetworkAvailable(getActivity())){
            mShimmerViewContainer.startShimmerAnimation();
            mShimmerViewContainer.setVisibility(View.VISIBLE);

//            mSwipeRefreshLayout.setRefreshing(true);
            mSwipeRefreshLayout.setEnabled(true);
            if(Language.isRTL()) {
//                mSwipeRefreshLayout.setRefreshing(true);
                cars.GetCarWashong("ar","pull_washing","","","");
            }else{
//                mSwipeRefreshLayout.setRefreshing(true);
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
        args.putString("id",String.valueOf(list.getVendor_id()));
        args.putString("tybe",list.getTybe());
        args.putString("phone",list.getPhone());
        fragmen.setArguments(args);
        getFragmentManager().beginTransaction()
                .replace(R.id.MenuFrame, fragmen )
                .addToBackStack(null)
                .commitAllowingStateLoss();

    }
    public void setData(List<com.example.ic.fixera.Model.PullCar> list, int i, double distance){
        Filter_Places filter_places=new Filter_Places();
        filter_places.setAddress(list.get(i).getCenterAddress());
        filter_places.setVendor_id(list.get(i).getVendorId());
        filter_places.setVendorName(list.get(i).getNameVendor());
        filter_places.setDescription(list.get(i).getPullDescription());
        filter_places.setUserPhotoUrl(list.get(i).getPullImage());
        filter_places.setPhone(list.get(i).getCenterPhone());
        filter_places.setRate(list.get(i).getRating());
        filter_places.setPrice(list.get(i).getPullPrice());
        filter_places.setTotal_Rates(String.valueOf(list.get(i).getTotal()));
        filter_places.setLat(String.valueOf(list.get(i).getCenterLat()));
        filter_places.setLng(String.valueOf(list.get(i).getCenterLng()));
        filter_places.setTelephone(list.get(i).getCenterTelephone());
        filter_places.setName(list.get(i).getCenterName());
        filter_places.setDistance(distance);
        filter_places.setTybe("pull_washing");
        filterPlaces.add(filter_places);

    }


    @Override
    public void GetAccessories(List<com.example.ic.fixera.Model.PullCar> list) {
        mShimmerViewContainer.stopShimmerAnimation();
        mShimmerViewContainer.setVisibility(View.GONE);
        filterPlaces.clear();
        if(TabsLayouts.latitude!=0.0){
            sortDistance.setTextColor(view.getResources().getColor(R.color.orange));
            sortRate.setTextColor(view.getResources().getColor(R.color.text));

            for(int i=0;i<list.size();i++){
                Location selected_location=new Location("locationA");
                selected_location.setLatitude(TabsLayouts.latitude);
                selected_location.setLongitude(TabsLayouts.longitude);
                Location near_locations=new Location("locationB");
                near_locations.setLatitude(Double.parseDouble(String.valueOf(list.get(i).getCenterLat())));
                near_locations.setLongitude(Double.parseDouble(String.valueOf(list.get(i).getCenterLng())));
                double distance=selected_location.distanceTo(near_locations);

                Filter_Places filter_places=new Filter_Places();
                filter_places.setAddress(list.get(i).getCenterAddress());
                filter_places.setVendor_id(list.get(i).getVendorId());
                filter_places.setVendorName(list.get(i).getNameVendor());
                filter_places.setDescription(list.get(i).getPullDescription());
                filter_places.setUserPhotoUrl(list.get(i).getPullImage());
                filter_places.setTotal_Rates(String.valueOf(list.get(i).getTotal()));
                filter_places.setPhone(list.get(i).getCenterPhone());
                filter_places.setPrice(list.get(i).getPullPrice());
                filter_places.setRate(list.get(i).getRating());
                filter_places.setLat(String.valueOf(list.get(i).getCenterLng()));
                filter_places.setLng(String.valueOf(list.get(i).getCenterLng()));
                filter_places.setTelephone(list.get(i).getCenterTelephone());
                filter_places.setName(list.get(i).getCenterName());
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
            sortDistance.setTextColor(view.getResources().getColor(R.color.text));
            sortRate.setTextColor(view.getResources().getColor(R.color.orange));
            Collections.sort(filterPlaces, new Comparator<Filter_Places>() {

                @Override
                public int compare(Filter_Places o1, Filter_Places o2) {
                    return Double.compare(o1.getRate(), o2.getRate());
                }
            });
            Collections.reverse(filterPlaces);
        }

        adapter.notifyDataSetChanged();
        mSwipeRefreshLayout.setRefreshing(false);

    }

    @Override
    public void ErrorAccessories() {
        mSwipeRefreshLayout.setRefreshing(false);
        mShimmerViewContainer.stopShimmerAnimation();
        mShimmerViewContainer.setVisibility(View.GONE);
    }
}
