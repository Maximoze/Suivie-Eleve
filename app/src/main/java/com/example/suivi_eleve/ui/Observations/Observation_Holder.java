package com.example.suivi_eleve.ui.Observations;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.suivi_eleve.R;

public class Observation_Holder extends RecyclerView.ViewHolder {

    TextView nomContent,typeContent,dateContent,noteContent;


    public Observation_Holder(@NonNull View itemView) {
        super(itemView);

        this.nomContent = itemView.findViewById(R.id.nomcontent);
        this.typeContent = itemView.findViewById(R.id.typecontent);
        this.dateContent = itemView.findViewById(R.id.datecontent);
        this.noteContent = itemView.findViewById(R.id.notecontent);
    }
}
