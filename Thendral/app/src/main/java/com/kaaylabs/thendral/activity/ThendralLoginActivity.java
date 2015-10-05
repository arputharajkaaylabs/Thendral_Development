package com.kaaylabs.thendral.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.kaaylabs.thendral.R;
import com.kaaylabs.thendral.navigation.NavigationHomeActivity;
import com.kaaylabs.thendral.util.ThendralAppUtils;

public class ThendralLoginActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener {

    Button loginButton,googleButton,facebookButton;
    TextView needAccountTextView,skipLoginTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thendral_login);
        //hide soft-keyboard
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        initView();
        addListeners();
    }

    private void addListeners() {
        loginButton.setOnClickListener(this);
        googleButton.setOnClickListener(this);
        facebookButton.setOnClickListener(this);

        needAccountTextView.setOnTouchListener(this);
        skipLoginTextView.setOnTouchListener(this);
    }

    private void initView() {
        loginButton = (Button)findViewById(R.id.loginButton);
        googleButton = (Button)findViewById(R.id.loginGoogleButton);
        facebookButton =  (Button)findViewById(R.id.loginFacebookButton);

        needAccountTextView =(TextView) findViewById(R.id.needAccountTextView);
        skipLoginTextView = (TextView) findViewById(R.id.skipLoginTextView);
    }


    @Override
    public void onClick(View v) {
        if(v == loginButton)
        {
            goToHome();
        }
        else if(v == googleButton)
        {
            ThendralAppUtils.showToastMessage(this, "Under Development");
        }
        else if(v == facebookButton)
        {
            ThendralAppUtils.showToastMessage(this, "Under Development");
        }
    }

    private void goToHome() {
        Intent homeIntent = new Intent(this, NavigationHomeActivity.class);
        startActivity(homeIntent);
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(v == needAccountTextView)
        {
           // goToHome();
            ThendralAppUtils.showToastMessage(this,"Under Development");
        }
        else if(v == skipLoginTextView)
        {
            goToHome();

        }
        return false;
    }
}
