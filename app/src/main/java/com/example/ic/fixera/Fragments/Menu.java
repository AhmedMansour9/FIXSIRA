package com.example.ic.fixera.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.ic.fixera.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Menu extends Fragment {


    public Menu() {
        // Required empty public constructor
    }
    View view;
    ImageView Image_Sparts;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_menu, container, false);
        Image_Sparts=view.findViewById(R.id.Image_Sparts);
        TabsLayouts.T_Service.setText(getResources().getString(R.string.chooserservice));
        Open_Sparts();


        return view;
    }

    public void Open_Sparts(){
        Image_Sparts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.MenuFrame,new SparParts()).addToBackStack(null).commit();
            }
        });
    }
}
