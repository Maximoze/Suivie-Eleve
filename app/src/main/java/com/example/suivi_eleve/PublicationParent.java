package com.example.suivi_eleve;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PublicationParent  extends AppCompatActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.publication_parent);


        TextView des = (TextView) findViewById(R.id.description);
        TextView teste_publication = (TextView) findViewById(R.id.text_publication);
        ImageButton publication = (ImageButton) findViewById(R.id.addImagePublicationParent);
        ImageButton  ajout= (ImageButton) findViewById(R.id.ajout);
    }
}
