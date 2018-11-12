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

import com.example.ic.fixera.Model.Cart;
import com.example.ic.fixera.Model.Filter_Places;
import com.example.ic.fixera.View.Count_View;
import com.example.ic.fixera.View.Details_Service;
import com.fixe.fixera.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by Ahmed on 14/10/2018.
 */

public class Cart_Adapter  extends RecyclerView.Adapter<Cart_Adapter.MyViewHolder>{

    private List<Cart> filteredList=new ArrayList<>();
    View itemView;
    Context con;
    Details_Service details_service;
    Count_View count_view;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView VendorName,T_Price,placeName,T_qount,Phone,telephone,textRate;
        private Button Callnow,Details;
        private ImageView person_image,plus,minus;
        public MyViewHolder(View view) {
            super(view);
            person_image=view.findViewById(R.id.img_Cart);
            VendorName=view.findViewById(R.id.T_Name);
            T_Price=view.findViewById(R.id.T_Price);
            plus=view.findViewById(R.id.plus);
            T_qount=view.findViewById(R.id.T_qount);
            minus=view.findViewById(R.id.minus);
        }
    }

    public Cart_Adapter(List<Cart> list, Context context){
        this.filteredList=list;
        this.con=context;
    }
    public void count(Count_View count_view){
        this.count_view=count_view;
    }
    @Override
    public Cart_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemcart, parent, false);
        return new Cart_Adapter.MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(final Cart_Adapter.MyViewHolder holder, final int position) {
        holder.VendorName.setText(filteredList.get(position).getTitle());
        holder.T_Price.setText(filteredList.get(position).getSalePrice());
        holder.T_qount.setText(filteredList.get(position).getQuantity());

        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count_view.count_plus(String.valueOf(filteredList.get(position).getProductsId()));
            }
        });

        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count_view.count_minus(String.valueOf(filteredList.get(position).getProductsId()));
                if(String.valueOf(filteredList.get(position).getQuantity()).equals("1")){
                 filteredList.remove(position);
                }

            }
        });

        String i = filteredList.get(position).getImageUrl();
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
