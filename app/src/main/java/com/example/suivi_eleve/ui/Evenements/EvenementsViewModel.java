package com.example.suivi_eleve.ui.Evenements;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class EvenementsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public EvenementsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Evenements fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}