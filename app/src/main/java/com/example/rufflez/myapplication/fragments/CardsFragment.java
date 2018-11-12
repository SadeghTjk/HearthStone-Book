package com.example.rufflez.myapplication.fragments;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.SearchView;

import com.example.rufflez.myapplication.Cards;
import com.example.rufflez.myapplication.R;
import com.example.rufflez.myapplication.adapter.CGLM;
import com.example.rufflez.myapplication.adapter.CardsListAdapter;
import com.example.rufflez.myapplication.fragments.cards.AllCards;
import com.example.rufflez.myapplication.readJson;

import java.util.ArrayList;
import java.util.List;

public class CardsFragment extends Fragment {
    RecyclerView recyclerView;
    List<Cards> cardsList;
    Context context;
    CardsListAdapter cla;
    private SearchView searchView = null;
    ViewPager viewPager;
    ViewPagerAdapter adapter;
    RadioButton hunter;
    int id[] = {R.id.druid,R.id.hunter,R.id.mage,R.id.paladin,R.id.priest,R.id.rogue,R.id.shaman,R.id.warlock,R.id.warrior,R.id.neutral};
    int imageResId[] = {R.drawable.druid,R.drawable.hunter};
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context = getActivity();

        View rootView = inflater.inflate(R.layout.fragment_cards, container, false);
        searchView = rootView.findViewById(R.id.appbar_search);
        recyclerView = rootView.findViewById(R.id.recyclerView);
        viewPager = rootView.findViewById(R.id.tab_viewpager);
        hunter = rootView.findViewById(R.id.hunter);
        adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());

        setupViewPager(viewPager);


        readJson readJson = new readJson(context);
        cardsList = readJson.getcardsList();


        cla = new CardsListAdapter(cardsList, context);
        recyclerView.setLayoutManager(new CGLM(context, 3, GridLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(cla);

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main, menu);
        MenuItem searchItem = menu.findItem(R.id.appbar_search);
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);

        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));

            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextChange(String newText) {
                    Log.i("onQueryTextChange", newText);
                    cla.getFilter().filter(newText);
                    return false;
                }

                @Override
                public boolean onQueryTextSubmit(String query) {
                    Log.i("onQueryTextSubmit", query);
                    cla.getFilter().filter(query);
                    return true;
                }
            });
        }
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle item selection
        switch (item.getItemId()) {
            case R.id.appbar_search:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupViewPager(ViewPager viewPager) {

        adapter.addFrag(new AllCards(), "All Cards");
        viewPager.setAdapter(adapter);
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
            View v = LayoutInflater.from(context).inflate(R.layout.class_tab, null);
            ImageView img = v.findViewById(R.id.tabicon);
            img.setImageResource(imageResId[position]);
            return v;
        }

    }

}
