package com.example.ticketbooking;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ticketbooking.model.Event;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Admin extends AppCompatActivity {

    EditText title, description;
    ImageView imageView;
    Button upload, chooseImageButton;
    FirebaseFirestore firebaseFirestore;

    FirebaseStorage storage;
    private Uri selectedImageUri;
    String imageUrlToSaveInFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin);

        firebaseFirestore = FirebaseFirestore.getInstance();

        title = findViewById(R.id.titleEditText);
        description = findViewById(R.id.descriptionEditText);
        upload = findViewById(R.id.uploadButton);
        chooseImageButton = findViewById(R.id.chooseImageButton);
        imageView = findViewById(R.id.imageView); // Ensure imageView is initialized
        storage = FirebaseStorage.getInstance();

        chooseImageButton.setOnClickListener(onClick -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            chooseEventImageLauncher.launch(intent);
        });

        upload.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (selectedImageUri == null) {
                    Toast.makeText(Admin.this, "Please select an image first", Toast.LENGTH_SHORT).show();
                    return;
                }
                saveImageToStorage();
            }
        });
    }

    private void saveImageToStorage() {
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        StorageReference storageRef = storage.getReference();
        StorageReference imageRef = storageRef.child("IMAGES_FOLDER").child(uid);

        imageRef.putFile(selectedImageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Image uploaded successfully, get the download URL
                        imageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri downloadUrl) {
                                // Store the download URL in Firestore
                                imageUrlToSaveInFirestore = downloadUrl.toString();
                                uploadEventDetails();
                            }
                        });
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Handle the error
                        String errorMessage = e.getMessage();
                        Toast.makeText(Admin.this, errorMessage, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void uploadEventDetails() {
        String Tittle = title.getText().toString();
        String Description = description.getText().toString();

        Event event = new Event(Tittle, Description, imageUrlToSaveInFirestore);
        firebaseFirestore.collection("events")
                .add(event)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(Admin.this, "Uploaded Successfully to the database", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Admin.this, "Failed to upload, Kindly repeat uploading", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private final ActivityResultLauncher<Intent> chooseEventImageLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    // Handle the result, e.g., get the selected image URI
                    Intent data = result.getData();
                    if (data != null && data.getData() != null) {
                        selectedImageUri = data.getData();
                        imageView.setImageURI(selectedImageUri);
                    }
                }
            }
    );
}
