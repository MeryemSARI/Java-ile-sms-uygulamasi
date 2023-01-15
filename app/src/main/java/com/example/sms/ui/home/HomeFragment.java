package com.example.sms.ui.home;

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
import com.example.sms.adapters.HomeSeciliMsgAdapter;
import com.example.sms.adapters.MsgGonderAdapter;
import com.example.sms.databinding.FragmentHomeBinding;
import com.example.sms.models.HomeSeciliMsgModel;
import com.example.sms.models.MsgGonderModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    public static final String TAG = "--HOME--";
    ArrayList<MsgGonderModel> msgGonder;
    MsgGonderAdapter msgGonderAdapter;
    RecyclerView msgGonderRv;
    ArrayList<HomeSeciliMsgModel> homeSeciliMsgModel;
    HomeSeciliMsgAdapter homeSeciliMsgAdapter;
    RecyclerView homeSeciliMsgRv;


    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        msgGonderRv = root.findViewById(R.id.rv_msg_gonder_secili_grup);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(RecyclerView.HORIZONTAL);
        llm.setReverseLayout(false);
        msgGonderRv.setLayoutManager(llm);

        msgGonder = new ArrayList<>();
        msgGonderAdapter = new MsgGonderAdapter(getActivity(), msgGonder);
        msgGonderRv.setAdapter(msgGonderAdapter);

        homeSeciliMsgRv = root.findViewById(R.id.rv_msg_gonder_secili_msg);
        homeSeciliMsgRv.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        homeSeciliMsgModel = new ArrayList<>();
        homeSeciliMsgAdapter = new HomeSeciliMsgAdapter(getActivity(), homeSeciliMsgModel);
        homeSeciliMsgRv.setAdapter(homeSeciliMsgAdapter);


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
                                MsgGonderModel mgm = doc.toObject(MsgGonderModel.class);
                                msgGonder.add(mgm);
                                msgGonderAdapter.notifyDataSetChanged();
                            }
                        }
                        else{
                            Log.d(TAG, "Veri YOK!");
                        }
                    }
                });
        db.collection("homeSeciliMsg")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isComplete()) {
                            for(QueryDocumentSnapshot doc: task.getResult()) {
                                Log.d(TAG, doc.getData().toString());
                                HomeSeciliMsgModel sm = doc.toObject(HomeSeciliMsgModel.class);
                                homeSeciliMsgModel.add(sm);
                                homeSeciliMsgAdapter.notifyDataSetChanged();
                            }
                        }
                        else{
                            Log.d(TAG, "Veri YOK!");
                        }
                    }
                });
    }

}