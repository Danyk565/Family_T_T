package com.example.family_task_tracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Parent_account extends AppCompatActivity {
    private int progress=40;
    private FirebaseAuth mAuth;
    private DatabaseReference mRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_account);
        ImageButton raz_zadachi_btn = findViewById(R.id.raz_zadachi_btn);
        ImageButton marafon_btn = findViewById(R.id.marafon_btn);
        ImageButton daily_tasks_btn = findViewById(R.id.daily_tasks_btn);
        TextView nameav = findViewById(R.id.nameav);

        mAuth = FirebaseAuth.getInstance();
        mRef = FirebaseDatabase.getInstance().getReference();
        FirebaseUser User = mAuth.getInstance().getCurrentUser();
        DatabaseReference UserRef = mRef.child("users").child(User.getUid());

        UserRef.child("username").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                nameav.setText(snapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        raz_zadachi_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Parent_account.this, Entering_bonus_task.class));
            }
        });
        daily_tasks_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Parent_account.this, Entering_daily_tasks.class));
            }
        });
        marafon_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Parent_account.this, Entering_marafon.class));
            }
        });
        postProgress(progress);
    }
    private void postProgress(int progress) {
        ProgressBar progressbar = findViewById(R.id.progressBar);
        String strProgress = String.valueOf(progress) + " %";
        progressbar.setProgress(progress);

    }


}