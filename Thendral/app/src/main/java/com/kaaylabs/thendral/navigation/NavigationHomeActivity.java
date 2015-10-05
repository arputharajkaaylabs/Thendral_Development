package com.kaaylabs.thendral.navigation;

import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.kaaylabs.thendral.R;
import com.kaaylabs.thendral.ThendralApplication;
import com.kaaylabs.thendral.activity.ThendralLoginActivity;
import com.kaaylabs.thendral.bean.CurrentPublishedIssueDetails;
import com.kaaylabs.thendral.fragment.AboutUsFragment;
import com.kaaylabs.thendral.fragment.CategoryFragment;
import com.kaaylabs.thendral.fragment.MyTopicsFragment;
import com.kaaylabs.thendral.fragment.SettingsFragment;
import com.kaaylabs.thendral.fragment.ThendralAudioFragment;
import com.kaaylabs.thendral.fragment.home.HomeTabFragment;
import com.kaaylabs.thendral.fragment.home.PreviousIssueFragment;
import com.kaaylabs.thendral.receiver.NetworkChangeReceiver;

import java.util.ArrayList;

public class NavigationHomeActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    Toolbar toolbar;
    // nav drawer title
    private CharSequence mDrawerTitle;

    // used to store app title
    private CharSequence mTitle;

    // slide menu items
    private String[] navMenuTitles;
    private TypedArray navMenuIcons;
    private NetworkChangeReceiver mNetSateReceiver = null;
    private ArrayList<NavDrawerItem> navDrawerItems;
    private NavDrawerListAdapter navigationAdapter;
    SearchView searchView = null;
    boolean isRegistered = false;
    String selectedTabPosition;
    private android.support.v4.app.FragmentManager fragmentManager;
    SharedPreferences sharedPreferences;


    String storeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // google tracking
        Tracker t = ((ThendralApplication) getApplication()).getTracker(ThendralApplication.TrackerName.APP_TRACKER);
        t.setScreenName("Home");
        t.send(new HitBuilders.AppViewBuilder().build());

        setContentView(R.layout.activity_home_navigation);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        selectedTabPosition = getIntent().getStringExtra("selectedTabPosition");
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(NavigationHomeActivity.this);
        storeId = sharedPreferences.getString("storeId", "");

        mTitle = mDrawerTitle = getTitle();

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        // load slide menu items
        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);
        // nav drawer icons from resources
        navMenuIcons = getResources()
                .obtainTypedArray(R.array.nav_drawer_icons);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.list_slidermenu);

        navDrawerItems = new ArrayList<NavDrawerItem>();

        // adding nav drawer items to array
        // Home
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons.getResourceId(0, -1)));
        // Previous Issues
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1)));
        // my account
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons.getResourceId(2, -1)));
        // change password
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons.getResourceId(3, -1)));
        // about
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[4], navMenuIcons.getResourceId(4, -1)));
        // sign Out
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[5], navMenuIcons.getResourceId(5, -1)));
        // about
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[6], navMenuIcons.getResourceId(6, -1)));
        // sign Out
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[7], navMenuIcons.getResourceId(7, -1)));




        // Recycle the typed array
        navMenuIcons.recycle();

        mDrawerList.setOnItemClickListener(new SlideMenuClickListener());

        // setting the nav drawer list adapter
        navigationAdapter = new NavDrawerListAdapter(getApplicationContext(),
                navDrawerItems);
        mDrawerList.setAdapter(navigationAdapter);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.app_name, R.string.app_name) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                // code here will execute once the drawer is opened( As I dont want anything happened whe drawer is
                // open I am not going to put anything here)
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                // Code here will execute once drawer is closed
            }


        };
        toolbar.inflateMenu(R.menu.menu_sliding_home);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return true;
            }
        });

        mDrawerLayout.setDrawerListener(mDrawerToggle);
        displayView(0);
    }

    @Override
    public void onStart() {
        super.onStart();
        GoogleAnalytics.getInstance(NavigationHomeActivity.this).reportActivityStart(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        GoogleAnalytics.getInstance(NavigationHomeActivity.this).reportActivityStop(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mNetSateReceiver = new NetworkChangeReceiver();
        if (mNetSateReceiver != null) {
            registerReceiver(mNetSateReceiver, new IntentFilter(
                    ConnectivityManager.CONNECTIVITY_ACTION));
            isRegistered = true;
        }

    }

    @Override
    protected void onPause() {
        super.onPause();

        if (isRegistered) {
            if (mNetSateReceiver != null) {
                unregisterReceiver(mNetSateReceiver);
                isRegistered = false;
            }
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isRegistered) {

            if (mNetSateReceiver != null) {
                unregisterReceiver(mNetSateReceiver);
            }
        }
    }

    /**
     * Slide menu item click listener
     */
    private class SlideMenuClickListener implements
            ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            // display view for selected nav drawer item
            selectedTabPosition = "" + position;
            if(position == 0)
            {
                  SharedPreferences shar = PreferenceManager.getDefaultSharedPreferences(NavigationHomeActivity.this);
                String currentIssuesId = shar.getString("currentIssuesId", "");
                CurrentPublishedIssueDetails.setIssueId(currentIssuesId);
            }
            displayView(position);
        }

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // toggle nav drawer on selecting action bar app icon/title
        return mDrawerToggle.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_sliding_home, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);


        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();

        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        }
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
//                Toast.makeText(getActivity(), "TEST1", Toast.LENGTH_LONG).show();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {


//                Toast.makeText(getActivity(), "TEST2", Toast.LENGTH_LONG).show();
                int charLen = s.length();

                return true;
            }
        });

        return true;
    }



    /**
     * Diplaying fragment view for selected nav drawer list item
     */
    private void displayView(int position) {
        // update the main content by replacing fragments
        Intent intent = null;
        Fragment fragment = null;
        switch (position) {
            case 0:

                fragment = new HomeTabFragment();
                if (selectedTabPosition == null) {
                    selectedTabPosition = "0";
                }



                HomeTabFragment.newInstance(selectedTabPosition);
                Bundle args = new Bundle();
                args.putString("selectedTabPosition", selectedTabPosition);
                fragment.setArguments(args);

                break;
            case 1:
                fragment = new PreviousIssueFragment();
                break;
            case 2:
                fragment = new CategoryFragment();
                break;
            case 3:
                fragment = new MyTopicsFragment();
                break;
            case 4:
                fragment = new ThendralAudioFragment();
                break;
            case 5:
                fragment = new AboutUsFragment();
                break;
            case 6:
                fragment = new SettingsFragment();
                break;
            case 7:
                signOut();
                break;
            default:
                break;
        }

        if (intent != null) {
            mDrawerList.setItemChecked(position, true);
            mDrawerList.setSelection(position);
            setTitle(navMenuTitles[position]);
            mDrawerLayout.closeDrawer(mDrawerList);
        }
        if (fragment != null) {

            fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit();

            // update selected item and title, then close the drawer
            mDrawerList.setItemChecked(position, true);
            mDrawerList.setSelection(position);
            setTitle(navMenuTitles[position]);
            mDrawerLayout.closeDrawer(mDrawerList);
        } else {
            // error in creating fragment

        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    private void signOut() {
        AlertDialog.Builder alertBuiler = new AlertDialog.Builder(this);
        alertBuiler.setMessage(getString(R.string.are_u_sure_want_to_logout));
        alertBuiler.setTitle("Confirmation");
        alertBuiler.setCancelable(false);
        alertBuiler.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();
                        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(NavigationHomeActivity.this);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("userName", "");
                        editor.putString("password", "");
                        editor.commit();
                        Intent intent = new Intent(NavigationHomeActivity.this, ThendralLoginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);

                    }


                });
        alertBuiler.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

            }
        });
        AlertDialog alert = alertBuiler.create();
        alert.show();
    }

}
