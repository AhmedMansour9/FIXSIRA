package com.example.ic.fixera.Fragments;


import android.content.Context;
import android.content.SharedPreferences;
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
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.ic.fixera.Activites.Navigation;
import com.example.ic.fixera.Language;
import com.example.ic.fixera.Presenter.OrderCart_Presenter;
import com.example.ic.fixera.Presenter.Profile_Presenter;
import com.example.ic.fixera.View.OrderCart_View;
import com.example.ic.fixera.View.Profile_View;
import com.fixsira.R;
import com.fourhcode.forhutils.FUtilsValidation;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class Order_Cart extends Fragment implements OrderCart_View,Profile_View {


    public Order_Cart() {
        // Required empty public constructor
    }
   View view;
    TextView T_Price;
    String price;
    EditText T_Email,T_Name,T_Phone,T_Address;
    Button servicerequest;
    OrderCart_Presenter orderCart_presenter;
    String lan;
    SharedPreferences Shared;
    String user;
    TextView T_Note;
    ProgressBar progressBar;
    Profile_Presenter profile_presenter;
    Toolbar toolbars;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_order__cart, container, false);
        Shared=getActivity().getSharedPreferences("login",MODE_PRIVATE);
        user=Shared.getString("logggin",null);
        progressBar=view.findViewById(R.id.prgrosss);
        toolbars=view.findViewById(R.id.toolbar);
        orderCart_presenter=new OrderCart_Presenter(getContext(),this);

        if(Language.isRTL()){
            lan="ar";
        }else {
            lan="en";
        }
        init();
        GetData();
        profile_presenter=new Profile_Presenter(getContext(),this);
        progressBar.setVisibility(View.VISIBLE);
        profile_presenter.register(user,lan);

        servicerequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FUtilsValidation.isEmpty(T_Email,getResources().getString(R.string.fullinformation));
                FUtilsValidation.isEmpty(T_Name,getResources().getString(R.string.fullinformation));
                FUtilsValidation.isEmpty(T_Phone,getResources().getString(R.string.fullinformation));
                FUtilsValidation.isEmpty(T_Address,getResources().getString(R.string.fullinformation));
                if (!T_Email.getText().toString().equals("") && !T_Name.getText().toString().equals("") && !T_Phone.getText().toString().equals("")&& !T_Address.getText().toString().equals("")) {
                    progressBar.setVisibility(View.VISIBLE);
                    orderCart_presenter.Order_Service(lan,user,T_Note.getText().toString(),T_Name.getText().toString(),T_Email.getText().toString(),T_Phone.getText().toString(),T_Address.getText().toString());
                }
                }
        });

        return view;
    }
    public void GetData(){
        Bundle args = getArguments();
        if (args != null) {
            price = args.getString("price");
            T_Price.setText(price+" LE");
        }
    }
    public void init(){
        T_Price=view.findViewById(R.id.T_Price);
        T_Email=view.findViewById(R.id.T_Email);
        T_Name=view.findViewById(R.id.T_Name);
        T_Phone=view.findViewById(R.id.T_Phone);
        T_Address=view.findViewById(R.id.T_Address);
        servicerequest=view.findViewById(R.id.servicerequest);
        T_Note=view.findViewById(R.id.T_Note);

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
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Navigation.Visablty = false;
    }

    @Override
    public void onStop() {
        super.onStop();
        Navigation.Visablty = true;
    }
    @Override
    public void order(String orderid) {
        progressBar.setVisibility(View.GONE);
        Thanks_Order fragmen = new Thanks_Order();
        Bundle args = new Bundle();
        args.putString("orderid",orderid);
        args.putString("price",price);

        fragmen.setArguments(args);
        getFragmentManager().beginTransaction()
                .replace(R.id.RelativeOrder, fragmen )
                .addToBackStack(null)
                .commitAllowingStateLoss();

    }

    @Override
    public void getProfile(String user, String Email, String userphoto,String phone,String carmodel,String caryear) {
        progressBar.setVisibility(View.GONE);
        T_Name.setText(user);
        T_Email.setText(Email);
        T_Phone.setText(phone);
    }

    @Override
    public void Error() {
        progressBar.setVisibility(View.GONE);
    }
}
