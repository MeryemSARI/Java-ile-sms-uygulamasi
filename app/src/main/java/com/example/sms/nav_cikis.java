package com.example.sms;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.sms.activities.SplashActivity;
import com.google.firebase.auth.FirebaseAuth;

public class nav_cikis extends Fragment {
    ImageView image;
    Button logout;

    public nav_cikis() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_nav_cikis, container, false);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        image = v.findViewById(R.id.iv_logout);
        logout = v.findViewById(R.id.btn_logout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), SplashActivity.class));
                firebaseAuth.signOut();
            }
        });

        return v;
    }

}