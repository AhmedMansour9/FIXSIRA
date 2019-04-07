package com.example.ic.fixera.Fragments;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.crashlytics.android.Crashlytics;
import com.example.ic.fixera.Language;
import com.example.ic.fixera.Presenter.Review_Presenter;
import com.example.ic.fixera.View.Review_View;
import com.fixsira.R;
import com.fourhcode.forhutils.FUtilsValidation;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import io.fabric.sdk.android.Fabric;

import static android.content.Context.MODE_PRIVATE;
import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * A simple {@link Fragment} subclass.
 */
public class Rating_Service extends Fragment implements Review_View{


    public Rating_Service() {
        // Required empty public constructor
    }

     TextView T_Name,T_Price,T_status;
    RatingBar ratingBar;
    EditText commnt;
    View view;
    Button Review;
    public String id,vendorid,servicetybe,carname,carmodel,price,img,date,status;
    ImageView img_Service;
    Review_Presenter review_presenter;
    SharedPreferences Shared;
    String user;
   String lan;
   String comment;
   String rating;
   ProgressBar progrossRating;
   CardView views;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_rating, container, false);
        Shared=getActivity().getSharedPreferences("login",MODE_PRIVATE);
        progrossRating=view.findViewById(R.id.progrossRating);
        views=view.findViewById(R.id.view);
        user=Shared.getString("logggin",null);
        img_Service=view.findViewById(R.id.img_Service);
        T_Name=view.findViewById(R.id.T_Name);
        T_Price=view.findViewById(R.id.T_Price);
        T_status=view.findViewById(R.id.T_status);
        ratingBar=view.findViewById(R.id.ratingBar);
        commnt=view.findViewById(R.id.comment);
        Review=view.findViewById(R.id.Review);
        Review=view.findViewById(R.id.Review);
        review_presenter=new Review_Presenter(getContext(),this);
        if(Language.isRTL()){
            lan="ar";
        }else {
            lan="en";
        }
      getData();
      SendReview();

        return view;
    }
    public void SendReview(){
        Review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rating=String.valueOf(ratingBar.getRating());
                comment=commnt.getText().toString();


                if(commnt.getText().toString().equals("")){
                    FUtilsValidation.isEmpty(commnt,"Insert Comment");
                }else {
                    progrossRating.setVisibility(View.VISIBLE);
                    review_presenter.Review(user, lan, comment, vendorid, rating);
                }
            }
        });
    }
    public void getData(){
        Bundle args = getArguments();
        if (args != null) {
            id=args.getString("id");
            servicetybe=args.getString("tybe");
            vendorid=args.getString("vendorid");
            carname=args.getString("carname");
            carmodel=args.getString("carmodel");
            img=args.getString("img");
            date=args.getString("date");
            status=args.getString("status");
            price=args.getString("price");
            T_Name.setText(carname);
            T_Price.setText(price);
            T_status.setText(status);
            if(status.equals("pending")){
                Review.setVisibility(View.INVISIBLE);
                ratingBar.setVisibility(View.INVISIBLE);
                commnt.setVisibility(View.INVISIBLE);
            }
            Picasso.with(getApplicationContext())
                    .load("http://fixsira.com/"+img)
                    .into(img_Service, new Callback() {
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
    public void review() {
        Snackbar.make(views,"Thanks For Your Rating",1500).show();
        progrossRating.setVisibility(View.GONE);
    }

    @Override
    public void Error() {

        progrossRating.setVisibility(View.GONE);
    }

    @Override
    public void reviewProduct() {

    }

    @Override
    public void reviewed() {
        Snackbar.make(views,getResources().getString(R.string.reviewed),1500).show();
    }

    @Override
    public void ErrorProduct() {

    }

    @Override
    public void onStart() {
        super.onStart();

    }
}
