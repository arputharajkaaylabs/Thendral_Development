package com.kaaylabs.thendral.fragment;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kaaylabs.thendral.R;
import com.kaaylabs.thendral.adapter.CategoryRecylerAdapter;
import com.kaaylabs.thendral.bean.CategoryItem;
import com.kaaylabs.thendral.bean.CurrentPublishedIssueDetails;
import com.kaaylabs.thendral.gson_pojo.CategoryList;
import com.kaaylabs.thendral.util.ConnectivityInfo;
import com.kaaylabs.thendral.util.ThendralAppUtils;
import com.kaaylabs.thendral.util.Thendral_URLs;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment {

    private List<CategoryItem> categoryItemList = new ArrayList<CategoryItem>();
    private List<CategoryList> categoryListDetails = new ArrayList<CategoryList>();
    CategoryListAsync categoryListAsync = new CategoryListAsync();
    RecyclerView categoryRecyclerView;
    CategoryRecylerAdapter categoryAdapter;
    RecyclerView mRecyclerView;
    FrameLayout progressLayout;
    ProgressDialog progressDialog;
    String categoryResponse;
    String xml;
    String statusCode;
    public CategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category, container, false);
         /* Initialize recyclerview */
        categoryRecyclerView = (RecyclerView)view.findViewById(R.id.recyclerCategoryView);
        progressLayout = (FrameLayout)view.findViewById(R.id.progressLayout_category);
       // progressLayout.setVisibility(View.GONE);
        categoryRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        if (categoryItemList == null) {
            categoryItemList = new ArrayList<CategoryItem>();
        }
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerCategoryView); // Assigning the RecyclerView Object to the xml View

        setCategoryAdapter();
        callWebService();
        return view;
    }
    private void setCategoryAdapter()
    {
        categoryAdapter = new CategoryRecylerAdapter(categoryListDetails, getActivity());
        mRecyclerView.setAdapter(categoryAdapter);
    }
    private void startAsycnhTask()
    {
        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB ) {
            categoryListAsync = new CategoryListAsync();
            categoryListAsync.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        } else {
            categoryListAsync = new CategoryListAsync();
            categoryListAsync.execute();
        }
    }
    private void callWebService() {
        if(ConnectivityInfo.isConnectivityAvailable(getActivity()))
        {
            if (AsyncTask.Status.RUNNING == categoryListAsync.getStatus()) {
                categoryListAsync.cancel(true);
                startAsycnhTask();
            } else if (AsyncTask.Status.FINISHED == categoryListAsync.getStatus()) {

                startAsycnhTask();
            }
            else {
                startAsycnhTask();
            }
        }
        else{
            ThendralAppUtils.showToastMessage(getActivity(), "No Internet Connection");
        }
    }
    class CategoryListAsync extends AsyncTask<String, String, String> {
        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected String doInBackground(String... s) {
            InputStream inputStream = null;
            HttpURLConnection urlConnection = null;
            Integer result = 0;
            try {
                /* forming th java.net.URL object */
                String url_link = Thendral_URLs.THENDRAL_CATEGORY_URL+""+ CurrentPublishedIssueDetails.getIssueId()+"/isActive/Y";
                URL url = new URL(url_link);
                urlConnection = (HttpURLConnection) url.openConnection();

                 /* optional request header */
                //   urlConnection.setRequestProperty("Content-Type", "application/json");

                /* optional request header */
                //    urlConnection.setRequestProperty("Accept", "application/json");

                /* for Get request */
                urlConnection.setRequestMethod("GET");
                int statusCodeValue = urlConnection.getResponseCode();

                /* 200 represents HTTP OK */
                if (statusCodeValue ==  200) {
                    inputStream = new BufferedInputStream(urlConnection.getInputStream());
                    xml = ThendralAppUtils.convertInputStreamToString(inputStream);
                    statusCode = "true";
                    GsonBuilder gsonBUilder = new GsonBuilder();
                    Gson gson = gsonBUilder.create();
                    categoryListDetails.clear();
                    categoryListDetails = new ArrayList<CategoryList>();
                    if(!ThendralAppUtils.isEmpty(xml))
                    {
                        categoryListDetails = Arrays.asList(gson.fromJson(xml,CategoryList[].class));
                    }

                }else{
                    statusCode = "false";
                }
            } catch (Exception e) {
                Log.d("Thendral", e.getLocalizedMessage());
            }
            return xml;
        }
        protected void onProgressUpdate(String... progress) {

        }

        protected void onPostExecute(String unused) {
            //Log.d("LOG_TAG", "Testttttasdsasda");
            if(statusCode == "true") {
                statusCode ="false";
                categoryList();
            }else{
                statusCode = "false";
                Toast.makeText(getActivity().getApplicationContext(),"Service Unavailable",Toast.LENGTH_LONG).show();
            }
        }
    }

    public void categoryList(){

        setCategoryAdapter();
        progressLayout.setVisibility(View.GONE);

        // Creating the Adapter of MyAdapter class(which we are going to see in a bit)
        // And passing the titles,icons,header view name, header view email,
        // and header view profile picture

        // Setting the adapter to RecyclerView

         final GestureDetector mGestureDetector = new GestureDetector(getActivity(), new GestureDetector.SimpleOnGestureListener() {

            @Override public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

        });


        /*mRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
                View child = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());


                if (child != null && mGestureDetector.onTouchEvent(motionEvent)) {

                    // Toast.makeText(getActivity(), "The Item Clicked is: " + recyclerView.getChildPosition(child), Toast.LENGTH_SHORT).show();
                    *//*Intent detailsIntent = new Intent(getActivity(), DetailsActivity.class);
                    getActivity().startActivity(detailsIntent);*//*
                  *//*  TextView txt = (TextView)child.findViewById(R.id.categoryId_category);
                    TextView cat = (TextView)child.findViewById(R.id.issuesId_category);
                   Log.i("category  id" ,"acasca "+txt.getText()+" ... "+cat.getText());*//*

                    return true;

                }

                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });*/
    }

}
