package com.example.suivi_eleve.ui.Observations;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.suivi_eleve.R;

public class ObservationsFragment extends Fragment {

    private ObservationsViewModel observationsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        observationsViewModel =
                ViewModelProviders.of(this).get(ObservationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_observations, container, false);
        final TextView textView = root.findViewById(R.id.text_dashboard);
        observationsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}