package com.example.family_task_tracker;

import android.os.Bundle;
import android.os.CountDownTimer;
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
import java.util.Locale;
import java.util.Map;

public class Entering_daily_tasks extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private DatabaseReference mRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_daily_tasks);

        DisplayMetrics bt = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(bt);
        int width = bt.widthPixels;
        int height = bt.heightPixels;
        getWindow().setLayout((int) (width * .8), (int) (height * .7));
        //textView = findViewById(R.id.textView10);
        //editTextTime = findViewById(R.id.editTextTime);

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -20;
        getWindow().setAttributes(params);


        mAuth = FirebaseAuth.getInstance();
        mRef = FirebaseDatabase.getInstance().getReference();
        FirebaseUser User = mAuth.getInstance().getCurrentUser();
        DatabaseReference UserRef = mRef.child("users").child(User.getUid());

        Button upload_daily_tasks_BT = findViewById(R.id.upload_daily_tasks_BT);
        EditText Name_task = findViewById(R.id.daily_tasks_name_Et);
        EditText Task_control = findViewById(R.id.daily_tasks_condition_Et);



        upload_daily_tasks_BT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Name_task.getText().toString().isEmpty() || Task_control.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Fields cannot be empty", Toast.LENGTH_SHORT).show();
                } else {
                    /*long seconds = Long.parseLong(editTextTime.getEditableText().toString());
                    CountDownTimer timer = new CountDownTimer(seconds * 1000, 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            textView.setText(String.format(Locale.getDefault(), "%d", millisUntilFinished / 1000));
                        }

                        @Override
                        public void onFinish() {
                            textView.setText("0");
                        }
                    };
                    timer.start();*/
                    //String key_name = mRef.child("users").child(User.getUid()).child("tasks").push().getKey();
                    //mRef.child("users").child(User.getUid()).child("tasks").setValue(Name_task.getText().toString());
                    Map<String,Object> task_map = new HashMap<>();
                    String str_Guid = String.valueOf(java.util.UUID.randomUUID());
                    task_map.put("tasks/"+"daily_tasks/"+str_Guid+"/name_task", Name_task.getText().toString());
                    task_map.put("tasks/"+"daily_tasks/"+str_Guid+"/condition", Task_control.getText().toString());
                    UserRef.updateChildren(task_map);
                    //Map<String,Object> task_condition = new HashMap<>();

                    // UserRef.updateChildren(task_condition);
                    finish();
                }
            }

        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }
}