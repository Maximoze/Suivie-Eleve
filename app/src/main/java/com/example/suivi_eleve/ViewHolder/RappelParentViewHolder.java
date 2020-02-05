package com.example.suivi_eleve.ViewHolder;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder;
import com.example.suivi_eleve.R;

public class RappelParentViewHolder extends ParentViewHolder {
    public TextView rappelTexteView;
    public ImageButton dropDownButton;

    public RappelParentViewHolder(View itemView) {
        super(itemView);

        rappelTexteView = itemView.findViewById(R.id.rappeltextView);
        dropDownButton = itemView.findViewById(R.id.dropDownButton);
    }
}
