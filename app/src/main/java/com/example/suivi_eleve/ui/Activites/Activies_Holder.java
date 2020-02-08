package com.example.suivi_eleve.ui.Activites;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.suivi_eleve.R;
import com.squareup.picasso.Picasso;

public class Activies_Holder extends RecyclerView.ViewHolder {

    ImageView imageUrl;
    TextView  libelle,type,date;

    public Activies_Holder(@NonNull View itemView) {
        super(itemView);
        this.date = itemView.findViewById(R.id.datecontent);
        this.libelle = itemView.findViewById(R.id.libelecontent);
        this.type = itemView.findViewById(R.id.typecontent);
        this.imageUrl = itemView.findViewById(R.id.imageActivite);

    }

    public void setImagage(FragmentActivity ctx, String image){

        Picasso.with(ctx).load(image).into(imageUrl);
    }
}
