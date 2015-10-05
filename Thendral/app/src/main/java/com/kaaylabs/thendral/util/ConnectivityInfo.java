
package com.kaaylabs.thendral.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * This class is used to check the Internet connection available or not.
 * 
 * @author ARaja
 * 
 */
public class ConnectivityInfo {

	/**
	 * This Method is used to check the Internet connection available or not.If
	 * connection available it return true. otherwise return false.
	 * 
	 * @param context of activity
	 * @return true/false
	 */
	public static boolean isConnectivityAvailable(Context context) {
		ConnectivityManager connectivity = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity != null) {
			// To get the network information.
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null)
				for (int i = 0; i < info.length; i++)
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
		}
		return false;
	}
}
