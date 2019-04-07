package com.example.ic.fixera.Fragments;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.ic.fixera.Activites.Navigation;
import com.fixsira.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Thanks_Order extends Fragment {


    public Thanks_Order() {
        // Required empty public constructor
    }
    TextView order,price;
    View view;
    Button finish;
    Toolbar toolbars;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.thanks_orders, container, false);
        finish=view.findViewById(R.id.finish);
        order=view.findViewById(R.id.T_Order);
        price=view.findViewById(R.id.T_Price);
        Bundle args = getArguments();
        toolbars=view.findViewById(R.id.toolbar);
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
        if (args != null) {
          String   title=args.getString("orderid");
            String pricee=args.getString("price");
            order.setText(title);
            price.setText(pricee+" LE");
        }
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Navigation.class));
                getActivity().finish();
            }
        });

        return view;
    }

}
