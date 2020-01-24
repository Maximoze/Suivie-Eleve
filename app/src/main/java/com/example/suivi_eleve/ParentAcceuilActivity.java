package com.example.suivi_eleve;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class ParentAcceuilActivity extends AppCompatActivity /*implements NavigationView.OnNavigationItemSelectedListener*/{

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_acceuil);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        /*AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_activites, R.id.navigation_observations, R.id.navigation_evennements,R.id.navigation_chat)
                .build();*/
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        //NavigationUI.setupActionBarWithNavController(this, navController);

        NavigationUI.setupWithNavController(navView, navController);

        /*drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigationView);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);*/


    }

    /*@Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.eleve_1:
                Toast.makeText(this,"Eleve 1 Selectionné", Toast.LENGTH_SHORT).show();
                break;
            case R.id.eleve_2:
                Toast.makeText(this,"Eleve 2 Selectionné", Toast.LENGTH_SHORT).show();
                break;
        }
        return false;*/

}
