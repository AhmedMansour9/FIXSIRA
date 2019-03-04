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

import com.example.ic.fixera.Model.MyOrders_Products;
import com.example.ic.fixera.Model.MyOrders_Service;
import com.example.ic.fixera.Model.My_Order_Services;
import com.example.ic.fixera.View.Category_id;
import com.example.ic.fixera.View.MyOrdersService;
import com.fixsira.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by Ahmed on 17/10/2018.
 */

public class MyOrders_Products_Adapter  extends RecyclerView.Adapter<MyOrders_Products_Adapter.MyViewHolder>{

    private List<MyOrders_Products> filteredList=new ArrayList<>();
    View itemView;
    Context con;
    Category_id category_id;
    MyOrdersService myOrdersService;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView T_currency,T_sevice,T_status,T_Price,schedule_date,status;
        private Button Callnow,Details;
        private ImageView img_Cart;
        private ProgressBar ProgrossSpare;
        Button ReviewOrder;
        public MyViewHolder(View view) {
            super(view);
            T_currency = view.findViewById(R.id.T_currency);
            T_Price = view.findViewById(R.id.T_Price);
            schedule_date = view.findViewById(R.id.schedule_date);
            T_status = view.findViewById(R.id.T_status);
            ReviewOrder=view.findViewById(R.id.ReviewOrder);
        }
    }
    public MyOrders_Products_Adapter(List<MyOrders_Products> list, Context context){
        this.filteredList=list;
        this.con=context;
    }
    public void setClickListener(MyOrdersService itemClickListener) {
        this.myOrdersService = itemClickListener;
    }

    @Override
    public MyOrders_Products_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_myorders_products, parent, false);
        return new MyOrders_Products_Adapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyOrders_Products_Adapter.MyViewHolder holder, final int position) {
        holder.T_currency.setText(filteredList.get(position).getOrderCurrency());
        holder.T_Price.setText(filteredList.get(position).getFinalOrderTotal());
        holder.schedule_date.setText(filteredList.get(position).getOrderDate());
        holder.T_status.setText(String.valueOf(filteredList.get(position).getOrderStatus()));
        if(filteredList.get(position).getIsReview()){
            holder.ReviewOrder.setVisibility(View.GONE);
        }else {
            if (filteredList.get(position).getOrderStatus().equals("on-hold")) {
                holder.ReviewOrder.setVisibility(View.GONE);
            } else {
                holder.ReviewOrder.setVisibility(View.VISIBLE);
            }
        }

        holder.ReviewOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                My_Order_Services myorders=new My_Order_Services();
                myorders.setId(String.valueOf(filteredList.get(position).getId()));
                myOrdersService.ReviewOrders(myorders);

            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                My_Order_Services myorders=new My_Order_Services();
                myorders.setId(String.valueOf(filteredList.get(position).getId()));
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
