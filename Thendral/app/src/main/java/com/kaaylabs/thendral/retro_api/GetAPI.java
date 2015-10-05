package com.kaaylabs.thendral.retro_api;


import com.kaaylabs.thendral.gson_pojo.CategoryList;
import com.kaaylabs.thendral.gson_pojo.ThenralAuthorList;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;


/**
 * Created by ARaja on 12/7/2015.
 */
public interface GetAPI {

    @GET("/getThendralAuthors")
    public void getAuthorList(Callback<List<ThenralAuthorList>> responseCallback);

    @GET("/getCategoryIndexNew")
    public void getCategoryList(Callback<List<CategoryList>> responseCallback);


}
