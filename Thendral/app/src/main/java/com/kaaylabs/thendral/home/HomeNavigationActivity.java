package com.kaaylabs.thendral.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.kaaylabs.thendral.LoginActivity;
import com.kaaylabs.thendral.R;
import com.kaaylabs.thendral.fragment.AboutUsFragment;
import com.kaaylabs.thendral.fragment.CategoryFragment;
import com.kaaylabs.thendral.fragment.MyTopicsFragment;
import com.kaaylabs.thendral.fragment.SettingsFragment;
import com.kaaylabs.thendral.fragment.ThendralAudioFragment;
import com.kaaylabs.thendral.fragment.home.HomeTabFragment;


public class HomeNavigationActivity extends AppCompatActivity implements NavigationDrawerCallbacks {
    SharedPreferences sharedPreferences;
    private Toolbar mToolbar;
    private NavigationDrawerFragment mNavigationDrawerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_topdrawer);
        mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);



        mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager().findFragmentById(R.id.fragment_drawer);
        mNavigationDrawerFragment.setup(R.id.fragment_drawer, (DrawerLayout) findViewById(R.id.drawer), mToolbar);
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }*/

    @Override
    public void onNavigationDrawerItemSelected(int position) {

        Toast.makeText(this, "Menu item selected -> " + position, Toast.LENGTH_SHORT).show();
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
                break;
            case 1:
                fragment = new CategoryFragment();
                break;
            case 2:
                fragment = new MyTopicsFragment();
                break;
            case 3:
                fragment = new ThendralAudioFragment();
                break;
            case 4:
                fragment = new AboutUsFragment();
                break;
            case 5:
                fragment = new SettingsFragment();
                break;
            case 6:
                signOut();
                break;
            default:
                break;
        }


    }
    @Override
    public void onBackPressed() {
        if (mNavigationDrawerFragment.isDrawerOpen())
            mNavigationDrawerFragment.closeDrawer();
        else
            super.onBackPressed();
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
                        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(HomeNavigationActivity.this);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("userName", "");
                        editor.putString("password", "");
                        editor.commit();
                        Intent intent = new Intent(HomeNavigationActivity.this, LoginActivity.class);
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
