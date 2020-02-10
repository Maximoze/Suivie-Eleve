package com.example.suivi_eleve.ObservationsEnseignant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.suivi_eleve.ActivitesActivity;
import com.example.suivi_eleve.R;
import com.example.suivi_eleve.Rappel.Eleve;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AjoutObservationsActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    Button eleveSpinner;
    EditText typeObservation;
    AutoCompleteTextView noteObservation;
    TextView eleve_text_view;
    long classe;
    int countEleves;
    List<Eleve> eleveList;
    List<Recup> arrayList;
    Button ButtonValider;

    DatabaseReference ref;
    DatabaseReference count;
    DatabaseReference countElevesRef;
    private FirebaseAuth mAuth;
    FirebaseUser user;
    int eleveSelected;
    int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_observations);

        eleveSpinner = findViewById(R.id.eleveButton);
        typeObservation = findViewById(R.id.typeObservationEditText);
        noteObservation = findViewById(R.id.noteEdit_Text);
        eleve_text_view = findViewById(R.id.eleve_text_view);
        ButtonValider = findViewById(R.id.buttonValider);


        mAuth = FirebaseAuth.getInstance();
        eleveList = new ArrayList<>();
        arrayList = new ArrayList<>();

        recupEleve();

        ButtonValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(AjoutObservationsActivity.this, "Selected Item: " +arrayList.get(eleveSelected).getId(), Toast.LENGTH_SHORT).show();
                count = FirebaseDatabase.getInstance().getReference();

                String eleveselect = arrayList.get(eleveSelected).getId();

                Date c = Calendar.getInstance().getTime();

                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
                String formattedDate = df.format(c);

                String typeContent = typeObservation.getText().toString();
                String noteContent = noteObservation.getText().toString();
                String nomEleve = arrayList.get(eleveSelected).getNom();
                Observation observation = new Observation(typeContent,noteContent,nomEleve,formattedDate);

                count.child("Observations").child(eleveselect).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        counter = (int) dataSnapshot.getChildrenCount();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(AjoutObservationsActivity.this, "NO data", Toast.LENGTH_LONG).show();
                    }
                });

                ref.child("Observations").child(eleveselect).child(""+counter).setValue(observation);
                Toast.makeText(AjoutObservationsActivity.this, "Observation envoyer", Toast.LENGTH_LONG).show();

            }
        });

    }


    public void eleveButton(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.popup_eleve, popup.getMenu());
        popup.setOnMenuItemClickListener(this);
        for (int i = 0; i < arrayList.size(); i++) {
            popup.getMenu().add(Menu.NONE, i, Menu.NONE, arrayList.get(i).getNom());
        }
        popup.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        eleveSpinner.setText(item.getTitle());
        eleveSelected = item.getItemId();
        return false;
    }

    private void recupEleve() {
        ref = FirebaseDatabase.getInstance().getReference();
        ref.keepSynced(true);
        user = mAuth.getCurrentUser();
        String myUserId = user.getUid();

        ref.child("enseignantClasseAttendees").child(myUserId).child("idClasse").addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                classe = dataSnapshot.getValue(long.class);
                Log.d("Classe", "Value is: " + classe);
                Toast.makeText(AjoutObservationsActivity.this, "Classe Récuperer" + classe, Toast.LENGTH_SHORT);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("RappelParent", "Failed to read value.", error.toException());
                Toast.makeText(AjoutObservationsActivity.this, "Recuperation Id faillure", Toast.LENGTH_SHORT);
            }
        });


        countElevesRef = FirebaseDatabase.getInstance().getReference().child("eleves");
        countElevesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()) {
                    countEleves = (int) dataSnapshot.getChildrenCount();
                    Log.d("Count", "" + countEleves);

                    for (int i = 1; i <= countEleves; i++) {
                        ref.child("eleves").child("" + i).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if (dataSnapshot.hasChildren()) {
                                    Eleve eleve = dataSnapshot.getValue(Eleve.class);
                                    if (eleve.getClasse() == classe) {
                                        eleveList.add(eleve);
                                        arrayList.add(new Recup("" + eleve.getnom(), "" + eleve.getId()));
                                    }
                                    Log.d("Eleve", "" + eleveList);
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });


                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(AjoutObservationsActivity.this, "NO data", Toast.LENGTH_LONG).show();
            }
        });

    }


}
