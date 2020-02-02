package com.example.suivi_eleve.ui.Activites;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.suivi_eleve.R;

import java.util.ArrayList;

public class ActivitesAdapter extends RecyclerView.Adapter<Activies_Holder> {

    ActivitesFragment context;
    ArrayList<Model_Activites> model_activites;

    public ActivitesAdapter(ActivitesFragment context, ArrayList<Model_Activites> model_activites) {
        this.context = context;
        this.model_activites = model_activites;
    }

    @NonNull
    @Override
    public Activies_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row,null);

        return new Activies_Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Activies_Holder holder, int position) {

        holder.libelle.setText(model_activites.get(position).getLibelle());
        holder.type.setText(model_activites.get(position).getType());
        holder.date_limite.setText(model_activites.get(position).getDate_limite());
        holder.imageActivite.setImageResource(model_activites.get(position).getImageActivites());
    }

    @Override
    public int getItemCount() {
        return model_activites.size();
    }
}
