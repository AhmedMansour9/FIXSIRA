package com.example.ic.fixera.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.ic.fixera.Activites.TabsLayouts;
import com.example.ic.fixera.Adapter.SparParts_Adapter;
import com.example.ic.fixera.EndlessRecyclerViewScrollListener;
import com.example.ic.fixera.Language;
import com.example.ic.fixera.Model.Spart_Detailss;
import com.example.ic.fixera.Model.Sparts_AnotherDetails;
import com.example.ic.fixera.Model.Sparts_Details;
import com.example.ic.fixera.Presenter.Sparts_Prsenter;
import com.example.ic.fixera.View.Details_Sparts;
import com.example.ic.fixera.View.Sparts_View;
import com.fixe.fixera.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SparParts_Products extends Fragment implements Details_Sparts,Sparts_View,SwipeRefreshLayout.OnRefreshListener{


    public SparParts_Products() {
        // Required empty public constructor
    }

    LinearLayoutManager linearLayoutManager;
    RecyclerView recyclerView;
   GridLayoutManager gridLayoutManager;
   Sparts_Prsenter sparts_prsenter;
    View view;
    SwipeRefreshLayout mSwipeRefreshLayout;
   SparParts_Adapter adapter;
    private EndlessRecyclerViewScrollListener scrollListener;
     int PAGE=1;
     List<Sparts_AnotherDetails> listSparts;
     Sparts_AnotherDetails sparts_anotherDetails;
     EditText E_Search;
     Button Btn_Search;
     Boolean Bolean_Search=true;
     String id,Search;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_spar_parts, container, false);
        recyclerView = view.findViewById(R.id.recycler_SparParts);
        listSparts=new ArrayList<>();
        E_Search=view.findViewById(R.id.searchh);
        Btn_Search=view.findViewById(R.id.Btn_Search);
        sparts_anotherDetails=new Sparts_AnotherDetails();
        sparts_prsenter=new Sparts_Prsenter(getContext(),this);
        adapter=new SparParts_Adapter(listSparts,getContext());
        adapter.setClickListener(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
       TabsLayouts.banner.setVisibility(View.GONE);
        Bundle args = getArguments();
        if (args != null) {
            id=args.getString("id");
            Search=args.getString("search");
        }

        SwipRefresh();
        onScroll();
        Serach();

        return view;
    }
   public void Serach(){
       Btn_Search.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String srch = E_Search.getText().toString();
                   if (Language.isRTL()) {
                       mSwipeRefreshLayout.setRefreshing(true);
                       listSparts.clear();
                       adapter.notifyDataSetChanged();
                       Bolean_Search=false;
                       sparts_prsenter.GetSearchSparts("ar", srch);
                   } else {
                       mSwipeRefreshLayout.setRefreshing(true);
                       listSparts.clear();
                       adapter.notifyDataSetChanged();
                       Bolean_Search=false;
                       sparts_prsenter.GetSearchSparts("en", srch);
                   }

           }
       });
   }
   public void onScroll(){
       LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
       linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
       recyclerView.setLayoutManager(linearLayoutManager);


       TabsLayouts.T_Service.setText(getResources().getString(R.string.spare));
       // Adds the scroll listener to RecyclerView
       recyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener(linearLayoutManager) {
           @Override
           public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
               if(Bolean_Search) {
                   mSwipeRefreshLayout.setRefreshing(true);
                   PAGE++;
                   sparts_prsenter.GetSparts("ar", String.valueOf(PAGE),id);
               }
           }
       });
   }


    @Override
    public void ListSparts(List<Sparts_Details> list) {

        for (int i=0;i<list.size();i++){
            sparts_anotherDetails=new Sparts_AnotherDetails();
            sparts_anotherDetails.setContent(list.get(i).getContent());
            sparts_anotherDetails.setImageUrl(list.get(i).getImageUrl());
            sparts_anotherDetails.setId(list.get(i).getId());
            sparts_anotherDetails.setSalePrice(list.get(i).getSalePrice());
            sparts_anotherDetails.setRegularPrice(String.valueOf(list.get(i).getRegularPrice()));
            sparts_anotherDetails.setTitle(list.get(i).getTitle());
            sparts_anotherDetails.setSlug(list.get(i).getSlug());
            sparts_anotherDetails.setStockQty(list.get(i).getStockQty());
            sparts_anotherDetails.setStockAvailability(list.get(i).getStockAvailability());
            sparts_anotherDetails.setAverage(String.valueOf(list.get(i).getRateAverage()));
            sparts_anotherDetails.setAverage_total(String.valueOf(list.get(i).getRateTotal()));
            sparts_anotherDetails.setVendorname(String.valueOf(list.get(i).getAuthorName()));
            sparts_anotherDetails.setStockAvailability(list.get(i).getStockAvailability());
            listSparts.add(sparts_anotherDetails);
        }
        adapter.notifyItemRangeInserted(adapter.getItemCount(), listSparts.size() - 1);
//        adapter=new SparParts_Adapter(listSparts,getContext());
//        adapter.notifyDataSetChanged();
        mSwipeRefreshLayout.setRefreshing(false);

    }

    @Override
    public void ErrorSparts() {
        mSwipeRefreshLayout.setRefreshing(false);
    }
    public void SwipRefresh(){
        mSwipeRefreshLayout =  view.findViewById(R.id.swipe_Sparts);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setRefreshing(true);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);

        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                if(Search!=null){
                    sparts_prsenter.GetSearchSparts("ar", Search);
                }else {
                    listSparts.clear();
                    adapter.notifyDataSetChanged();
                    PAGE = 1;
                    sparts_prsenter.GetSparts("ar", String.valueOf(PAGE), id);
                }
            }
        });
    }

    @Override
    public void onRefresh() {
        listSparts.clear();
        adapter.notifyDataSetChanged();
        PAGE=1;
        mSwipeRefreshLayout.setRefreshing(true);
        sparts_prsenter.GetSparts("ar",String.valueOf(PAGE),id);
    }

    @Override
    public void Listdetails(Spart_Detailss list) {
        Spart_Details fragmen = new Spart_Details();
        Bundle args = new Bundle();
        args.putString("id",list.getId());
        args.putString("title",list.getTitle());
        args.putString("price",list.getPrice());
        args.putString("stock",list.getStock());
        args.putString("image",list.getImage());
        args.putString("description",list.getDescription());
        args.putString("vendorname",list.getVendorname());
        args.putString("evragerate",list.getEvragerate());
        fragmen.setArguments(args);
        getFragmentManager().beginTransaction()
                .replace(R.id.MenuFrame, fragmen )
                .addToBackStack(null)
                .commit();

    }
}


