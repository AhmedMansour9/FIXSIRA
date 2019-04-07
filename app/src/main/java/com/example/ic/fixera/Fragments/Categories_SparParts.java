package com.example.ic.fixera.Fragments;


import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.crashlytics.android.Crashlytics;
import com.example.ic.fixera.Activites.Navigation;
import com.example.ic.fixera.Adapter.Categories_Sparts_Adapter;
import com.example.ic.fixera.Language;
import com.example.ic.fixera.Model.Sparts_Category;
import com.example.ic.fixera.NetworikConntection;
import com.example.ic.fixera.Presenter.Caetgoris_Sparts_Presenter;
import com.example.ic.fixera.View.Caetgories_Sparts_View;
import com.example.ic.fixera.View.Category_id;
import com.fixsira.R;

import java.util.List;

import io.fabric.sdk.android.Fabric;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * A simple {@link Fragment} subclass.
 */
public class Categories_SparParts extends Fragment implements Category_id,Caetgories_Sparts_View,SwipeRefreshLayout.OnRefreshListener{


    public Categories_SparParts() {
        // Required empty public constructor
    }
    Caetgoris_Sparts_Presenter categories;
    RecyclerView recyclerView;
    SwipeRefreshLayout mSwipeRefreshLayout;
    View view;
    Categories_Sparts_Adapter categories_adapter;
    NetworikConntection networikConntection;
    FrameLayout Categories;
    Button Btn_Search;
    EditText E_Search;
    Toolbar toolbars;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_spar_parts__categories, container, false);
        categories=new Caetgoris_Sparts_Presenter(getContext(),this);
        Btn_Search=view.findViewById(R.id.Btn_Search);
        E_Search=view.findViewById(R.id.searchh);
        toolbars=view.findViewById(R.id.toolbar);
        Categories=view.findViewById(R.id.Categories);
       networikConntection=new NetworikConntection(getApplicationContext());
        Recyclview();
        SwipRefresh();
        Navigation.toolbar.setVisibility(View.GONE);
        Navigation.toggle = new ActionBarDrawerToggle(
                getActivity(), Navigation.drawer, toolbars,R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        Navigation.drawer.addDrawerListener(Navigation.toggle);
        Navigation.toggle.syncState();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            toolbars.setNavigationOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (Navigation.drawer.isDrawerOpen(GravityCompat.START)) {
                        Navigation.drawer.closeDrawer(GravityCompat.START);
                    } else {
                        Navigation.drawer.openDrawer(GravityCompat.START);
                    }
                }
            });
        }
        Btn_Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(E_Search.getText().toString()!=null) {
                    SparParts_Products fragmentB = new SparParts_Products();
                    Bundle args = new Bundle();
                    args.putString("search", E_Search.getText().toString());
                    fragmentB.setArguments(args);
                    getFragmentManager().beginTransaction()
                            .replace(R.id.Categories, fragmentB)
                            .addToBackStack(null)
                            .commit();
                }
            }
        });


        return view;
    }

  
    @Override
    public void GetAccessories(List<Sparts_Category> list) {
        categories_adapter = new Categories_Sparts_Adapter(list,getContext());
        categories_adapter.setClickListener(this);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
//        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        recyclerView.setLayoutManager(linearLayoutManager);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
      GridLayoutManager  gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(gridLayoutManager);

        recyclerView.setAdapter(categories_adapter);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void ErrorAccessories() {
        mSwipeRefreshLayout.setRefreshing(false);
    }
    public void Recyclview(){
        recyclerView = view.findViewById(R.id.recycler_Categories);
        recyclerView.setHasFixedSize(true);
    }

    public void SwipRefresh(){
        mSwipeRefreshLayout =  view.findViewById(R.id.swipe_Categories);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);

        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
        if(networikConntection.isNetworkAvailable(getContext())) {
            if (Language.isRTL()) {
                mSwipeRefreshLayout.setRefreshing(true);
                categories.GetCategoriesSpart("ar");
            } else {
                mSwipeRefreshLayout.setRefreshing(true);
                categories.GetCategoriesSpart("en");
            }
        }else {
//            Snackbar.make(Categories,getResources().getString(R.string.internet),1500).show();
        }
                    }


        });
    }

    @Override
    public void onRefresh() {
        if(networikConntection.isNetworkAvailable(getApplicationContext())) {
            if (Language.isRTL()) {
                mSwipeRefreshLayout.setRefreshing(true);
                categories.GetCategoriesSpart("ar");
            } else {
                mSwipeRefreshLayout.setRefreshing(true);
                categories.GetCategoriesSpart("en");
            }
        }
    }

    @Override
    public void category_id(int poistion, String a) {
        SparParts_Products fragmentB = new SparParts_Products ();
        Bundle args = new Bundle();
        args.putString("id",a);
        args.putString("offer",null);
        fragmentB .setArguments(args);
        getFragmentManager().beginTransaction()
                .replace(R.id.Categories, fragmentB )
                .addToBackStack(null)
                .commit();

    }
    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Navigation.Visablty = true;
        Navigation.toolbar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onStop() {
        super.onStop();
        Navigation.Visablty = false;
    }
}
