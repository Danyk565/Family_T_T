package com.example.family_task_tracker;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
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

public class Entering_bonus_task extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private DatabaseReference mRef;
    private TextView textView;
    private ImageButton finish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_entering_bonus_task);
        DisplayMetrics bt = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(bt);
        int width = bt.widthPixels;
        int height = bt.heightPixels;
        //NumberPicker hours,minutes,seconds;
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        getWindow().setLayout((int) (width * .85), (int) (height * .8));
        getSupportActionBar().hide();
        //textView = findViewById(R.id.textView10);
        NumberPicker hours = findViewById(R.id.Pickerhours);
        hours.setMinValue(0);
        hours.setMaxValue(23);
        NumberPicker minutes = findViewById(R.id.pickerminutes);
        minutes.setMinValue(0);
        minutes.setMaxValue(59);
        NumberPicker seconds = findViewById(R.id.Pickerseconds);
        seconds.setMinValue(0);
        seconds.setMaxValue(59);

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -20;
        getWindow().setAttributes(params);

        ImageButton finish= findViewById(R.id.ebt_finish);
        Button upload_task = findViewById(R.id.upload_task);
        EditText Name_task = findViewById(R.id.Name_task);
        EditText Task_control = findViewById(R.id.Task_conditions);
        EditText Scores = findViewById(R.id.scores_et);
        Switch switch1 = findViewById(R.id.switch1);
        if (switch1.isChecked()) {
            hours.setVisibility(View.VISIBLE);
            minutes.setVisibility(View.VISIBLE);
            seconds.setVisibility(View.VISIBLE);
        } else {
            hours.setVisibility(View.INVISIBLE);
            minutes.setVisibility(View.INVISIBLE);
            seconds.setVisibility(View.INVISIBLE);
        }
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    hours.setVisibility(View.VISIBLE);
                    minutes.setVisibility(View.VISIBLE);
                    seconds.setVisibility(View.VISIBLE);
                } else {
                    hours.setVisibility(View.INVISIBLE);
                    minutes.setVisibility(View.INVISIBLE);
                    seconds.setVisibility(View.INVISIBLE);
                }
            }
        });
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        mRef = FirebaseDatabase.getInstance().getReference();
        FirebaseUser User = mAuth.getInstance().getCurrentUser();
        DatabaseReference UserRef = mRef.child("users").child(User.getUid());
        upload_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Name_task.getText().toString().isEmpty() || Task_control.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Fields cannot be empty", Toast.LENGTH_SHORT).show();
                } else {
                    long tseconds = (long) seconds.getValue();
                    long tminutes = (long) minutes.getValue();
                    long thours = (long) hours.getValue();
                    CountDownTimer timer = new CountDownTimer(((thours*3600000)+(tminutes*60000)+(tseconds * 1000)), 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            //textView.setText(String.format(Locale.getDefault(), "%d", millisUntilFinished / 1000));
                        }

                        @Override
                        public void onFinish() {
                            textView.setText("0");
                        }
                    };
                    timer.start();
                    //String key_name = mRef.child("users").child(User.getUid()).child("tasks").push().getKey();
                    //mRef.child("users").child(User.getUid()).child("tasks").setValue(Name_task.getText().toString());
                    Map<String,Object> task_map = new HashMap<>();
                    String str_Guid = String.valueOf(java.util.UUID.randomUUID());
                    task_map.put("tasks/"+"bonus_tasks/"+str_Guid+"/name_task", Name_task.getText().toString());
                    task_map.put("tasks/"+"bonus_tasks/"+str_Guid+"/condition", Task_control.getText().toString());
                    task_map.put("tasks/"+"bonus_tasks/"+str_Guid+"/scores", Scores.getText().toString());
                    UserRef.updateChildren(task_map);
                    //Map<String,Object> task_condition = new HashMap<>();

                    // UserRef.updateChildren(task_condition);
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
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}


