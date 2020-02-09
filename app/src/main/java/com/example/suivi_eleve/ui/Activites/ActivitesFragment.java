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

import com.example.suivi_eleve.ListE;
import com.example.suivi_eleve.ParentAcceuilActivity;
import com.example.suivi_eleve.R;
import com.example.suivi_eleve.Rappel.Eleve;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ActivitesFragment extends Fragment {

    private ActivitesViewModel activitesViewModel;

    RecyclerView mRecyclerView;
    DatabaseReference mReference;
    FirebaseAuth mAuth;
    List<String> idEleves;
    List<Eleve> eleveLists;
    ListE listE;
    Eleve eleve;
    Model_Activites model_activites;
    List<String> classe;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        activitesViewModel =
                ViewModelProviders.of(this).get(ActivitesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_activites, container, false);
        mRecyclerView = root.findViewById(R.id.recyclerView);

        mReference = FirebaseDatabase.getInstance().getReference().child("activites").child("1");
        mReference.keepSynced(true);

        idEleves = new ArrayList<>();
        eleveLists = new ArrayList<>();
        classe = new ArrayList<>();
        mReference = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

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

    public void readList(final ActivitesFragment.FirebaseCallBack firebaseCallBack) {
        FirebaseUser user = mAuth.getCurrentUser();

        mReference = FirebaseDatabase.getInstance().getReference();
        mReference.child("parentEleveAttendees").child("" + user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()) {
                    for (DataSnapshot child : dataSnapshot.getChildren()) {
                        listE = child.getValue(ListE.class);
                        idEleves.add(listE.getId());
                        Log.d("IdRecup", "Value is: " + listE.getId());
                    }

                    mReference.child("eleves").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for (DataSnapshot child : dataSnapshot.getChildren()) {
                                eleve = child.getValue(Eleve.class);
                                for (int i = 0; i < idEleves.size(); i++) {
                                    if (eleve.getId().equals(idEleves.get(i))) {
                                        eleveLists.add(new Eleve(eleve.getClasse(), eleve.getId(), eleve.getnom()));
                                    }
                                }

                            }

                            mReference.child("activites").addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    for (DataSnapshot child : dataSnapshot.getChildren()) {
                                        model_activites = child.getValue(Model_Activites.class);
                                        for (int i = 0; i < eleveLists.size(); i++) {
                                            if (model_activites.getClass().equals(idEleves.get(i))) {
                                                eleveLists.add(new Eleve(eleve.getClasse(), eleve.getId(), eleve.getnom()));
                                            }
                                        }
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                            firebaseCallBack.onCallBack(eleveLists);
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


    private interface FirebaseCallBack {
        void onCallBack(List<Eleve> list);
    }

}
