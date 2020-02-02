package com.example.suivi_eleve.ui.Activites;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.suivi_eleve.R;

public class Activies_Holder extends RecyclerView.ViewHolder {

    ImageView imageActivite;
    TextView  libelle,type,date_limite;

    public Activies_Holder(@NonNull View itemView) {
        super(itemView);

        this.imageActivite = itemView.findViewById(R.id.imageActivite);
        this.libelle = itemView.findViewById(R.id.libelecontent);
        this.type = itemView.findViewById(R.id.typecontent);
        this.date_limite = itemView.findViewById(R.id.datecontent);

    }
}
