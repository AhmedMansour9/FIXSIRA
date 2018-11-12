package com.example.ic.fixera.Adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ic.fixera.Fragments.CarMaintenence;
import com.example.ic.fixera.Fragments.Carwash;
import com.example.ic.fixera.Model.CarWashing;
import com.example.ic.fixera.Model.Filter_Places;
import com.example.ic.fixera.View.Category_id;
import com.example.ic.fixera.View.Details_Service;
import com.fixe.fixera.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by ic on 9/30/2018.
 */

public class CarMaintenence_Adapter  extends RecyclerView.Adapter<CarMaintenence_Adapter.MyViewHolder>{

    private List<Filter_Places> filteredList=new ArrayList<>();
    View itemView;
    Context con;
    Details_Service  details_service;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView VendorName,placeName,Address,Phone,telephone,textRate,Destance,T_rates,T_Price;
        private Button Callnow,Details;
        private ImageView person_image,Starone,Startwo,StarThree,StarFour,StarFive;
        public MyViewHolder(View view) {
            super(view);
            Callnow = view.findViewById(R.id.callnow);
            Details=view.findViewById(R.id.Details);
            person_image=view.findViewById(R.id.person_image);
            VendorName=view.findViewById(R.id.vendorName);
            placeName=view.findViewById(R.id.placeName);
            Starone=view.findViewById(R.id.Starone);
            Address=view.findViewById(R.id.Address);
            textRate=view.findViewById(R.id.textRate);
            Startwo=view.findViewById(R.id.Startwo);
            StarThree=view.findViewById(R.id.Starthree);
            StarFour=view.findViewById(R.id.StarFour);
            StarFive=view.findViewById(R.id.StarFive);
            Destance=view.findViewById(R.id.Distance);
            T_rates=view.findViewById(R.id.T_Rates);
            T_Price=view.findViewById(R.id.T_Price);
        }
    }
    public void setClickListener(Details_Service itemClickListener) {
        this.details_service = itemClickListener;
    }
    public CarMaintenence_Adapter(List<Filter_Places> list, Context context){
        this.filteredList=list;
        this.con=context;
    }
    @Override
    public CarMaintenence_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemcarwashing, parent, false);
        return new CarMaintenence_Adapter.MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(final CarMaintenence_Adapter.MyViewHolder holder, final int position) {
        holder.VendorName.setText(filteredList.get(position).getVendorName());
        holder.placeName.setText(filteredList.get(position).getName());
        holder.Address.setText(filteredList.get(position).getAddress());
        double a=filteredList.get(position).getDistance()/1000;
        int distance=(int)a;
        if(distance>1) {
            holder.Destance.setText(distance + "  KM");
        }
        else if(distance<1){
            String str =String.valueOf(filteredList.get(position).getDistance());
            String kept = str.substring( 0, str.indexOf("."));
            holder.Destance.setText(kept + "M");
        }
        if(Integer.parseInt(filteredList.get(position).getTotal_Rates())!=0) {
            holder.T_rates.setText(filteredList.get(position).getTotal_Rates() + "");
        }else {
            holder.T_rates.setText(con.getResources().getString(R.string.norate) + "");
        }
        if(CarMaintenence.Service == "Mobile Service") {
            holder.T_Price.setText(filteredList.get(position).getTotal_Price() + " LE");
        }else {
            holder.T_Price.setText(filteredList.get(position).getPrice() + " LE");
        }



        if(filteredList.get(position).getRate()==1){
            holder.Starone.setVisibility(View.VISIBLE);
        }else if(filteredList.get(position).getRate()==2){
            holder.Starone.setVisibility(View.VISIBLE);
            holder.Startwo.setVisibility(View.VISIBLE);
        }else if(filteredList.get(position).getRate()==3){
            holder.Starone.setVisibility(View.VISIBLE);
            holder.Startwo.setVisibility(View.VISIBLE);
            holder.StarThree.setVisibility(View.VISIBLE);
        }else if(filteredList.get(position).getRate()==4){
            holder.Starone.setVisibility(View.VISIBLE);
            holder.Startwo.setVisibility(View.VISIBLE);
            holder.StarThree.setVisibility(View.VISIBLE);
            holder.StarFour.setVisibility(View.VISIBLE);
        }else if(filteredList.get(position).getRate()==5){
            holder.Starone.setVisibility(View.VISIBLE);
            holder.Startwo.setVisibility(View.VISIBLE);
            holder.StarThree.setVisibility(View.VISIBLE);
            holder.StarFour.setVisibility(View.VISIBLE);
            holder.StarFive.setVisibility(View.VISIBLE);
        }
        String i = filteredList.get(position).getUserPhotoUrl();
        Uri u = Uri.parse(i);
//        holder.progressBar.setVisibility(View.VISIBLE);
        Picasso.with(getApplicationContext())
                .load("http://fixsira.com/"+u)
                .placeholder(R.drawable.profile)
                .fit()
                .centerCrop()
                .into(holder.person_image, new Callback() {
                    @Override
                    public void onSuccess() {
//                        holder.progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {
//                        holder.progressBar.setVisibility(View.GONE);
                    }
                });
   holder.itemView.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View view) {
           com.example.ic.fixera.Model.Details_Service filter_places=new com.example.ic.fixera.Model.Details_Service();
           filter_places.setAddress(filteredList.get(position).getAddress());
           filter_places.setId(filteredList.get(position).getId());
           filter_places.setVendorName(filteredList.get(position).getVendorName());
           filter_places.setDescription(filteredList.get(position).getDescription());
           filter_places.setUserPhotoUrl(filteredList.get(position).getUserPhotoUrl());
           filter_places.setPhone(filteredList.get(position).getPhone());
           filter_places.setRate(filteredList.get(position).getRate());
           filter_places.setLat(filteredList.get(position).getLat());
           filter_places.setLng(filteredList.get(position).getLng());
           filter_places.setTelephone(filteredList.get(position).getTelephone());
           filter_places.setName(filteredList.get(position).getName());
           filter_places.setPrice(filteredList.get(position).getPrice());
           filter_places.setVendor_id(filteredList.get(position).getVendor_id());
           filter_places.setEvragerate(String.valueOf(filteredList.get(position).getRate()));
           filter_places.setTotal_rate(filteredList.get(position).getTotal_Rates());
           filter_places.setTybe("car_maintenance");
           details_service.listsevice(filter_places);

       }
   });

    }

    @Override
    public int getItemCount() {
//        return filteredList.size();
        return filteredList.size()  ;
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

}
