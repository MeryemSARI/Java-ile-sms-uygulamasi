package com.example.sms.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.sms.MainActivity;
import com.example.sms.R;
import com.example.sms.Tools;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    Button login;
    EditText email,password;
    ProgressBar progressBar;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = (Button) findViewById(R.id.buttonlogin);
        email = (EditText) findViewById(R.id.editTextTextEmailAddress);
        password = (EditText) findViewById(R.id.editTextTextPassword);
        progressBar = (ProgressBar) findViewById(R.id.progressBar2);
        progressBar.setVisibility(View.INVISIBLE);
        firebaseAuth = FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                loginUser();
            }
        });
    }

    private void loginUser() {
        String userEmail = email.getText().toString();
        String userPass = password.getText().toString();
        if(userEmail.isEmpty()){
            Tools.showMessage("E-mail boş bırakılamaz!");
        }
        if(userPass.isEmpty() || userEmail.length() <6){
            Tools.showMessage("Şifre geçersizdir!");
        }
        Log.d("--------",userEmail);

        firebaseAuth.signInWithEmailAndPassword(userEmail,userPass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.INVISIBLE);
                        if(task.isSuccessful()){
                            Tools.showMessage("Hoşgeldiniz");
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        }else{
                            Tools.showMessage("Geçersiz kullanıcı");
                        }
                    }
                });
    }

    public void signupClick(View view) {
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
    }
}