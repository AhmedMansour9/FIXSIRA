package com.example.ic.fixera.Fragments;


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ic.fixera.Language;
import com.example.ic.fixera.Presenter.Get_Counter_Presenter;
import com.example.ic.fixera.View.Counter_View;
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
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class  TabsLayouts extends Fragment implements Counter_View,OnMapReadyCallback, com.google.android.gms.location.LocationListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener{

    public static double latitude, longitude=0;
    private Toolbar toolbar;
    public static TabLayout tabLayout;
    private ViewPager viewPager;
    final int REQUEST_LOCATION_CODE = 99;
    private GoogleMap googleMap;
    GoogleApiClient mGoogleApiClient;
    Location lastlocation;
    LocationRequest locationReques;
    public static TextView T_Service;
    Get_Counter_Presenter counter_presenter;
    public TabsLayouts() {
        // Required empty public constructor
    }
    View view;
    public static ImageView banner;
    SharedPreferences Shared;
    String user;
    SharedPreferences shared;
//    View view3;
    TextView textcounter;
    ViewPagerAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_tabs_layouts, container, false);
//        Fabric.with(getContext(), new Crashlytics());
//         view3 = getLayoutInflater().inflate(R.layout.iconwashingcar, null);
//        textcounter=view3.findViewById(R.id.cartt);
//        T_Service=findViewById(R.id.T_Service);
//        banner=findViewById(R.id.banner);
        // Inflate the layout for this fragment
        toolbar = view.findViewById(R.id.toolbar);
        viewPager =view.findViewById(R.id.viewpager);
        tabLayout =view.findViewById(R.id.tabs);


        counter_presenter=new Get_Counter_Presenter(getContext(),this);
        Shared=getActivity().getSharedPreferences("login",MODE_PRIVATE);
        user=Shared.getString("logggin",null);

       setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.MAP);
        mapFragment.getMapAsync(this);
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        checkLocationPermission();
        setupTabIcons();
        if(Language.isRTL()){
            counter_presenter.GetCounter(user,"ar");
            tabLayout.getTabAt(2).select();
        }else {
            counter_presenter.GetCounter(user,"en");
            tabLayout.getTabAt(1).select();
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            tabLayout.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        }
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                FragmentManager fm = getChildFragmentManager(); // or 'getSupportFragmentManager();'
                int count = fm.getBackStackEntryCount();
                if(count!=0) {
                    for (int i = 0; i < count; ++i) {
                        fm.popBackStack();
                    }
                }
//                switch(tab.getPosition()) {
                    viewPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    return view;
    }

    private void setupTabIcons() {
        View view1 = getLayoutInflater().inflate(R.layout.sparepart, null);
        View view2 = getLayoutInflater().inflate(R.layout.pullcar, null);
        View view3 = getLayoutInflater().inflate(R.layout.iconwashingcar, null);
        View view4 = getLayoutInflater().inflate(R.layout.iconmaintenence, null);
        if(Language.isRTL()){
            tabLayout.getTabAt(0).setCustomView(view4);
            tabLayout.getTabAt(1).setCustomView(view3);
            tabLayout.getTabAt(2).setCustomView(view2);
            tabLayout.getTabAt(3).setCustomView(view1);
        }else {
            tabLayout.getTabAt(0).setCustomView(view1);
            tabLayout.getTabAt(1).setCustomView(view2);
            tabLayout.getTabAt(2).setCustomView(view3);
            tabLayout.getTabAt(3).setCustomView(view4);
        }
    }
    private void setupViewPager(ViewPager viewPager) {
         adapter = new ViewPagerAdapter(getFragmentManager());
        if(Language.isRTL()){
            adapter.addFrag(new CarMaintenence(), getResources().getString(R.string.Profile));
            adapter.addFrag(new Carwash(), getResources().getString(R.string.cart));
            adapter.addFrag(new PullCar(), getResources().getString(R.string.menu));
            adapter.addFrag(new Categories_SparParts(), getResources().getString(R.string.setting));
        }else {
            adapter.addFrag(new Categories_SparParts(), getResources().getString(R.string.setting));
            adapter.addFrag(new PullCar(), getResources().getString(R.string.menu));
            adapter.addFrag(new Carwash(), getResources().getString(R.string.cart));
            adapter.addFrag(new CarMaintenence(), getResources().getString(R.string.Profile));
        }
        viewPager.setAdapter(adapter);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

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
        locationReques.setSmallestDisplacement(10);
        locationReques.setFastestInterval(10000);
        locationReques.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);


        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, locationReques, this);
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationReques);


        SettingsClient client = LocationServices.getSettingsClient(getActivity());
        Task<LocationSettingsResponse> task = client.checkLocationSettings(builder.build());

        task.addOnFailureListener(getActivity(), new OnFailureListener() {
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

    @Override
    public void onConnectionSuspended(int i) {

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
    public void onLocationChanged(Location location) {

        latitude = location.getLatitude();
        longitude = location.getLongitude();
    }

    @Override
    public void onMapReady(GoogleMap googleMaps) {
        googleMap = googleMaps;
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        buildGoogleapiclint();

    }

    @Override
    public void Counter(String counter) {
//        if(Language.isRTL()){
//            TabLayout.Tab tab = tabLayout.getTabAt(1); // fourth tab
//            View tabView = tab.getCustomView();
//            TextView textView = tabView.findViewById(R.id.cartt);
//            textView.setText("  (" + counter + ")");
//
//        }else {
//            TabLayout.Tab tab = tabLayout.getTabAt(2); // fourth tab
//            View tabView = tab.getCustomView();
//            TextView textView = tabView.findViewById(R.id.cartt);
//            textView.setText("  (" + counter + ")");
//        }
    }

    @Override
    public void Error() {

    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();
        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }
        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }
        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            // TODO Auto-generated method stub
        }

    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 99: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // location-related task you need to do.
                    if (ContextCompat.checkSelfPermission(getContext(),
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {
                        buildGoogleapiclint();

                    }

                } else {
                }
                return;
            }

        }
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        finish();

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
}

