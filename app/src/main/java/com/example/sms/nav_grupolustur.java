package com.example.sms;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sms.adapters.GrupOlusturAdapter;
import com.example.sms.models.GrupOlusturModel;
import com.example.sms.models.MsgGonderModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class nav_grupolustur extends Fragment {
    public static final String TAG = "--GRUP_OLUSTUR--";
    ArrayList<GrupOlusturModel> grupOlusturModel;
    GrupOlusturAdapter grupOlusturAdapter;
    RecyclerView grupOlusturRv;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.fragment_nav_grupolustur, container, false);

        grupOlusturRv = root.findViewById(R.id.rv_gruplar);
        grupOlusturRv.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));

        grupOlusturModel = new ArrayList<>();
        grupOlusturAdapter = new GrupOlusturAdapter(getActivity(), grupOlusturModel);
        grupOlusturRv.setAdapter(grupOlusturAdapter);


        getCloudData();

        return root;
    }

    private void getCloudData() {
        db.collection("msggonder")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isComplete()) {
                            for(QueryDocumentSnapshot doc: task.getResult()) {
                                Log.d(TAG, doc.getData().toString());
                                GrupOlusturModel gom = doc.toObject(GrupOlusturModel.class);
                                grupOlusturModel.add(gom);
                                grupOlusturAdapter.notifyDataSetChanged();
                            }
                        }
                        else{
                            Log.d(TAG, "Veri YOK!");
                        }
                    }
                });
    }
}