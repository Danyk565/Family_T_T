package com.example.family_task_tracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;


public class Child_account extends AppCompatActivity {
    ArrayList<Tasks> task = new ArrayList<Tasks>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_account);
        ImageButton link_parent = findViewById(R.id.link_parent_btn);
        RecyclerView List = findViewById(R.id.tasks_list_rv);
        //Intent intent = getIntent();
        //ArrayList<Bonus_Tasks> receivedList = (ArrayList<Bonus_Tasks>) intent.getSerializableExtra("bonusTasksList");
        //String s=receivedList.get(0).Name_task;
       //if (receivedList != null) {

        ArrayList<Bonus_Tasks> receivedList = getIntent().getParcelableArrayListExtra("bonusTasksList");
        String s=receivedList.get(0).Name_task;
            TasksAdapter adapter = new TasksAdapter( receivedList, this);
            List.setAdapter(adapter);
        //}

        link_parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Child_account.this, Link_parent.class));
            }
        });
        // начальная инициализация списка
        /*setInitialData();
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
    }*/
    }
}


