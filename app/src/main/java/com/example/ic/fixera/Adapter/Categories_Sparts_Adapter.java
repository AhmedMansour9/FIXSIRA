package com.example.ic.fixera.Adapter;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.ic.fixera.Fragments.SparParts_Products;
import com.example.ic.fixera.Model.Sparts_AnotherDetails;
import com.example.ic.fixera.Model.Sparts_Category;
import com.example.ic.fixera.View.Category_id;
import com.fixsira.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by Ahmed on 10/10/2018.
 */

public class Categories_Sparts_Adapter extends RecyclerView.Adapter<Categories_Sparts_Adapter.MyViewHolder>{

    private List<Sparts_Category> filteredList=new ArrayList<>();
    View itemView;
    Context con;
   Category_id category_id;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView Text_Spare,Text_Price,Text_Details;
        private Button Callnow,Details;
        private ImageView img_Spare;
        private ProgressBar ProgrossSpare;
        public MyViewHolder(View view) {
            super(view);
            img_Spare=view.findViewById(R.id.Image_Caetgory);
            Text_Spare=view.findViewById(R.id.T_Name);
            ProgrossSpare=view.findViewById(R.id.progrossimage);
        }

    }

    public Categories_Sparts_Adapter(List<Sparts_Category> list, Context context){
        this.filteredList=list;
        this.con=context;
    }
    public void setClickListener(Category_id itemClickListener) {
        this.category_id = itemClickListener;
    }

    @Override
    public Categories_Sparts_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemcategoriessparts, parent, false);
        return new Categories_Sparts_Adapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final Categories_Sparts_Adapter.MyViewHolder holder, final int position) {
        holder.Text_Spare.setText(filteredList.get(position).getName());

        String i = filteredList.get(position).getCategoryImgUrl();
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

       holder.itemView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
            category_id.category_id(position,String.valueOf(filteredList.get(position).getTermId()));

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
