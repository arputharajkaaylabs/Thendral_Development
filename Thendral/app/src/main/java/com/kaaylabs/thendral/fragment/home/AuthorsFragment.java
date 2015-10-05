package com.kaaylabs.thendral.fragment.home;


import android.app.ProgressDialog;
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
import android.widget.FrameLayout;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kaaylabs.thendral.R;
import com.kaaylabs.thendral.fragment.home.author.FastScroller;
import com.kaaylabs.thendral.fragment.home.author.LargeAdapter;
import com.kaaylabs.thendral.gson_pojo.ThenralAuthorList;
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
import java.io.InputStream;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class AuthorsFragment extends Fragment {

    List<String> authorsNameList;
    List<ThenralAuthorList> thendralAuthorList;

    ProgressDialog progressDialog;
    RecyclerView recyclerView;
    FastScroller fastScroller;
    FrameLayout progressLayout_authersPage;
    ServiceCollectAuthorNameList serviceCollectAuthorNameList = new ServiceCollectAuthorNameList();
    String statusCode="",xml="";
    public AuthorsFragment() {
        // Required empty public constructor
    }
    public String loadJSONFromAsset(String string) {
        String json = null;
        try {

            InputStream is = getActivity().getAssets().open(string);

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_authers, container, false);
        setHasOptionsMenu(true);
        thendralAuthorList = new ArrayList<ThenralAuthorList>();
        authorsNameList=new ArrayList<>();
        progressLayout_authersPage = (FrameLayout)view.findViewById(R.id.progressLayout_authersPage);
        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerview);
       /* try {

            JSONObject obj = new JSONObject(loadJSONFromAsset("author.json"));
            if (obj.has("items")) {
                JSONArray catag = obj.getJSONArray("items");
                for (int i = 0; i < catag.length(); i++) {
                    JSONObject catitem = catag.getJSONObject(i);
                    if (catitem.has("catalog_items")) {
                        JSONArray posts = catitem.getJSONArray("catalog_items");
                        if (posts.length() > 0) {

                            for (int j = 0; j < posts.length(); j++) {
                                JSONObject singleItem = posts.getJSONObject(j);
                                items.add(singleItem.getString("name"));
                            }
                        }
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }*/

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        fastScroller=(FastScroller)view.findViewById(R.id.fastscroller);
        fastScroller.setRecyclerView(recyclerView);

        recyclerView.setAdapter(new LargeAdapter(getActivity().getApplicationContext(), thendralAuthorList,authorsNameList));


        return view;
    }

    class ServiceCollectAuthorNameList extends AsyncTask<String, String, String> {
        protected void onPreExecute() {
            super.onPreExecute();

        }

        protected String doInBackground(String... s) {
            try {
                // DefaultHttpClient httpClient = new DefaultHttpClient();
                SSLHttpClient ssl = new SSLHttpClient();
                HttpClient httpClient = ssl.getHttpClient();

                HttpGet httpGet = new HttpGet("http://int.thendralonline.com/ThendralWS/getThendralAuthors");
                HttpResponse httpResponse = httpClient.execute(httpGet);
                HttpEntity httpEntity = httpResponse.getEntity();
                xml = EntityUtils.toString(httpEntity);
                Log.i("<><><><z z ><", xml);

                if(httpResponse.getStatusLine().getStatusCode() == 200){
                    statusCode = "true";
                    GsonBuilder gsonBUilder = new GsonBuilder();
                    Gson gson = gsonBUilder.create();
                    thendralAuthorList = Arrays.asList(gson.fromJson(xml,ThenralAuthorList[].class));
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
                        if(statusCode == "true") {
                            statusCode ="false";

                            getAuthorList();
                        }else{
                            statusCode = "false";
                          //  Toast.makeText(getActivity().getApplicationContext(), "Service Unavailable", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }catch (Exception e)
            {
                e.printStackTrace();
            }

        }
    }
    public void getAuthorList() {


        for(int i =0;i<thendralAuthorList.size();i++){
            authorsNameList.add(thendralAuthorList.get(i).getAuthorName());
        }

        recyclerView.setAdapter(new LargeAdapter(getActivity().getApplicationContext(), thendralAuthorList,authorsNameList));
        progressLayout_authersPage.setVisibility(View.GONE);

    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            // to call web service here
            //new ServiceCollectAuthorNameList().execute();
            if(ConnectivityInfo.isConnectivityAvailable(getActivity()))
            {
                if (AsyncTask.Status.RUNNING == serviceCollectAuthorNameList.getStatus()) {
                    serviceCollectAuthorNameList.cancel(true);
                    serviceCollectAuthorNameList = new ServiceCollectAuthorNameList();
                    if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB ) {
                        new ServiceCollectAuthorNameList().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                    } else {
                        new ServiceCollectAuthorNameList().execute();
                    }
                } else if (AsyncTask.Status.FINISHED == serviceCollectAuthorNameList.getStatus()) {
                    serviceCollectAuthorNameList = new ServiceCollectAuthorNameList();
                    if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB ) {
                        new ServiceCollectAuthorNameList().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                    } else {
                        new ServiceCollectAuthorNameList().execute();
                    }
                }
                else {
                    if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB ) {
                        new ServiceCollectAuthorNameList().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                    } else {
                        new ServiceCollectAuthorNameList().execute();
                    }
                }
            }
            else{
                ThendralAppUtils.showToastMessage(getActivity(), "No Internet Connection");
            }


        }else{
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
