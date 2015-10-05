package com.kaaylabs.thendral.activity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aphidmobile.flip.FlipViewController;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kaaylabs.thendral.R;
import com.kaaylabs.thendral.bean.CategoryEvents;
import com.kaaylabs.thendral.bean.CategoryEventsList;
import com.kaaylabs.thendral.bean.CurrentPublishedIssueDetails;
import com.kaaylabs.thendral.util.SSLHttpClient;
import com.mayuonline.tamilandroidunicodeutil.TamilUtil;
import com.squareup.picasso.Picasso;

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
import java.util.List;

public class CategoryDetailsPage extends AppCompatActivity {

    String statusCode="",xml="",categoryName="";
    CategoryEvents categoryEvents;
    Context ctx;
    public FlipViewController flipView;
    TextView currentIssueHeadTextView,titleTextView,authorTextView,descriptionTextView,commentTextView;
    List<CategoryEventsList> categoryEventsObj;
    String categoryUrl;
    ImageView toolbar_navigation;
    ImageView image_category;
    LayoutInflater inflater;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_details_page);
        ctx = this ;
        toolbar_navigation = (ImageView)findViewById(R.id.toolbar_navigation);
        toolbar_navigation.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                finish();
                return false;
            }
        });
        Intent in = getIntent();
        categoryUrl = in.getStringExtra("category_events");

        new ServiceHomePageTopCategory().execute();

    }





    class ServiceHomePageTopCategory extends AsyncTask<String, String, String> {
        protected void onPreExecute() {
            super.onPreExecute();

        }

        protected String doInBackground(String... s) {
            try {

                Log.i("current categoryID",categoryUrl);
                // DefaultHttpClient httpClient = new DefaultHttpClient();
                SSLHttpClient ssl = new SSLHttpClient();
                HttpClient httpClient = ssl.getHttpClient();

                HttpGet httpGet = new HttpGet(categoryUrl);
                HttpResponse httpResponse = httpClient.execute(httpGet);
                HttpEntity httpEntity = httpResponse.getEntity();
                xml = EntityUtils.toString(httpEntity);
                Log.i("<><><><z z ><", xml);

                if(httpResponse.getStatusLine().getStatusCode() == 200){
                    statusCode = "true";
                    GsonBuilder gsonBUilder = new GsonBuilder();
                    Gson gson = gsonBUilder.create();
                    categoryEvents = new CategoryEvents();
                    categoryEvents = gson.fromJson(xml, CategoryEvents.class);
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
               /* ctx.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {*/
                        if(statusCode == "true") {
                            statusCode ="false";

                            loadCategoryListValues();
                        }else{
                            statusCode = "false";
                            //  Toast.makeText(getActivity().getApplicationContext(), "Service Unavailable", Toast.LENGTH_LONG).show();
                        }
                   /* }
                });*/
            }catch (Exception e)
            {
                e.printStackTrace();
            }

        }
    }

public void loadCategoryListValues(){


    LinearLayout flipperLayout = (LinearLayout) findViewById(R.id.flipper_category_layout);
    inflater = LayoutInflater.from(ctx);
    flipView = new FlipViewController(ctx);

    if(categoryEvents.getCategoryName().size() > 0){
        categoryName = categoryEvents.getCategoryName().get(0).getCategoryName();
    }

     categoryEventsObj = new ArrayList<CategoryEventsList>();
    if(categoryEvents.getArticleMaster().size()> 0){
        categoryEventsObj = categoryEvents.getArticleMaster();
    }


    flipView.setAdapter(new BaseAdapter() {

        @Override
        public int getCount() {

            return categoryEventsObj.size();
        }

        @Override
        public Object getItem(int position) {
            return categoryEventsObj.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View view;

            if (convertView == null) {
                view = convertView;
                view = inflater.inflate(R.layout.category_list_details,null);

                currentIssueHeadTextView = (TextView) view.findViewById(R.id.currentIssueHeadTextView);
                titleTextView = (TextView) view.findViewById(R.id.titleTextView);
                authorTextView = (TextView) view.findViewById(R.id.authorTextView);
                descriptionTextView = (TextView) view.findViewById(R.id.descriptionTextView);
                commentTextView = (TextView) view.findViewById(R.id.commentTextView);
                image_category = (ImageView) view.findViewById(R.id.image_category);


            } else {
                view = convertView;
            }

        //    titleTextView.setTypeface(ThendralApplication.tf_tamil_font);
         //   authorTextView.setTypeface(ThendralApplication.tf_tamil_font);
          //  descriptionTextView.setTypeface(ThendralApplication.tf_tamil_font);
          //  currentIssueHeadTextView.setTypeface(ThendralApplication.tf_tamil_font);
            String TSCIIString = TamilUtil.convertToTamil(TamilUtil.BAMINI, categoryName);
            //Setting the new string to TextView
            currentIssueHeadTextView.setText(categoryName);

            String title =   categoryEventsObj.get(position).getHeadLine();
            String titleString = TamilUtil.convertToTamil(TamilUtil.BAMINI, categoryEventsObj.get(position).getHeadLine());
            String authorName = TamilUtil.convertToTamil(TamilUtil.BAMINI, "");
            String description = TamilUtil.convertToTamil(TamilUtil.BAMINI,categoryEventsObj.get(position).getAbstractContent());

            System.out.println("url === " + "http://intimages.thendral.com.s3.amazonaws.com/hp/" + categoryEventsObj.get(position).getArchiveImageUrl());
            Picasso.with(ctx).load("http://intimages.thendral.com.s3.amazonaws.com/hp/"+categoryEventsObj.get(position).getArchiveImageUrl()).into(image_category);

            titleTextView.setText(categoryEventsObj.get(position).getHeadLine());
         //   authorTextView.setText(authorName);
            descriptionTextView.setText(categoryEventsObj.get(position).getAbstractContent());
            commentTextView.setText("6 comments - 50 likes");

            image_category.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("onclick", "-----------------on click-----------");
                    Intent in = new Intent(ctx,DetailsActivity.class);
                    in.putExtra("detailsPageUrl","http://int.thendralonline.com/ThendralWS/getGetMoreArticleNew/issueId/"+CurrentPublishedIssueDetails.getIssueId()+"/categoryID/"+categoryEventsObj.get(position).getCategoryId()+"/articleID/"+categoryEventsObj.get(position).getArticleId());
                    startActivity(in);
                }
            });

            return view;
        }

    });

    flipperLayout.addView(flipView);
}

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_category_details_page, menu);
        return true;
    }
*/
    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/
}
