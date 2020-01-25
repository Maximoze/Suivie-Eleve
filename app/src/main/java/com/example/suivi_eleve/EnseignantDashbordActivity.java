package com.example.suivi_eleve;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class EnseignantDashbordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enseignant_dashbord);

        Toolbar toolbar = (Toolbar)  findViewById(R.id.toolbar);


        /** textView Tableau de bord*/
        TextView TableauDeBord = (TextView) findViewById(R.id.textView)  ;

        /** bouton publier*/
        Button publier = (Button) findViewById(R.id.button);


        /** Image activite a faire*/
        ImageButton activite = (ImageButton) findViewById(R.id.activite);

        /** Observation a faire*/
        ImageButton observation = (ImageButton) findViewById(R.id.observation);

        /** Evenement a faire*/
        ImageButton evenement = (ImageButton) findViewById(R.id.evenement);

        /** Rappel a faire*/
        ImageButton rappel = (ImageButton) findViewById(R.id.rappel);

        activite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  Intentactivite =  new Intent(EnseignantDashbordActivity.this, ActivitesActivity.class);
                startActivity(Intentactivite);
            }

        });

        rappel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  Intentactivite =  new Intent(EnseignantDashbordActivity.this, RappelActivity.class);
                startActivity(Intentactivite);
            }

        });

        evenement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  Intentactivite =  new Intent(EnseignantDashbordActivity.this, EvenementActivity.class);
                startActivity(Intentactivite);
            }

        });

        observation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  Intentactivite =  new Intent(EnseignantDashbordActivity.this, ObservationActivity.class);
                startActivity(Intentactivite);
            }

        });
    }
}
