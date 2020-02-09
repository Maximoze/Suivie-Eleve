package com.example.suivi_eleve.Rappel;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.suivi_eleve.R;

public class Rappel_Holder extends RecyclerView.ViewHolder {

    TextView titleTextView;
    Button presentButton, absentButton;
    ConstraintLayout expandableLayout;

    public Rappel_Holder(@NonNull View itemView) {
        super(itemView);

        this.titleTextView = itemView.findViewById(R.id.eleveTextView);
        this.presentButton = itemView.findViewById(R.id.presentButton);
        this.absentButton = itemView.findViewById(R.id.absentButton);
        this.expandableLayout = itemView.findViewById(R.id.expandableLayout);

        /*titleTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Rappel rappel = rappelList.get(getAdapterPosition());
                rappel.setExpanded(!rappel.isExpanded());
                notifyItemChanged(getAdapterPosition());
                Log.d("Click", ""+ titleTextView.getText());
            }
        });*/

    }


}
