package com.example.suivi_eleve.ui.Activites;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.suivi_eleve.EleveAdapater;
import com.example.suivi_eleve.R;
import com.example.suivi_eleve.Rappel.Eleve;
import com.example.suivi_eleve.Rappel.RecupAbsent;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ActivityAdapter  extends RecyclerView.Adapter<ActivityAdapter.ActivityVH> {


    List<Model_Activites> rappelList;
    DatabaseReference mReference;
    List<RecupAbsent> recupAbsents;


    Context context;

    public ActivityAdapter(List<Model_Activites> rappelList, Context context) {
        this.rappelList = rappelList;
        mReference = FirebaseDatabase.getInstance().getReference();
        mReference.keepSynced(true);
        this.context = context;
        Log.d("IdRecup", "Eleve List  Adapter  1   " + rappelList);
    }

    @NonNull
    @Override
    public ActivityAdapter.ActivityVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_activites, parent, false);
        return new ActivityAdapter.ActivityVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ActivityAdapter.ActivityVH holder, final int position) {

        Model_Activites model_activites = rappelList.get(position);
        Log.d("IdRecup", "Activyt Adapter     " +  rappelList.get(position).getLibelle());

        Picasso.with(context).load(model_activites.getimageUrl()).into(holder.imageUrl);
        holder.libelle.setText(model_activites.getLibelle());
        holder.type.setText(model_activites.getType());
        holder.date.setText(model_activites.getDate());


    }

    @Override
    public int getItemCount() {
        return rappelList.size();
    }

    class ActivityVH extends RecyclerView.ViewHolder {

        ImageView imageUrl;
        TextView  libelle,type,date;

        DatabaseReference ref;
        DatabaseReference count;

        public ActivityVH(@NonNull final View itemView) {
            super(itemView);

            this.date = itemView.findViewById(R.id.datecontent);
            this.libelle = itemView.findViewById(R.id.libelecontent);
            this.type = itemView.findViewById(R.id.typecontent);
            this.imageUrl = itemView.findViewById(R.id.imageActivite);


        }

    }
}
