package com.example.suivi_eleve;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.suivi_eleve.ProfilParent.ProfilParentActivity;
import com.example.suivi_eleve.Rappel.Eleve;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ParentAcceuilActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    NavController navController;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    ListE listE;
    List<String> idEleves;
    List<Eleve> eleveLists;
    RecyclerView eleveList;
    TextView username, mailText;
    List<Info> inf;
    Info info;
    Eleve eleve;

    FirebaseAuth mAuth;

    DatabaseReference mReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_acceuil);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navigationView = findViewById(R.id.navigationView);

        drawer = findViewById(R.id.drawer_layout);

        eleveList = findViewById(R.id.eleveListRecyclerView);
        idEleves = new ArrayList<>();
        eleveLists = new ArrayList<>();
        inf = new ArrayList<>();
        mReference = FirebaseDatabase.getInstance().getReference();
        mReference.keepSynced(true);
        mAuth = FirebaseAuth.getInstance();
        username = findViewById(R.id.username);
        mailText = findViewById(R.id.mailtext);

        toggle = new ActionBarDrawerToggle(this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        NavigationUI.setupWithNavController(navView, navController);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView.setNavigationItemSelectedListener(this);

        profil();
        readList(new FirebaseCallBack() {
            @Override
            public void onCallBack(List<Eleve> list) {
                Log.d("Firebase", "Eleve List     " + list.toString());
                initRecyclerView();
            }
        });

    }

    private void initRecyclerView() {
        EleveAdapater eleveAdapater = new EleveAdapater(eleveLists);
        eleveList.setLayoutManager(new LinearLayoutManager(this));
        eleveList.setAdapter(eleveAdapater);
    }

    public void readList(final FirebaseCallBack firebaseCallBack) {
        FirebaseUser user = mAuth.getCurrentUser();

        mReference = FirebaseDatabase.getInstance().getReference();
        mReference.child("parentEleveAttendees").child("" + user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()) {
                    for (DataSnapshot child : dataSnapshot.getChildren()) {
                        listE = child.getValue(ListE.class);
                        idEleves.add(listE.getId());
                        Log.d("IdRecup", "Value is: " + listE.getId());
                    }

                    mReference.child("eleves").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for (DataSnapshot child : dataSnapshot.getChildren()) {
                                eleve = child.getValue(Eleve.class);
                                for (int i = 0; i < idEleves.size(); i++) {
                                    if (eleve.getId().equals(idEleves.get(i))) {
                                        eleveLists.add(new Eleve(eleve.getClasse(), eleve.getId(), eleve.getnom()));
                                    }
                                }

                            }
                            firebaseCallBack.onCallBack(eleveLists);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    private void profil() {

        mReference = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        String uid = user.getUid();

        mReference.child("Parent").child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                username.setText(dataSnapshot.child("nom").getValue(String.class));
                mailText.setText(dataSnapshot.child("email").getValue(String.class));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        mReference.keepSynced(true);
    }

    private interface FirebaseCallBack {
        void onCallBack(List<Eleve> list);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.profileparents:
                Intent profilIntent = new Intent(ParentAcceuilActivity.this, ProfilParentActivity.class);
                startActivity(profilIntent);
                break;
        }

        return false;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

}
