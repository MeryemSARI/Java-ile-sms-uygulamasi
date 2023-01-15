package com.example.sms.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.example.sms.MainActivity;
import com.example.sms.R;
import com.example.sms.Tools;
import com.google.firebase.auth.FirebaseAuth;

public class SplashActivity extends AppCompatActivity {
    ProgressBar progressBar;
    FirebaseAuth firebaseAuth;
    Thread wait;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Intent intent = getIntent();

        Tools.context = getApplicationContext();

        firebaseAuth = FirebaseAuth.getInstance();
        progressBar = (ProgressBar) findViewById(R.id.progressBar3);
        progressBar.setVisibility(View.VISIBLE);
        SplashThread();

        //firebaseAuth.signOut();
        if(firebaseAuth.getCurrentUser() != null) {
            Tools.showMessage("Zaten giriş yapmışsınız. Ana sayfaya yönlendiriliyorsunuz.");
            wait.start();
        }else {
            progressBar.setVisibility(View.INVISIBLE);
            Tools.showMessage("Lütfen Login veya Sign Up olun!");
        }
    }
    public void loginClick(View view) {
        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
    }
    public void signupClick(View view) {
        startActivity(new Intent(SplashActivity.this, RegisterActivity.class));
    }
    public  void SplashThread(){
        wait = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(2000);
                    progressBar.setVisibility(View.INVISIBLE);
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
    }
}