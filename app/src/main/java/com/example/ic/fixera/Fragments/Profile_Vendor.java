package com.example.ic.fixera.Fragments;


import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ic.fixera.Activites.Hours_Vendor;
import com.example.ic.fixera.Activites.MyProducts_Vendor;
import com.example.ic.fixera.Activites.MyServices_vendor;
import com.example.ic.fixera.Language;
import com.example.ic.fixera.Model.Location_Vendor;
import com.example.ic.fixera.NetworikConntection;
import com.example.ic.fixera.Presenter.Profilevendor_Presenter;
import com.example.ic.fixera.View.Profilevendor_View;
import com.fixe.fixera.R;
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

import static android.content.Context.MODE_PRIVATE;
import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * A simple {@link Fragment} subclass.
 */
public class Profile_Vendor extends Fragment implements SwipeRefreshLayout.OnRefreshListener,Profilevendor_View,OnMapReadyCallback, com.google.android.gms.location.LocationListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener{


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
   public String Tybe_Service;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.profile_vendor, container, false);
        profilevendor=new Profilevendor_Presenter(getContext(),this);
        Shared=getActivity().getSharedPreferences("login",MODE_PRIVATE);
        networikConntection=new NetworikConntection(getActivity());
        btn_ShowService=view.findViewById(R.id.showservices);
        user=Shared.getString("logggin",null);
        profileFrame=view.findViewById(R.id.profileFrame);
        init();
        getData();
        SwipRefresh();
        ShowServices();

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
        ShowProducts();
        ShowHours();
        return view;
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
            Total_rate=args.getString("Total_rate");
            Tybe_Service=args.getString("tybeservice");
//            vendor_id=args.getString("vendor_id");
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
                    .load("http://fixsira.com/site/"+photo)
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
       show_service=view.findViewById(R.id.show_service);
       btn_ShowHours=view.findViewById(R.id.show_hours);
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
                fragmen.setArguments(args);
                getFragmentManager().beginTransaction()
                        .replace(R.id.MenuFrame, fragmen )
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

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                new android.app.AlertDialog.Builder(getContext())
                        .setTitle("info")
                        .setMessage("Enable gbs")
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(getActivity(),
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                        REQUEST_LOCATION_CODE);
                            }
                        })
                        .create()
                        .show();


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
    public void Error() {
        mSwipeRefreshLayout.setRefreshing(false);
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
                    mSwipeRefreshLayout.setRefreshing(true);
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
            mSwipeRefreshLayout.setRefreshing(true);
            profilevendor.profilevendor(user, lan, id);
        }else {
            Snackbar.make(profileFrame,getResources().getString(R.string.internet),1500).show();
        }

    }
}
