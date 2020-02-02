package com.example.suivi_eleve;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class EvenementActivity extends AppCompatActivity {

    Button button;
    EditText libelle_evenement;
    EditText type_evenement;
    EditText description_evenement;
    EditText lieu_evenement;
    EditText heur_evenement;
    EditText date_evenement;
    EditText cotisation_evenement;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evenement);

        button = findViewById(R.id.boutonevenement);
        libelle_evenement = findViewById(R.id.libelle);
        type_evenement =  findViewById(R.id.type);
        description_evenement= findViewById(R.id.description);
        lieu_evenement = findViewById(R.id.lieu);
        heur_evenement = findViewById(R.id.heure);
        date_evenement = findViewById(R.id.date);
        cotisation_evenement = findViewById(R.id.cotisation);


        // exemple RappelActivity n'est pas valide
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Intentactivite =  new Intent(EvenementActivity.this, RappelActivity.class);
                startActivity(Intentactivite);
            }
        });










    }
}
