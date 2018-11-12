package com.example.ic.fixera.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ic.fixera.Model.Hours_Vendor;
import com.example.ic.fixera.Model.Vendor_Services;
import com.example.ic.fixera.View.Category_id;
import com.example.ic.fixera.View.MyOrdersService;
import com.fixe.fixera.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ahmed on 29/10/2018.
 */

public class Vendor_Hours_Adapter extends RecyclerView.Adapter<Vendor_Hours_Adapter.MyViewHolder>{

    private List<Hours_Vendor> filteredList=new ArrayList<>();
    View itemView;
    Context con;
    Category_id category_id;
    MyOrdersService myOrdersService;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView T_Day,T_On,T_Of;
        public MyViewHolder(View view) {
            super(view);
            T_Day=view.findViewById(R.id.T_Day);
            T_On=view.findViewById(R.id.T_On);
            T_Of=view.findViewById(R.id.T_Of);
        }
    }
    public Vendor_Hours_Adapter(List<Hours_Vendor> list, Context context){
        this.filteredList=list;
        this.con=context;
    }


    @Override
    public Vendor_Hours_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemdays, parent, false);
        return new Vendor_Hours_Adapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final Vendor_Hours_Adapter.MyViewHolder holder, final int position) {
        holder.T_Day.setText(filteredList.get(position).getDay());
        holder.T_On.setText(String.valueOf(filteredList.get(position).getStart()));
        holder.T_Of.setText(String.valueOf(filteredList.get(position).getEnd()));
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
