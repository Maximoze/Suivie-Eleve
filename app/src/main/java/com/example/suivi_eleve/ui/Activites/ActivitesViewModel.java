package com.example.suivi_eleve.ui.Activites;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ActivitesViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ActivitesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Activites fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}