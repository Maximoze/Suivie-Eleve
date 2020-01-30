package com.example.suivi_eleve;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;

public class ActivitesActivity extends AppCompatActivity {

    private static final int IMAGE_PICK_CODE = 1000;
    private static final int PERMISSION_CODE = 1001;

    ImageView imageView;
    ImageButton imageButton;
    EditText libelle_activite;
    EditText type_activite;
    EditText date_activite;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activite);

        imageView = findViewById(R.id.imageadded);
        imageButton = findViewById(R.id.addImageButton);
        libelle_activite = findViewById(R.id.edit_text_ajout_libelle);
        type_activite = findViewById(R.id.typeEditText);
       // date_activite = findViewById(R.id.dateEditText);


        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
                        //Permission refusé, le demander
                        String [] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                        requestPermissions(permissions,PERMISSION_CODE);
                    }else{
                        //permission accordé
                        pickImageFromGallery();
                    }
                }else{
                    pickImageFromGallery();
                }
            }
        });

    }

    private void pickImageFromGallery(){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PICK_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
            switch (requestCode){
                case PERMISSION_CODE:
                    if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                        pickImageFromGallery();
                    }else{
                        Toast.makeText(this, "Permission non accordée.....!",Toast.LENGTH_SHORT).show();
                    }
            }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            imageView.setBackground(null);
            imageView.setImageURI(data.getData());
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
