package com.example.suivi_eleve.ui.Photos_Evenements;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import com.example.suivi_eleve.R;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.suivi_eleve.R;
import com.squareup.picasso.Picasso;

public class Photo_Holder extends RecyclerView.ViewHolder {

    TextView descritionPublication;
    ImageView imageViewPublication;

    public Photo_Holder(@NonNull View itemView) {
        super(itemView);


        this.descritionPublication = itemView.findViewById(R.id.descriptionContent);

        this.imageViewPublication = itemView.findViewById(R.id.imagePublication);


    }

}

