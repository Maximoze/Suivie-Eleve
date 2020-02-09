package com.example.suivi_eleve.ui.Observations;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.suivi_eleve.R;
import com.example.suivi_eleve.ui.Activites.Activies_Holder;
import com.example.suivi_eleve.ui.Activites.Activite;
import com.example.suivi_eleve.ui.Activites.ActivitesFragment;
import com.example.suivi_eleve.ui.Activites.ActivityAdapter;
import com.example.suivi_eleve.ui.Activites.ListEl;
import com.example.suivi_eleve.ui.Activites.Model_Activites;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ObservationsFragment extends Fragment {

    private ObservationsViewModel observationsViewModel;

    RecyclerView mRecyclerView;
    DatabaseReference mReference;

    FirebaseAuth mAuth;
    List<String> idEleves;
    List<Activite> eleveActivite;
    ListEl listA;
    Activite eleve;
    Observation observation;
    List<Observation> observationsActivites;
    List<String> added;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        observationsViewModel =
                ViewModelProviders.of(this).get(ObservationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_observations, container, false);

        mRecyclerView = root.findViewById(R.id.observationRecyclerView);

        mReference = FirebaseDatabase.getInstance().getReference().child("Observations").child("1");
        mReference.keepSynced(true);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        idEleves = new ArrayList<>();
        eleveActivite = new ArrayList<>();
        observationsActivites = new ArrayList<>();
        added = new ArrayList<>();
        mReference = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        readList(new FirebaseCallBack3() {
            @Override
            public void onCallBack(List<Observation> list2) {
                initRecyclerView();
            }
        });

        return root;
    }

    private void initRecyclerView() {
        ObservationAdapter observationAdapter = new ObservationAdapter(observationsActivites, getActivity());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(observationAdapter);
    }

    public void readList(final FirebaseCallBack3 firebaseCallBack) {
        FirebaseUser user = mAuth.getCurrentUser();
        //activites.clear();
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

                            for (int i=1;i<=eleveActivite.size();i++){
                                mReference.child("Observations").child(""+i).addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                        for (DataSnapshot child : dataSnapshot.getChildren()) {
                                            observation = child.getValue(Observation.class);

                                                //Log.d("IdRecup", "Fragemnt  size" +observation.getClasse());
                                                //if ((observation.getClasse() == eleveActivite.get(i).getClasse()) & (!activites.contains(model_activites.getClasse()))) {
                                                    //Log.d("IdRecup", "Fragemnt  " + model_activites.getLibelle()+""+ model_activites.getType()+""+ model_activites.getDate()+""+model_activites.getimageUrl());
                                                    observationsActivites.add(new Observation(observation.getDate(), observation.getNomEleve(), observation.getNoteContent(),observation.getTypeContent()));
                                                //}

                                        }

                                        Log.d("IdRecup", "Fragemnt  " + observationsActivites);
                                        firebaseCallBack.onCallBack(observationsActivites);
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });
                            }
                            //Log.d("IdRecup", "Fragemnt  size" + eleveActivite.size());


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


    private interface FirebaseCallBack3 {
        void onCallBack(List<Observation> list3);
    }

    /*@Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Observation, Observation_Holder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Observation, Observation_Holder>
                (Observation.class, R.layout.row_observation, Observation_Holder.class, mReference) {
            @Override
            protected void populateViewHolder(Observation_Holder observation_holder, Observation observation, int i) {

                observation_holder.nomContent.setText(observation.getNomEleve());
                observation_holder.typeContent.setText(observation.getTypeContent());
                observation_holder.dateContent.setText(observation.getDate());
                observation_holder.noteContent.setText(observation.getNoteContent());
            }
        };

        mRecyclerView.setAdapter(firebaseRecyclerAdapter);
    }*/


}