package com.example.family_task_tracker;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Active_tasks extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private DatabaseReference mRef;
    private AlertDialog.Builder list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_active_tasks);

        RecyclerView List_parent = findViewById(R.id.List_parent);
        List_parent.setLayoutManager(new LinearLayoutManager(this)); // Make sure to set the LayoutManager

        mAuth = FirebaseAuth.getInstance();
        mRef = FirebaseDatabase.getInstance().getReference();
        FirebaseUser User = mAuth.getInstance().getCurrentUser();
        DatabaseReference UserRef = mRef.child("users").child(User.getUid());

        ArrayList<Bonus_Tasks> receivedList2 = getIntent().getParcelableArrayListExtra("bonusTasksList3");

        if (receivedList2 != null && !receivedList2.isEmpty()) {
            List<Integer> drawableList = new ArrayList<>();
            drawableList.add(R.drawable.stroke2);
            drawableList.add(R.drawable.stroke3);

            TaskparentAdapter adapter = new TaskparentAdapter(receivedList2, drawableList, this, task -> {
                Intent intent = new Intent(Active_tasks.this, Text_parent_task.class);
                intent.putExtra("taskName3", task.getName_task());
                intent.putIntegerArrayListExtra("drawableList2", new ArrayList<>(drawableList));
                startActivity(intent);
            });
            List_parent.setAdapter(adapter);
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
