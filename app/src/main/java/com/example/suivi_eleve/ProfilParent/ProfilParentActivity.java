package com.example.suivi_eleve.ProfilParent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.suivi_eleve.LoginActivity;
import com.example.suivi_eleve.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfilParentActivity extends AppCompatActivity {

    Button deconnexion;
    TextView nomp;
    TextView adressep;
    TextView telephonep;
    TextView emailp;

    DatabaseReference ref;
    FirebaseAuth mAuth;
    Model_Parent parent;

    String nom, prenom, adresse, telephone, email, mot_de_passe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_parent);



        deconnexion = findViewById(R.id.boutonprofil);
        nomp = findViewById(R.id.nomcontent_ens);
        adressep = findViewById(R.id.adresse_content_ens);
        telephonep = findViewById(R.id.tel_content_ens);
        emailp = findViewById(R.id.email_content_ens);


        profil();

        deconnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Intentprofil = new Intent(ProfilParentActivity.this, LoginActivity.class);
                startActivity(Intentprofil);
            }

        });

    }

    private void profil() {

        ref = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        String uid = user.getUid();

        ref.child("Parent").child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    ;
                    //parent = child.getValue(Model_Parent.class);
                    nomp.setText(dataSnapshot.child("nom").getValue(String.class));
                    adressep.setText(dataSnapshot.child("adresse").getValue(String.class));
                    emailp.setText(dataSnapshot.child("email").getValue(String.class));
                    telephonep.setText(""+dataSnapshot.child("telephone").getValue(long.class));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        ref.keepSynced(true);
    }


}
