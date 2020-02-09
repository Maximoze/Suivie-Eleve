//package com.example.suivi_eleve.ui.Evenements;
//
//import android.content.Context;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.suivi_eleve.EleveAdapater;
//import com.example.suivi_eleve.R;
//import com.example.suivi_eleve.Rappel.Eleve;
//import com.example.suivi_eleve.Rappel.RecupAbsent;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class EvenementAdapter extends RecyclerView.Adapter<EvenementAdapter.EvenementVH> {
//
//
//    List<Eleve> rappelList;
//    DatabaseReference mReference;
//    List<RecupAbsent> recupAbsents;
//
//    Context context;
//    public EvenementAdapter(List<Eleve> rappelList, Context context) {
//        this.context = context;
//        this.rappelList = rappelList;
//        mReference = FirebaseDatabase.getInstance().getReference();
//        mReference.keepSynced(true);
//        recupAbsents = new ArrayList<>();
//        Log.d("IdRecup", "Eleve List  Adapter  1   " + rappelList);
//    }
//
//    @NonNull
//    @Override
//    public EvenementAdapter.EvenementVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_eleve, parent, false);
//        return new EvenementAdapter.EvenementVH(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull final EvenementAdapter.EvenementVH holder, final int position) {
//
//        Eleve eleve = rappelList.get(position);
//        Log.d("IdRecup", "Eleve List  String     " +  rappelList);
//        holder.date.setText(model_evenement.getHeure());
//        holder.libelle.setText(model_evenement.getLibelle());
//        holder.type.setText(model_evenement.getType());
//        holder.description.setText(model_evenement.getDate());
//        holder.cotisation.setText(model_evenement.getCotisation());
//        holder.lieu.setText(model_evenement.getLieu());
//        holder.heure.setText(model_evenement.getDescription());
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return rappelList.size();
//    }
//
//    class EvenementVH extends RecyclerView.ViewHolder {
//
//        TextView libelle,type,date,description,heure,lieu,cotisation;
//
//        DatabaseReference ref;
//        DatabaseReference count;
//
//        public EvenementVH(@NonNull final View itemView) {
//            super(itemView);
//
//            this.date = itemView.findViewById(R.id.datecontent_ev);
//
//            this.libelle = itemView.findViewById(R.id.nomcontent_ev);
//            this.type = itemView.findViewById(R.id.typecontent_ev);
//            this.description = itemView.findViewById(R.id.descriptioncontent_ev);
//
//            this.heure = itemView.findViewById(R.id.heurecontent_ev);
//            this.lieu = itemView.findViewById(R.id.lieucontent_ev);
//            this.cotisation = itemView.findViewById(R.id.cotisationcontent_ev);
//
//        }
//
//    }
//}
