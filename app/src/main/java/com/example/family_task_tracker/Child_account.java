package com.example.family_task_tracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;

public class Child_account extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_account);

        ImageButton link_parent = findViewById(R.id.link_parent_btn);
        RecyclerView List = findViewById(R.id.tasks_list);
        List.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<Bonus_Tasks> receivedList = getIntent().getParcelableArrayListExtra("bonusTasksList");
        if (receivedList != null && !receivedList.isEmpty()) {
            TasksAdapter adapter = new TasksAdapter(receivedList, this);
            List.setAdapter(adapter);
        }

        link_parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Child_account.this, Link_parent.class));
            }
        });
    }
}