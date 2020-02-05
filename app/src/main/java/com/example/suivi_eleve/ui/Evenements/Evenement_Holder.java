package com.example.suivi_eleve.ui.Evenements;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.suivi_eleve.R;

public class Evenement_Holder extends RecyclerView.ViewHolder {


    TextView libelle,type,date,description,heure,lieu,cotisation;

    public Evenement_Holder(@NonNull View itemView) {
        super(itemView);


        this.date = itemView.findViewById(R.id.datecontent_ev);

        this.libelle = itemView.findViewById(R.id.nomcontent_ev);
        this.type = itemView.findViewById(R.id.typecontent_ev);
        this.description = itemView.findViewById(R.id.descriptioncontent_ev);

        this.heure = itemView.findViewById(R.id.heurecontent_ev);
        this.lieu = itemView.findViewById(R.id.lieucontent_ev);
        this.cotisation = itemView.findViewById(R.id.cotisationcontent_ev);




    }
}

