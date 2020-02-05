package com.example.suivi_eleve;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class EvenementActivity extends AppCompatActivity {

    int countActivites;

    Button button;
    EditText libelle_evenement;
    EditText type_evenement;
    EditText description_evenement;
    EditText lieu_evenement;
    EditText heur_evenement;
    EditText date_evenement;
    EditText cotisation_evenement;

    DatabaseReference activiteRef;
    DatabaseReference countActivitesRef;

    String libelle, type, desc, lieu, heure, date, cotisation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evenement);

        button = findViewById(R.id.boutonevenement);
        libelle_evenement = findViewById(R.id.libelle);
        type_evenement = findViewById(R.id.type);
        description_evenement = findViewById(R.id.description);
        lieu_evenement = findViewById(R.id.lieu);
        heur_evenement = findViewById(R.id.heure);
        date_evenement = findViewById(R.id.date);
        cotisation_evenement = findViewById(R.id.cotisation);

        activiteRef = FirebaseDatabase.getInstance().getReference();
        countActivitesRef = FirebaseDatabase.getInstance().getReference().child("evennementscolaire").child("1");

        // exemple RappelParent n'est pas valide


        countActivitesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                countActivites = (int) dataSnapshot.getChildrenCount();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(EvenementActivity.this, "NO data", Toast.LENGTH_LONG).show();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                writeNewActivity();

            }
        });


    }

    private void writeNewActivity() {
        libelle = libelle_evenement.getText().toString();
        type = type_evenement.getText().toString();
        date = date_evenement.getText().toString();
        desc = description_evenement.getText().toString();
        heure = heur_evenement.getText().toString();
        lieu = lieu_evenement.getText().toString();
        cotisation = cotisation_evenement.getText().toString();

        EvennementColoneContent EvennementColoneContent = new EvennementColoneContent(libelle, type, desc, heure, date, lieu, cotisation);
        activiteRef.child("evennementscolaire").child("1").child("" + countActivites++).setValue(EvennementColoneContent);

        Toast.makeText(EvenementActivity.this, "Evennement Added", Toast.LENGTH_LONG).show();

    }

}
