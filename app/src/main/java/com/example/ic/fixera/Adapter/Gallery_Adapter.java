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

import com.example.ic.fixera.Model.GalleryImage;
import com.example.ic.fixera.Model.Spart_Detailss;
import com.example.ic.fixera.Model.Sparts_AnotherDetails;
import com.example.ic.fixera.View.Details_Sparts;
import com.fixsira.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by Ahmed on 10/10/2018.
 */

public class Gallery_Adapter extends RecyclerView.Adapter<Gallery_Adapter.MyViewHolder>{

    private List<GalleryImage> filteredList=new ArrayList<>();
    View itemView;
    Context con;
    Details_Sparts details_sparts;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView Text_Spare,Text_Price,Text_Details;
        private Button Callnow,Details;
        private ImageView img_Spare;
        private ProgressBar ProgrossSpare;
        public MyViewHolder(View view) {
            super(view);
            img_Spare=view.findViewById(R.id.image_gallery);
        }

    }

    public Gallery_Adapter(List<GalleryImage> list, Context context){
        this.filteredList=list;
        this.con=context;
    }


    @Override
    public Gallery_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemgallery, parent, false);
        return new Gallery_Adapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final Gallery_Adapter.MyViewHolder holder, final int position) {

        String i = filteredList.get(position).getUrl();
        Uri u = Uri.parse(i);
        Picasso.with(getApplicationContext())
                .load("http://fixsira.com/"+u)
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
