package com.example.ic.fixera.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.ic.fixera.Model.MyOrders_Service;
import com.example.ic.fixera.Model.Vendor_Services;
import com.example.ic.fixera.View.Category_id;
import com.example.ic.fixera.View.MyOrdersService;
import com.fixe.fixera.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ahmed on 28/10/2018.
 */

public class MyServices_Vendor_Adapter extends RecyclerView.Adapter<MyServices_Vendor_Adapter.MyViewHolder>{

    private List<Vendor_Services> filteredList=new ArrayList<>();
    View itemView;
    Context con;
    Category_id category_id;
    MyOrdersService myOrdersService;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView T_Name,T_sevice,T_CarName,T_Price,schedule_date,status;
        public MyViewHolder(View view) {
            super(view);
            T_Name=view.findViewById(R.id.T_Name);
            T_Price=view.findViewById(R.id.T_Price);
        }
    }
    public MyServices_Vendor_Adapter(List<Vendor_Services> list, Context context){
        this.filteredList=list;
        this.con=context;
    }
    public void setClickListener(MyOrdersService itemClickListener) {
        this.myOrdersService = itemClickListener;
    }

    @Override
    public MyServices_Vendor_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.myservices_vendor, parent, false);
        return new MyServices_Vendor_Adapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyServices_Vendor_Adapter.MyViewHolder holder, final int position) {
        holder.T_Name.setText(filteredList.get(position).getName());
        holder.T_Price.setText(String.valueOf(filteredList.get(position).getPrice()));

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
