package com.example.family_task_tracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import java.util.ArrayList;


public class Child_account extends AppCompatActivity {
    ArrayList<Tasks> task = new ArrayList<Tasks>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_account);
        // начальная инициализация списка
        setInitialData();
        RecyclerView recyclerView = findViewById(R.id.rvView);
        // создаем адаптер
        TasksAdapter adapter = new TasksAdapter(this, task);
        // устанавливаем для списка адаптер
        recyclerView.setAdapter(adapter);
    }
    private void setInitialData(){

        task.add(new Tasks ("Бразилия", "Бразилиа", 500));
        task.add(new Tasks ("Аргентина", "Буэнос-Айрес", 300));
        task.add(new Tasks ("Колумбия", "Богота", 400));
        task.add(new Tasks ("Уругвай", "Монтевидео", 200));
        task.add(new Tasks ("Чили", "Сантьяго", 600));
    }
}
