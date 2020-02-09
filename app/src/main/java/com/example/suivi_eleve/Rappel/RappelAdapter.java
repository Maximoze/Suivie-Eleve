package com.example.suivi_eleve.Rappel;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.suivi_eleve.R;
import com.example.suivi_eleve.itemClickListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class RappelAdapter extends RecyclerView.Adapter<RappelAdapter.MovieVH> {

    private static final String TAG = "RappelAdapter";
    List<Rappel> rappelList;
    DatabaseReference mReference;
    List<RecupAbsent> recupAbsents;
    RecupAbsent recupAbsent;
    String formattedDate;
    RappelParent rappelParent;
    //ListE<>

    public RappelAdapter(List<Rappel> rappelList) {
        this.rappelList = rappelList;
        mReference = FirebaseDatabase.getInstance().getReference();
        mReference.keepSynced(true);
        rappelParent = new RappelParent();
        recupAbsents = new ArrayList<>();

        Date c = Calendar.getInstance().getTime();

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        formattedDate = df.format(c);
    }

    @NonNull
    @Override
    public MovieVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rappel_row, parent, false);
        return new MovieVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieVH holder, final int position) {

        final Rappel rappel = rappelList.get(position);
        holder.titleTextView.setText(rappel.getnom());

        boolean isExpanded = rappelList.get(position).isExpanded();
        holder.expandableLayout.setVisibility(isExpanded ? View.VISIBLE : View.GONE);

    }

    @Override
    public int getItemCount() {
        return rappelList.size();
    }

    class MovieVH extends RecyclerView.ViewHolder {

        itemClickListener itemClickListener;

        private static final String TAG = "MovieVH";

        ConstraintLayout expandableLayout;
        TextView titleTextView;
        Button presentButton, absentButton;

        DatabaseReference ref;
        DatabaseReference count;
        int counter;
        List<RecupAbsent> recupAbsents;
        List<Absent> absents;

        public void setItemClickListener(com.example.suivi_eleve.itemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        public MovieVH(@NonNull final View itemView) {
            super(itemView);

            titleTextView = itemView.findViewById(R.id.eleveTextView);
            presentButton = itemView.findViewById(R.id.presentButton);
            absentButton = itemView.findViewById(R.id.absentButton);
            expandableLayout = itemView.findViewById(R.id.expandableLayout);
            this.recupAbsents = new ArrayList<>();
            this.absents = new ArrayList<>();

            titleTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Rappel rappel = rappelList.get(getAdapterPosition());
                    rappel.setExpanded(!rappel.isExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });

            presentButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    presentButton.setBackgroundColor(itemView.getResources().getColor(R.color.colorPrimaryDark));
                    absentButton.setBackgroundColor(itemView.getResources().getColor(R.color.colorWhite));
                }
            });

            absentButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Rappel rappel = rappelList.get(getAdapterPosition());

                    Log.d("RecupAbsent", "" + rappel.getnom() + " " + rappel.getId());

                    recupAbsent = new RecupAbsent(rappel.getId(), rappel.getnom());

                    Log.d("Recup Absent", " Ajouter    " + recupAbsent);

                    //Log.d("Recup Absent",""+recupAbsents);
                    count = FirebaseDatabase.getInstance().getReference();

                    Date c = Calendar.getInstance().getTime();

                    SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
                    String formattedDate = df.format(c);

                    count.child("Observations").child("" + recupAbsent.getId()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            counter = (int) dataSnapshot.getChildrenCount();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                            Log.w(TAG, "Failed to read value.", databaseError.toException());

                        }
                    });

                    ref = FirebaseDatabase.getInstance().getReference();
                    ref.child("Observations").child("" + recupAbsent.getId()).child("" + counter).setValue(
                            new Absent("Absences", "Votre enfant est absent", recupAbsent.getNom(), formattedDate));

                    absentButton.setBackgroundColor(itemView.getResources().getColor(R.color.colorAccent));
                    presentButton.setBackgroundColor(itemView.getResources().getColor(R.color.colorWhite));

                }

            });
        }
    }
}
