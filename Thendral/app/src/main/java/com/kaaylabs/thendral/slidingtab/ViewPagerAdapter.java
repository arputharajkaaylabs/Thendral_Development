package com.kaaylabs.thendral.slidingtab;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.kaaylabs.thendral.fragment.events.EventsSlidingTabFragment;
import com.kaaylabs.thendral.fragment.home.AuthorsFragment;
import com.kaaylabs.thendral.fragment.home.ClassifiedsFragment;
import com.kaaylabs.thendral.fragment.home.CurrentIssueFragment;
import com.kaaylabs.thendral.fragment.home.ShortStoryFragment;

/**
 * Created by ARaja on 06-05-2015.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    CharSequence Titles[]; // This will Store the Titles of the Tabs which are Going to be passed when ViewPagerAdapter is created
    int NumbOfTabs; // Store the number of tabs, this will also be passed when the ViewPagerAdapter is created


    // Build a Constructor and assign the passed Values to appropriate values in the class
    public ViewPagerAdapter(FragmentManager fm,CharSequence mTitles[], int mNumbOfTabsumb) {
        super(fm);

        this.Titles = mTitles;
        this.NumbOfTabs = mNumbOfTabsumb;

    }

    //This method return the fragment for the every position in the View Pager
    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                CurrentIssueFragment currentIssueFragment = new CurrentIssueFragment();
                return currentIssueFragment;
            case 1:
                ShortStoryFragment shortStoryFragment = new ShortStoryFragment();
                return shortStoryFragment;
            case 2:
                AuthorsFragment authorsFragment = new AuthorsFragment();
                return authorsFragment;
            case 3:
                EventsSlidingTabFragment eventsFragment = new EventsSlidingTabFragment();
                return eventsFragment;
            case 4:
                ClassifiedsFragment classifiedsFragment = new ClassifiedsFragment();
                return classifiedsFragment;
            default:
                return null;
        }
    }


    // This method return the titles for the Tabs in the Tab Strip

    @Override
    public CharSequence getPageTitle(int position) {
        return Titles[position];
    }

    // This method return the Number of tabs for the tabs Strip

    @Override
    public int getCount() {
        return NumbOfTabs;
    }
}
