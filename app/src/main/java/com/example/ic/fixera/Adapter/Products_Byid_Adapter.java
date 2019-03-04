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

import com.example.ic.fixera.Model.My_Order_Services;
import com.example.ic.fixera.Model.OrdersProducts;
import com.example.ic.fixera.Model.Spart_Detailss;
import com.example.ic.fixera.Model.Sparts_AnotherDetails;
import com.example.ic.fixera.View.Details_Sparts;
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

public class Products_Byid_Adapter extends RecyclerView.Adapter<Products_Byid_Adapter.MyViewHolder>{

    private List<OrdersProducts> filteredList=new ArrayList<>();
    View itemView;
    Context con;
    Details_Sparts details_sparts;
    MyOrdersService myOrdersService;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView Text_Spare,Text_Price,Text_Details;
        private Button Callnow,Details;
        private ImageView img_Spare;
        Button ReviewOrder;
        private ProgressBar ProgrossSpare;

        public MyViewHolder(View view) {
            super(view);
            img_Spare=view.findViewById(R.id.img_Spare);
            Text_Spare=view.findViewById(R.id.Text_Spare);
            Text_Price=view.findViewById(R.id.Text_Price);
            ProgrossSpare=view.findViewById(R.id.ProgrossSpare);
            ReviewOrder=view.findViewById(R.id.ReviewProduct);
        }

    }

    public Products_Byid_Adapter(List<OrdersProducts> list, Context context){
        this.filteredList=list;
        this.con=context;
    }
    public void setClickListener(Details_Sparts details_sparts) {
        this.details_sparts = details_sparts;
    }
    public void setClickL(MyOrdersService itemClickListener) {
        this.myOrdersService = itemClickListener;
    }
    @Override
    public Products_Byid_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemmyproductsbyid, parent, false);
        return new Products_Byid_Adapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final Products_Byid_Adapter.MyViewHolder holder, final int position) {
        holder.Text_Spare.setText(filteredList.get(position).getName());
        holder.Text_Price.setText(String.valueOf(filteredList.get(position).getPrice()));
//        if(filteredList.get(position).getIsReview()){
//            holder.ReviewOrder.setVisibility(View.GONE);
//        }else {
//            holder.ReviewOrder.setVisibility(View.VISIBLE);
//        }

        String i = filteredList.get(position).getImgSrc();
        Uri u = Uri.parse(i);
        holder.ProgrossSpare.setVisibility(View.VISIBLE);
        Picasso.with(getApplicationContext())
                .load("http://fixsira.com/"+u)
                .into(holder.img_Spare, new Callback() {
                    @Override
                    public void onSuccess() {
                        holder.ProgrossSpare.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {
                        holder.ProgrossSpare.setVisibility(View.GONE);
                    }
                });
        holder.ReviewOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                My_Order_Services myorders=new My_Order_Services();
                myorders.setId(String.valueOf(filteredList.get(position).getProductsId()));
                myOrdersService.ReviewOrders(myorders);

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
