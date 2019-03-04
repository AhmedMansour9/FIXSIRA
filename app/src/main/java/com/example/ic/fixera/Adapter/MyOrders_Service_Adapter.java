package com.example.ic.fixera.Adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.ic.fixera.Model.MyOrders_Service;
import com.example.ic.fixera.Model.My_Order_Services;
import com.example.ic.fixera.Model.Sparts_Category;
import com.example.ic.fixera.View.Category_id;
import com.example.ic.fixera.View.MyOrdersService;
import com.fixsira.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by Ahmed on 15/10/2018.
 */

public class MyOrders_Service_Adapter extends RecyclerView.Adapter<MyOrders_Service_Adapter.MyViewHolder>{

    private List<MyOrders_Service> filteredList=new ArrayList<>();
    View itemView;
    Context con;
    Category_id category_id;
    MyOrdersService myOrdersService;
     public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView T_Name,T_sevice,T_CarName,T_Price,schedule_date,status;
        private Button Callnow,Details;
        private ImageView img_Cart;
        private ProgressBar ProgrossSpare;
        public MyViewHolder(View view) {
            super(view);
            T_Name=view.findViewById(R.id.T_Name);
            T_sevice=view.findViewById(R.id.T_sevice);
            T_CarName=view.findViewById(R.id.T_CarName);
            T_Price=view.findViewById(R.id.T_Price);
            schedule_date=view.findViewById(R.id.schedule_date);
            status=view.findViewById(R.id.status);
            img_Cart=view.findViewById(R.id.img_Cart);
        }
    }
    public MyOrders_Service_Adapter(List<MyOrders_Service> list, Context context){
        this.filteredList=list;
        this.con=context;
    }
    public void setClickListener(MyOrdersService itemClickListener) {
        this.myOrdersService = itemClickListener;
    }

    @Override
    public MyOrders_Service_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemmyservices, parent, false);
        return new MyOrders_Service_Adapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyOrders_Service_Adapter.MyViewHolder holder, final int position) {
        holder.T_Name.setText(filteredList.get(position).getName());
        holder.T_sevice.setText(filteredList.get(position).getServicesType());
        holder.T_CarName.setText(filteredList.get(position).getCarName());
        holder.T_Price.setText(String.valueOf(filteredList.get(position).getPrice()));
        holder.schedule_date.setText(filteredList.get(position).getScheduleDate());
        holder.status.setText(String.valueOf(filteredList.get(position).getStatus()));
       if(filteredList.get(position).getImg()!=null) {
           String i = filteredList.get(position).getImg();
           Uri u = Uri.parse(i);
//        holder.ProgrossSpare.setVisibility(View.VISIBLE);
           Picasso.with(getApplicationContext())
                   .load("http://fixsira.com/" + u)
                   .into(holder.img_Cart, new Callback() {
                       @Override
                       public void onSuccess() {
//                        holder.ProgrossSpare.setVisibility(View.GONE);
                       }

                       @Override
                       public void onError() {
//                        holder.ProgrossSpare.setVisibility(View.GONE);
                       }
                   });
       }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                My_Order_Services myorders=new My_Order_Services();
                myorders.setId(String.valueOf(filteredList.get(position).getId()));
                myorders.setVendorid(String.valueOf(filteredList.get(position).getVendorId()));
                myorders.setServicetybe(filteredList.get(position).getServicesType());
                myorders.setCarname(filteredList.get(position).getCarName());
                myorders.setCarmodel(filteredList.get(position).getCarModel());
                myorders.setPrice(String.valueOf(filteredList.get(position).getPrice()));
                myorders.setImg(filteredList.get(position).getImg());
                myorders.setDate(filteredList.get(position).getScheduleDate());
                myorders.setStatus(filteredList.get(position).getStatus());

               myOrdersService.OrdersService(myorders);
            }
        });
    }

    @Override
    public int getItemCount() {
        return filteredList.size();
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
