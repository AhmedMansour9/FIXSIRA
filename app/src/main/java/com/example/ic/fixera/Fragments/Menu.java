package com.example.ic.fixera.Fragments;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.ic.fixera.Activites.TabsLayouts;
import com.example.ic.fixera.Adapter.Banner_Adapter;
import com.example.ic.fixera.Model.Banner_details;
import com.example.ic.fixera.Presenter.BannerPresenter;
import com.example.ic.fixera.View.BannerView;
import com.fixsira.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class Menu extends Fragment implements BannerView{

    public Menu() {
        // Required empty public constructor
    }
    View view;
    ImageView Image_Sparts,CarWashing,carmaintaince,Pull_Car;
    MyReceiver r;
    Banner_Adapter banerAdapter;
    int position;
    List<Banner_details> banne=new ArrayList<>();
    Boolean end;
    BannerPresenter baner;
    private RecyclerView rv_autoScroll;
    LinearLayoutManager linearLayoutManager;
    Button Btn_Offer;
    Timer timer;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_menu, container, false);
        Image_Sparts=view.findViewById(R.id.Image_Sparts);
        CarWashing=view.findViewById(R.id.CarWashing);
        Btn_Offer=view.findViewById(R.id.Btn_Offer);
        baner=new BannerPresenter(getContext(),this);
        baner.GetBanner("ar");
        Pull_Car=view.findViewById(R.id.Pull_Car);
        carmaintaince=view.findViewById(R.id.carmaintaince);
        Recyclview();
        Open_Sparts();
        Open_CarWashing();
        Open_Maintenence();
        Open_PullCar();
        Open_TopOffer();
        return view;

    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            getFragmentManager().beginTransaction().detach(this).attach(this).commit();
        }
    }
    public void Open_Sparts(){
        Image_Sparts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.MenuFrame,new Categories_SparParts())
                        .addToBackStack(null).commitAllowingStateLoss();
            }
        });
    }
    public void Open_CarWashing(){
        CarWashing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DropDown fragmen = new DropDown();
                Bundle args = new Bundle();
                args.putString("tybe","car_washing");
                fragmen.setArguments(args);
                getFragmentManager().beginTransaction()
                        .replace(R.id.MenuFrame, fragmen )
                        .addToBackStack(null)
                        .commitAllowingStateLoss();
            }
        });
    }
    public void Open_TopOffer(){
        Btn_Offer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SparParts_Products fragmen = new SparParts_Products();
                Bundle args = new Bundle();
                args.putString("offer","offersProduct");
                fragmen.setArguments(args);
                getFragmentManager().beginTransaction()
                        .replace(R.id.MenuFrame, fragmen )
                        .addToBackStack(null)
                        .commitAllowingStateLoss();
            }
        });


    }
    public void Recyclview(){
        rv_autoScroll = view.findViewById(R.id.recycler_banner2);
        rv_autoScroll.setHasFixedSize(true);
    }
    public void Open_Maintenence(){
        carmaintaince.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DropDown fragmen = new DropDown();
                Bundle args = new Bundle();
                args.putString("tybe","car_maintenance");
                fragmen.setArguments(args);
                getFragmentManager().beginTransaction()
                        .replace(R.id.MenuFrame, fragmen )
                        .addToBackStack(null)
                        .commitAllowingStateLoss();
            }
        });
    }
    public void Open_PullCar(){
        Pull_Car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.MenuFrame,new PullCar())
                        .addToBackStack(null).commitAllowingStateLoss();
            }
        });
    }
    public void refresh() {
    }

    public void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(r);
    }

    public void onResume() {
        super.onResume();
        r = new MyReceiver();
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(r,
                new IntentFilter("TAG_REFRESH"));
    }

    @Override
    public void getBanner(List<Banner_details> banners) {

        banne=banners;
        banerAdapter = new Banner_Adapter(banners,getContext());
        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv_autoScroll.setLayoutManager(linearLayoutManager);
        rv_autoScroll.setAdapter(banerAdapter);
        if(banners.size()>0) {
             timer = new Timer();
            timer.scheduleAtFixedRate(new AutoScrollTask(), 2500, 8000);
        }
    }

    @Override
    public void Errorbaner() {
    }

    private class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Menu.this.refresh();
        }
    }

    private class AutoScrollTask extends TimerTask {
        @Override
        public void run() {
            if(position == banne.size()){
                end = true;
            }
            else if (position == 0) {
                end = false;
            }
            if(!end){
                position++;
            } else {
                position--;
            }
            rv_autoScroll.smoothScrollToPosition(position);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if(timer!=null) {
            timer.cancel();
        }
    }
}
