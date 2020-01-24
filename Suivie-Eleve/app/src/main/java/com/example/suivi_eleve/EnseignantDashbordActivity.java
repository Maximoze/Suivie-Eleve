package com.example.suivi_eleve;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
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
        ImageView activite = (ImageView) findViewById(R.id.activite);

        /** Image activite a faire*/
        ImageView observation = (ImageView) findViewById(R.id.observation);

        /** Image activite a faire*/
        ImageView evenement = (ImageView) findViewById(R.id.evenement);

        /** Image activite a faire*/
        ImageView rappel = (ImageView) findViewById(R.id.rappel);

    }
}
