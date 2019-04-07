package com.example.ic.fixera.Fragments;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.example.ic.fixera.Activites.MainActivity;
import com.example.ic.fixera.Activites.Navigation;
import com.example.ic.fixera.Language;
import com.example.ic.fixera.Presenter.Order_Service_Presenter;
import com.example.ic.fixera.Presenter.Profile_Presenter;
import com.example.ic.fixera.View.OrderService_View;
import com.example.ic.fixera.View.Profile_View;
import com.fixsira.R;
import com.fourhcode.forhutils.FUtilsValidation;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import io.fabric.sdk.android.Fabric;

import static android.content.Context.MODE_PRIVATE;
import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * A simple {@link Fragment} subclass.
 */
public class Details_Services_fragment extends Fragment implements Profile_View,OrderService_View{


    public Details_Services_fragment() {
        // Required empty public constructor
    }
    private String id,title,price,image,address,tybe,phone;
   View view;
   ImageView person_image;
   TextView PlaceName,Address,Price,T_Address;
   Button imgphone,btn_date,Reserve;
   EditText textdate;
   DatePickerDialog.OnDateSetListener dateSetListener;
   EditText E_Phone,E_CarName,E_CarModel,E_CarYear,E_Address;
   String    T_Phone,T_CarName,T_CarModel,T_CarYear;
    Order_Service_Presenter order_service;
    ProgressBar Progross_Service;
    FrameLayout Details_Frame;
    String Car_id,Service_id;
    String Tybe_Service,services_id;
    Calendar date;
    RadioButton rad1,rad2;
    RadioGroup radioGroup;
    Boolean checkedAddress;
    String lan;
    Profile_Presenter profile_presenter;
    String user;
    SharedPreferences Shared;
    // initiate a Switch
    Switch simpleSwitch;
    TextView T_Price;
    String totalprice;
    Toolbar toolbars;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.details_service, container, false);
        order_service=new Order_Service_Presenter(getContext(),this);
        Shared=getActivity().getSharedPreferences("login",MODE_PRIVATE);
        Details_Frame=view.findViewById(R.id.Details_Frame);
        simpleSwitch=view.findViewById(R.id.switch1);
        T_Price=view.findViewById(R.id.T_Price);

        user=Shared.getString("logggin",null);
        Progross_Service=view.findViewById(R.id.Progross_Service);
//        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        String date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH).format(new Date());
        init();
        textdate.setText(date);
        GetData();
//        CallPhone();
        GetDate();
        Reservee();
//        visabltyAddress();
        profile_presenter=new Profile_Presenter(getContext(),this);
        Progross_Service.setVisibility(View.VISIBLE);
        profile_presenter.register(user,"en");

        if (Language.isRTL()) {
            lan="ar";
        }else {
            lan="en";
        }

        return view;
    }
//    public void visabltyAddress(){
//        radioGroup = view.findViewById(R.id.myRadioGroup);
//         radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup radioGroup, int i) {
//                if(i == R.id.radio1) {
//                    rad2.setChecked(false);
//                    checkedAddress=false;
//                    T_Address.setVisibility(View.GONE);
//                    E_Address.setVisibility(View.GONE);
//                } else if(i == R.id.radio2) {
//                    rad1.setChecked(false);
//                    checkedAddress=true;
//                    T_Address.setVisibility(View.VISIBLE);
//                    E_Address.setVisibility(View.VISIBLE);
//                }
//
//            }
//        });
//    }
    public void init(){
        person_image=view.findViewById(R.id.person_image);
        PlaceName=view.findViewById(R.id.PlaceName);
        Address=view.findViewById(R.id.Address);
        imgphone=view.findViewById(R.id.calldriver);
//        T_Address=view.findViewById(R.id.addrs);
//        E_Address=view.findViewById(R.id.E_Address);
//        rad1=view.findViewById(R.id.radio1);
//        rad2=view.findViewById(R.id.radio2);
        textdate=view.findViewById(R.id.T_Date);
        E_Phone=view.findViewById(R.id.E_Phone);
        E_CarName=view.findViewById(R.id.E_CarName);
        E_CarModel=view.findViewById(R.id.E_CarModel);
        E_CarYear=view.findViewById(R.id.E_Year);
        Reserve=view.findViewById(R.id.Reserve);
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

    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Navigation.Visablty = false;
//        Navigation.toolbar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onStop() {
        super.onStop();
        Navigation.Visablty = true;

    }

    @Override
    public void onDetach() {
        super.onDetach();
//        Navigation.toolbar.setVisibility(View.GONE);
    }

    public void Reservee(){
        Reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(user!=null) {
                   T_Phone = E_Phone.getText().toString();
                   T_CarName = E_CarName.getText().toString();
                   T_CarModel = E_CarModel.getText().toString();
                   T_CarYear = E_CarYear.getText().toString();

                       FUtilsValidation.isEmpty(E_Phone, getResources().getString(R.string.fullinformation));
                   FUtilsValidation.isEmpty(E_CarName, getResources().getString(R.string.fullinformation));
                   FUtilsValidation.isEmpty(E_CarModel, getResources().getString(R.string.fullinformation));
                   FUtilsValidation.isEmpty(E_CarYear, getResources().getString(R.string.fullinformation));

                   if (!E_Phone.getText().toString().equals("") && !E_CarName.getText().toString().equals("") &&
                           !E_CarModel.getText().toString().equals("") && !E_CarYear.getText().toString().equals("") &&
                           (!textdate.getText().toString().equals(""))) {

                       Progross_Service.setVisibility(View.VISIBLE);
                       order_service.Order_Service(services_id, lan, user, id, tybe, T_Phone, T_CarName,
                               T_CarModel, T_CarYear, textdate.getText().toString(),
                               String.valueOf(TabsLayouts.latitude), String.valueOf(TabsLayouts.longitude)
                               , Car_id, Service_id, Tybe_Service,"");
                   }

               }else {
                   Toast.makeText(getActivity(), ""+getResources().getString(R.string.pleaseloginfirst)
                           , Toast.LENGTH_SHORT).show();
                   startActivity(new Intent(getActivity(), MainActivity.class));
                   getActivity().finish();
               }

            }
        });

    }
    public void GetDate(){
        textdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Calendar calendar=Calendar.getInstance();
//                int year=calendar.get(Calendar.YEAR);
//                int month=calendar.get(Calendar.MONTH);
//                int day=calendar.get(Calendar.DAY_OF_MONTH);
//
//                DatePickerDialog datePickerDialog=new DatePickerDialog(getContext(),
//                        android.R.style.Theme_Holo_Dialog_MinWidth,
//                        dateSetListener,
//                        year,month,day);
//                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                datePickerDialog.show();
                showDateTimePicker();
            }
        });
        dateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                textdate.setText(""+i+"-"+i1+"-"+i2);
            }
        };
    }

    public void CallPhone(){
        imgphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                startActivity(intent);

            }
        });
    }
    public void GetData(){
        Bundle args = getArguments();
        if (args != null) {
            id=args.getString("id");
            services_id=args.getString("services_id");
            title=args.getString("placeName");
            price=args.getString("price");
            image=args.getString("image");
            address=args.getString("address");
            tybe=args.getString("tybe");
            phone=args.getString("phone");
            Car_id=args.getString("car_id");
            Service_id=args.getString("tybe_id");
//            Tybe_Service=args.getString("tybeservice");
            totalprice=args.getString("totalprice");
            services_id=args.getString("services_id");

//            PlaceName.setText(title);
//            Address.setText(address);

            Boolean switchState = simpleSwitch.isChecked();
            if(switchState){
                Tybe_Service="Mobile Service";
                if(totalprice!=null) {

                    T_Price.setVisibility(View.VISIBLE);
                    T_Price.setText(totalprice + getResources().getString(R.string.currency));
                }
            }else {
                Tybe_Service="On Site";
            }
            simpleSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if(b){
                        Tybe_Service="Mobile Service";
                        if(totalprice!=null) {
                            T_Price.setVisibility(View.VISIBLE);
                            T_Price.setText(totalprice + getResources().getString(R.string.currency));
                        }

                    }else {
                        Tybe_Service="On Site";
                        T_Price.setVisibility(View.GONE);
                    }
                }
            });
//
            if (tybe.equals("pull_washing")){

            } else  if (tybe.equals("car_maintenance")){

            }else  if (tybe.equals("car_washing")){

            }


        }
    }
    @Override
    public void Orders(Integer list,String pricce) {
        Boolean switchState = simpleSwitch.isChecked();
        Thanks_Order fragmen = new Thanks_Order();
        Bundle args = new Bundle();
        args.putString("orderid", String.valueOf(list));
        if (switchState) {
            args.putString("price", totalprice);
        } else{
            args.putString("price", price);
    }

        if (tybe.equals("pull_washing")){
            fragmen.setArguments(args);
            getFragmentManager().beginTransaction()
                    .replace(R.id.Frame_PullCar, fragmen )
                    .addToBackStack(null)
                    .commitAllowingStateLoss();

        } else  if (tybe.equals("car_maintenance")){
            fragmen.setArguments(args);
            getFragmentManager().beginTransaction()
                    .replace(R.id.carRelative, fragmen )
                    .addToBackStack(null)
                    .commitAllowingStateLoss();

        }else  if (tybe.equals("car_washing")){
            fragmen.setArguments(args);
            getFragmentManager().beginTransaction()
                    .replace(R.id.carwash_Frame, fragmen )
                    .addToBackStack(null)
                    .commitAllowingStateLoss();

        }

        Progross_Service.setVisibility(View.GONE);
    }

    @Override
    public void getProfile(String user, String Email, String userphoto, String phone, String carmodel, String caryear) {

        E_CarName.setText(user);
        E_Phone.setText(phone);
       E_CarModel.setText(carmodel);
        E_CarYear.setText(caryear);

        Progross_Service.setVisibility(View.GONE);

    }

    @Override
    public void Error() {

        Progross_Service.setVisibility(View.GONE);
    }
    public void showDateTimePicker() {
        final Calendar currentDate = Calendar.getInstance();
        date = Calendar.getInstance();
        new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, final int year, final int monthOfYear, final int dayOfMonth) {
                date.set(year, monthOfYear, dayOfMonth);
                new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        date.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        date.set(Calendar.MINUTE, minute);
                        textdate.setText(""+year+"-"+monthOfYear+"-"+dayOfMonth+" "+hourOfDay+":"+minute);
                    }
                }, currentDate.get(Calendar.HOUR_OF_DAY), currentDate.get(Calendar.MINUTE), false).show();
            }
        }, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DATE)).show();
    }


}
