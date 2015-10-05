package com.kaaylabs.thendral;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Window;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kaaylabs.thendral.activity.ThendralLoginActivity;
import com.kaaylabs.thendral.bean.CurrentPublishedIssueDetails;
import com.kaaylabs.thendral.bean.PublishedIssueVO;
import com.kaaylabs.thendral.util.SSLHttpClient;

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

public class SplashActivity extends Activity {

    public List<PublishedIssueVO> publishedIssueVO;
    public String xml;
    Context ctx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // remove title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
        new ServiceForPublishedIssue().execute();
    }

    class ServiceForPublishedIssue extends AsyncTask<String, String, String> {
        protected void onPreExecute() {
            super.onPreExecute();

        }

        protected String doInBackground(String... s) {
            try {
                // DefaultHttpClient httpClient = new DefaultHttpClient();
                SSLHttpClient ssl = new SSLHttpClient();
                HttpClient httpClient = ssl.getHttpClient();

                HttpGet httpGet = new HttpGet("http://int.thendralonline.com/ThendralWS/getPublishedIssue");
                HttpResponse httpResponse = httpClient.execute(httpGet);
                HttpEntity httpEntity = httpResponse.getEntity();
                xml = EntityUtils.toString(httpEntity);
                Log.i("<><><><><", xml);

                if(httpResponse.getStatusLine().getStatusCode() == 200){
                    GsonBuilder gsonBUilder = new GsonBuilder();
                    Gson gson = gsonBUilder.create();
                    publishedIssueVO = new ArrayList<PublishedIssueVO>();
                    publishedIssueVO = Arrays.asList(gson.fromJson(xml,PublishedIssueVO[].class));
                } else {

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

           Log.i("Issues Id ", "" + publishedIssueVO.get(0).getIssueId());

           try{
               if(publishedIssueVO.size() > 0){

                   CurrentPublishedIssueDetails currentPublishedIssueDetails = new CurrentPublishedIssueDetails();
                   currentPublishedIssueDetails.setCoverImage(publishedIssueVO.get(0).getCoverImage());
                   currentPublishedIssueDetails.setIssueId(publishedIssueVO.get(0).getIssueId());
                   currentPublishedIssueDetails.setDisplayName(publishedIssueVO.get(0).getDisplayName());
                   currentPublishedIssueDetails.setHeaderImage(publishedIssueVO.get(0).getHeaderImage());
                   currentPublishedIssueDetails.setIssueName(publishedIssueVO.get(0).getIssueName());
                   currentPublishedIssueDetails.setPublishIssue(publishedIssueVO.get(0).getPublishIssue());

                   SharedPreferences shar = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                   SharedPreferences.Editor editor = shar.edit();
                   editor.putString("currentIssuesId", publishedIssueVO.get(0).getIssueId());
                   editor.commit();
                   //CurrentPublishedIssueDetails.setPublishedIssueVO(publishedIssueVO);
               }

           }catch (Exception e){

           }


            Thread splashThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(3000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                Intent loginIntent = new Intent(SplashActivity.this, ThendralLoginActivity.class);
                                startActivity(loginIntent);
                                finish();
                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            });
            splashThread.start();
        }
    }
}
