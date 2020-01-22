package com.example.suivi_eleve;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /** Called when the user taps the Enseignant button */
    public void toEnseingant (View view) {
        // Do something in response to button
        Intent intentEnseignant = new Intent(this, EnseignantDashbordActivity.class);
        startActivity(intentEnseignant);
    }

    /** Called when the user taps the Parent button */
    public void toParent (View view) {
        // Do something in response to button
        Intent intentParent = new Intent(this, ParentAcceuilActivity.class);
        startActivity(intentParent);
    }

}
