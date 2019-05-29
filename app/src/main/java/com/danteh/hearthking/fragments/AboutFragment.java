package com.danteh.hearthking.fragments;

import android.content.Context;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.danteh.hearthking.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;


public class AboutFragment extends Fragment {
    Context context;
    TabLayout tabLayout;
    ViewPagerAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        context = getActivity();
        View rootView = inflater.inflate(R.layout.fragment_about, container, false);
        tabLayout = rootView.findViewById(R.id.tablayout);
        adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());

        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            tab.setCustomView(adapter.getTabView(i, context));
        }

        return rootView;
    }

    static class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();
        private int[] imageResId = {R.drawable.druid};

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

        public View getTabView(int position, Context context) {
            int imageResId[] = {R.drawable.druid,R.drawable.hunter};
            View v = LayoutInflater.from(context).inflate(R.layout.class_tab, null);
            ImageView img = v.findViewById(R.id.tabicon);
            img.setImageResource(imageResId[position]);
            return v;
        }

    }
}
