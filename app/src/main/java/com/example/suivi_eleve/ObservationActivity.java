package com.example.suivi_eleve;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class ObservationActivity extends AppCompatActivity {

    TextView eleve;
    TextView type0bservation;
    TextView note;
    EditText contenue_eleve;
    EditText contenue_type;
    EditText contenue_note;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observation);

        eleve = findViewById(R.id.eleve);
        type0bservation = findViewById(R.id.typeO);
        note = findViewById(R.id.note);

        contenue_eleve = findViewById(R.id.contenu_eleve);
        contenue_type = findViewById(R.id.contenu_type);
        contenue_note = findViewById(R.id.contenu_note);

    }
}
