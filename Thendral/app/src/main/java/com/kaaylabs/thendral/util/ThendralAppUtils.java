package com.kaaylabs.thendral.util;

import android.content.Context;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ARaja on 25-06-2015.
 */
public class ThendralAppUtils {
    /*
   * To show a toast messages
   * */
    public static void showToastMessage(Context activity,String message)
    {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
    }
    /**
     * To check the particular string is empty or not.
     *
     * @param value
     * @return If the string is empty it return true,otherwise it return false.
     */
    public static boolean isEmpty(String value)
    {
        return value.trim().equals("null") || value.trim().equals("")
                || value.trim().equals(" ") || value.trim().equals(null);

    }
    // validating email id
    public static boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    public static String loadJSONFromAsset(String string,Context context) {
        String json = null;
        try {

            InputStream is = context.getAssets().open(string);

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;

    }
    public static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null){
            result += line;
        }

            /* Close Stream */
        if(null!=inputStream){
            inputStream.close();
        }
        return result;
    }
}
