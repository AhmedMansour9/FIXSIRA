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

import com.example.ic.fixera.Language;
import com.example.ic.fixera.Model.CarModel;
import com.example.ic.fixera.Model.ServicesType;
import com.example.ic.fixera.Model.Spinner_CarModel;
import com.example.ic.fixera.NetworikConntection;
import com.example.ic.fixera.Presenter.Get_CarModels_Presenter;
import com.example.ic.fixera.Presenter.Servicetybe_Presenter;
import com.example.ic.fixera.View.CarModels_View;
import com.example.ic.fixera.View.Spinner_Service_tybe_View;
import com.fixe.fixera.R;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;
import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * A simple {@link Fragment} subclass.
 */
public class DropDown extends Fragment implements SwipeRefreshLayout.OnRefreshListener,CarModels_View,Spinner_Service_tybe_View,AdapterView.OnItemSelectedListener{


    public DropDown() {
        // Required empty public constructor
    }
    ArrayAdapter<CarModel> ListCarModel;
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
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_drop_down__car_maintenence, container, false);

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

                if(CarModel!=null&&Service!=null)
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
        categories.add(getResources().getString(R.string.mobileservice));
        categories.add(getResources().getString(R.string.outside));
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

                if(SelectService.getSelectedItem().toString().equals("زيارة")){
                    service="MobileService";
                }else if(SelectService.getSelectedItem().toString().equals("الصيانة في المكان")){
                    service="On Site";
                }else if(SelectService.getSelectedItem().toString().equals("Mobile Service")){
                    service="Mobile Service";
                }
                else if(SelectService.getSelectedItem().toString().equals("On Site")){
                    service="On Site";
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    @Override
    public void GetCarModels(final List<CarModel> list) {
        mSwipeRefreshLayout.setRefreshing(false);
        ListCarModel = new ArrayAdapter<CarModel>(getApplicationContext(), R.layout.textcolorspinner,list) {
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
                    if(list.get(i).getModels().equals(CarModel)){
                        Car_id=String.valueOf(list.get(i).getId());
                    }
                }
                mSwipeRefreshLayout.setRefreshing(true);
                servicetyb.GetServices(lan, tybe, user,Car_id);


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
                    mSwipeRefreshLayout.setRefreshing(true);
                    carsmodel.GetCarModel(lan, user);

                }else {
                    Snackbar.make(dropFrame,getResources().getString(R.string.internet),1500).show();
                }
                }
        });
    }
    @Override
    public void Error() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void services(final List<ServicesType> list) {
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
                    if(list.get(i).getName().equals(Service)){
                        Tybe_id=String.valueOf(list.get(i).getId());
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
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.setRefreshing(true);
        carsmodel.GetCarModel(lan,user);


    }
}
