package com.example.suivi_eleve;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.suivi_eleve.Rappel.Eleve;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.List;

public class ActivitesActivity extends AppCompatActivity {
    int countActivites;
    private static final int IMAGE_PICK_CODE = 1000;
    private static final int PERMISSION_CODE = 1001;

    ImageView imageView;
    ImageButton imageButton;
    EditText libelle_activite;
    EditText type_activite;
    EditText date_activite;
    DatabaseReference activiteRef;
    DatabaseReference countActivitesRef;
    StorageReference storageReference;
    Button validateButton;

    FirebaseAuth mAuth;
    String libelle, type, date;
    Uri imageUri;
    long value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activite);

        imageView = findViewById(R.id.imageadded);
        imageButton = findViewById(R.id.addImageButton);
        libelle_activite = findViewById(R.id.edit_text_ajout_libelle);
        type_activite = findViewById(R.id.typeEditText);
        date_activite = findViewById(R.id.dateEditText);
        validateButton = findViewById(R.id.buttonValider);

        activiteRef = FirebaseDatabase.getInstance().getReference();
        countActivitesRef = FirebaseDatabase.getInstance().getReference().child("activites");
        storageReference = FirebaseStorage.getInstance().getReference("Images");

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                        //Permission refusé, le demander
                        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                        requestPermissions(permissions, PERMISSION_CODE);
                    } else {
                        //permission accordé
                        pickImageFromGallery();
                    }
                } else {
                    pickImageFromGallery();
                }
            }
        });

        countActivitesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                countActivites = (int) dataSnapshot.getChildrenCount();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ActivitesActivity.this, "NO data", Toast.LENGTH_LONG).show();
            }
        });


        validateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            recupClasse(new FirebaseCallBack2() {
                @Override
                public void onCallBack(long value) {
                    writeNewActivity(value);
                }
            });



            }
        });

    }

    private void writeNewActivity(final long valeur) {

        libelle = libelle_activite.getText().toString();
        type = type_activite.getText().toString();
        date = date_activite.getText().toString();

        final StorageReference ref = storageReference.child(System.currentTimeMillis() + "." + getExtension(imageUri));

        ref.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {


                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {

                                String imageUrl = String.valueOf(uri);

                                Log.d("New"," Acivites"+countActivites+"  "+valeur);
                                ActiviteColoneContent activiteColoneContent = new ActiviteColoneContent(libelle, type, date, imageUrl, valeur);
                                activiteRef.child("activites").child("" + countActivites++).setValue(activiteColoneContent);

                                Toast.makeText(ActivitesActivity.this, "Image upload successfull", Toast.LENGTH_LONG).show();

                            }
                        });

                    }
                })

                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        Toast.makeText(ActivitesActivity.this, "Image upload faillure", Toast.LENGTH_LONG).show();
                    }
                });

        Toast.makeText(ActivitesActivity.this, "Activites Added", Toast.LENGTH_LONG).show();
    }


    public void recupClasse(final FirebaseCallBack2 firebaseCallBack2) {
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        String uid = user.getUid();

        activiteRef.child("enseignantClasseAttendees").child(uid).child("idClasse").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                value = dataSnapshot.getValue(long.class);

                firebaseCallBack2.onCallBack(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private interface FirebaseCallBack2 {
        void onCallBack(long value);
    }

    private void pickImageFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PICK_CODE);
    }

    String getExtension(Uri uri) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    pickImageFromGallery();
                } else {
                    Toast.makeText(this, "Permission non accordée.....!", Toast.LENGTH_SHORT).show();
                }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            imageView.setBackground(null);
            imageView.setImageURI(data.getData());
            imageUri = data.getData();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


}
