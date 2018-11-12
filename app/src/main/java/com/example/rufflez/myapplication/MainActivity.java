package com.example.rufflez.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;

import com.example.rufflez.myapplication.fragments.CardsFragment;
import com.example.rufflez.myapplication.fragments.ClassFragment;
import com.example.rufflez.myapplication.fragments.AboutFragment;
import com.example.rufflez.myapplication.fragments.DecksFragment;
import com.example.rufflez.myapplication.fragments.ExpansionFragment;
import com.example.rufflez.myapplication.fragments.HomeFragment;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ViewPager viewPager;
    static List<Cards> cardsList;
    ImageView toolbariv;
    SearchView searchView;
    Fragment homeFrag,cardsFrag,classFrag,aboutFrag,expansionsFrag,decksFrag,activeFrag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Reading Cards From Json
        readJson readJson = new readJson(this);
        readJson.getItemsFromJson();
        cardsList = readJson.getcardsList();

        //References
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navView = findViewById(R.id.navigation_view);

        //Set Toolbar
        Toolbar toolbar = findViewById(R.id.mToolbar);
        setSupportActionBar(toolbar);

        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);



        //Creating Fragmnets
        aboutFrag = new AboutFragment();
        classFrag = new ClassFragment();
        cardsFrag = new CardsFragment();
        decksFrag = new DecksFragment();
        homeFrag = new HomeFragment();
        expansionsFrag = new ExpansionFragment();
        activeFrag = homeFrag;


        //Hiding Fragments!!
        getSupportFragmentManager().beginTransaction().add(R.id.container,homeFrag,"HearthStone").commit();
        getSupportFragmentManager().beginTransaction().add(R.id.container,cardsFrag,"Cards List").hide(cardsFrag).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.container,classFrag,"Classes").hide(classFrag).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.container,expansionsFrag,"Expantions").hide(expansionsFrag).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.container,decksFrag,"Deck Lists").hide(decksFrag).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.container,aboutFrag,getString(R.string.aboutus)).hide(aboutFrag).commit();


        //Setup Drawer Listener
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                //menuItem.setChecked(true);

                switch (menuItem.getItemId()) {
                    case R.id.drawer_home:
                        showFragment(homeFrag);
                        activeFrag = homeFrag;
                        break;

                    case R.id.drawer_expansions:
                        showFragment(expansionsFrag);
                        activeFrag = expansionsFrag;
                        break;

                    case R.id.drawer_cards:
                        showFragment(cardsFrag);
                        activeFrag = cardsFrag;
                        break;

                    case R.id.drawer_classes:
                        showFragment(classFrag);
                        activeFrag = classFrag;
                        break;

                    case R.id.drawer_decks:
                        showFragment(decksFrag);
                        activeFrag = decksFrag;
                        break;

                    case R.id.drawer_about:
                        showFragment(aboutFrag);
                        activeFrag = aboutFrag;
                        break;
                }

                drawerLayout.closeDrawers();
                return true;
            }
        });
    }

    private void showFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().hide(activeFrag).show(fragment).commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;

            case R.id.action_settings:
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
