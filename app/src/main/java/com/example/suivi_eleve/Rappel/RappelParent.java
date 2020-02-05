package com.example.suivi_eleve.Rappel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.bignerdranch.expandablerecyclerview.Model.ParentObject;
import com.example.suivi_eleve.R;
import com.example.suivi_eleve.RappelAdapter.MyAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RappelParent extends AppCompatActivity implements ParentObject {

    private List<Object> mChildrenList;
    private UUID _id;
    private String title;
    RecyclerView recyclerView;

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        ((MyAdapter)recyclerView.getAdapter()).onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rappel);

        recyclerView = findViewById(R.id.rappelRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager((this)));

        MyAdapter adapter = new MyAdapter(this,initData());
        adapter.setParentClickableViewAnimationDefaultDuration();
        adapter.setParentAndIconExpandOnClick(true);

        recyclerView.setAdapter(adapter);
    }

    private  List<ParentObject>initData(){
        RappelCreator rappelCreator = RappelCreator.get(this);
        List<RappelParent> rappelParents = rappelCreator.getAll();
        List<ParentObject> parentObjects = new ArrayList<>();
        for (RappelParent rappelParents1 : rappelParents){
            List<Object> childList = new ArrayList<>();
            childList.add(new RappelChild("Present","Absent"));
            rappelParents1.setChildObjectList(childList);
            parentObjects.add(rappelParents1);
        }
        return parentObjects;
    }


    @Override
    public List<Object> getChildObjectList() {
        return mChildrenList;
    }

    @Override
    public void setChildObjectList(List<Object> list) {

        mChildrenList = list;
    }

    public RappelParent(String title){
        this.title = title;
        _id = UUID.randomUUID();
    }

    public UUID get_id() {
        return _id;
    }

    public void set_id(UUID _id) {
        this._id = _id;
    }

    public String getTittle(){
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
