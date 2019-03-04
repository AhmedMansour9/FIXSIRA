package com.example.ic.fixera.Fragments;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ic.fixera.Activites.MainActivity;
import com.example.ic.fixera.Activites.TabsLayouts;
import com.example.ic.fixera.Adapter.Gallery_Adapter;
import com.example.ic.fixera.Language;
import com.example.ic.fixera.Model.GalleryImage;
import com.example.ic.fixera.Presenter.AddCart_Presenter;
import com.example.ic.fixera.Presenter.Gallery_Image_Presenter;
import com.example.ic.fixera.View.Cart_View;
import com.example.ic.fixera.View.Gallery_View;
import com.fixsira.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;
import static com.facebook.FacebookSdk.getApplicationContext;


/**
 * A simple {@link Fragment} subclass.
 */
public class Spart_Details extends Fragment implements Gallery_View,Cart_View{


    public Spart_Details() {
        // Required empty public constructor
    }

    public String id,title,price,stock,image,description,vendorname,evragerate;
    ImageView imgspart;
    TextView T_Name,Text_Price,Text_Stock,Text_Descrption,T_Vendorname;
    View view;
    Gallery_Image_Presenter gallery_image;
    RecyclerView recyclerView;
    Gallery_Adapter gallery_adapter;
    public static String name="Spart_Details";
    Button Add_Cart;
    ProgressBar ProgrossSpare;
    AddCart_Presenter addCart;
    SharedPreferences Shared;
    String user;
    FrameLayout spartFrame;
    String vendor_id;
    ImageView plus,minus;
    TextView T_Count;
    Button reviews;
    int current;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_spart_details, container, false);
        gallery_image=new Gallery_Image_Presenter(getContext(),this);
        init();
        GetData();
        Recyclview();
        Add_To_Cart();
        Plus();
        minus();
      openReviwes();
        if(Language.isRTL()){
            gallery_image.GetImages("ar",id);
        }else {
            gallery_image.GetImages("en",id);
        }


        return view;
    }
    public void openReviwes(){
        reviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                All_Reviwes fragmen = new All_Reviwes();
                Bundle args = new Bundle();
                args.putString("id",id);


                fragmen.setArguments(args);
                getFragmentManager().beginTransaction()
                        .replace(R.id.MenuFrame, fragmen )
                        .addToBackStack(null)
                        .commitAllowingStateLoss();

            }
        });
    }
    public void Recyclview(){
        recyclerView = view.findViewById(R.id.recycler_SparImagesDetails);
        recyclerView.setHasFixedSize(true);
    }
    public void Plus(){
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 current = Integer.valueOf(T_Count.getText().toString());
                 current++;
                T_Count.setText(current+"");

            }
        });
    }
    public void minus(){
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 current = Integer.valueOf(T_Count.getText().toString());
                if(current>1) {
                    current--;
                    T_Count.setText(current + "");
                }
            }
        });
    }
    public void Add_To_Cart(){
        Add_Cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(user!=null) {
                    ProgrossSpare.setVisibility(View.VISIBLE);
                    if (Language.isRTL()) {
                        addCart.Add_toCart("ar", user, id, vendor_id, Integer.parseInt(T_Count.getText().toString()));
                    } else {
                        addCart.Add_toCart("en", user, id, vendor_id, Integer.parseInt(T_Count.getText().toString()));
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

   public void GetData(){
       Bundle args = getArguments();
       if (args != null) {
           id=args.getString("id");
           title=args.getString("title");
           price=args.getString("price");
           stock=args.getString("stock");
           image=args.getString("image");
           vendor_id=args.getString("vendor_id");
           description=args.getString("description");
           vendorname=args.getString("vendorname");
           evragerate=args.getString("evragerate");
           T_Vendorname.setText(vendorname);
           T_Name.setText(title);
           Text_Price.setText(price);
           Text_Stock.setText(stock);
           Text_Descrption.setText(description);
           Picasso.with(getApplicationContext())
                   .load("http://fixsira.com/"+image)
                   .into(imgspart, new Callback() {
                       @Override
                       public void onSuccess() {
                       }

                       @Override
                       public void onError() {
                       }
                   });
       }
   }
  public void init(){
      Shared=getActivity().getSharedPreferences("login",MODE_PRIVATE);
      user=Shared.getString("logggin",null);
      ProgrossSpare=view.findViewById(R.id.ProgrossSpare);

      Add_Cart=view.findViewById(R.id.Add_Cart);
      spartFrame=view.findViewById(R.id.spartFrame);
      T_Vendorname=view.findViewById(R.id.T_Vendorname);
      T_Count=view.findViewById(R.id.T_qount);
      plus=view.findViewById(R.id.plus);
      minus=view.findViewById(R.id.minus);
      addCart=new AddCart_Presenter(getContext(),this);
      T_Name=view.findViewById(R.id.T_Name);
      Text_Price=view.findViewById(R.id.Text_Price);
      Text_Stock=view.findViewById(R.id.Text_Stock);
      Text_Descrption=view.findViewById(R.id.Text_Descrption);
      imgspart=view.findViewById(R.id.Image_Caetgory);
      reviews=view.findViewById(R.id.reviews);
  }

    @Override
    public void listimages(List<GalleryImage> images) {
        gallery_adapter = new Gallery_Adapter(images,getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(gallery_adapter);
    }

    @Override
    public void Error() {

    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void Success() {
        Toast.makeText(getContext(),getResources().getString(R.string.cartsuccesfull), Toast.LENGTH_SHORT).show();

         startActivity(new Intent(getContext(), TabsLayouts.class));
        getActivity().finish();
//        getFragmentManager().beginTransaction().replace(R.id.MenuFrame,new Menu()).commit();
        ProgrossSpare.setVisibility(View.INVISIBLE);
    }

    @Override
    public void Failed() {
        ProgrossSpare.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onPause() {
        super.onPause();
//        getActivity().finish();
    }
}
