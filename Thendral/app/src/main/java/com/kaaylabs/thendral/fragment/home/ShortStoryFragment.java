package com.kaaylabs.thendral.fragment.home;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kaaylabs.thendral.R;
import com.kaaylabs.thendral.adapter.ShortStoriesAdapter;
import com.kaaylabs.thendral.bean.CurrentPublishedIssueDetails;
import com.kaaylabs.thendral.bean.HomePageCenterClips;
import com.kaaylabs.thendral.util.ConnectivityInfo;
import com.kaaylabs.thendral.util.SSLHttpClient;
import com.kaaylabs.thendral.util.ThendralAppUtils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ShortStoryFragment extends Fragment {
    public ShortStoryFragment() {
        // Required empty public constructor
    }

    List<HomePageCenterClips> homePageCenterClips = new ArrayList<HomePageCenterClips>();
    RecyclerView mRecyclerView;
    RecyclerView newStoriesRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    ServiceCollectNewStory serviceCollectNewStory = new ServiceCollectNewStory();
    String statusCode = "", xml = "";
    RecyclerView.Adapter mAdapter;
    ShortStoriesAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_short_story, container, false);
        setHasOptionsMenu(true);
        // Calling the RecyclerView
        //mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
       // mRecyclerView.setHasFixedSize(true);
        newStoriesRecyclerView = (RecyclerView)view.findViewById(R.id.shortStoriesRecyclerView);
        newStoriesRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity());
        newStoriesRecyclerView.setLayoutManager(mLayoutManager);
        // The number of Columns
       /* mLayoutManager = new GridLayoutManager(getActivity(), 2);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new GridAdapter(getActivity().getApplicationContext());
        mRecyclerView.setAdapter(mAdapter);*/

        //setStoryAdapter();
        return view;
    }

    private void setStoryAdapter() {
        adapter = new ShortStoriesAdapter(homePageCenterClips,getActivity());
        newStoriesRecyclerView.setAdapter(adapter);
    }

    class ServiceCollectNewStory extends AsyncTask<String, String, String>{

    protected void onPreExecute() {
        super.onPreExecute();

    }

    protected String doInBackground(String... s) {
        try {
            // DefaultHttpClient httpClient = new DefaultHttpClient();
            SSLHttpClient ssl = new SSLHttpClient();
            HttpClient httpClient = ssl.getHttpClient();

            HttpGet httpGet = new HttpGet("http://int.thendralonline.com/ThendralWS/getHomePageCenterClips/issueId/"+CurrentPublishedIssueDetails.getIssueId());
            HttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();
            xml = EntityUtils.toString(httpEntity);
            Log.i("<><><><z z ><", xml);

            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                statusCode = "true";
                GsonBuilder gsonBUilder = new GsonBuilder();
                Gson gson = gsonBUilder.create();

                homePageCenterClips = Arrays.asList(gson.fromJson(xml, HomePageCenterClips[].class));
            } else {
                statusCode = "false";
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnrecoverableKeyException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (KeyStoreException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return xml;
    }

    protected void onProgressUpdate(String... progress) {

    }

    protected void onPostExecute(String unused) {
        //Log.d("LOG_TAG", "Testttttasdsasda");
        try {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (statusCode == "true") {
                        statusCode = "false";

                        setStoryAdapter();
                    } else {
                        statusCode = "false";
                        //  Toast.makeText(getActivity().getApplicationContext(), "Service Unavailable", Toast.LENGTH_LONG).show();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

        @Override
        public void setUserVisibleHint(boolean isVisibleToUser) {
            super.setUserVisibleHint(isVisibleToUser);
            if (isVisibleToUser) {
                // to call web service here
                //new ServiceCollectAuthorNameList().execute();
                if (ConnectivityInfo.isConnectivityAvailable(getActivity())) {
                    if (AsyncTask.Status.RUNNING == serviceCollectNewStory.getStatus()) {
                        serviceCollectNewStory.cancel(true);
                        serviceCollectNewStory = new ServiceCollectNewStory();
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                            new ServiceCollectNewStory().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                        } else {
                            new ServiceCollectNewStory().execute();
                        }
                    } else if (AsyncTask.Status.FINISHED == serviceCollectNewStory.getStatus()) {
                        serviceCollectNewStory = new ServiceCollectNewStory();
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                            new ServiceCollectNewStory().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                        } else {
                            new ServiceCollectNewStory().execute();
                        }
                    } else {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                            new ServiceCollectNewStory().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                        } else {
                            new ServiceCollectNewStory().execute();
                        }
                    }
                } else {
                    ThendralAppUtils.showToastMessage(getActivity(), "No Internet Connection");
                }


            } else {
           /* if(serviceCollectAuthorNameList.getStatus() == AsyncTask.Status.RUNNING){
                Log.i("Authur Invisible list", "---------------RUNNING ---------");
                //serviceCollectAuthorNameList.cancel(true);

            }else if(serviceCollectAuthorNameList.getStatus() == AsyncTask.Status.PENDING){
                Log.i("Authur Invisible list", "---------------PENDING ---------");
                //serviceCollectAuthorNameList.cancel(true);

            }else{

            }*/


            }
        }

}
