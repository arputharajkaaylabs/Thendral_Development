package com.kaaylabs.thendral.fragment.events;


import android.app.SearchManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.kaaylabs.thendral.R;
import com.kaaylabs.thendral.util.ThendralAppUtils;


public class EventsSlidingTabFragment extends Fragment {

    EventsViewPagerAdapter adapter;
    public ViewPager pager;
    EventsSlidingTabLayout tabs;
  //  String[] Titles = {"அறிவிப்புகள்", "நடந்தவை", "மற்றவை"};
    String[] Titles = {"அறிவிப்புகள்", "நடந்தவை"};

    int Numboftabs = 2;
    SearchView searchView;
    private static final String SELECTED_TAB_POSITION = "selectedTabPosition";
    SharedPreferences sharedPreferences;
    String selectedTab = "";
    String currentTab = "0";

    int storeId;
    // TODO: Rename and change types and number of parameters
    public static EventsSlidingTabFragment newInstance(String param1) {
        EventsSlidingTabFragment fragment = new EventsSlidingTabFragment();
        Bundle args = new Bundle();
        args.putString(SELECTED_TAB_POSITION, param1);
        fragment.setArguments(args);
        return fragment;
    }

    public EventsSlidingTabFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            selectedTab = getArguments().getString(SELECTED_TAB_POSITION);

        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.events_sliding_layout, container, false);

        // Creating The ViewPagerAdapter and Passing Fragment Manager, Titles fot the Tabs and Number Of Tabs.
        adapter = new EventsViewPagerAdapter(getChildFragmentManager(), Titles, Numboftabs);

        // Assigning ViewPager View and setting the adapter
        pager = (ViewPager) view.findViewById(R.id.pager);
        pager.setAdapter(adapter);


        // Assiging the Sliding Tab Layout View
        tabs = (EventsSlidingTabLayout) view.findViewById(R.id.tabs);
        tabs.setDistributeEvenly(true); // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width

        // Setting Custom Color for the Scroll bar indicator of the Tab View
        tabs.setCustomTabColorizer(new EventsSlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.tabsScrollColor);
            }
        });

        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        tabs.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {

            }

            @Override
            public void onPageSelected(int i) {

                    pager.setCurrentItem(i);
                switch (i){

                    case 0:

                        break;
                    case 1:

                        break;
                    case 2:

                        break;

                    default:
                        selectedTab = "" + i;
                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });


        // Setting the ViewPager For the SlidingTabsLayout
        tabs.setViewPager(pager);

        if(!ThendralAppUtils.isEmpty(selectedTab))
        {
            selectedTabAndPage(Integer.parseInt(selectedTab));
        }

            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("currentEventSelectedTab", currentTab);
            editor.commit();


        return view;
    }

    public void selectedTabAndPage(int postion)
    {
        pager.setCurrentItem(postion);
        selectedTab ="0";
    }



    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        MenuItem searchItem = menu.findItem(R.id.action_search);

        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);


        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();

        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        }
        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
    }
}



