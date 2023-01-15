package com.example.sms.ui.slideshow;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sms.R;
import com.example.sms.adapters.MesajAdapter;
import com.example.sms.databinding.FragmentSlideshowBinding;
import com.example.sms.models.HomeSeciliMsgModel;
import com.example.sms.models.MesajModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class SlideshowFragment extends Fragment {
    public static final String TAG = "--Slideshow--";
    ArrayList<MesajModel> mesajModel;
    MesajAdapter mesajAdapter;
    RecyclerView mesajRv;

    FirebaseFirestore db = FirebaseFirestore.getInstance();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);

        mesajRv = root.findViewById(R.id.rv_mesajlar);
        mesajRv.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));

        mesajModel = new ArrayList<>();
        mesajAdapter = new MesajAdapter(getActivity(),mesajModel);
        mesajRv.setAdapter(mesajAdapter);

        getCloudData();

        return root;
    }

    private void getCloudData() {
        db.collection("homeSeciliMsg")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isComplete()) {
                            for(QueryDocumentSnapshot doc: task.getResult()) {
                                Log.d(TAG, doc.getData().toString());
                                MesajModel mm = doc.toObject(MesajModel.class);
                                mesajModel.add(mm);
                                mesajAdapter.notifyDataSetChanged();
                            }
                        }
                        else{
                            Log.d(TAG, "Veri YOK!");
                        }
                    }
                });
    }
}