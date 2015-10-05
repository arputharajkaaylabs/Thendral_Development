package com.kaaylabs.thendral.fragment.home;


import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kaaylabs.thendral.R;
import com.kaaylabs.thendral.adapter.PreviousIssueGridAdapter;
import com.kaaylabs.thendral.bean.PreviousIssuesByYearVO;
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
public class PreviousIssueFragment extends Fragment {

    private static final int GRID_COL_NUM = 3;
    TableRow tbh;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;
    String statusCode="",xml="";
    TableLayout table;
    FrameLayout progressLayout_previousIssuePage;
    ServiceCollectPreviousIssueList previousIssuesService = new ServiceCollectPreviousIssueList();
    List<PreviousIssuesByYearVO> previousIssuesByYearVO = new ArrayList<PreviousIssuesByYearVO>();;
    public PreviousIssueFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_previous_issue, container, false);
        setHasOptionsMenu(true);
        table = (TableLayout) rootView
                .findViewById(R.id.tableLayout);
        table.setStretchAllColumns(true);
        table.setShrinkAllColumns(true);
        progressLayout_previousIssuePage = (FrameLayout) rootView.findViewById(R.id.progressLayout_previousIssuePage);
        callWebService();
        return rootView;
    }

    private void startAsycnhTask()
    {
        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB ) {
            previousIssuesService = new ServiceCollectPreviousIssueList();
            previousIssuesService.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        } else {
            previousIssuesService = new ServiceCollectPreviousIssueList();
            previousIssuesService.execute();
        }
    }
    private void callWebService() {
        if(ConnectivityInfo.isConnectivityAvailable(getActivity()))
        {
            if (AsyncTask.Status.RUNNING == previousIssuesService.getStatus()) {
                previousIssuesService.cancel(true);
                startAsycnhTask();
            } else if (AsyncTask.Status.FINISHED == previousIssuesService.getStatus()) {

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

    /**
     * Now this is a life saver. Gridviews aren't friendly with scrollviews (I
     * might be wrong, but happens with me everytime), especially if generated
     * dynamically. Hence this function calculates height of each item of
     * gridview dynamically and hence sets the total view height adapted from
     * http://stackoverflow.com/a/22555947/1136491
     **/
    public void setGridViewHeightBasedOnChildren(RecyclerView gridView, int columns) {
        RecyclerView.Adapter listAdapter = gridView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        int items = listAdapter.getItemCount();//.getCount();
        int rows = 0;

        totalHeight = 380;//listItem.getMeasuredHeight();

        float x = 1;
        if (items > columns) {
            x = items / columns;
            rows = (int) (x + 1);
            totalHeight *= rows;
        }

        ViewGroup.LayoutParams params = gridView.getLayoutParams();
        params.height = totalHeight;
        gridView.setLayoutParams(params);

    }

    public TableRow addGridRow(ArrayList gridItemList) {
        TableRow gridrow = new TableRow(getActivity());
        RecyclerView gv = new RecyclerView(getActivity());
        TableRow.LayoutParams params = new TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT);
        gv.setLayoutParams(params);
        gv.setHasFixedSize(true);

        // The number of Columns
        mLayoutManager = new GridLayoutManager(getActivity(), GRID_COL_NUM);
        gv.setLayoutManager(mLayoutManager);

        mAdapter = new PreviousIssueGridAdapter(getActivity(),gridItemList,previousIssuesByYearVO);
        gv.setAdapter(mAdapter);
        gridrow.addView(gv);
        // Thanks for not messing up my gridview inside scrollview
        setGridViewHeightBasedOnChildren(gv, GRID_COL_NUM); // 3 Columns

        final GestureDetector mGestureDetector = new GestureDetector(getActivity(), new GestureDetector.SimpleOnGestureListener() {

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

        });
        gv.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent motionEvent) {
                View child = rv.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
                if (child != null && mGestureDetector.onTouchEvent(motionEvent)) {
                    //Toast.makeText(getActivity(), "item clicked" + rv.getChildPosition(child), Toast.LENGTH_LONG).show();
                }
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
        return gridrow;
    }

    // Simply add title above the gridview
    public TableRow addRowTitle(String titleb) {
        TableRow rowTitle = new TableRow(getActivity());
        rowTitle.setGravity(Gravity.CENTER_HORIZONTAL);
        // title column/row
        TextView title = new TextView(getActivity());
        title.setText(titleb);
        int color = getActivity().getResources().getColor(R.color.sub_title_head_color);
        title.setTextColor(color);
        title.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
        title.setGravity(Gravity.LEFT);
        title.setTypeface(Typeface.SERIF, Typeface.NORMAL);

        TableRow.LayoutParams params = new TableRow.LayoutParams();
        params.span = 6;
        params.setMargins(25,20,0,0);
        rowTitle.addView(title, params);
        return rowTitle;
    }
    class ServiceCollectPreviousIssueList extends AsyncTask<String, String, String> {
        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected String doInBackground(String... s) {
                InputStream inputStream = null;
                HttpURLConnection urlConnection = null;
                Integer result = 0;
                try {
                /* forming th java.net.URL object */
                    String url_link = Thendral_URLs.THENDRAL_PREVIOUS_ISSUE;
                    System.out.println("url "+url_link);
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
                        previousIssuesByYearVO.clear();
                        previousIssuesByYearVO = new ArrayList<PreviousIssuesByYearVO>();
                        if(!ThendralAppUtils.isEmpty(xml))
                        {
                            previousIssuesByYearVO = Arrays.asList(gson.fromJson(xml,PreviousIssuesByYearVO[].class));
                        }

                    }else{
                        statusCode = "false";
                    }
                } catch (Exception e) {
                    System.out.println("Thendral "+ e.getLocalizedMessage());
                }
            return xml;
        }
        protected void onProgressUpdate(String... progress) {

        }

        protected void onPostExecute(String unused) {
            if(statusCode == "true") {
                statusCode ="false";
                loadPreviousIssueFiles();
            }else{
                statusCode = "false";
                Toast.makeText(getActivity().getApplicationContext(),"Service Unavailable",Toast.LENGTH_LONG).show();
            }
        }
    }

    public void loadPreviousIssueFiles(){
        TableRow tbr = addRowTitle(previousIssuesByYearVO.size()+" volume");
        table.addView(tbr);

        ArrayList<String> itemList = new ArrayList<>();

        for (int j = 0; j < previousIssuesByYearVO.size(); j++) {

            itemList.add(previousIssuesByYearVO.get(j).getIssueName());
        }
        tbh = addGridRow(itemList); // Create Grid of
        // collections and add
        // it to a tablerow

        table.addView(tbh);

       progressLayout_previousIssuePage.setVisibility(View.GONE);

    }

}
