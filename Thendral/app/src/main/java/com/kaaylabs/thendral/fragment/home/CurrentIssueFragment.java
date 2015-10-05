package com.kaaylabs.thendral.fragment.home;


import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aphidmobile.flip.FlipViewController;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kaaylabs.thendral.R;
import com.kaaylabs.thendral.activity.DetailsActivity;
import com.kaaylabs.thendral.bean.CurrentPublishedIssueDetails;
import com.kaaylabs.thendral.bean.HomePageTopCategory;
import com.kaaylabs.thendral.util.DynamicImageView;
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
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CurrentIssueFragment extends Fragment {

    private String xml;
    private List<HomePageTopCategory> homePageCenterClips;
    public FlipViewController flipView;
    FrameLayout progressLayout_currentIssuePage;
    LayoutInflater inflater;
    View viewFragment;
    int homeCategoryLength;
    public CurrentIssueFragment() {
        // Required empty public constructor
    }

    TextView currentIssueHeadTextView,titleTextView,authorTextView,descriptionTextView,commentTextView;
    DynamicImageView image_category;
    String statusCode="";
    SearchView searchView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        viewFragment = inflater.inflate(R.layout.home_page_layout, container, false);
        progressLayout_currentIssuePage = (FrameLayout)viewFragment.findViewById(R.id.progressLayout_currentIssuePage);
        // setHasOptionsMenu(true);



        return viewFragment;
    }

    class ServiceHomePageTopCategory extends AsyncTask<String, String, String> {
        protected void onPreExecute() {
            super.onPreExecute();

        }

        protected String doInBackground(String... s) {
            try {

                //Log.i("current categoryID","+http://int.thendralonline.com/ThendralWS/getHomePageTopCategories/issueidval/"+CurrentPublishedIssueDetails.getIssueId());
                // DefaultHttpClient httpClient = new DefaultHttpClient();
                SSLHttpClient ssl = new SSLHttpClient();
                HttpClient httpClient = ssl.getHttpClient();

                HttpGet httpGet = new HttpGet("http://int.thendralonline.com/ThendralWS/getHomePageTopCategories/issueidval/"+CurrentPublishedIssueDetails.getIssueId());
                HttpResponse httpResponse = httpClient.execute(httpGet);
                HttpEntity httpEntity = httpResponse.getEntity();
                xml = EntityUtils.toString(httpEntity);
                Log.i("<><><><z z ><", xml);

                if(httpResponse.getStatusLine().getStatusCode() == 200){
                    statusCode = "true";
                    GsonBuilder gsonBUilder = new GsonBuilder();
                    Gson gson = gsonBUilder.create();
                    homePageCenterClips = new ArrayList<HomePageTopCategory>();
                    homePageCenterClips = Arrays.asList(gson.fromJson(xml,HomePageTopCategory[].class));
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

                            loadHomePageContent();
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

public void loadHomePageContent(){


    homeCategoryLength = homePageCenterClips.size();
    Context cx=null;
    cx = getActivity();

  /*  for(int i=0;i<len;i++){

        LayoutInflater inflater = null;
        inflater = (LayoutInflater) getActivity().getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.fragment_current_issue,null);

        currentIssueHeadTextView = (TextView) view.findViewById(R.id.currentIssueHeadTextView);
        titleTextView = (TextView) view.findViewById(R.id.titleTextView);
        authorTextView = (TextView) view.findViewById(R.id.authorTextView);
        descriptionTextView = (TextView) view.findViewById(R.id.descriptionTextView);
        commentTextView = (TextView) view.findViewById(R.id.commentTextView);

        titleTextView.setTypeface(ThendralApplication.tf_tamil_font);
        authorTextView.setTypeface(ThendralApplication.tf_tamil_font);
        descriptionTextView.setTypeface(ThendralApplication.tf_tamil_font);
        currentIssueHeadTextView.setTypeface(ThendralApplication.tf_tamil_font);
        String TSCIIString = TamilUtil.convertToTamil(TamilUtil.BAMINI, "நேர்காணல்");
        //Setting the new string to TextView
        currentIssueHeadTextView.setText(TSCIIString);

        String title =   homePageTopCategory.get(i).getCategoryName();
        String titleString = TamilUtil.convertToTamil(TamilUtil.BAMINI, homePageTopCategory.get(i).getHeadline());
        String authorName = TamilUtil.convertToTamil(TamilUtil.BAMINI, homePageTopCategory.get(i).getAuthornames());
        String description = TamilUtil.convertToTamil(TamilUtil.BAMINI,homePageTopCategory.get(i).getUniCodeContent());

        titleTextView.setText(titleString);
        authorTextView.setText(authorName);
        descriptionTextView.setText(description);
        commentTextView.setText("6 comments - 50 likes");

        flipperLayout.addView(view);
    }*/

    LinearLayout flipperLayout = (LinearLayout) viewFragment.findViewById(R.id.flipper_layout);
    inflater = LayoutInflater.from(cx);
    flipView = new FlipViewController(cx);

    flipView.setAdapter(new BaseAdapter() {

        @Override
        public int getCount() {
            return homeCategoryLength;
        }

        @Override
        public Object getItem(int position) {
            return homePageCenterClips.get(position);
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
                view = inflater.inflate(R.layout.fragment_current_issue,null);

                currentIssueHeadTextView = (TextView) view.findViewById(R.id.currentIssueHeadTextView);
                titleTextView = (TextView) view.findViewById(R.id.titleTextView);
                authorTextView = (TextView) view.findViewById(R.id.authorTextView);
                descriptionTextView = (TextView) view.findViewById(R.id.descriptionTextView);
                commentTextView = (TextView) view.findViewById(R.id.commentTextView);
                image_category = (DynamicImageView) view.findViewById(R.id.image_category);


            } else {
                view = convertView;
            }

           // titleTextView.setTypeface(ThendralApplication.tf_tamil_font);
           // authorTextView.setTypeface(ThendralApplication.tf_tamil_font);
           // descriptionTextView.setTypeface(ThendralApplication.tf_tamil_font);
           // currentIssueHeadTextView.setTypeface(ThendralApplication.tf_tamil_font);
            String TSCIIString = TamilUtil.convertToTamil(TamilUtil.BAMINI,homePageCenterClips.get(position).getCategoryName() );
            //Setting the new string to TextView
            currentIssueHeadTextView.setText(homePageCenterClips.get(position).getCategoryName());

            String title =   homePageCenterClips.get(position).getCategoryName();
            String titleString = TamilUtil.convertToTamil(TamilUtil.BAMINI, homePageCenterClips.get(position).getHeadline());
            String authorName = TamilUtil.convertToTamil(TamilUtil.BAMINI, homePageCenterClips.get(position).getAuthornames());
            String description = TamilUtil.convertToTamil(TamilUtil.BAMINI,homePageCenterClips.get(position).getUniCodeContent());

            //DynamicImageView img = image_category;
            Log.i("log ","imageurls "+"http://intimages.thendral.com.s3.amazonaws.com/"+homePageCenterClips.get(position).getIssueName()+"/"+ homePageCenterClips.get(position).getCategoryid()+"/"+homePageCenterClips.get(position).getiImage());
            Picasso.with(getActivity()).load("http://intimages.thendral.com.s3.amazonaws.com/"+homePageCenterClips.get(position).getIssueName()+"/"+ homePageCenterClips.get(position).getCategoryid()+"/"+homePageCenterClips.get(position).getiImage()).into(image_category);

                    titleTextView.setText(homePageCenterClips.get(position).getHeadline());
            authorTextView.setText(homePageCenterClips.get(position).getAuthornames());
            descriptionTextView.setText(homePageCenterClips.get(position).getUniCodeContent());
            commentTextView.setText("6 comments - 50 likes");

            image_category.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("onclick", "-----------------on click-----------");
                    Intent in = new Intent(getActivity(),DetailsActivity.class);
                    in.putExtra("detailsPageUrl","http://int.thendralonline.com/ThendralWS/getGetMoreArticleNew/issueId/"+CurrentPublishedIssueDetails.getIssueId()+"/categoryID/"+homePageCenterClips.get(position).getCategoryid()+"/articleID/"+homePageCenterClips.get(position).getArticleid());
                    startActivity(in);
                }
            });

            return view;
        }

    });

    flipperLayout.addView(flipView);
    progressLayout_currentIssuePage.setVisibility(View.GONE);
}


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            // to call web service here
            if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB ) {
                new ServiceHomePageTopCategory().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            } else {
                new ServiceHomePageTopCategory().execute();
            }

        }
    }


   /* @Override
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

    }*/

}
