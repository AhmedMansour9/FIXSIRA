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

import com.example.ic.fixera.Model.Service;
import com.example.ic.fixera.Model.Sparts_Category;
import com.example.ic.fixera.View.Category_id;
import com.example.ic.fixera.View.Details_Service;
import com.fixe.fixera.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by Ahmed on 13/10/2018.
 */

public class Service_Adapter extends RecyclerView.Adapter<Service_Adapter.MyViewHolder>{

private List<Service> filteredList=new ArrayList<>();
        View itemView;
        Context con;
        Category_id category_id;
    Details_Service details_service;
public class MyViewHolder extends RecyclerView.ViewHolder {
    private TextView Text_Spare,Text_Price,Text_Details,textRate;
    private Button Callnow,Details;
    private ImageView img_Spare;
    private ProgressBar ProgrossSpare;
    public MyViewHolder(View view) {
        super(view);
        img_Spare=view.findViewById(R.id.person_image);
        Text_Spare=view.findViewById(R.id.vendorName);
        Text_Details=view.findViewById(R.id.Discrption);
        Text_Price=view.findViewById(R.id.T_Price);
        textRate=view.findViewById(R.id.textRate);
        ProgrossSpare=view.findViewById(R.id.progrossimage);
    }

}
    public void setClickListener(Details_Service itemClickListener) {
        this.details_service = itemClickListener;
    }
    public Service_Adapter(List<Service> list, Context context){
        this.filteredList=list;
        this.con=context;
    }
    public void setClickListener(Category_id itemClickListener) {
        this.category_id = itemClickListener;
    }

    @Override
    public Service_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemservice, parent, false);
        return new Service_Adapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final Service_Adapter.MyViewHolder holder, final int position) {
        holder.Text_Spare.setText(filteredList.get(position).getName());
        holder.Text_Details.setText(filteredList.get(position).getDescription());
        holder.textRate.setText(filteredList.get(position).getRate());
        holder.Text_Price.setText(String.valueOf(filteredList.get(position).getPrice()));
        String i = filteredList.get(position).getImg();
        Uri u = Uri.parse(i);
//        holder.ProgrossSpare.setVisibility(View.VISIBLE);
        Picasso.with(getApplicationContext())
                .load("http://fixsira.com/site/"+u)
                .into(holder.img_Spare, new Callback() {
                    @Override
                    public void onSuccess() {
//                        holder.ProgrossSpare.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {
//                        holder.ProgrossSpare.setVisibility(View.GONE);
                    }
                });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                com.example.ic.fixera.Model.Details_Service filter_places=new com.example.ic.fixera.Model.Details_Service();
                filter_places.setId(filteredList.get(position).getId());
                filter_places.setDescription(filteredList.get(position).getDescription());
                filter_places.setUserPhotoUrl(filteredList.get(position).getImg());
                filter_places.setRate(Double.parseDouble(filteredList.get(position).getRate()));
                filter_places.setName(filteredList.get(position).getName());
                filter_places.setPrice(filteredList.get(position).getPrice());
                details_service.listsevice(filter_places);

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
