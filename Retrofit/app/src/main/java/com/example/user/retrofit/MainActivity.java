package com.example.user.retrofit;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import API.GetAPI;
import Model.MerchantDetails;
import Model.Product;
import Model.ProductDealDetails;
import Model.StoreTypes;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MainActivity extends Activity implements View.OnTouchListener {
    CheckedTextView ch_tv_get,ch_tv_post;
    String API="https://qamobile.orderrabbit.in/";
    long id=1000;
    AsyncTask<Void, Void, String>  getMerchantsDetails;
    MerchantDetails posts;
    Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ctx=this;
        init();

    }

private void init()
{
    ch_tv_get=(CheckedTextView)findViewById(R.id.get);
    ch_tv_get.setOnTouchListener(MainActivity.this);
    ch_tv_post=(CheckedTextView)findViewById(R.id.post);
    ch_tv_post.setOnTouchListener(MainActivity.this);
}

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(v == ch_tv_get)
        {
            Toast.makeText(getApplicationContext(),"Get",Toast.LENGTH_LONG).show();
           // get();
          //  asynGet();
           // getMappedProducts();'
            getStoretypes();
        }if(v == ch_tv_post){
            Toast.makeText(getApplicationContext(),"Post",Toast.LENGTH_LONG).show();
            post();
        }
        return false;
    }

    public void get()
    {
       try {
           RestAdapter restAdapter=new RestAdapter.Builder().setLogLevel(RestAdapter.LogLevel.BASIC).setEndpoint(API).build();
           GetAPI getAPI=restAdapter.create(GetAPI.class);

            getAPI.getMerchantDetails(id, new Callback<MerchantDetails>() {
                @Override
                public void success(MerchantDetails merchantDetails, Response response) {
                    Toast.makeText(getApplicationContext(), "Get-->" + merchantDetails.getProprietorName(), Toast.LENGTH_LONG).show();
                }

                @Override
                public void failure(RetrofitError error) {
                    Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
public void getMappedProducts()
{
    try{
        RestAdapter restAdapter=new RestAdapter.Builder().setLogLevel(RestAdapter.LogLevel.BASIC).setEndpoint(API).build();
        GetAPI getAPI=restAdapter.create(GetAPI.class);

        getAPI.getMappedProducts(1000, 0, 10, new Callback<List<Product>>() {
            @Override
            public void success(List<Product> product, Response response) {
                for (int i = 0; i < product.size(); i++) {
                    Toast.makeText(getApplicationContext(), product.get(i).getProductName(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }catch (Exception e)
    {
        e.printStackTrace();
    }
}

    public void getStoretypes(){
        try{
            RestAdapter restAdapter=new RestAdapter.Builder().setLogLevel(RestAdapter.LogLevel.BASIC).setEndpoint(API).build();
            GetAPI getAPI=restAdapter.create(GetAPI.class);

            getAPI.getStoreTypes(new Callback<List<StoreTypes>>() {
                @Override
                public void success(List<StoreTypes> storeTypes, Response response) {
                    for(StoreTypes s: storeTypes) {
                        Toast.makeText(getApplicationContext(), s.getStore_type(), Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void failure(RetrofitError error) {
                    Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                }
            });
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public void post() {
        try {
            RestAdapter restAdapter = new RestAdapter.Builder().setLogLevel(RestAdapter.LogLevel.BASIC).setEndpoint(API).build();
            GetAPI getAPI = restAdapter.create(GetAPI.class);

            getAPI.updateDeal(new ProductDealDetails(10, "10-04-2015", "10-05-2015", "Y", 180.5), new Callback<Response>() {
                @Override
                public void success(Response response, Response response2) {
                    Toast.makeText(getApplicationContext(), response.getReason()+"", Toast.LENGTH_LONG).show();
                }
                @Override
                public void failure(RetrofitError error) {
                    Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void asynGet()
    {
        getMerchantsDetails = new AsyncTask<Void, Void, String>() {
            BufferedReader in = null;
            String page = null;

            @Override
            protected String doInBackground(Void... params) {
                try {
                    HttpClient httpClient = new DefaultHttpClient();


                    HttpGet httpGet = new HttpGet(API+"getMerchandetailByStoreId?storeid="+id);
                    HttpResponse response = httpClient.execute(httpGet);
                    int responseCode = response.getStatusLine().getStatusCode();

                    Log.i("responseCode", responseCode + "");

                    in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                    StringBuffer sb = new StringBuffer("");
                    String line = "";
                    while ((line = in.readLine()) != null) {
                        sb.append(line);
                    }
                    in.close();
                    page = sb.toString();

                    Log.i(page, page);
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    Gson gson = gsonBuilder.create();
                    posts = gson.fromJson(sb.toString(), MerchantDetails.class);

                } catch (Exception e) {
                    Log.i("Error", e.getMessage());
                    e.printStackTrace();

                } finally {
                    if (in != null) {
                        try {
                            in.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                return page;
            }
            @Override
            protected void onPostExecute(String result) {

                Toast.makeText(ctx,"Asyn--->"+posts.getProprietorName(),Toast.LENGTH_LONG).show();
            }
        };

        getMerchantsDetails.execute(null,null,null);
    }
}
