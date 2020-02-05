package com.example.suivi_eleve.Rappel;

import android.content.Context;
import android.icu.text.CaseMap;

import java.util.ArrayList;
import java.util.List;

public class RappelCreator {
    static RappelCreator _rappelCreator;
    List<RappelParent> _rappelParents;

    public RappelCreator(Context context) {
        _rappelParents = new ArrayList<>();
        for (int i=1; i<100;i++){
            RappelParent rappelParent = new RappelParent(String.format("Caller id",i));
            _rappelParents.add(rappelParent);
        }
    }

    public static RappelCreator get(Context context){
        if (_rappelCreator == null)
            _rappelCreator = new RappelCreator(context);
        return  _rappelCreator;
    }

    public List<RappelParent> getAll(){
        return _rappelParents;
    }
}
