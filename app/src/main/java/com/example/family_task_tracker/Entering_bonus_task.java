package com.example.family_task_tracker;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Entering_bonus_task extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private DatabaseReference mRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_entering_bonus_task);
        DisplayMetrics bt = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(bt);
        int width = bt.widthPixels;
        int height = bt.heightPixels;
        getWindow().setLayout((int)(width*.8),(int)(height*.7));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -20;
        getWindow().setAttributes(params);


        Button upload_task = findViewById(R.id.upload_task);
        EditText Name_task = findViewById(R.id.Name_task);
        EditText Task_control = findViewById(R.id.Task_conditions);

        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        mRef = FirebaseDatabase.getInstance().getReference();
        FirebaseUser User = mAuth.getInstance().getCurrentUser();
        DatabaseReference UserRef = mRef.child("users").child(User.getUid());


        upload_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Name_task.getText().toString().isEmpty() || Task_control.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Fields cannot be empty", Toast.LENGTH_SHORT).show();
                }
                else{
                    //String key_name = mRef.child("users").child(User.getUid()).child("tasks").push().getKey();
                    //mRef.child("users").child(User.getUid()).child("tasks").setValue(Name_task.getText().toString());
                    Map<String,Object> task_map = new HashMap<>();
                    task_map.put("tasks/name_task", Name_task.getText().toString());
                    UserRef.updateChildren(task_map);
                    Map<String,Object> task_condition = new HashMap<>();
                    task_map.put("name_task/condition", Task_control.getText().toString());
                    UserRef.child("tasks").updateChildren(task_condition);
                    finish();
                }
            }
        });
        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}