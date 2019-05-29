package com.danteh.hearthking;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.SearchView;

import com.danteh.hearthking.fragments.CardsFragment;
import com.danteh.hearthking.fragments.ClassFragment;
import com.danteh.hearthking.fragments.AboutFragment;
import com.danteh.hearthking.fragments.DecksFragment;
import com.danteh.hearthking.fragments.ExpansionFragment;
import com.danteh.hearthking.fragments.HomeFragment;
import com.google.android.material.navigation.NavigationView;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ViewPager viewPager;
    static List<Cards> cardsList;
    static Boolean readed;
    ImageView toolbariv;
    SearchView searchView;
    Fragment homeFrag,cardsFrag,classFrag,aboutFrag,expansionsFrag,decksFrag,activeFrag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Reading Cards From Json
        readJson readJson = new readJson(this);
        readed = readJson.getItemsFromJson();
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

        //Creating Fragments
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
        }

        return super.onOptionsItemSelected(item);
    }

}
