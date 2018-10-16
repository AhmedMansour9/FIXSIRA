package com.example.ic.fixera.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.ic.fixera.Activites.TabsLayouts;
import com.example.ic.fixera.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Menu extends Fragment {


    public Menu() {
        // Required empty public constructor
    }
    View view;
    ImageView Image_Sparts,CarWashing,carmaintaince,Pull_Car;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_menu, container, false);
        Image_Sparts=view.findViewById(R.id.Image_Sparts);
        CarWashing=view.findViewById(R.id.CarWashing);
//        MenuFrame=view.findViewById(R.id.MenuFrame);
        Pull_Car=view.findViewById(R.id.Pull_Car);
        carmaintaince=view.findViewById(R.id.carmaintaince);
        TabsLayouts.T_Service.setText(getResources().getString(R.string.chooserservice));
        Open_Sparts();
      Open_CarWashing();
      Open_Maintenence();
      Open_PullCar();
        return view;
    }

    public void Open_Sparts(){
        Image_Sparts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.MenuFrame,new Categories_SparParts()).addToBackStack(null).commitAllowingStateLoss();
            }
        });
    }
    public void Open_CarWashing(){
        CarWashing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.MenuFrame,new Carwash()).addToBackStack(null).commitAllowingStateLoss();
            }
        });
    }
    public void Open_Maintenence(){
        carmaintaince.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.MenuFrame,new CarMaintenence()).addToBackStack(null).commitAllowingStateLoss();
            }
        });
    }
    public void Open_PullCar(){
        Pull_Car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.MenuFrame,new PullCar()).addToBackStack(null).commitAllowingStateLoss();
            }
        });
    }
}
