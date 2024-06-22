package com.example.family_task_tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Parent_account extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_account);
        ImageButton raz_zadachi_btn = findViewById(R.id.raz_zadachi_btn);
        ImageButton daily_tasks_btn = findViewById(R.id.daily_tasks_btn);
        raz_zadachi_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Parent_account.this,Entering_bonus_task.class));
            }
        });
        daily_tasks_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Parent_account.this, Entering_daily_tasks.class));
            }
        });
    }
}