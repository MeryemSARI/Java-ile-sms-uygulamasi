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

import com.example.sms.R;
import com.example.sms.Tools;
import com.example.sms.models.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    Button signup;
    EditText email, pass;
    ProgressBar progressBar;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        signup = (Button) findViewById(R.id.buttonlogin);
        email = (EditText) findViewById(R.id.editTextTextEmailAddress);
        pass = (EditText) findViewById(R.id.editTextTextPassword);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);

        firebaseAuth = FirebaseAuth.getInstance();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                signupClick();
            }
        });
    }

    private void signupClick() {
        String userEmail = email.getText().toString();
        String userPass = pass.getText().toString();
        if(userEmail.isEmpty()){
           Tools.showMessage("E-mail boş bırakılamaz!");
        }
        if(userPass.isEmpty() || userEmail.length() <6){
            Tools.showMessage("Geçersiz şifre!");
        }
        Log.d("--------",userEmail);

        firebaseAuth.createUserWithEmailAndPassword(userEmail, userPass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.INVISIBLE);
                        if(task.isSuccessful()) {
                            UserModel user = new UserModel(userEmail,userPass);
                            String uid = task.getResult().getUser().getUid();
                            Tools.showMessage("Kayıt başarıyla oluşturuldu.");
                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            DatabaseReference myRef = database.getReference("users").child(uid);
                            myRef.setValue(user);


                            startActivity(new Intent(RegisterActivity.this, SplashActivity.class));
                        }else{
                            Tools.showMessage("Kayıt oluşturulamadı");
                        }
                    }
                });
    }

    public void loginClick(View view) {
        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
    }
}