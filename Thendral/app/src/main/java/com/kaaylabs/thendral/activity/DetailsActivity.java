package com.kaaylabs.thendral.activity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kaaylabs.thendral.R;
import com.kaaylabs.thendral.bean.ArticleDetailMainBO;
import com.kaaylabs.thendral.bean.ArticleDetailsPageBO;
import com.kaaylabs.thendral.util.SSLHttpClient;
import com.kaaylabs.thendral.util.URLImageParser;
import com.mayuonline.tamilandroidunicodeutil.TamilUtil;

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

public class DetailsActivity extends AppCompatActivity {
    TextView detailsIssueHeadTextView,titleTextView,authorTextView,descriptionTextView;

    ImageView backImageView;
    String xml="",statusCode="";
    String url="";
    ArticleDetailMainBO articleDetailMainBO;
    List<ArticleDetailsPageBO> articleDetailsPageBO;
    Context ctx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
       ctx = this;
       Intent in= getIntent();
        url = in.getStringExtra("detailsPageUrl");
        Log.i("urls","-------"+url);
        initView();
        addListiner();

    }

    private void initView() {
        backImageView = (ImageView)findViewById(R.id.toolbar_navigation);
        detailsIssueHeadTextView = (TextView) findViewById(R.id.detailsIssueHeadTextView);
      //  detailsIssueHeadTextView.setTypeface(ThendralApplication.tf_tamil_font);


        titleTextView = (TextView) findViewById(R.id.titleTextView);
        authorTextView = (TextView) findViewById(R.id.authorTextView);
        descriptionTextView = (TextView)findViewById(R.id.descriptionTextView);
     //   titleTextView.setTypeface(ThendralApplication.tf_tamil_font);
      //  authorTextView.setTypeface(ThendralApplication.tf_tamil_font);
     //   descriptionTextView.setTypeface(ThendralApplication.tf_tamil_font);

        new ServiceDetailScreen().execute();
    }


    private void addListiner() {
        backImageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                finish();;
                return false;
            }
        });
    }
    class ServiceDetailScreen extends AsyncTask<String, String, String> {
        protected void onPreExecute() {
            super.onPreExecute();

        }

        protected String doInBackground(String... s) {
            try {
                // DefaultHttpClient httpClient = new DefaultHttpClient();
                SSLHttpClient ssl = new SSLHttpClient();
                HttpClient httpClient = ssl.getHttpClient();

                HttpGet httpGet = new HttpGet(url);
                HttpResponse httpResponse = httpClient.execute(httpGet);
                HttpEntity httpEntity = httpResponse.getEntity();
                xml = EntityUtils.toString(httpEntity);
                Log.i("<><><><z z ><", xml);

                if(httpResponse.getStatusLine().getStatusCode() == 200){
                    statusCode = "true";
                    GsonBuilder gsonBUilder = new GsonBuilder();
                    Gson gson = gsonBUilder.create();
                    articleDetailMainBO = new ArticleDetailMainBO();
                    articleDetailMainBO = gson.fromJson(xml, ArticleDetailMainBO.class);
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
                loadArticleDetails();
            }else{
                statusCode = "false";
                Toast.makeText(getApplicationContext(), "Service Unavailable", Toast.LENGTH_LONG).show();
            }
        }
    }
  public void loadArticleDetails(){


      try {

          if(articleDetailMainBO.getArticleContent().size() > 0){
              articleDetailsPageBO = new ArrayList<ArticleDetailsPageBO>();
              articleDetailsPageBO = articleDetailMainBO.getArticleContent();
              String TSCIIString = TamilUtil.convertToTamil(TamilUtil.BAMINI, articleDetailsPageBO.get(0).getCategoryName());
              //Setting the new string to TextView
              detailsIssueHeadTextView.setText(articleDetailsPageBO.get(0).getCategoryName());

              String title =   articleDetailsPageBO.get(0).getArticleTitle();
         //     String titleString = TamilUtil.convertToTamil(TamilUtil.BAMINI, title);
           //   String authorName = TamilUtil.convertToTamil(TamilUtil.BAMINI, "நேர்காணல்");
           //   String description = TamilUtil.convertToTamil(TamilUtil.BAMINI, articleDetailsPageBO.get(0).getUnicodeContent1()+"      "+articleDetailsPageBO.get(0).getUnicodeContent2());

              titleTextView.setText(title);
              //authorTextView.setText(authorName);
             // descriptionTextView.setText(Html.fromHtml(articleDetailsPageBO.get(0).getUnicodeContent1()+"      "+articleDetailsPageBO.get(0).getUnicodeContent2()));
              String uiContents = articleDetailsPageBO.get(0).getUnicodeContent1()+articleDetailsPageBO.get(0).getUnicodeContent2();
              uiContents = uiContents.replaceAll("<center>", "<br>");
              uiContents = uiContents.replaceAll("</center>","<br><br><br><br>");
              URLImageParser p = new URLImageParser(descriptionTextView, this);
              Spanned htmlSpan = Html.fromHtml(uiContents, p, null);
              descriptionTextView.setText(htmlSpan);

          }

      }catch (Exception e){

      }


      /*try {

          JSONObject obj = new JSONObject(ThendralAppUtils.loadJSONFromAsset("current_issue.json", this));
          if (obj.has("items")) {
              JSONArray catag = obj.getJSONArray("items");
              for (int i = 0; i < catag.length(); i++) {
                  JSONObject catitem = catag.getJSONObject(i);
                  if (catitem.has("catalog_items")) {
                      JSONArray posts = catitem.getJSONArray("catalog_items");
                      if (posts.length() > 0) {

                          for (int j = 0; j < posts.length(); j++) {
                              JSONObject singleItem = posts.getJSONObject(j);
                              String title =   singleItem.getString("name");

                              String titleString = TamilUtil.convertToTamil(TamilUtil.BAMINI, title);
                              String authorName = TamilUtil.convertToTamil(TamilUtil.BAMINI, singleItem.getString("author"));
                              String description = TamilUtil.convertToTamil(TamilUtil.BAMINI, singleItem.getString("description"));

                              titleTextView.setText(titleString);
                              authorTextView.setText(authorName);
                              descriptionTextView.setText(description);
                          }
                      }
                  }
              }
          }
      } catch (JSONException e) {
          e.printStackTrace();
      }*/
  }
}
