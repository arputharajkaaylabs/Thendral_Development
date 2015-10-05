package com.kaaylabs.thendral.fragment.events;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kaaylabs.thendral.R;
import com.kaaylabs.thendral.adapter.EventsInformationAdapter;
import com.kaaylabs.thendral.bean.EventInformation;
import com.kaaylabs.thendral.bean.EventInformationBO;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventsOthersFragment extends Fragment {
    public ArrayList<EventInformation> eventInformations;
    RecyclerView informationRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    List<EventInformationBO> eventInformationBO;
    public EventsOthersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_events_others, container, false);
        informationRecyclerView = (RecyclerView)view.findViewById(R.id.eventsOthersRecyclerView);
        informationRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity());
        informationRecyclerView.setLayoutManager(mLayoutManager);
        initializeData();
        eventInformationBO = new ArrayList<EventInformationBO>();
        EventsInformationAdapter adapter = new EventsInformationAdapter(eventInformationBO,getActivity());
        informationRecyclerView.setAdapter(adapter);
        return view;
    }

    // This method creates an ArrayList that has three Person objects
// Checkout the project associated with this tutorial on Github if
// you want to use the same images.
    private void initializeData(){
        eventInformations = new ArrayList<>();
        eventInformations.add(new EventInformation(getString(R.string.tamil_author_name), getString(R.string.tamil_font), R.drawable.nadanthavai_1));
        eventInformations.add(new EventInformation(getString(R.string.tamil_author_name), getString(R.string.tamil_font), R.drawable.nadanthavai_2));
        eventInformations.add(new EventInformation(getString(R.string.tamil_author_name), getString(R.string.tamil_font), R.drawable.others_1));

        eventInformations.add(new EventInformation(getString(R.string.tamil_author_name), getString(R.string.tamil_font), R.drawable.others_2));
        eventInformations.add(new EventInformation(getString(R.string.tamil_author_name), getString(R.string.tamil_font), R.drawable.nadanthavai_2));
        eventInformations.add(new EventInformation(getString(R.string.tamil_author_name), getString(R.string.tamil_font), R.drawable.others_1));
        eventInformations.add(new EventInformation(getString(R.string.tamil_author_name), getString(R.string.tamil_font), R.drawable.nadanthavai_1));
        eventInformations.add(new EventInformation(getString(R.string.tamil_author_name), getString(R.string.tamil_font), R.drawable.others_2));
        eventInformations.add(new EventInformation(getString(R.string.tamil_author_name), getString(R.string.tamil_font), R.drawable.nadanthavai_1));

        eventInformations.add(new EventInformation(getString(R.string.tamil_author_name),getString(R.string.tamil_font), R.drawable.nadanthavai_1));
        eventInformations.add(new EventInformation(getString(R.string.tamil_author_name),getString(R.string.tamil_font), R.drawable.nadanthavai_1));
        eventInformations.add(new EventInformation(getString(R.string.tamil_author_name),getString(R.string.tamil_font), R.drawable.nadanthavai_1));
    }




}
