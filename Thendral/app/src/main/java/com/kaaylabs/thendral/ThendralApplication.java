package com.kaaylabs.thendral;

import android.app.Application;
import android.graphics.Typeface;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

import java.util.HashMap;

/**
 * Created by ARaja on 26-06-2015.
 */
public class ThendralApplication extends Application {
    public static GoogleAnalytics analytics;
    public static Tracker tracker;

    public static Typeface
            tf_open_sans_bold,
            tf_open_sans_bold_italic,
            tf_open_sans_extra_bold,
            tf_open_sans_extra_bold_italic,tf_tamil_font;

    @Override
    public void onCreate() {
        super.onCreate();

        tf_tamil_font  = Typeface.createFromAsset(this.getAssets(), "bamini.ttf");
        tf_open_sans_bold = Typeface.createFromAsset(this.getAssets(), "OpenSans-Bold.ttf");
        tf_open_sans_bold_italic = Typeface.createFromAsset(this.getAssets(), "OpenSans-BoldItalic.ttf");
        tf_open_sans_extra_bold = Typeface.createFromAsset(this.getAssets(), "OpenSans-ExtraBold.ttf");
        tf_open_sans_extra_bold_italic = Typeface.createFromAsset(this.getAssets(), "OpenSans-ExtraBoldItalic.ttf");

        init();
        analytics = GoogleAnalytics.getInstance(this);
        analytics.setLocalDispatchPeriod(1800);
        tracker = analytics.newTracker(R.xml.app_tracker);
       // tracker = analytics.newTracker("UA-63045676-1"); // Replace with actual tracker/property Id
        tracker.enableExceptionReporting(true);
        tracker.enableAdvertisingIdCollection(true);
        tracker.enableAutoActivityTracking(true);
    }


    private void init() {
      //  MyVolley.init(this);
    }

    public static int GENERAL_TRACKER = 0;

    public enum TrackerName {
        APP_TRACKER,
    }

    public HashMap<TrackerName, Tracker> mTrackers = new HashMap<TrackerName, Tracker>();

    public ThendralApplication() {
        super();
    }

    public synchronized Tracker getTracker(TrackerName appTracker) {
        if (!mTrackers.containsKey(appTracker)) {
            GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
            Tracker t = analytics.newTracker(R.xml.app_tracker);
            mTrackers.put(appTracker, t);
        }
        return mTrackers.get(appTracker);
    }
}
