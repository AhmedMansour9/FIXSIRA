package com.example.ic.fixera.Fragments;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ic.fixera.Activites.TabsLayouts;
import com.example.ic.fixera.Language;
import com.example.ic.fixera.Presenter.Order_Service_Presenter;
import com.example.ic.fixera.View.OrderService_View;
import com.fixe.fixera.R;
import com.fourhcode.forhutils.FUtilsValidation;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;
import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * A simple {@link Fragment} subclass.
 */
public class Details_Services_fragment extends Fragment implements OrderService_View{


    public Details_Services_fragment() {
        // Required empty public constructor
    }
    private String id,title,price,image,address,tybe,phone;
   View view;
   ImageView person_image;
   TextView PlaceName,Address,Price;
   Button imgphone,btn_date,Reserve;
   TextView textdate;
   DatePickerDialog.OnDateSetListener dateSetListener;
   EditText E_Phone,E_CarName,E_CarModel,E_CarYear;
   String    T_Phone,T_CarName,T_CarModel,T_CarYear;
    Order_Service_Presenter order_service;
    SharedPreferences Shared;
    String user;
    ProgressBar Progross_Service;
    FrameLayout Details_Frame;
    String Car_id,Service_id;
    String Tybe_Service;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.details_service, container, false);
        order_service=new Order_Service_Presenter(getContext(),this);
        Shared=getActivity().getSharedPreferences("login",MODE_PRIVATE);
        Details_Frame=view.findViewById(R.id.Details_Frame);
        user=Shared.getString("logggin",null);
        Progross_Service=view.findViewById(R.id.Progross_Service);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(new Date());

        init();
        textdate.setText(date);
        GetData();
        CallPhone();
        GetDate();
        Reservee();

        return view;
    }
    public void init(){
        person_image=view.findViewById(R.id.person_image);
        PlaceName=view.findViewById(R.id.PlaceName);
        Address=view.findViewById(R.id.Address);
        imgphone=view.findViewById(R.id.calldriver);
//        btn_date=view.findViewById(R.id.Btn_Date);
        textdate=view.findViewById(R.id.T_Date);
        E_Phone=view.findViewById(R.id.E_Phone);
        E_CarName=view.findViewById(R.id.E_CarName);
        E_CarModel=view.findViewById(R.id.E_CarModel);
        E_CarYear=view.findViewById(R.id.E_Year);
        Reserve=view.findViewById(R.id.Reserve);
    }
    public void Reservee(){
        Reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TabsLayouts.latitude != 0.0) {
                    T_Phone = E_Phone.getText().toString();
                    T_CarName = E_CarName.getText().toString();
                    T_CarModel = E_CarModel.getText().toString();
                    T_CarYear = E_CarYear.getText().toString();
                    if (tybe.equals("car_washing"))

                    FUtilsValidation.isEmpty(E_Phone, getResources().getString(R.string.fullinformation));
                    FUtilsValidation.isEmpty(E_CarName, getResources().getString(R.string.fullinformation));
                    FUtilsValidation.isEmpty(E_CarModel, getResources().getString(R.string.fullinformation));
                    FUtilsValidation.isEmpty(E_CarYear, getResources().getString(R.string.fullinformation));

                    if (!E_Phone.getText().toString().equals("") && !E_CarName.getText().toString().equals("") && !E_CarModel.getText().toString().equals("") && !E_CarYear.getText().toString().equals("") &&
                            (!textdate.getText().toString().equals(""))) {

                        if (Language.isRTL()) {
                            Progross_Service.setVisibility(View.VISIBLE);
                            order_service.Order_Service("ar", user, id, tybe, T_Phone, T_CarName,
                                    T_CarModel, T_CarYear, textdate.getText().toString(),
                                    String.valueOf(TabsLayouts.latitude), String.valueOf(TabsLayouts.longitude),Car_id,Service_id,Tybe_Service);
                        } else {
                            Progross_Service.setVisibility(View.VISIBLE);
                            order_service.Order_Service("en", user, id, tybe, T_Phone, T_CarName,
                                    T_CarModel, T_CarYear, textdate.getText().toString(),
                                    String.valueOf(TabsLayouts.latitude), String.valueOf(TabsLayouts.longitude),Car_id,Service_id,Tybe_Service);
                        }
                    }

                } else {
                    Toast.makeText(getContext(), getResources().getString(R.string.turnon), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    public void GetDate(){
        textdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar=Calendar.getInstance();
                int year=calendar.get(Calendar.YEAR);
                int month=calendar.get(Calendar.MONTH);
                int day=calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog=new DatePickerDialog(getContext(),
                        android.R.style.Theme_Holo_Dialog_MinWidth,
                        dateSetListener,
                        year,month,day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
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
            title=args.getString("placeName");
            price=args.getString("price");
            image=args.getString("image");
            address=args.getString("address");
            tybe=args.getString("tybe");
            phone=args.getString("phone");
            Car_id=args.getString("car_id");
            Service_id=args.getString("tybe_id");
            Tybe_Service=args.getString("tybeservice");
            PlaceName.setText(title);
            Address.setText(address);


            Picasso.with(getApplicationContext())
                    .load("http://fixsira.com/site/"+image)
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

    @Override
    public void Orders(Integer list,String pricce) {
        Thanks_Order fragmen = new Thanks_Order();
        Bundle args = new Bundle();
        args.putString("orderid",String.valueOf(list));
        args.putString("price",pricce);

        fragmen.setArguments(args);
        getFragmentManager().beginTransaction()
                .replace(R.id.MenuFrame, fragmen )
                .addToBackStack(null)
                .commitAllowingStateLoss();

        Progross_Service.setVisibility(View.GONE);
    }

    @Override
    public void Error() {

        Progross_Service.setVisibility(View.GONE);
    }
}
