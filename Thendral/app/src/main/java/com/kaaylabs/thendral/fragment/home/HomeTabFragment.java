package com.kaaylabs.thendral.fragment.home;


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
import com.kaaylabs.thendral.slidingtab.SlidingTabLayout;
import com.kaaylabs.thendral.slidingtab.ViewPagerAdapter;
import com.kaaylabs.thendral.util.ThendralAppUtils;


public class HomeTabFragment extends Fragment {

    ViewPagerAdapter adapter;
    public ViewPager pager;
    SlidingTabLayout tabs;
    CharSequence Titles[] = {"Home", "New Stories", "Authors", "Events","Classifieds"};
    int Numboftabs = 5;
    SearchView searchView;
    private static final String SELECTED_TAB_POSITION = "selectedTabPosition";
    SharedPreferences sharedPreferences;
    String selectedTab = "";
    String currentTab = "0";

    int storeId;
    // TODO: Rename and change types and number of parameters
    public static HomeTabFragment newInstance(String param1) {
        HomeTabFragment fragment = new HomeTabFragment();
        Bundle args = new Bundle();
        args.putString(SELECTED_TAB_POSITION, param1);
        fragment.setArguments(args);
        return fragment;
    }

    public HomeTabFragment() {
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

        View view = inflater.inflate(R.layout.activity_sliding_home, container, false);

        // Creating The ViewPagerAdapter and Passing Fragment Manager, Titles fot the Tabs and Number Of Tabs.
        adapter = new ViewPagerAdapter(getChildFragmentManager(), Titles, Numboftabs);

        // Assigning ViewPager View and setting the adapter
        pager = (ViewPager) view.findViewById(R.id.pager);
        pager.setAdapter(adapter);
        // load slide menu items

        tabs = (SlidingTabLayout) view.findViewById(R.id.tabs);
        Integer[] iconResourceArray = { R.drawable.selector_current_issue,
                R.drawable.selector_previous_issue, R.drawable.selector_author,R.drawable.selector_events,
                R.drawable.selector_classified };
        tabs.setIconResourceArray(iconResourceArray);

        // Assiging the Sliding Tab Layout View
      //  tabs = (SlidingTabLayout) view.findViewById(R.id.tabs);
        tabs.setDistributeEvenly(true); // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width

        // Setting Custom Color for the Scroll bar indicator of the Tab View
        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
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

                //   Toast.makeText(getActivity(), "Page " + i2, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageSelected(int i) {
             //   Toast.makeText(getActivity(), "tab Click" + i, Toast.LENGTH_SHORT).show();

                    pager.setCurrentItem(i);
                switch (i){

                    case 0:
                        getActivity().setTitle(Titles[0]);
                        break;
                    case 1:
                        getActivity().setTitle(Titles[1]);
                        break;
                    case 2:
                        getActivity().setTitle(Titles[2]);
                        break;
                    case 3:
                        getActivity().setTitle(Titles[3]);
                        break;
                    case 4:
                        getActivity().setTitle(Titles[4]);
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


        System.out.println("Selected Tab Position = " + selectedTab);
        if(!ThendralAppUtils.isEmpty(selectedTab))
        {
            selectedTabAndPage(Integer.parseInt(selectedTab));

        }

            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());

            SharedPreferences.Editor editor = sharedPreferences.edit();

            editor.putString("currentSelectedTab", currentTab);
            editor.commit();


        return view;
    }

    public void selectedTabAndPage(int postion)
    {
        pager.setCurrentItem(postion);
        selectedTab ="0";
    }



    @Override
    public void onCreateOptionsMenu(final Menu menu, final MenuInflater inflater) {

        inflater.inflate(R.menu.menu_sliding_home, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);

        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);


        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();

        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
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

        super.onCreateOptionsMenu(menu, inflater);

    }
}



