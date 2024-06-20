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
        }
            else{
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
           /* private boolean isLoggedIn() {
                SharedPreferences sharedPreferences = PreferenceManager
                        .getDefaultSharedPreferences(this);
                //The false represents the default value, if the variable is not stored
                boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);
                return isLoggedIn;
            }

            private void saveLoggedIn(boolean value) {
                SharedPreferences sharedPreferences = PreferenceManager
                        .getDefaultSharedPreferences(this);
                Editor editor = sharedPreferences.edit();
                editor.putBoolean("isLoggedIn", value);
                editor.commit();
            }*/
            }
        }
    }