package com.example.suivi_eleve.ui.Observations;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ObservationsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ObservationsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Partie Observations");
    }

    public LiveData<String> getText() {
        return mText;
    }
}