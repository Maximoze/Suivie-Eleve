package com.example.suivi_eleve.ui.Observations;

import android.os.Bundle;
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
import com.example.suivi_eleve.ui.Activites.Model_Activites;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ObservationsFragment extends Fragment {

    private ObservationsViewModel observationsViewModel;

    RecyclerView mRecyclerView;
    DatabaseReference mReference;

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

        return root;
    }

    @Override
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
    }

    public void recupEleve(){

    }
}