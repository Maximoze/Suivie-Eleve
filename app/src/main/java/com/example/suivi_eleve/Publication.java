package com.example.suivi_eleve;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Publication extends AppCompatActivity {

    TextView teste;
    ImageButton ajout;
    TextView description;
  Button valide;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.publication_enseignant);

        teste = findViewById(R.id.ajoutImage_publication);
        ajout = findViewById(R.id.addImagePublication);
       description = findViewById(R.id.description);
       valide = findViewById(R.id.buttonValider);



        valide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Intentactivite =  new Intent(Publication.this, PublicationParent.class);
                startActivity(Intentactivite);
            }

        });

    }
}