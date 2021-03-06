package com.example.ic.fixera.Adapter;

import android.content.Context;
import android.graphics.Paint;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.ic.fixera.Model.Spart_Detailss;
import com.example.ic.fixera.Model.Sparts_AnotherDetails;
import com.example.ic.fixera.Model.Sparts_Details;
import com.example.ic.fixera.View.Details_Sparts;
import com.fixsira.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by Ahmed on 28/10/2018.
 */

public class MyProducts_vendor_Adapter   extends RecyclerView.Adapter<MyProducts_vendor_Adapter.MyViewHolder>{

    private List<Sparts_Details> filteredList=new ArrayList<>();
    View itemView;
    Context con;
    Details_Sparts details_sparts;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private Button Callnow,Details;
        private ImageView img_Spare;
        private ProgressBar ProgrossSpare;
        private TextView Text_Spare,Text_Price,Text_Details,textRate,T_rates,T_Vendor,Text_RegularPrice;
        private ImageView person_image,Starone,Startwo,StarThree,StarFour,StarFive;
        public MyViewHolder(View view) {
            super(view);
            img_Spare=view.findViewById(R.id.img_Spare);
            Text_Spare=view.findViewById(R.id.Text_Spare);
            Text_Price=view.findViewById(R.id.Text_Price);
            ProgrossSpare=view.findViewById(R.id.ProgrossSpare);
            Starone=view.findViewById(R.id.Starone);
            textRate=view.findViewById(R.id.T_Rates);
            Startwo=view.findViewById(R.id.Startwo);
            StarThree=view.findViewById(R.id.Starthree);
            StarFour=view.findViewById(R.id.StarFour);
            StarFive=view.findViewById(R.id.StarFive);
            T_rates=view.findViewById(R.id.T_Rates);
            T_Vendor=view.findViewById(R.id.Text_Vendor);
            Text_RegularPrice=view.findViewById(R.id.Text_RegularPrice);
        }

    }

    public MyProducts_vendor_Adapter(List<Sparts_Details> list, Context context){
        this.filteredList=list;
        this.con=context;
    }


    @Override
    public MyProducts_vendor_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemsparts, parent, false);
        return new MyProducts_vendor_Adapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyProducts_vendor_Adapter.MyViewHolder holder, final int position) {
        holder.Text_Spare.setText(filteredList.get(position).getTitle());
        holder.Text_Price.setText(String.valueOf(filteredList.get(position).getSalePrice()));
        holder.Text_RegularPrice.setText(String.valueOf(filteredList.get(position).getRegularPrice()));
        holder.Text_RegularPrice.setPaintFlags(holder.Text_RegularPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        holder.T_Vendor.setText(filteredList.get(position).getAuthorName());

        if(filteredList.get(position).getRateTotal()!=0) {
            holder.T_rates.setText(filteredList.get(position).getRateAverage() + "");
        }else {
            holder.T_rates.setText(con.getResources().getString(R.string.norate) + "");
        }

        if(filteredList.get(position).getRateAverage()==1){
            holder.Starone.setVisibility(View.VISIBLE);
        }else if(filteredList.get(position).getRateAverage()==2){
            holder.Starone.setVisibility(View.VISIBLE);
            holder.Startwo.setVisibility(View.VISIBLE);
        }else if(filteredList.get(position).getRateAverage()==3){
            holder.Starone.setVisibility(View.VISIBLE);
            holder.Startwo.setVisibility(View.VISIBLE);
            holder.StarThree.setVisibility(View.VISIBLE);
        }else if(filteredList.get(position).getRateAverage()==4){
            holder.Starone.setVisibility(View.VISIBLE);
            holder.Startwo.setVisibility(View.VISIBLE);
            holder.StarThree.setVisibility(View.VISIBLE);
            holder.StarFour.setVisibility(View.VISIBLE);
        }else if(filteredList.get(position).getRateAverage()==5){
            holder.Starone.setVisibility(View.VISIBLE);
            holder.Startwo.setVisibility(View.VISIBLE);
            holder.StarThree.setVisibility(View.VISIBLE);
            holder.StarFour.setVisibility(View.VISIBLE);
            holder.StarFive.setVisibility(View.VISIBLE);
        }

        String i = filteredList.get(position).getImageUrl();
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

