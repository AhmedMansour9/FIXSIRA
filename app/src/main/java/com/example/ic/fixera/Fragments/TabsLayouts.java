package com.example.ic.fixera.Fragments;


import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ic.fixera.Language;
import com.example.ic.fixera.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabsLayouts extends Fragment {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
//    private int[] tabIcons = {
//            R.drawable.ic_tab_favourite,
//            R.drawable.ic_tab_call,
//            R.drawable.ic_tab_contacts
//    };
    public TabsLayouts() {
        // Required empty public constructor
    }

   View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_tabs_layouts, container, false);
        // Inflate the layout for this fragment
        toolbar = view.findViewById(R.id.toolbar);
//        getActivity().setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager =view.findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout)view.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
        if(Language.isRTL()){
            tabLayout.getTabAt(2).select();
        }else {
            tabLayout.getTabAt(1).select();
        }


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            tabLayout.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        }

        return view;
    }
    private void setupTabIcons() {
        View view1 = getLayoutInflater().inflate(R.layout.iconprofile, null);
        View view2 = getLayoutInflater().inflate(R.layout.menuicon, null);
        View view3 = getLayoutInflater().inflate(R.layout.carticon, null);
        View view4 = getLayoutInflater().inflate(R.layout.setting, null);
        if(Language.isRTL()){
            tabLayout.getTabAt(0).setCustomView(view4);
            tabLayout.getTabAt(1).setCustomView(view3);
            tabLayout.getTabAt(2).setCustomView(view2);
            tabLayout.getTabAt(3).setCustomView(view1);
        }else {
            tabLayout.getTabAt(0).setCustomView(view1);
            tabLayout.getTabAt(1).setCustomView(view2);
            tabLayout.getTabAt(2).setCustomView(view3);
            tabLayout.getTabAt(3).setCustomView(view4);
        }
    }
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getFragmentManager());
        if(Language.isRTL()){
            adapter.addFrag(new Profile(), getResources().getString(R.string.Profile));

            adapter.addFrag(new Cart(), getResources().getString(R.string.cart));
            adapter.addFrag(new Menu(), getResources().getString(R.string.menu));
            adapter.addFrag(new Orders(), getResources().getString(R.string.setting));





        }else {
            adapter.addFrag(new Profile(), getResources().getString(R.string.Profile));
            adapter.addFrag(new Menu(), getResources().getString(R.string.menu));
            adapter.addFrag(new Cart(), getResources().getString(R.string.cart));
            adapter.addFrag(new Orders(), getResources().getString(R.string.setting));

        }
        viewPager.setAdapter(adapter);
    }
    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
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

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}

