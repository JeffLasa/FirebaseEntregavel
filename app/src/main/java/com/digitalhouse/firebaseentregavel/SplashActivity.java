package com.digitalhouse.firebaseentregavel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.digitalhouse.firebaseentregavel.modules.login.view.LoginActivity;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        new Timer().schedule(new TimerTask(){
            @Override
            public void run() {
                irParaUserLoginActivity();
            }
        }, 2000);
    }

    private void irParaUserLoginActivity () {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

    }
}