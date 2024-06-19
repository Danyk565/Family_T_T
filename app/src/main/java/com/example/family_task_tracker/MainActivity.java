package com.example.family_task_tracker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseAuth auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() == null) {
            Log.d(TAG, "User is not logged in, redirecting to LoginActivity");
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        } else {
            String userId = auth.getCurrentUser().getUid();
            FirebaseFirestore db = FirebaseFirestore.getInstance();

            db.collection("users").document(userId).get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document != null && document.exists()) {
                        String role = document.getString("role");
                        Log.d(TAG, "User role: " + role);

                        if ("Parent".equals(role != null ? role.trim() : "")) {
                            Intent intent = new Intent(MainActivity.this, Parent_account.class);
                            startActivity(intent);
                            finish();
                        }
                        //else if ("Child".equals(role != null ? role.trim() : "")) {
                            //Intent intent = new Intent(MainActivity.this, Child_activity.class);
                            //startActivity(intent);
                            //finish();
                        //}
                    else {
                            Log.w(TAG, "Unknown role, redirecting back to LoginActivity");
                            startActivity(new Intent(MainActivity.this, LoginActivity.class));
                            finish();
                        }
                    } else {
                        Log.w(TAG, "Document does not exist");
                        startActivity(new Intent(MainActivity.this, LoginActivity.class));
                        finish();
                    }
                } else {
                    Log.e(TAG, "Failed to get user role", task.getException());
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    finish();
                }
            });
        }
    }
}