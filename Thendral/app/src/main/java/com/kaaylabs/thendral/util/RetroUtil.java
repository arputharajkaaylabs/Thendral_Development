package com.kaaylabs.thendral.util;

import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

/**
 * Created by ARaja on 12-07-2015.
 */
public class RetroUtil {
    /**
     * To set the socket timeout for http
     */
    public static OkHttpClient getClient() {
        OkHttpClient client = new OkHttpClient();
        client.setConnectTimeout(5, TimeUnit.MINUTES);
        client.setReadTimeout(5, TimeUnit.MINUTES);
        return client;
    }
}
