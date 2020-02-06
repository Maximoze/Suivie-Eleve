package com.example.suivi_eleve.ProfilEnseignant;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.suivi_eleve.LoginActivity;
import com.example.suivi_eleve.ProfilParent.Model_Parent;
import com.example.suivi_eleve.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfilEnseignantActivity extends AppCompatActivity {

    Button deconnexion;
    TextView nom_ens;

    TextView adresse_ens;
    TextView telephone_ens;
    TextView email_ens;

    DatabaseReference ref;
    FirebaseAuth mAuth;
    Model_Parent enseignant;


    String nom, prenom, adresse, telephone, email, mot_de_passe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_profil_enseignant);

        deconnexion = findViewById(R.id.boutonprofil_ens);
        nom_ens = findViewById(R.id.nomcontent_ens);

        adresse_ens = findViewById(R.id.adresse_content_ens);
        telephone_ens = findViewById(R.id.tel_content_ens);
        email_ens = findViewById(R.id.email_content_ens);


        profil();


        deconnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Intentprofil = new Intent(ProfilEnseignantActivity.this, LoginActivity.class);
                startActivity(Intentprofil);
            }

        });

    }

    private void profil() {

        ref = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        String uid = user.getUid();

        ref.child("Enseignants").child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ;
                //parent = child.getValue(Model_Parent.class);
                nom_ens.setText(dataSnapshot.child("nom").getValue(String.class));
                adresse_ens.setText(dataSnapshot.child("adresse").getValue(String.class));
                email_ens.setText(dataSnapshot.child("email").getValue(String.class));
                telephone_ens.setText("" + dataSnapshot.child("telephone").getValue(long.class));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        ref.keepSynced(true);


    }
}
