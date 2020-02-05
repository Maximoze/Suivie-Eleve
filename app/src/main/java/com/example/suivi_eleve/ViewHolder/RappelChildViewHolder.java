package com.example.suivi_eleve.ViewHolder;

import android.view.View;
import android.widget.Button;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;
import com.example.suivi_eleve.R;

public class RappelChildViewHolder extends ChildViewHolder {
    public Button presentButton;
    public Button absentButton;

    public RappelChildViewHolder(View itemView) {
        super(itemView);
        presentButton = itemView.findViewById(R.id.presentButton);
        absentButton = itemView.findViewById(R.id.absentButton);
    }
}
