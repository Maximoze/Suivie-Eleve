package com.example.suivi_eleve;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;


import com.example.suivi_eleve.Rappel.Eleve;
import com.example.suivi_eleve.Rappel.Rappel;
import com.example.suivi_eleve.Rappel.RecupAbsent;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import java.util.List;

public class EleveAdapater extends RecyclerView.Adapter<EleveAdapater.EleveVH> {


    List<Eleve> rappelList;
    DatabaseReference mReference;
    List<RecupAbsent> recupAbsents;


    public EleveAdapater(List<Eleve> rappelList) {
        this.rappelList = rappelList;
        mReference = FirebaseDatabase.getInstance().getReference();
        mReference.keepSynced(true);
        recupAbsents = new ArrayList<>();
        Log.d("IdRecup", "Eleve List  Adapter  1   " + rappelList);
    }

    @NonNull
    @Override
    public EleveAdapater.EleveVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_eleve, parent, false);
        return new EleveAdapater.EleveVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final EleveAdapater.EleveVH holder, final int position) {

        Eleve eleve = rappelList.get(position);
        Log.d("IdRecup", "Eleve List  String     " +  rappelList);
        holder.eleve.setText(eleve.getnom());

    }

    @Override
    public int getItemCount() {
        return rappelList.size();
    }

    class EleveVH extends RecyclerView.ViewHolder {

        TextView eleve;

        DatabaseReference ref;
        DatabaseReference count;

        public EleveVH(@NonNull final View itemView) {
            super(itemView);

            eleve = itemView.findViewById(R.id.eleve);

            eleve.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Eleve rappel = rappelList.get(getAdapterPosition());

                    Log.d("Botton click", "Eleve List  String     " +  rappel.getnom()+" "+ rappel.getId());

                }
            });

        }

        public void Retrieved(clickRecup clickRecup){

        }

    }

    private interface clickRecup{
        void retrieve();

    }
}
