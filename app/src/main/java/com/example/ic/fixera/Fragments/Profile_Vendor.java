package com.example.ic.fixera.Fragments;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
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
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
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
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.crashlytics.android.Crashlytics;
import com.example.ic.fixera.Activites.Hours_Vendor;
import com.example.ic.fixera.Activites.MyProducts_Vendor;
import com.example.ic.fixera.Activites.MyServices_vendor;
import com.example.ic.fixera.Activites.Navigation;
import com.example.ic.fixera.Adapter.Vendor_Hours_Adapter;
import com.example.ic.fixera.Language;
import com.example.ic.fixera.Model.Location_Vendor;
import com.example.ic.fixera.NetworikConntection;
import com.example.ic.fixera.Presenter.Profilevendor_Presenter;
import com.example.ic.fixera.Presenter.Vendor_Hours_Presenter;
import com.example.ic.fixera.View.Profilevendor_View;
import com.example.ic.fixera.View.Vendor_Hours_View;
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
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import io.fabric.sdk.android.Fabric;

import static android.content.Context.MODE_PRIVATE;
import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * A simple {@link Fragment} subclass.
 */
public class Profile_Vendor extends Fragment implements SwipeRefreshLayout.OnRefreshListener, Vendor_Hours_View,
        Profilevendor_View,OnMapReadyCallback, com.google.android.gms.location.LocationListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener{


    public Profile_Vendor() {
        // Required empty public constructor
    }
    String id;
    String lan;
    SharedPreferences Shared;
    String user;
    TextView vendorname,vendoraddress,diescrption;
    Profilevendor_Presenter profilevendor;
    LocationRequest locationReques;
    final int REQUEST_LOCATION_CODE = 99;
    private GoogleMap googleMap;
    GoogleApiClient mGoogleApiClient;
    double latitude,longetude=0;
    View view;
    String Address,phone,tybe,Rate,Total_rate;
    Marker marker;
    Button btnReserve,btn_ShowService,show_service,btn_ShowHours;
    String photo,price,Discrption,Name;
    SwipeRefreshLayout mSwipeRefreshLayout;
    NetworikConntection networikConntection;
    FrameLayout profileFrame;
    CircleImageView person_image;
    String Car_id,Service_id;
    private ImageView Starone,Startwo,StarThree,StarFour,StarFive;
   private String vendor_id;
   public String Tybe_Service,services_id;
    private ShimmerFrameLayout mShimmerViewContainer;
    TextView allreviwes;
    Vendor_Hours_Presenter products;
    RecyclerView recyclerView ;
    Vendor_Hours_Adapter categories_adapter;
    TextView T_hours;
    String TotalPrice;
    Toolbar toolbars;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.profile_vendor, container, false);
        toolbars=view.findViewById(R.id.toolbar);
        init();
        getData();
        SwipRefresh();
//        ShowServices();

        if(Language.isRTL()){
            lan="ar";
        }else {
            lan="en";
        };

        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        checkLocationPermission();
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.MMap);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
        Open_Reserve();
//        ShowProducts();
//        ShowHours();
//        openReviewes();
        return view;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Navigation.Visablty = false;
//        Navigation.toolbar.setVisibility(View.VISIBLE);
    }

    public void openReviewes(){
        allreviwes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                All_Reviwes fragmen = new All_Reviwes();
                Bundle args = new Bundle();
                args.putString("tybe_id",Service_id);
                args.putString("tybeservice",tybe);
                args.putString("id",null);

                fragmen.setArguments(args);
                getFragmentManager().beginTransaction()
                        .replace(R.id.MenuFrame, fragmen )
                        .addToBackStack(null)
                        .commitAllowingStateLoss();

            }
        });
    }
    public void ShowHours(){
        btn_ShowHours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inty=new Intent(getActivity(), Hours_Vendor.class);
                inty.putExtra("vendor_id",id);
                inty.putExtra("user_token",user);
                startActivity(inty);

            }
        });
    }
    public void ShowServices(){
        btn_ShowService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent inty=new Intent(getActivity(), MyServices_vendor.class);
            inty.putExtra("tybe",tybe);
            inty.putExtra("user_token",user);
            inty.putExtra("vendor_id",vendor_id);
            startActivity(inty);

            }
        });
    }

    public void getData(){
        Bundle args = getArguments();
        if (args != null) {
            id = args.getString("id");
            tybe=args.getString("tybe");
            photo=args.getString("image");
            price=args.getString("price");
            Name=args.getString("placeName");
            Car_id=args.getString("car_id");
            Service_id=args.getString("tybe_id");
            Rate=args.getString("rate");
            services_id=args.getString("service_id");
            Total_rate=args.getString("Total_rate");
            Tybe_Service=args.getString("tybeservice");
            Address=args.getString("address");
            TotalPrice=args.getString("totalprice");
//            vendor_id=args.getString("vendor_id");z
            vendorname.setText(Name);
            vendoraddress.setText(Address);
            double rates=Double.parseDouble(Rate);
            if(rates==1.0){
                Starone.setVisibility(View.VISIBLE);
            }else if(rates==2.0){
                Starone.setVisibility(View.VISIBLE);
                Startwo.setVisibility(View.VISIBLE);
            }else if(rates==3.0){
                Starone.setVisibility(View.VISIBLE);
                Startwo.setVisibility(View.VISIBLE);
                StarThree.setVisibility(View.VISIBLE);
            }
            else if(rates==4.0){
                Starone.setVisibility(View.VISIBLE);
                Startwo.setVisibility(View.VISIBLE);
                StarThree.setVisibility(View.VISIBLE);
                StarFour.setVisibility(View.VISIBLE);
            }
            else if(rates==5.0){
                Starone.setVisibility(View.VISIBLE);
                Startwo.setVisibility(View.VISIBLE);
                StarThree.setVisibility(View.VISIBLE);
                StarFour.setVisibility(View.VISIBLE);
                StarFive.setVisibility(View.VISIBLE);
            }


            Picasso.with(getApplicationContext())
                    .load("http://fixsira.com/"+photo)
                    .placeholder(R.drawable.profile)
                    .into(person_image, new Callback() {
                        @Override
                        public void onSuccess() {
                        }

                        @Override
                        public void onError() {
                        }
                    });
        }
    }
   public void init(){
       profilevendor=new Profilevendor_Presenter(getContext(),this);
       Shared=getActivity().getSharedPreferences("login",MODE_PRIVATE);
       mShimmerViewContainer =view.findViewById(R.id.shimmer_view_container);
       allreviwes=view.findViewById(R.id.reviews);
       networikConntection=new NetworikConntection(getActivity());
       T_hours=view.findViewById(R.id.T_hours);
//       btn_ShowService=view.findViewById(R.id.showservices);
       user=Shared.getString("logggin",null);
       profileFrame=view.findViewById(R.id.profileFrame);
       products=new Vendor_Hours_Presenter(getContext(),this);
       recyclerView =view.findViewById(R.id.recycler_Hours);
       recyclerView.setHasFixedSize(true);
       person_image=view.findViewById(R.id.person_image);
       btnReserve=view.findViewById(R.id.Reserves);
       vendorname=view.findViewById(R.id.vendorname);
       vendoraddress=view.findViewById(R.id.vendoraddress);
       diescrption=view.findViewById(R.id.T_discrption);
       Starone=view.findViewById(R.id.Starone);
       Startwo=view.findViewById(R.id.Startwo);
       StarThree=view.findViewById(R.id.Starthree);
       StarFour=view.findViewById(R.id.StarFour);
       StarFive=view.findViewById(R.id.StarFive);
//       show_service=view.findViewById(R.id.show_service);
       btn_ShowHours=view.findViewById(R.id.show_hours);
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
   }

   public void ShowProducts(){
       show_service.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent inty=new Intent(getActivity(), MyProducts_Vendor.class);
               inty.putExtra("tybe",tybe);
               inty.putExtra("vendor_id",vendor_id);
               startActivity(inty);


           }
       });
   }
    public void Open_Reserve(){
        btnReserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Details_Services_fragment fragmen = new Details_Services_fragment();
                    Bundle args = new Bundle();
                    args.putString("id",id);
                    args.putString("tybe",tybe);
                    args.putString("phone",phone);
                    args.putString("discription",Discrption);
                    args.putString("image",photo);
                    args.putString("placeName",Name);
                    args.putString("address",Address);
                    args.putString("price",price);
                    args.putString("car_id",Car_id);
                    args.putString("tybe_id",Service_id);
                    args.putString("tybeservice",Tybe_Service);
                    args.putString("services_id",services_id);
                    args.putString("totalprice",TotalPrice);
                    fragmen.setArguments(args);
                    getFragmentManager().beginTransaction()
                            .replace(R.id.Home_tabs, fragmen )
                            .addToBackStack(null)
                            .commitAllowingStateLoss();


            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_LOCATION_CODE:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        buildGoogleapiclint();
                        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                            return;
                        }
                        googleMap.setMyLocationEnabled(true);
                        break;
                    case Activity.RESULT_CANCELED:
                        break;
                    default:
                        break;
                }
                break;
        }

    }
    private synchronized void buildGoogleapiclint() {
        mGoogleApiClient = new GoogleApiClient.Builder(getContext())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }
    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

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

    @Override
    public void getprofile(List<Location_Vendor> list) {
        mSwipeRefreshLayout.setRefreshing(false);
        mShimmerViewContainer.stopShimmerAnimation();
        mShimmerViewContainer.setVisibility(View.GONE);

        if(list!=null) {
            Address=list.get(0).getAddress();
            vendorname.setText(""+list.get(0).getName());
           vendoraddress.setText(""+list.get(0).getAddress());
           if(list.get(0).getDescription()!=null) {
               diescrption.setText("" + list.get(0).getDescription());
               Discrption=list.get(0).getDescription();
           }

            vendor_id=list.get(0).getVendorId();
            phone=list.get(0).getPhone();
            latitude=Double.parseDouble(list.get(0).getLat());
            longetude=Double.parseDouble(list.get(0).getLng());
        }
    }

    @Override
    public void GetHours(List<com.example.ic.fixera.Model.Hours_Vendor> list) {
        recyclerView.setVisibility(View.VISIBLE);
        T_hours.setVisibility(View.VISIBLE);
        categories_adapter=new Vendor_Hours_Adapter(list,getContext());


//        GridLayoutManager gridLayoutManager=new GridLayoutManager(Hours_Vendor.this,2);
//        recyclerView.setLayoutManager(gridLayoutManager);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(categories_adapter);
        mSwipeRefreshLayout.setRefreshing(false);


    }

    @Override
    public void Error() {
        mSwipeRefreshLayout.setRefreshing(false);
        mShimmerViewContainer.stopShimmerAnimation();
        mShimmerViewContainer.setVisibility(View.GONE);

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
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        if (marker != null) {
            marker.remove();
        }
        if(latitude!=0){
            LatLng latLng=new LatLng(latitude,longetude);
           marker=googleMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.marker))
                    .position(latLng).title(Address));
            Camerapoistion(latitude, longetude);

        }
    }
    public void Camerapoistion(double latiI, double longeE) {
        googleMap.getUiSettings().setMyLocationButtonEnabled(true);

        CameraPosition currentPlace = new CameraPosition.Builder()
                .target(new LatLng(latiI, longeE))
                .bearing(240).tilt(30).zoom(15f).build();
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(currentPlace));
    }
    @Override
    public void onMapReady(GoogleMap googleMaps) {
        googleMap = googleMaps;
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        buildGoogleapiclint();
    }

    public void SwipRefresh(){
        mSwipeRefreshLayout =  view.findViewById(R.id.swipe_Profile);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);

        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                if(networikConntection.isNetworkAvailable(getContext())) {
//                    mSwipeRefreshLayout.setRefreshing(true);
                    mShimmerViewContainer.startShimmerAnimation();
                    mShimmerViewContainer.setVisibility(View.VISIBLE);
                    products.VendorHours("ar",id,user);
                    profilevendor.profilevendor(user, lan, id);
                }else {
                    Snackbar.make(profileFrame,getResources().getString(R.string.internet),1500).show();
                }

            }
        });
    }

    @Override
    public void onRefresh() {
        if(networikConntection.isNetworkAvailable(getContext())) {
//            mSwipeRefreshLayout.setRefreshing(true);

            mShimmerViewContainer.startShimmerAnimation();
            mShimmerViewContainer.setVisibility(View.VISIBLE);
            profilevendor.profilevendor(user, lan, id);
        }else {
            Snackbar.make(profileFrame,getResources().getString(R.string.internet),1500).show();
        }

    }

    @Override
    public void onStop() {
        super.onStop();
        Navigation.Visablty = true;

        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onDetach() {
        super.onDetach();
//        Navigation.toolbar.setVisibility(View.GONE);
    }
}
