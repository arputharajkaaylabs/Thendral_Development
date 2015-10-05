package com.kaaylabs.thendral.fragment.events;


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
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kaaylabs.thendral.R;
import com.kaaylabs.thendral.adapter.EventsInformationAdapter;
import com.kaaylabs.thendral.bean.CurrentPublishedIssueDetails;
import com.kaaylabs.thendral.bean.EventInformation;
import com.kaaylabs.thendral.bean.EventInformationBO;
import com.kaaylabs.thendral.navigation.NavDrawerListAdapter;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class EventInformationFragment extends Fragment {

    public ArrayList<EventInformation> eventInformations;
    RecyclerView informationRecyclerView;
    NavDrawerListAdapter navigationAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    List<EventInformationBO> eventInformationBO;
    ServiceEventInformation serviceEventInformation = new ServiceEventInformation();
    String xml="",statusCode="";
    public EventInformationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_event_information, container, false);
        informationRecyclerView = (RecyclerView)view.findViewById(R.id.eventsRecyclerView);
        informationRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity());
        informationRecyclerView.setLayoutManager(mLayoutManager);
      //  initializeData();


        return view;
    }
    // This method creates an ArrayList that has three Person objects
// Checkout the project associated with this tutorial on Github if
// you want to use the same images.
    private void initializeData(){
        eventInformations = new ArrayList<>();
        eventInformations.add(new EventInformation(getString(R.string.tamil_author_name),getString(R.string.tamil_font), R.drawable.jothi));
        eventInformations.add(new EventInformation(getString(R.string.tamil_author_name),getString(R.string.tamil_font), R.drawable.jothi));
        eventInformations.add(new EventInformation(getString(R.string.tamil_author_name),getString(R.string.tamil_font), R.drawable.jothi));

        eventInformations.add(new EventInformation(getString(R.string.tamil_author_name),getString(R.string.tamil_font), R.drawable.jothi));
        eventInformations.add(new EventInformation(getString(R.string.tamil_author_name),getString(R.string.tamil_font), R.drawable.jothi));
        eventInformations.add(new EventInformation(getString(R.string.tamil_author_name),getString(R.string.tamil_font), R.drawable.jothi));
        eventInformations.add(new EventInformation(getString(R.string.tamil_author_name),getString(R.string.tamil_font), R.drawable.jothi));
        eventInformations.add(new EventInformation(getString(R.string.tamil_author_name),getString(R.string.tamil_font), R.drawable.jothi));
        eventInformations.add(new EventInformation(getString(R.string.tamil_author_name),getString(R.string.tamil_font), R.drawable.jothi));

        eventInformations.add(new EventInformation(getString(R.string.tamil_author_name),getString(R.string.tamil_font), R.drawable.jothi));
        eventInformations.add(new EventInformation(getString(R.string.tamil_author_name),getString(R.string.tamil_font), R.drawable.jothi));
        eventInformations.add(new EventInformation(getString(R.string.tamil_author_name),getString(R.string.tamil_font), R.drawable.jothi));
    }

    class ServiceEventInformation extends AsyncTask<String, String, String> {
        protected void onPreExecute() {
            super.onPreExecute();

        }

        protected String doInBackground(String... s) {
            try {
                // DefaultHttpClient httpClient = new DefaultHttpClient();
                SSLHttpClient ssl = new SSLHttpClient();
                HttpClient httpClient = ssl.getHttpClient();

                HttpGet httpGet = new HttpGet("http://int.thendralonline.com/ThendralWS/getHomePageNigazvu/issueid/"+ CurrentPublishedIssueDetails.getIssueId());
                HttpResponse httpResponse = httpClient.execute(httpGet);
                HttpEntity httpEntity = httpResponse.getEntity();
                xml = EntityUtils.toString(httpEntity);
                Log.i("<><><><z z ><", xml);

                if(httpResponse.getStatusLine().getStatusCode() == 200){
                    statusCode = "true";
                    GsonBuilder gsonBUilder = new GsonBuilder();
                    Gson gson = gsonBUilder.create();
                    eventInformationBO = new ArrayList<EventInformationBO>();
                    eventInformationBO = Arrays.asList(gson.fromJson(xml, EventInformationBO[].class));
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
            if(statusCode == "true") {
                statusCode ="false";
                loadEventInformation();
            }else{
                statusCode = "false";
                Toast.makeText(getActivity().getApplicationContext(), "Service Unavailable", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void loadEventInformation(){


        EventsInformationAdapter adapter = new EventsInformationAdapter(eventInformationBO,getActivity());
        informationRecyclerView.setAdapter(adapter);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            // to call web service here
            // new ServiceCollectPreviousIssueList().execute();


            if(ConnectivityInfo.isConnectivityAvailable(getActivity()))
            {
                if (AsyncTask.Status.RUNNING == serviceEventInformation.getStatus()) {
                    serviceEventInformation.cancel(true);
                    serviceEventInformation = new ServiceEventInformation();
                    if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB ) {
                        new ServiceEventInformation().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                    } else {
                        new ServiceEventInformation().execute();
                    }
                } else if (AsyncTask.Status.FINISHED == serviceEventInformation.getStatus()) {
                    serviceEventInformation = new ServiceEventInformation();
                    if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB ) {
                        new ServiceEventInformation().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                    } else {
                        new ServiceEventInformation().execute();
                    }
                }
                else {
                    if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB ) {
                        new ServiceEventInformation().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                    } else {
                        new ServiceEventInformation().execute();
                    }
                }
            }
            else{
                ThendralAppUtils.showToastMessage(getActivity(), "No Internet Connection");
            }
        }
    }

}
