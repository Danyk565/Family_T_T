package com.example.family_task_tracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Child_account extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private DatabaseReference mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_account);


        ImageButton link_parent = findViewById(R.id.link_parent_btn);
        RecyclerView list = findViewById(R.id.tasks_list);
        list.setLayoutManager(new LinearLayoutManager(this));
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

        ArrayList<Bonus_Tasks> receivedList = getIntent().getParcelableArrayListExtra("bonusTasksList");
        if (receivedList != null && !receivedList.isEmpty()) {
            List<Integer> drawableList = new ArrayList<>();
            drawableList.add(
                    R.drawable.stroke2
            );
            drawableList.add(
                    R.drawable.stroke3
            );
            TasksAdapter adapter = new TasksAdapter(receivedList, drawableList, this, task -> {
                Intent intent = new Intent(Child_account.this, Text_task.class);
                intent.putIntegerArrayListExtra("drawableList", new ArrayList<>(drawableList));
                startActivity(intent);
            });
            list.setAdapter(adapter);
        }

        link_parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Child_account.this, Link_parent.class));
            }
        });
    }
}