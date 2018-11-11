package com.example.ic.fixera.Fragments;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.ic.fixera.Activites.TabsLayouts;
import com.fixe.fixera.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Menu extends Fragment {


    public Menu() {
        // Required empty public constructor
    }
    View view;
    ImageView Image_Sparts,CarWashing,carmaintaince,Pull_Car;
    MyReceiver r;
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
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            getFragmentManager().beginTransaction().detach(this).attach(this).commit();
        }
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
                DropDown fragmen = new DropDown();
                Bundle args = new Bundle();
                args.putString("tybe","car_washing");
                fragmen.setArguments(args);
                getFragmentManager().beginTransaction()
                        .replace(R.id.MenuFrame, fragmen )
                        .addToBackStack(null)
                        .commitAllowingStateLoss();
            }
        });
    }
    public void Open_Maintenence(){
        carmaintaince.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DropDown fragmen = new DropDown();
                Bundle args = new Bundle();
                args.putString("tybe","car_maintenance");
                fragmen.setArguments(args);
                getFragmentManager().beginTransaction()
                        .replace(R.id.MenuFrame, fragmen )
                        .addToBackStack(null)
                        .commitAllowingStateLoss();
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
    public void refresh() {
    }

    public void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(r);
    }

    public void onResume() {
        super.onResume();
        r = new MyReceiver();
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(r,
                new IntentFilter("TAG_REFRESH"));
    }

    private class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Menu.this.refresh();
        }
    }
}
