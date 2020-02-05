package com.example.suivi_eleve.RappelAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentObject;
import com.example.suivi_eleve.R;
import com.example.suivi_eleve.Rappel.RappelChild;
import com.example.suivi_eleve.Rappel.RappelParent;
import com.example.suivi_eleve.ViewHolder.RappelChildViewHolder;
import com.example.suivi_eleve.ViewHolder.RappelParentViewHolder;

import java.util.List;

public class MyAdapter extends ExpandableRecyclerAdapter<RappelParentViewHolder, RappelChildViewHolder>{

    LayoutInflater inflater;

    public MyAdapter(Context context, List<ParentObject> parentItemList) {
        super(context, parentItemList);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RappelParentViewHolder onCreateParentViewHolder(ViewGroup viewGroup) {
        View view = inflater.inflate(R.layout.rappelparent,viewGroup,false);
        return new RappelParentViewHolder(view);
    }

    @Override
    public RappelChildViewHolder onCreateChildViewHolder(ViewGroup viewGroup) {
        View view = inflater.inflate(R.layout.rappel_child,viewGroup,false);
        return new RappelChildViewHolder(view);
    }

    @Override
    public void onBindParentViewHolder(RappelParentViewHolder rappelParentViewHolder, int i, Object o) {
        RappelParent rappelParent = (RappelParent)o;
        rappelParentViewHolder.rappelTexteView.setText(rappelParent.getTittle());
    }

    @Override
    public void onBindChildViewHolder(RappelChildViewHolder rappelChildViewHolder, int i, Object o) {
        RappelChild rappelChild = (RappelChild)o;
        rappelChildViewHolder.presentButton.setText(rappelChild.getPresent());
        rappelChildViewHolder.absentButton.setText(rappelChild.getAbsent());
    }
}
