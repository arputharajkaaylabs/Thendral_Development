package com.kaaylabs.thendral;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.kaaylabs.thendral.navigation.NavigationHomeActivity;

public class LoginActivity extends Activity implements View.OnClickListener {
    Button loginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // remove title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        initView();
        addListeners();
    }

    private void addListeners() {
        loginButton.setOnClickListener(LoginActivity.this);
    }

    private void initView() {
        loginButton = (Button)findViewById(R.id.loginButton);
    }

    @Override
    public void onClick(View v) {
        if(v == loginButton)
        {
            Intent homeIntent = new Intent(this, NavigationHomeActivity.class);
            startActivity(homeIntent);
            finish();
        }
    }
}
