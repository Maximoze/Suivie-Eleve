package com.example.suivi_eleve.ui.Evenements;

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
import com.squareup.picasso.Picasso;

public class EvenementsFragment extends Fragment {

    private EvenementsViewModel evenementsViewModel;
    DatabaseReference mReference;
    RecyclerView mRecyclerView_evenement;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

<<<<<<< HEAD
        evenementsViewModel =
                ViewModelProviders.of(this).get(EvenementsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_evenements, container, false);

        mRecyclerView_evenement = root.findViewById(R.id.recyclerView_evenement);

        mReference = FirebaseDatabase.getInstance().getReference().child("evennementscolaire").child("1");
        mReference.keepSynced(true);

        mRecyclerView_evenement.setHasFixedSize(true);
        mRecyclerView_evenement.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView_evenement.setItemAnimator(new DefaultItemAnimator());

        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Model_Evenement,Evenement_Holder>firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Model_Evenement, Evenement_Holder>
                (Model_Evenement.class,R.layout.row_evenement,Evenement_Holder.class,mReference) {
            @Override
            protected void populateViewHolder(Evenement_Holder evenement_holder, Model_Evenement model_evenement, int i) {
                evenement_holder.date.setText(model_evenement.getHeure());
                evenement_holder.libelle.setText(model_evenement.getLibelle());
                evenement_holder.type.setText(model_evenement.getType());
                evenement_holder.description.setText(model_evenement.getDate());
                evenement_holder.cotisation.setText(model_evenement.getCotisation());
                evenement_holder.lieu.setText(model_evenement.getLieu());
                evenement_holder.heure.setText(model_evenement.getDescription());
            }
        };


        mRecyclerView_evenement.setAdapter(firebaseRecyclerAdapter);

=======
        View root = inflater.inflate(R.layout.fragment_evenements, container, false);

        return root;
>>>>>>> 2596ded458dbc5d1f911c93c3ece828bd0430443
    }

}