package com.example.suivi_eleve;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button connexionButton;
    EditText username;
    EditText password;
    private FirebaseAuth mAuth;
    DatabaseReference ref;
    Button parentButton;
    Button enseignantButton;
    String identite;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.nom_utilisateur_EditText);
        password = findViewById(R.id.mot_de_passe_EditText);
        connexionButton = findViewById(R.id.connexionButton);
        parentButton = findViewById(R.id.parentToggleButton);
        enseignantButton = findViewById(R.id.enseignantToggleButton);

        mAuth = FirebaseAuth.getInstance();

        parentButton.setOnClickListener(this);
        enseignantButton.setOnClickListener(this);

        connexionButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Login();
            }
        });

    }

    private void Login() {
        String utilisateur = username.getText().toString();
        String motpasse = password.getText().toString();
        mAuth.signInWithEmailAndPassword(utilisateur, motpasse)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            ref = FirebaseDatabase.getInstance().getReference();
                            FirebaseUser user = mAuth.getCurrentUser();
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("MainActivity", "Authentification  success");

                            switch (identite) {
                                case "Enseignant": {
                                    ref.child("Users").child("Enseignants").addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                            // This method is called once with the initial value and again
                                            // whenever data at this location is updated.
                                            String value;
                                            boolean verif = false;
                                            for (DataSnapshot child : dataSnapshot.getChildren()) {
                                                // TODO: handle the post
                                                value = child.getValue(String.class);
                                                // Log.i(TAG, child.getKey());
                                                if (value.equals(mAuth.getCurrentUser().getUid())) {
                                                    verif = true;
                                                }
                                            }
                                            if (verif) {
                                                Intent intentEnseignant = new Intent(MainActivity.this, EnseignantDashbordActivity.class);
                                                startActivity(intentEnseignant);
                                            } else {
                                                Toast.makeText(MainActivity.this, "Veuiller vérifier vos informations remplies", Toast.LENGTH_SHORT).show();
                                            }

                                        }

                                        @Override
                                        public void onCancelled(DatabaseError error) {
                                            // Failed to read value
                                            Log.w("Connection", "Failed to read value.", error.toException());
                                        }
                                    });

                                    break;
                                }

                                case "Parent": {
                                    ref.child("Users").child("Parents").addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                            // This method is called once with the initial value and again
                                            // whenever data at this location is updated.
                                            String value;
                                            boolean verif = false;
                                            for (DataSnapshot child : dataSnapshot.getChildren()) {
                                                // TODO: handle the post
                                                value = child.getValue(String.class);
                                                // Log.i(TAG, child.getKey());
                                                if (value.equals(mAuth.getCurrentUser().getUid())) {
                                                    verif = true;
                                                }
                                            }

                                            if (verif) {
                                                Intent intentParent = new Intent(MainActivity.this, ParentAcceuilActivity.class);
                                                startActivity(intentParent);
                                            } else {
                                                Toast.makeText(MainActivity.this, "Veuiller vérifier vos informations remplies", Toast.LENGTH_SHORT).show();
                                            }

                                        }

                                        @Override
                                        public void onCancelled(DatabaseError error) {
                                            // Failed to read value
                                            Log.w("Connection", "Failed to read value.", error.toException());
                                        }
                                    });
                                }

                            }
                            Toast.makeText(MainActivity.this, "Authentication success", Toast.LENGTH_SHORT).show();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("MainActivity", "Echec connexion", task.getException());
                            Toast.makeText(MainActivity.this, "Echec Authentication",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // ...
                    }
                });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.parentToggleButton:
                identite = "Parent";
                parentButton.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                enseignantButton.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                //  Toast.makeText(this,identite,Toast.LENGTH_SHORT).show();
                break;
            case R.id.enseignantToggleButton:
                identite = "Enseignant";
                enseignantButton.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                parentButton.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                //Toast.makeText(this,identite,Toast.LENGTH_SHORT).show();
                break;
        }
    }

/**
 * Called when the user taps the enseignant button
 *//*
    public void toEnseingant(View view) {

        // Do something in response to button
        Intent intentEnseignant = new Intent(this, EnseignantDashbordActivity.class);
        startActivity(intentEnseignant);

    }

    *//**
     * Called when the user taps the Parent button
     *//*
    public void toParent(View view) {
        // Do something in response to button
        Intent intentParent = new Intent(this, ParentAcceuilActivity.class);
        startActivity(intentParent);
    }*/
}
