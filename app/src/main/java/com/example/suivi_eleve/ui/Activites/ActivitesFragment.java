package com.example.suivi_eleve.ui.Activites;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class ActivitesFragment extends Fragment {

    private ActivitesViewModel activitesViewModel;

    RecyclerView mRecyclerView;
    ActivitesAdapter activitesAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        activitesViewModel =
                ViewModelProviders.of(this).get(ActivitesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_activites, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        final TextView libellecontent = root.findViewById(R.id.libelecontent);
        mRecyclerView = root.findViewById(R.id.recyclerView);

        /*activitesViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        activitesAdapter = new ActivitesAdapter(ActivitesFragment.this, getMylist());
        mRecyclerView.setAdapter(activitesAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        return root;
    }

    private ArrayList<Model_Activites> getMylist() {

        ArrayList<Model_Activites> models = new ArrayList<>();

        Model_Activites m = new Model_Activites();
        m.setLibelle("Frist Test");
        m.setDate_limite("16 Mai 1996");
        m.setImageActivites(R.drawable.activite);
        models.add(m);

        m = new Model_Activites();
        m.setLibelle("Frist Test");
        m.setDate_limite("16 Mai 1996");
        m.setImageActivites(R.drawable.activite);
        models.add(m);

        return models;

    }
}
