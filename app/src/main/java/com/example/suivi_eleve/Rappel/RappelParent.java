package com.example.suivi_eleve.Rappel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.suivi_eleve.ObservationsEnseignant.AjoutObservationsActivity;
import com.example.suivi_eleve.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class RappelParent extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth mAuth;
    FirebaseUser user;
    RecyclerView recyclerView;
    DatabaseReference ref;
    List<Rappel> rappelList = new ArrayList();
    String nom[] = new String[10000];
    List<Eleve> list;
    long classe;
    Rappel rappel;
    Eleve eleve;
    int i=0;
    DatabaseReference count;
    int counter;
    List<RecupAbsent> recupAbsents;
    List<Absent> absents;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rappel);

        recyclerView = findViewById(R.id.recyclerView);

        mAuth = FirebaseAuth.getInstance();
        this.recupAbsents = new ArrayList<>();

        initData();
        initRecyclerView();
    }

    public void RecupAbsent(List<RecupAbsent> recup){

        this.recupAbsents = recup;
        Log.d("Recup Absent",""+recupAbsents);
    }

    private void initRecyclerView() {
        RappelAdapter rappelAdapter = new RappelAdapter(rappelList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(rappelAdapter);
    }

    private void initData() {
        ref = FirebaseDatabase.getInstance().getReference();
        ref.keepSynced(true);
        user = mAuth.getCurrentUser();

        list = new ArrayList();
        String myUserId = user.getUid();

        // Read from the database
        ref.child("enseignantClasseAttendees").child(myUserId).child("idClasse").addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                classe = dataSnapshot.getValue(long.class);
                Log.d("Classe", "Value is: " + classe);
                Toast.makeText(RappelParent.this,"Classe Récuperer"+classe,Toast.LENGTH_SHORT);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("RappelParent", "Failed to read value.", error.toException());
                Toast.makeText(RappelParent.this,"Recuperation Id faillure",Toast.LENGTH_SHORT);
            }
        });


        ref.child("eleves").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    if (dataSnapshot.hasChildren()) {
                        // TODO: handle the post
                        rappel = child.getValue(Rappel.class);
                        if (rappel.getClasse() == classe) {
                            rappelList.add(new Rappel(rappel.getnom(), rappel.getId(), rappel.classe));
                            Log.d("ListE", "Eleve Recuperer" + rappel);
                        }
                    }

                    Toast.makeText(RappelParent.this, "Recuperation Eleve effectué", Toast.LENGTH_SHORT);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(RappelParent.this,"Recuperation Eleve faillure",Toast.LENGTH_SHORT);
                Log.w("RappelParent", "Failed to read value.", databaseError.toException());
            }
        });

    }


    @Override
    public void onClick(View view) {

        Log.d("Recup Absent",""+recupAbsents);

        /*Log.d("Recup Absent",""+recupAbsents);
        count = FirebaseDatabase.getInstance().getReference();

        Date c = Calendar.getInstance().getTime();

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c);

        for (int i =0 ; i<recupAbsents.size(); i++){
            absents.add(new Absent("Absences","Votre enfant est absent",recupAbsents.get(i).getNom(),formattedDate));
        }

        for (int i =0 ; i<recupAbsents.size(); i++) {
            count.child("Observations").child(recupAbsents.get(i).getId()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    counter = (int) dataSnapshot.getChildrenCount();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(RappelParent.this, "NO data", Toast.LENGTH_LONG).show();
                }
            });
            Toast.makeText(RappelParent.this, "count"+counter, Toast.LENGTH_LONG).show();
            ref.child("Observations").child(recupAbsents.get(i).getId()).child(""+counter).setValue(absents);
        }*/
    }
}