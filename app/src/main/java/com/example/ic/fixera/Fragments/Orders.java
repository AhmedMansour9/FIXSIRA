package com.example.ic.fixera.Fragments;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.crashlytics.android.Crashlytics;
import com.example.ic.fixera.Activites.Navigation;
import com.example.ic.fixera.Language;
import com.fixsira.R;

import java.util.ArrayList;
import java.util.List;

import io.fabric.sdk.android.Fabric;

/**
 * A simple {@link Fragment} subclass.
 */
public class Orders extends Fragment {


    public Orders() {
        // Required empty public constructor
    }
    View view;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    MyReceiver r;
    Toolbar toolbars;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_tabs_orders, container, false);
        viewPager = view.findViewById(R.id.viewpa);
        setupViewPager(viewPager);
        toolbars=view.findViewById(R.id.toolbar);
        tabLayout = view.findViewById(R.id.tas);
        tabLayout.setupWithViewPager(viewPager);
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

        tabLayout.setTabTextColors(
                ColorStateList.valueOf(Color.BLACK));
        if(Language.isRTL()) {
            tabLayout.getTabAt(1).select();
        }else {
            tabLayout.getTabAt(0).select();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            tabLayout.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        }



        return view;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        Navigation.toolbar.setVisibility(View.VISIBLE);
        Navigation.Visablty=true;
    }

    @Override
    public void onDetach() {
        super.onDetach();
//        Navigation.toolbar.setVisibility(View.GONE);
//        Navigation.Visablty=false;


    }
//    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        super.setUserVisibleHint(isVisibleToUser);
//        if (isVisibleToUser) {
//            getFragmentManager().beginTransaction().detach(this).attach(this).commit();
//        }
//    }
////
    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getChildFragmentManager());

        if (Language.isRTL()) {
            // The view has RTL layout
            adapter.addFragment(new MyOrders_Services(), getResources().getString(R.string.service));
            adapter.addFragment(new MyOrders_Products(),getResources().getString(R.string.products));
        } else {
            // The view has LTR layout
            adapter.addFragment(new MyOrders_Products(),getResources().getString(R.string.products));
            adapter.addFragment(new MyOrders_Services(), getResources().getString(R.string.service));

        }

        viewPager.setCurrentItem(adapter.getCount() - 1);
        viewPager.setAdapter(adapter);
    }
    static  class Adapter extends FragmentStatePagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public Adapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }


        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            // TODO Auto-generated method stub
        }


        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
    public void refresh() {
        //yout code in refresh.

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

    private class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Orders.this.refresh();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
