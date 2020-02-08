package com.example.suivi_eleve.Rappel;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.suivi_eleve.R;

import java.util.List;

public class RappelAdapter extends RecyclerView.Adapter<RappelAdapter.MovieVH> {

    private static final String TAG = "RappelAdapter";
    List<Rappel> rappelList;

    //List<>

    public RappelAdapter(List<Rappel> rappelList) {
        this.rappelList = rappelList;
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

        holder.presentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Click", ""+ holder.presentButton.getText()+" "+position);

            }
        });
        holder.absentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Click", ""+ holder.absentButton.getText()+" "+position);
                Log.d("Absent",""+rappel.getnom()+" "+rappel.getId()+" "+rappel.getClasse());
            }
        });

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

        public void setItemClickListener(com.example.suivi_eleve.Rappel.itemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        public MovieVH(@NonNull final View itemView) {
            super(itemView);

            titleTextView = itemView.findViewById(R.id.eleveTextView);
            presentButton = itemView.findViewById(R.id.presentButton);
            absentButton = itemView.findViewById(R.id.absentButton);
            expandableLayout = itemView.findViewById(R.id.expandableLayout);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClickListener.onClick(view,getAdapterPosition());
                }
            });

            titleTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Rappel rappel = rappelList.get(getAdapterPosition());
                    rappel.setExpanded(!rappel.isExpanded());
                    notifyItemChanged(getAdapterPosition());
                    Log.d("Click", ""+ titleTextView.getText()+rappel.getId());

                }
            });
        }
    }
}
