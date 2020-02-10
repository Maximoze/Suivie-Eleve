package com.example.suivi_eleve.ui.Activites;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.suivi_eleve.R;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ActivitesFragment extends Fragment {

    private ActivitesViewModel activitesViewModel;

    RecyclerView mRecyclerView;
    DatabaseReference mReference;
    FirebaseAuth mAuth;
    List<String> idEleves;
    List<Activite> eleveActivite;
    ListEl listA;
    Activite eleve;
    Model_Activites model_activites;
    List<Model_Activites> activites;
    List<String> added;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        activitesViewModel =
                ViewModelProviders.of(this).get(ActivitesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_activites, container, false);
        mRecyclerView = root.findViewById(R.id.recyclerView);

        idEleves = new ArrayList<>();
        eleveActivite = new ArrayList<>();
        activites = new ArrayList<>();
        added = new ArrayList<>();
        mReference = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        readList(new FirebaseCallBack2() {
            @Override
            public void onCallBack(List<Model_Activites> list2) {
                initRecyclerView();
            }
        });

        return root;
    }

    private void initRecyclerView() {
        ActivityAdapter activityAdapter = new ActivityAdapter(activites, getActivity());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(activityAdapter);
    }

    public void readList(final ActivitesFragment.FirebaseCallBack2 firebaseCallBack) {
        FirebaseUser user = mAuth.getCurrentUser();
        activites.clear();
        mReference = FirebaseDatabase.getInstance().getReference();
        mReference.child("parentEleveAttendees").child("" + user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()) {
                    for (DataSnapshot child : dataSnapshot.getChildren()) {
                        listA = child.getValue(ListEl.class);
                        idEleves.add(listA.getId());

                    }

                    mReference.child("eleves").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for (DataSnapshot child : dataSnapshot.getChildren()) {
                                eleve = child.getValue(Activite.class);
                                for (int i = 0; i < idEleves.size(); i++) {
                                    if (eleve.getId().equals(idEleves.get(i))) {
                                        eleveActivite.add(new Activite(eleve.getClasse(), eleve.getId(), eleve.getnom()));
                                    }
                                }
                            }

                            mReference.child("activites").addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    for (DataSnapshot child : dataSnapshot.getChildren()) {
                                        boolean test = false;
                                        model_activites = child.getValue(Model_Activites.class);

                                        for (int i = 0; i < eleveActivite.size(); i++) {
                                            if ((model_activites.getClasse() == eleveActivite.get(i).getClasse())) {
                                                test = true;
                                                added.add(eleveActivite.get(i).getId());
                                            }
                                        }

                                        if (test){
                                            Log.d("IdRecup", "Fragemnt  " + model_activites.getLibelle() + "" + model_activites.getType() + "" + model_activites.getDate() + "" + model_activites.getimageUrl());
                                            activites.add(new Model_Activites(model_activites.getLibelle(), model_activites.getType(), model_activites.getDate(), model_activites.getimageUrl(), model_activites.getClasse()));
                                        }

                                    }
                                    Log.d("IdRecup", "Fragemnt  " + activites);
                                    firebaseCallBack.onCallBack(activites);
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }


    private interface FirebaseCallBack2 {
        void onCallBack(List<Model_Activites> list2);
    }

}
