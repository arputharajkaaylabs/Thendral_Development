package com.kaaylabs.thendral.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.kaaylabs.thendral.util.ConnectivityInfo;
import com.kaaylabs.thendral.util.NetworkUtil;

/**
 * Created by ARaja on 15-05-2015.
 */
public class NetworkChangeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, final Intent intent) {


        if(ConnectivityInfo.isConnectivityAvailable(context))
        {

        }
        else{
            String status = NetworkUtil.getConnectivityStatusString(context);

            Toast.makeText(context, status, Toast.LENGTH_LONG).show();
        }

    }
}
