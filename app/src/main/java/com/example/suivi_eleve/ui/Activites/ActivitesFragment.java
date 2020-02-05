package com.example.suivi_eleve.ui.Activites;

import android.os.Bundle;
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
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class ActivitesFragment extends Fragment {

    private ActivitesViewModel activitesViewModel;

    RecyclerView mRecyclerView;
    DatabaseReference mReference;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        activitesViewModel =
                ViewModelProviders.of(this).get(ActivitesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_activites, container, false);
        mRecyclerView = root.findViewById(R.id.recyclerView);

        mReference = FirebaseDatabase.getInstance().getReference().child("activites").child("1");
        mReference.keepSynced(true);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Model_Activites,Activies_Holder>firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Model_Activites, Activies_Holder>
                (Model_Activites.class,R.layout.row_activites,Activies_Holder.class,mReference) {
            @Override
            protected void populateViewHolder(Activies_Holder activies_holder, Model_Activites model_activites, int i) {
                Picasso.with(getActivity()).load(model_activites.getimageUrl()).into(activies_holder.imageUrl);
                activies_holder.libelle.setText(model_activites.getLibelle());
                activies_holder.type.setText(model_activites.getType());
                activies_holder.date.setText(model_activites.getDate());
            }
        };

        mRecyclerView.setAdapter(firebaseRecyclerAdapter);

    }

}
