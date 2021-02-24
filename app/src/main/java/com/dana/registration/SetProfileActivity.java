package com.dana.registration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.dana.registration.databinding.ActivitySetProfileBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SetProfileActivity extends AppCompatActivity {

    private ActivitySetProfileBinding mBinding;
    private ProgressDialog pd;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_profile);

        mBinding = ActivitySetProfileBinding.inflate(getLayoutInflater());

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Add Data");

        Intent i = getIntent();
        String email = i.getStringExtra("1");

        db = FirebaseFirestore.getInstance();
        pd = new ProgressDialog(this);
        Log.d("Panda", "you");
        initView();
    }

    private void initView() {
        mBinding.saveBtn.setOnClickListener(view -> {
            Log.d("Panda", "hello");
            String title = mBinding.titleEt.getText().toString().trim();
            String description = mBinding.descriptionEt.getText().toString().trim();
            uploadData(title, description);
            Log.d("Panda", title);
        });
    }

    private void uploadData(String title, String description) {
        pd.setTitle("Adding Data to the fireStore");
        pd.show();

        String id = UUID.randomUUID().toString();

        Map<String, Object> doc = new HashMap<>();
        doc.put("id", id);
        doc.put("title", title);
        doc.put("description", description);

        Log.d("Panda", doc.toString());

        db.collection("Documents").document(id).set(doc)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        //this will be called when data added succesfully
                        pd.dismiss();
                        Log.d("Panda", doc.toString());
                        Toast.makeText(SetProfileActivity.this, "Uploaded", Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //this will be called when error occured while uploading
                        pd.dismiss();
                        Log.d("Panda", e.toString());
                        Toast.makeText(SetProfileActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();

                    }
                });

    }

}