package com.example.suivi_eleve.ui.Photos_Evenements;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.suivi_eleve.R;
import com.example.suivi_eleve.ui.Activites.ActivitesViewModel;
import com.example.suivi_eleve.ui.Evenements.Evenement_Holder;
import com.example.suivi_eleve.ui.Evenements.Model_Evenement;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;


public class PhotoFragment extends Fragment {

    DatabaseReference mReference;
    RecyclerView mRecyclerView_photos;

    private PhotoViewModel photoViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        photoViewModel =
                ViewModelProviders.of(this).get(PhotoViewModel.class);
        View root = inflater.inflate(R.layout.fragment_photo, container, false);

        mRecyclerView_photos = root.findViewById(R.id.photoRecyclerView);

        mReference = FirebaseDatabase.getInstance().getReference().child("Photos");
        mReference.keepSynced(true);

        mRecyclerView_photos.setHasFixedSize(true);
        mRecyclerView_photos.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView_photos.setItemAnimator(new DefaultItemAnimator());

        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Model_Photo, Photo_Holder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Model_Photo, Photo_Holder>
                (Model_Photo.class,R.layout.row_photos,Photo_Holder.class,mReference) {
            @Override
            protected void populateViewHolder(Photo_Holder photo_holder, Model_Photo model_photo, int i) {
                photo_holder.descritionPublication.setText(model_photo.getDescritption());
                Picasso.with(getActivity()).load(model_photo.getImageUrl()).into(photo_holder.imageViewPublication);
            }

        };

        mRecyclerView_photos.setAdapter(firebaseRecyclerAdapter);


    }

}
