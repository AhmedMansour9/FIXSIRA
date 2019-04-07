package com.example.ic.fixera.Fragments;


import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.crashlytics.android.Crashlytics;
import com.example.ic.fixera.Language;
import com.example.ic.fixera.Model.CarModel;
import com.example.ic.fixera.Model.Car_Details;
import com.example.ic.fixera.Model.ServicesType;
import com.example.ic.fixera.Model.Spinner_CarModel;
import com.example.ic.fixera.NetworikConntection;
import com.example.ic.fixera.Presenter.Get_CarModels_Presenter;
import com.example.ic.fixera.Presenter.Servicetybe_Presenter;
import com.example.ic.fixera.View.CarModels_View;
import com.example.ic.fixera.View.Spinner_Service_tybe_View;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.fixsira.R;

import java.util.ArrayList;
import java.util.List;

import io.fabric.sdk.android.Fabric;

import static android.content.Context.MODE_PRIVATE;
import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * A simple {@link Fragment} subclass.
 */
public class DropDown extends Fragment implements SwipeRefreshLayout.OnRefreshListener,CarModels_View,Spinner_Service_tybe_View,AdapterView.OnItemSelectedListener{


    public DropDown() {
        // Required empty public constructor
    }
    ArrayAdapter<Car_Details> ListCarModel;
    ArrayAdapter<ServicesType> listcarmaintencnce;
    Get_CarModels_Presenter carsmodel;
    View view;
    Spinner carspinner;
    Spinner servicespinner,SelectService;
    SharedPreferences Shared;
    String user;
    Button Btn_Search;
    Servicetybe_Presenter servicetyb;
    ProgressBar progross;
    ArrayAdapter<String> ListServices;
    String CarModel,Service,tybe,lan,model;
    SwipeRefreshLayout mSwipeRefreshLayout;
    NetworikConntection networikConntection;
    FrameLayout dropFrame;
     String Car_id,Tybe_id;
     String service;
    private ShimmerFrameLayout mShimmerViewContainer;
     List<Car_Details> list_Car=new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_drop_down__car_maintenence, container, false);
        mShimmerViewContainer =view.findViewById(R.id.shimmer_view_container);

        init();
        SwipRefresh();
        if(Language.isRTL()){
            lan="ar";
        }else {
            lan="en";
        };
        Bundle args = getArguments();
        if (args != null) {
            tybe = args.getString("tybe");
        }

        Spin_Service();
        Btn_Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(networikConntection.isNetworkAvailable(getContext())){

                if(!service.equals("Select")&&!service.equals("اختار")&&
                        CarModel!=null&&
                        !CarModel.equals("اختار")&&Service!=null&&
                        !CarModel.equals("Select"))
                if(tybe.equals("car_washing")) {
                    Carwash fragmen = new Carwash();
                    Bundle args = new Bundle();

                    args.putString("tybe", "car_washing");
                    args.putString("car_id",Car_id);
                    args.putString("tybe_id", Tybe_id);
                    args.putString("service",service);

                    fragmen.setArguments(args);
                    getFragmentManager().beginTransaction()
                            .replace(R.id.MenuFrame, fragmen)
                            .addToBackStack(null)
                            .commitAllowingStateLoss();
                }else {
                    CarMaintenence fragmen = new CarMaintenence();
                    Bundle args = new Bundle();

                    args.putString("tybe", "car_maintenance");
                    args.putString("car_id",Car_id);
                    args.putString("tybe_id", Tybe_id);
                   args.putString("service",service);
                    fragmen.setArguments(args);
                    getFragmentManager().beginTransaction()
                            .replace(R.id.MenuFrame, fragmen)
                            .addToBackStack(null)
                            .commitAllowingStateLoss();
                }
                }else{
                    Snackbar.make(dropFrame,getResources().getString(R.string.internet),1500).show();

                    }


            }
        });
        return view;
    }

      public void init(){
          carsmodel=new Get_CarModels_Presenter(getContext(),this);
          Shared=getActivity().getSharedPreferences("login",MODE_PRIVATE);
          user=Shared.getString("logggin",null);
          servicetyb=new Servicetybe_Presenter(getContext(),this);
          carspinner=view.findViewById(R.id.Spin_Cartybe);
          servicespinner=view.findViewById(R.id.Spin_Maintenencetybe);
          Btn_Search=view.findViewById(R.id.Btn_Search);
          dropFrame=view.findViewById(R.id.dropFrame);
          SelectService=view.findViewById(R.id.Spin_SelectService);
          networikConntection=new NetworikConntection(getApplicationContext());

      }

    public void Spin_Service(){

        List<String> categories = new ArrayList<String>();
        categories.add(getResources().getString(R.string.select));
        if(tybe.equals("car_washing")) {
            categories.add(getResources().getString(R.string.visitwashing));
            categories.add(getResources().getString(R.string.mobileservice));
        }else {
            categories.add(getResources().getString(R.string.mobileservice));
            categories.add(getResources().getString(R.string.onside));

        }
        ListServices = new ArrayAdapter<String>(getApplicationContext(), R.layout.textcolorspinner, categories) {
            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                TextView textView = (TextView) super.getDropDownView(position, convertView, parent);
                textView.setTextColor(Color.WHITE);
                return textView;
            }
        };
        ListServices.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SelectService.setOnItemSelectedListener(this);
        SelectService.setAdapter(ListServices);
        SelectService.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if(SelectService.getSelectedItem().toString().equals("زيارة للعميل")){
                    service="Mobile Service";
                }else if(SelectService.getSelectedItem().toString().equals("الصيانة في الورشة")){
                    service="On Site";
                }else if(SelectService.getSelectedItem().toString().equals("غسيل في المركز")){
                    service="On Site";
                }
                else if(SelectService.getSelectedItem().toString().equals("Client Visit")){
                    service="Mobile Service";
                }
                else if(SelectService.getSelectedItem().toString().equals("On Site")){
                    service="On Site";
                }
                else if(SelectService.getSelectedItem().toString().equals("Select")){
                    service="Select";
                }
                else if(SelectService.getSelectedItem().toString().equals("اختار")){
                    service="Select";
                }
                else if(SelectService.getSelectedItem().toString().equals("At Service Center")){
                    service="Mobile Service";
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    @Override
    public void GetCarModels(final List<CarModel> list) {
        list_Car.clear();
        mShimmerViewContainer.stopShimmerAnimation();
        mShimmerViewContainer.setVisibility(View.GONE);
        Car_Details car_detail=new Car_Details();
        car_detail.setId("0");
        car_detail.setName(view.getResources().getString(R.string.select));
        list_Car.add(car_detail);
        for(int i=0;i<list.size();i++){
            Car_Details car_details=new Car_Details();
            car_details.setId(String.valueOf(list.get(i).getManufacturerId()));
            car_details.setName(String.valueOf(list.get(i).getManufacturer()));
            list_Car.add(car_details);
        }
        ListCarModel = new ArrayAdapter<Car_Details>(getApplicationContext(), R.layout.textcolorspinner,list_Car) {
            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                TextView textView = (TextView) super.getDropDownView(position, convertView, parent);
                textView.setTextColor(Color.WHITE);
                return textView;
            }
        };
        ListCarModel.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        carspinner.setOnItemSelectedListener(this);
        carspinner.setAdapter(ListCarModel);
        carspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                CarModel= carspinner.getSelectedItem().toString();

                for(i = 0; i<list.size(); i++){
                    if(list.get(i).getManufacturer().equals(CarModel)){
                        Car_id=String.valueOf(list.get(i).getManufacturerId());
                    }
                }
                if(!CarModel.equals("Select")&&!CarModel.equals("اختار")) {
//                    mSwipeRefreshLayout.setRefreshing(true);
                    mShimmerViewContainer.startShimmerAnimation();
                    mShimmerViewContainer.setVisibility(View.VISIBLE);
                    servicetyb.GetServices(lan, tybe, user, Car_id);
                }


            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
//       }


    }
    public void SwipRefresh(){
        mSwipeRefreshLayout =  view.findViewById(R.id.swipe_Dropdown);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);

        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                if(networikConntection.isNetworkAvailable(getApplicationContext())) {
//                    mSwipeRefreshLayout.setRefreshing(true);
                    mShimmerViewContainer.startShimmerAnimation();
                    mShimmerViewContainer.setVisibility(View.VISIBLE);

                    carsmodel.GetCarModel(lan, user);

                }else {
//                    Snackbar.make(dropFrame,getResources().getString(R.string.internet),1500).show();
                }
                }
        });
    }
    @Override
    public void Error() {
        mSwipeRefreshLayout.setRefreshing(false);
        mShimmerViewContainer.stopShimmerAnimation();
        mShimmerViewContainer.setVisibility(View.GONE);

    }

    @Override
    public void services(final List<ServicesType> list) {
        mShimmerViewContainer.stopShimmerAnimation();
        mShimmerViewContainer.setVisibility(View.GONE);

        mSwipeRefreshLayout.setRefreshing(false);
        listcarmaintencnce = new ArrayAdapter<ServicesType>(getApplicationContext(), R.layout.textcolorspinner,list) {
            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                TextView textView = (TextView) super.getDropDownView(position, convertView, parent);
                textView.setTextColor(Color.WHITE);
                return textView;
            }
        };
        listcarmaintencnce.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        servicespinner.setOnItemSelectedListener(this);
        servicespinner.setAdapter(listcarmaintencnce);
        servicespinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Service= servicespinner.getSelectedItem().toString();

                for(i = 0; i<list.size(); i++){
                    if(list.get(i).getServiceName().equals(Service)){
                        Tybe_id=String.valueOf(list.get(i).getServicesId());
                    }
                }


            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    @Override
    public void ErrorService() {
        mSwipeRefreshLayout.setRefreshing(false);
        mShimmerViewContainer.stopShimmerAnimation();
        mShimmerViewContainer.setVisibility(View.GONE);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onRefresh() {
//        mSwipeRefreshLayout.setRefreshing(true);
        mShimmerViewContainer.startShimmerAnimation();
        mShimmerViewContainer.setVisibility(View.VISIBLE);

        carsmodel.GetCarModel(lan,user);
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putString("greeting", "Hello");
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        String greeting = (savedInstanceState != null) ? savedInstanceState.getString("greeting") : "null";

    }

    @Override
    public void onStart() {
        super.onStart();
        Fabric.with(getContext(), new Crashlytics());
    }
}
