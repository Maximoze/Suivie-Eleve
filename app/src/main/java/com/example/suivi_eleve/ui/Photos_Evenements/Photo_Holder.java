package com.example.suivi_eleve.ui.Photos_Evenements;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
<<<<<<< HEAD
import androidx.recyclerview.widget.RecyclerView;

import com.example.suivi_eleve.R;

public class Photo_Holder extends RecyclerView.ViewHolder {

    TextView descritionPublication;
    ImageView imageViewPublication;
=======
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.suivi_eleve.R;
import com.squareup.picasso.Picasso;

public class Photo_Holder extends RecyclerView.ViewHolder {

    ImageView imageUrl;
    TextView description ;
>>>>>>> 7af94fb6a88977f1fe69c99b386bceca1cc84c8c

    public Photo_Holder(@NonNull View itemView) {
        super(itemView);

<<<<<<< HEAD
        this.descritionPublication = itemView.findViewById(R.id.descriptionContent);

        this.imageViewPublication = itemView.findViewById(R.id.imagePublication);

=======
        this.description = itemView.findViewById(R.id.libelecontent);
        this.imageUrl = itemView.findViewById(R.id.imageActivite);
}
    public void setImagage(FragmentActivity ctx, String image){

        Picasso.with(ctx).load(image).into(imageUrl);
>>>>>>> 7af94fb6a88977f1fe69c99b386bceca1cc84c8c
    }
}
