package com.example.family_task_tracker;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Text_parent_task extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private DatabaseReference mRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_text_parent_task);
        DisplayMetrics bt = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(bt);
        int width = bt.widthPixels;
        int height = bt.heightPixels;

        TextView task_condition = findViewById(R.id.task_condition_parent);
        TextView scores = findViewById(R.id.Scores_tv_parent);
        TextView name_task = findViewById(R.id.name_task_parent);

        mAuth = FirebaseAuth.getInstance();
        mRef = FirebaseDatabase.getInstance().getReference();
        FirebaseUser User = mAuth.getInstance().getCurrentUser();

        String taskName = getIntent().getStringExtra("taskName3");


        //NumberPicker hours,minutes,seconds;
        ImageButton finish= findViewById(R.id.ebt_finish);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        getWindow().setLayout((int) (width * .85), (int) (height * .8));
        getSupportActionBar().hide();
        mRef.child("users").child(User.getUid()).child("parent").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String parentUid = dataSnapshot.getValue(String.class);
                DatabaseReference ParentRef_bonus_tasks = mRef.child("users").child(parentUid).child("tasks").child("bonus_tasks");
                ParentRef_bonus_tasks.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Bonus_Tasks task = new Bonus_Tasks();
                            task.Uid = snapshot.getKey();
                            for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                                String Key = childSnapshot.getKey();
                                if(Key.equals("condition")){
                                    task.Task_conditions = childSnapshot.getValue(String.class);

                                }
                                if(Key.equals("name_task")){
                                    task.Name_task =childSnapshot.getValue(String.class);}

                                if(Key.equals("scores")){
                                    task.scores = childSnapshot.getValue(String.class);}

                            }


                            if (task.scores==null){
                                task.scores="0";
                            }
                            if(task.Name_task.equals(taskName)){
                                name_task.setText( task.Name_task);
                                task_condition.setText(task.Task_conditions);
                                scores.setText(task.scores);
                            }
                            task.Task_control=false;
                            task.Type = "bonus_tasks";
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Обработка ошибок
            }
        });
        mRef.child("users").child(User.getUid()).child("parent").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String parentUid = dataSnapshot.getValue(String.class);
                DatabaseReference ParentRef_bonus_tasks = mRef.child("users").child(parentUid).child("tasks").child("daily_tasks");
                ParentRef_bonus_tasks.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Bonus_Tasks task = new Bonus_Tasks();
                            task.Uid = snapshot.getKey();
                            for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                                String Key = childSnapshot.getKey();
                                if(Key.equals("condition")){
                                    task.Task_conditions = childSnapshot.getValue(String.class);}
                                if(Key.equals("name_task")){
                                    task.Name_task =childSnapshot.getValue(String.class);}
                                if(Key.equals("scores")){
                                    task.scores = childSnapshot.getValue(String.class);}

                            }
                            if (task.scores==null){
                                task.scores="0";
                            }
                            if(task.Name_task.equals(taskName)){
                                name_task.setText( task.Name_task);
                                task_condition.setText(task.Task_conditions);
                                scores.setText(task.scores);
                            }
                            task.Task_control=false;
                            task.Type = "daily_tasks";
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Обработка ошибок
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}