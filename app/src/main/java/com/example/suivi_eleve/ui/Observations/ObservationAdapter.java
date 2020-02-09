package com.example.suivi_eleve.ui.Observations;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.suivi_eleve.EleveAdapater;
import com.example.suivi_eleve.R;
import com.example.suivi_eleve.Rappel.Eleve;
import com.example.suivi_eleve.Rappel.RecupAbsent;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ObservationAdapter extends RecyclerView.Adapter<ObservationAdapter.ObservationVH> {


    List<Observation> rappelList;
    DatabaseReference mReference;
    List<RecupAbsent> recupAbsents;

Context context;
    public ObservationAdapter(List<Observation> rappelList, Context context) {
        this.context = context;
        this.rappelList = rappelList;
        mReference = FirebaseDatabase.getInstance().getReference();
        mReference.keepSynced(true);
        recupAbsents = new ArrayList<>();
        Log.d("IdRecup", "Eleve List  Adapter  1   " + rappelList);
    }

    @NonNull
    @Override
    public ObservationAdapter.ObservationVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_observation, parent, false);
        return new ObservationAdapter.ObservationVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ObservationAdapter.ObservationVH holder, final int position) {

        Observation observation = rappelList.get(position);
        Log.d("IdRecup", "Eleve List  String     " +  rappelList);

        holder.nomContent.setText(observation.getNomEleve());
        holder.typeContent.setText(observation.getTypeContent());
        holder.dateContent.setText(observation.getDate());
        holder.noteContent.setText(observation.getNoteContent());

    }

    @Override
    public int getItemCount() {
        return rappelList.size();
    }

    class ObservationVH extends RecyclerView.ViewHolder {

        TextView nomContent,typeContent,dateContent,noteContent;

        DatabaseReference ref;
        DatabaseReference count;

        public ObservationVH(@NonNull final View itemView) {
            super(itemView);

            this.nomContent = itemView.findViewById(R.id.nomcontent);
            this.typeContent = itemView.findViewById(R.id.typecontent);
            this.dateContent = itemView.findViewById(R.id.datecontent);
            this.noteContent = itemView.findViewById(R.id.notecontent);

        }

    }
}
