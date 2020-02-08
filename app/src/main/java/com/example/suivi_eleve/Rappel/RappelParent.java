package com.example.suivi_eleve.Rappel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.suivi_eleve.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RappelParent extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rappel);

        recyclerView = findViewById(R.id.recyclerView);
        mAuth = FirebaseAuth.getInstance();

        initData();
        initRecyclerView();
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
        //Query myTopPostsQuery = ref.child("Enseignant_classes_attendees").child(myUserId).child("id_classes");

        //Log.d("RappelParent",myUserId);

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
                            Log.d("List", "Eleve Recuperer" + rappel);
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


}