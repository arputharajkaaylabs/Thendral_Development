package com.kaaylabs.thendral.fragment.home;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kaaylabs.thendral.R;
import com.kaaylabs.thendral.adapter.GridAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClassifiedsFragment extends Fragment {


    public ClassifiedsFragment() {
        // Required empty public constructor
    }

    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_classifieds, container, false);
        setHasOptionsMenu(true);
        // Calling the RecyclerView
        mRecyclerView = (RecyclerView)view.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);

        // The number of Columns
        mLayoutManager = new GridLayoutManager(getActivity(), 2);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new GridAdapter(getActivity().getApplicationContext());
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }


}
