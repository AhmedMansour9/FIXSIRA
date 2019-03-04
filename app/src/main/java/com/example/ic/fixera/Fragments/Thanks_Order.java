package com.example.ic.fixera.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.ic.fixera.Activites.TabsLayouts;
import com.fixsira.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import static com.facebook.FacebookSdk.getApplicationContext;

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
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.thanks_orders, container, false);
        finish=view.findViewById(R.id.finish);
        order=view.findViewById(R.id.T_Order);
        price=view.findViewById(R.id.T_Price);
        Bundle args = getArguments();
        if (args != null) {
          String   title=args.getString("orderid");
            String pricee=args.getString("price");

            order.setText(title);
            price.setText(pricee+" LE");


        }
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), TabsLayouts.class));
                getActivity().finish();
            }
        });

        return view;
    }

}
