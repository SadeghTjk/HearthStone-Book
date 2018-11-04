package com.example.rufflez.myapplication;

import android.app.SearchManager;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.rufflez.myapplication.adapter.CardsListAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class MainActivity extends AppCompatActivity implements CardsListAdapter.CardsAdapterListener {

    private DrawerLayout drawerLayout;
    private ViewPager viewPager;
    static List<Cards> cardsList;
    ImageView toolbariv;
    SearchView searchView;
    CardsListAdapter cla;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbariv = findViewById(R.id.toolbariv);
        drawerLayout = findViewById(R.id.drawer_layout);
        viewPager = findViewById(R.id.tab_viewpager);
        NavigationView navView = findViewById(R.id.navigation_view);
        TabLayout tabLayout = findViewById(R.id.tabLayout);

        Toolbar toolbar = findViewById(R.id.mToolbar);
        setSupportActionBar(toolbar);

        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);

        final readJson readJson = new readJson(this);
        readJson.getItemsFromJson();
        cardsList = readJson.getcardsList();

        if (navView != null) {
            setupDrawerContent(navView);
        }
        if (viewPager != null) {
            setupViewPager(viewPager);
        }

        tabLayout.setupWithViewPager(viewPager);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                Toast.makeText(MainActivity.this, ""+readJson.getPriestCards().size(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new FloatingLabelsFragment(), "Floating Labels");
        adapter.addFrag(new FABLayoutFragment(), "Priest");
        adapter.addFrag(new CoordinatorFragment(), "Coordinator Layout");
        viewPager.setAdapter(adapter);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(true);

                switch (menuItem.getItemId()) {
                    case R.id.drawer_home:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.drawer_expansions:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.drawer_cards:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.drawer_classes:
                        viewPager.setCurrentItem(3);
                        break;
                    case R.id.drawer_decks:
                        viewPager.setCurrentItem(3);
                        break;
                }

                drawerLayout.closeDrawers();
                return true;
            }
        });
    }

    @Override
    public void onCardsSelected(Cards cards) {
        Toast.makeText(getApplicationContext(), "Selected: " + cards.getName(), Toast.LENGTH_LONG).show();
    }

    static class ViewPagerAdapter extends FragmentPagerAdapter {
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


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
////        cla = new CardsListAdapter(cardsList,this,this);
////
////        SearchManager searchManager = (SearchManager) getSystemService(this.SEARCH_SERVICE);
////        searchView = (SearchView) menu.findItem(R.id.appbar_search).getActionView();
////        searchView.setSearchableInfo(searchManager
////                .getSearchableInfo(getComponentName()));
////        searchView.setMaxWidth(Integer.MAX_VALUE);
////
////        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
////            @Override
////            public boolean onQueryTextSubmit(String query) {
////                // filter recycler view when query submitted
////                cla.getFilter().filter(query);
////                return false;
////            }
////
////            @Override
////            public boolean onQueryTextChange(String query) {
////                // filter recycler view when text is changed
////                cla.getFilter().filter(query);
////                return false;
////            }
////        });
//        return true;
//    }


//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//        if (id == R.id.appbar_search) {
//            return true;
//        }
//
//        switch (id) {
//            case android.R.id.home:
//                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
//                    drawerLayout.closeDrawer(GravityCompat.START);
//                } else {
//                    drawerLayout.openDrawer(GravityCompat.START);
//                }
//
//                return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public void onBackPressed() {
        // close search view on back button pressed
        if (!searchView.isIconified()) {
            searchView.setIconified(true);
            return;
        }
        super.onBackPressed();
    }


}
