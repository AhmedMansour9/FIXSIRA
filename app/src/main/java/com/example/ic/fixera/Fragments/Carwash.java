package com.example.ic.fixera.Fragments;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.crashlytics.android.Crashlytics;
import com.example.ic.fixera.Activites.Navigation;
import com.example.ic.fixera.Adapter.Banner_Adapter;
import com.example.ic.fixera.Adapter.CarWash_Adapter;
import com.example.ic.fixera.Language;
import com.example.ic.fixera.Model.Banner_details;
import com.example.ic.fixera.Model.CarWashing;
import com.example.ic.fixera.Model.Filter_Places;
import com.example.ic.fixera.NetworikConntection;
import com.example.ic.fixera.Presenter.BannerPresenter;
import com.example.ic.fixera.Presenter.CarWashing_presenter;
import com.example.ic.fixera.View.BannerView;
import com.example.ic.fixera.View.CarWashing_View;
import com.example.ic.fixera.View.Details_Service;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.fixsira.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import io.fabric.sdk.android.Fabric;

/**
 * A simple {@link Fragment} subclass.
 */
public class Carwash extends Fragment implements BannerView,GoogleApiClient.OnConnectionFailedListener,OnMapReadyCallback, com.google.android.gms.location.LocationListener, GoogleApiClient.ConnectionCallbacks,CarWashing_View,SwipeRefreshLayout.OnRefreshListener,Details_Service {


    public Carwash() {
        // Required empty public constructor
    }
    CarWashing_presenter cars;
    RecyclerView recyclerView;
    View view;
    CarWash_Adapter CARS;
    SwipeRefreshLayout mSwipeRefreshLayout;
    List<Filter_Places> filterPlaces=new ArrayList<>();
    Timer timer;
    NetworikConntection networikConntection;
    String Car_id,Service_id;
    public static String Service;
    Button sortRate,sortDistance;
    Boolean status=false;
    private ShimmerFrameLayout mShimmerViewContainer;
    GoogleApiClient mGoogleApiClient;
    LocationRequest locationReques;
    final int REQUEST_LOCATION_CODE = 99;
    TextView Nobranch;
    Toolbar toolbars;
    ImageView Img_Search;
    EditText E_Search;
    BannerPresenter baner;
    private RecyclerView rv_autoScroll;
    LinearLayoutManager linearLayoutManager;
    List<Banner_details> banne=new ArrayList<>();
    Banner_Adapter banerAdapter;
    int position;
    Boolean end;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_carwash, container, false);
        cars=new CarWashing_presenter(getContext(),this);
        networikConntection=new NetworikConntection(getActivity());
        baner=new BannerPresenter(getContext(),this);
        baner.GetBanner("ar");
        Nobranch=view.findViewById(R.id.No_Branch);
        mShimmerViewContainer =view.findViewById(R.id.shimmer_view_container);
        toolbars=view.findViewById(R.id.toolbar);
        Img_Search=view.findViewById(R.id.Img_Search);
        E_Search=view.findViewById(R.id.E_Search);
        Img_Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                E_Search.setVisibility(View.VISIBLE);
            }
        });
        Navigation.toolbar.setVisibility(View.GONE);
        Navigation.toggle = new ActionBarDrawerToggle(
                getActivity(), Navigation.drawer, toolbars,R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        Navigation.drawer.addDrawerListener(Navigation.toggle);
        Navigation.toggle.syncState();

//        Navigation.toggle.setDrawerIndicatorEnabled(false);
//        toolbars.setNavigationIcon(R.drawable. navigat);

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


        getdata();
     Recyclview();
     SwipRefresh();
     setSortRate();
     setDistance();
     RecycleviewSerach();

     return view;

    }
    public void Recyclview(){
        recyclerView = view.findViewById(R.id.recycler_WashingCar);
        sortRate=view.findViewById(R.id.sortrate);
        sortDistance=view.findViewById(R.id.sortdestance);
        recyclerView.setHasFixedSize(true);
        rv_autoScroll = view.findViewById(R.id.recycler_banner2);
        rv_autoScroll.setHasFixedSize(true);
        CARS = new CarWash_Adapter(filterPlaces, getContext());
        CARS.setClickListener(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(CARS);
    }

    public void setSortRate(){
        sortRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (networikConntection.isNetworkAvailable(getContext())) {
                    sortDistance.setTextColor(getResources().getColor(R.color.text));
                    sortRate.setTextColor(getResources().getColor(R.color.orange));
                    Collections.sort(filterPlaces, new Comparator<Filter_Places>() {

                        @Override
                        public int compare(Filter_Places o1, Filter_Places o2) {
                            return Double.compare(o1.getRate(),o2.getRate());
                        }
                    });
                    Collections.reverse(filterPlaces);
                    CARS.notifyDataSetChanged();

                    }
            }
        });
    }
    public void setDistance(){
        sortDistance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TabsLayouts.latitude != 0) {
                    sortDistance.setTextColor(view.getResources().getColor(R.color.orange));
                    sortRate.setTextColor(view.getResources().getColor(R.color.text));

                    if (Language.isRTL()) {
                        mShimmerViewContainer.stopShimmerAnimation();
                        mShimmerViewContainer.setVisibility(View.GONE);


//                        mSwipeRefreshLayout.setRefreshing(true);
                        cars.GetCarWashong("ar", "car_washing",Car_id,Service_id, Service);
                    } else {
                        mShimmerViewContainer.stopShimmerAnimation();
                        mShimmerViewContainer.setVisibility(View.GONE);

//                        mSwipeRefreshLayout.setRefreshing(true);
                        cars.GetCarWashong("en", "car_washing",Car_id,Service_id,Service);
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
                    baner.GetBanner("ar");
                    if (Language.isRTL()) {

                        mShimmerViewContainer.startShimmerAnimation();
                        mShimmerViewContainer.setVisibility(View.VISIBLE);
//                        mSwipeRefreshLayout.setRefreshing(true);
                        cars.GetCarWashong("ar", "car_washing",Car_id,Service_id, Service);
                    } else {
                        mShimmerViewContainer.startShimmerAnimation();
                        mShimmerViewContainer.setVisibility(View.VISIBLE);
//                        mSwipeRefreshLayout.setRefreshing(true);
                        cars.GetCarWashong("en", "car_washing",Car_id,Service_id,Service);
                    }
                }
            }

        });
    }


    @Override
    public void GetAccessories(List<CarWashing> list) {
        mShimmerViewContainer.stopShimmerAnimation();
        mShimmerViewContainer.setVisibility(View.GONE);
        filterPlaces.clear();

            if (TabsLayouts.latitude != 0) {
                sortDistance.setTextColor(this.view.getResources().getColor(R.color.orange));
                sortRate.setTextColor(this.view.getResources().getColor(R.color.text));

                for (int i = 0; i < list.size(); i++) {
                    Location selected_location = new Location("locationA");
                    selected_location.setLatitude(TabsLayouts.latitude);
                    selected_location.setLongitude(TabsLayouts.longitude);
                    Location near_locations = new Location("locationB");

                    near_locations.setLatitude(Double.parseDouble(list.get(i).getLat()));
                    near_locations.setLongitude(Double.parseDouble(list.get(i).getLng()));
                    double distance = selected_location.distanceTo(near_locations);
                    Filter_Places filter_places = new Filter_Places();
                    filter_places.setAddress(list.get(i).getAddress());
                    filter_places.setId(list.get(i).getId());
                    filter_places.setVendorName(list.get(i).getVendorName());
                    filter_places.setDescription(list.get(i).getDescription());
                    filter_places.setUserPhotoUrl(list.get(i).getPullImage());
                    filter_places.setPhone(list.get(i).getPhone());
                    filter_places.setService_id(String.valueOf(list.get(i).getServicesId()));
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

            } else {
                for (int i = 0; i < list.size(); i++) {
                    setData(list, i, 0);
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
        mShimmerViewContainer.setVisibility(View.GONE);
        CARS.notifyDataSetChanged();
        mSwipeRefreshLayout.setRefreshing(false);

    }
    public void setData(List<CarWashing> list,int i,double distance){
        Filter_Places filter_places=new Filter_Places();
        filter_places.setAddress(list.get(i).getAddress());
        filter_places.setId(list.get(i).getId());
        filter_places.setVendorName(list.get(i).getVendorName());
        filter_places.setDescription(list.get(i).getDescription());
        filter_places.setUserPhotoUrl(list.get(i).getPullImage());
        filter_places.setPhone(list.get(i).getPhone());
        filter_places.setRate(list.get(i).getRateAverage());
        filter_places.setLat(String.valueOf(list.get(i).getLat()));
        filter_places.setLng(String.valueOf(list.get(i).getLng()));
        filter_places.setService_id(String.valueOf(list.get(i).getServicesId()));
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
        mShimmerViewContainer.stopShimmerAnimation();
        mShimmerViewContainer.setVisibility(View.GONE);
//        Nobranch.setText(View.VISIBLE);
    }

    @Override
    public void EmptyBranches() {
        Nobranch.setVisibility(View.VISIBLE);
//        mSwipeRefreshLayout.setRefreshing(false);
        mShimmerViewContainer.stopShimmerAnimation();
        mShimmerViewContainer.setVisibility(View.GONE);

//        Nobranch.setText(View.VISIBLE);
    }

    @Override
    public void onRefresh() {
        mShimmerViewContainer.startShimmerAnimation();
        mShimmerViewContainer.setVisibility(View.VISIBLE);
        baner.GetBanner("ar");
        filterPlaces.clear();
        if(Language.isRTL()){
//            mSwipeRefreshLayout.setRefreshing(true);
            cars.GetCarWashong("ar","car_washing",Car_id,Service_id,Service);
        }else {
//            mSwipeRefreshLayout.setRefreshing(true);
            cars.GetCarWashong("en","car_washing",Car_id,Service_id,Service);
        }
    }

    @Override
    public void listsevice(com.example.ic.fixera.Model.Details_Service list) {
        Profile_Vendor fragmen = new Profile_Vendor();
        Bundle args = new Bundle();
        args.putString("id",String.valueOf(list.getId()));
        args.putString("totalprice",String.valueOf(list.getTotalPrice()));
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
        args.putString("service_id",list.getServices_id());
        args.putString("tybeservice",Service);
        args.putString("totalprice",list.getTotalPrice());
        fragmen.setArguments(args);
        getFragmentManager().beginTransaction()
                .replace(R.id.carwash_Frame, fragmen )
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_LOCATION_CODE:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        buildGoogleapiclint();
                        break;
                    case Activity.RESULT_CANCELED:
                        break;
                    default:
                        break;
                }
                break;
        }

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        locationReques = new LocationRequest();
        //  locationReques.setSmallestDisplacement(1);
        locationReques.setFastestInterval(1000);
        locationReques.setInterval(1000);
        locationReques.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        int permissionCheck = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION);
        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, locationReques, this);
            LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                    .addLocationRequest(locationReques);
            SettingsClient client = LocationServices.getSettingsClient(getActivity());
            Task<LocationSettingsResponse> task = client.checkLocationSettings(builder.build());
            task.addOnFailureListener((getActivity()), new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    if (e instanceof ResolvableApiException) {
                        try {
                            ResolvableApiException resolvable = (ResolvableApiException) e;
                            resolvable.startResolutionForResult(getActivity(),
                                    REQUEST_LOCATION_CODE);
                        } catch (IntentSender.SendIntentException sendEx) {
                        }
                    }
                }
            });
        }
    }
    @Override
    public void onConnectionSuspended(int i) {
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    private synchronized void buildGoogleapiclint() {
        mGoogleApiClient = new GoogleApiClient.Builder(getContext())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Navigation.Visablty = true;
        Navigation.toolbar.setVisibility(View.GONE);
    }

    @Override
    public void onStop() {
        super.onStop();
        Navigation.Visablty = false;
    }
    public void RecycleviewSerach(){
        E_Search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                CARS.getFilter().filter(charSequence);
                CARS.notifyDataSetChanged();

            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }


    @Override
    public void getBanner(List<Banner_details> banners) {

        banne=banners;
        banerAdapter = new Banner_Adapter(banners,getContext());
        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv_autoScroll.setLayoutManager(linearLayoutManager);
        rv_autoScroll.setAdapter(banerAdapter);
        if(banners.size()>0) {
            timer = new Timer();
            timer.scheduleAtFixedRate(new AutoScrollTask(), 2500, 8000);
        }
    }

    @Override
    public void Errorbaner() {
    }
    private class AutoScrollTask extends TimerTask {
        @Override
        public void run() {
            if(position == banne.size()){
                end = true;
            }
            else if (position == 0) {
                end = false;
            }
            if(!end){
                position++;
            } else {
                position--;
            }
            rv_autoScroll.smoothScrollToPosition(position);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }
}
