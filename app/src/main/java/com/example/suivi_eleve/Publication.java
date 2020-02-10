package com.example.suivi_eleve;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Publication extends AppCompatActivity {

    int countActivites;
    private static final int IMAGE_PICK_CODE = 1000;
    private static final int PERMISSION_CODE = 1001;

<<<<<<< HEAD
    ImageView imageViewPublication;
    ImageButton imageButtonPublication;
    EditText descriptionPublication;

    DatabaseReference activiteRef;
    DatabaseReference countActivitesRef;
    StorageReference storageReference;
    Button validateButtonpublication;

    String description;
    Uri imageUri;
=======
    ImageButton ajout;
    ImageView imageView;
    EditText Desc_photo;
    TextView teste;
    TextView desc;
    Button valide;

    DatabaseReference photoRef;
    DatabaseReference countActivitesRef;
    StorageReference storageReference;
>>>>>>> 7af94fb6a88977f1fe69c99b386bceca1cc84c8c

    String description;
    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.publication_enseignant);

<<<<<<< HEAD
        imageViewPublication = findViewById(R.id.imageaddedPublication);
        imageButtonPublication = findViewById(R.id.addImageButtonPublication);
        descriptionPublication = findViewById(R.id.edit_text_ajout_description_publication);
        validateButtonpublication = findViewById(R.id.buttonValiderPublication);
=======
        imageView= findViewById(R.id.imageadded);
        teste = findViewById(R.id.ajoutImage_publication);
        ajout = findViewById(R.id.addImagePublication);
       desc = findViewById(R.id.description);
        Desc_photo = findViewById(R.id.edit_text_ajout_photo);
       valide = findViewById(R.id.buttonValider);
        photoRef = FirebaseDatabase.getInstance().getReference();
        countActivitesRef = FirebaseDatabase.getInstance().getReference().child("photos").child("1");
        storageReference = FirebaseStorage.getInstance().getReference("Images");
>>>>>>> 7af94fb6a88977f1fe69c99b386bceca1cc84c8c

        activiteRef = FirebaseDatabase.getInstance().getReference();
        countActivitesRef = FirebaseDatabase.getInstance().getReference().child("Photos");
        storageReference = FirebaseStorage.getInstance().getReference("Images");

        imageButtonPublication.setOnClickListener(new View.OnClickListener() {
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

<<<<<<< HEAD
        countActivitesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                countActivites = (int) dataSnapshot.getChildrenCount();
=======

        ajout.setOnClickListener(new View.OnClickListener() {
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
>>>>>>> 7af94fb6a88977f1fe69c99b386bceca1cc84c8c
            }
        });

<<<<<<< HEAD
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Publication.this, "NO data", Toast.LENGTH_LONG).show();
            }
        });

        validateButtonpublication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                writeNewActivity();


            }
        });

=======
        countActivitesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                countActivites = (int) dataSnapshot.getChildrenCount();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Publication.this, "NO data", Toast.LENGTH_LONG).show();
            }
        });

        valide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                writeNewActivity();


            }
        });


>>>>>>> 7af94fb6a88977f1fe69c99b386bceca1cc84c8c

    }

    private void writeNewActivity() {

<<<<<<< HEAD
        description = descriptionPublication.getText().toString();
=======
        description = Desc_photo.getText().toString();
>>>>>>> 7af94fb6a88977f1fe69c99b386bceca1cc84c8c


        final StorageReference ref = storageReference.child(System.currentTimeMillis() + "." + getExtension(imageUri));

        ref.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {



                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {

                                String imageUrl = String.valueOf(uri);

<<<<<<< HEAD
                                Info info = new Info(description, imageUrl);
                                activiteRef.child("Photos").child("" + countActivites++).setValue(info);
=======
                               PhotoColoneContent activiteColoneContent = new PhotoColoneContent( description, imageUrl);
                                photoRef.child("photos").child("1").child("" + countActivites++).setValue(activiteColoneContent);
>>>>>>> 7af94fb6a88977f1fe69c99b386bceca1cc84c8c

                                Toast.makeText(Publication.this, "Image upload successfull", Toast.LENGTH_LONG).show();

                            }
                        });

                    }
                })

                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        Toast.makeText(Publication.this, "Image upload faillure", Toast.LENGTH_LONG).show();
                    }
                });

<<<<<<< HEAD
        Toast.makeText(Publication.this, "Photos Added", Toast.LENGTH_LONG).show();
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
            imageViewPublication.setBackground(null);
            imageViewPublication.setImageURI(data.getData());
=======
        Toast.makeText(Publication.this, "Activites Added", Toast.LENGTH_LONG).show();
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
            imageView.setImageURI(data.getData());
>>>>>>> 7af94fb6a88977f1fe69c99b386bceca1cc84c8c
            imageUri = data.getData();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

<<<<<<< HEAD

=======
>>>>>>> 7af94fb6a88977f1fe69c99b386bceca1cc84c8c
}