package com.example.family_task_tracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.family_task_tracker.databinding.ActivityRoleBinding;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ToggleButton;
;

import com.example.family_task_tracker.databinding.ActivityRoleBinding;

import java.io.Serializable;
import java.util.HashMap;

public class Role_choosing extends RegistrActivity {
    private ActivityRoleBinding binding;
    private FirebaseAuth mAuth;
    private DatabaseReference MyRef;
   // User user_r = (User) getIntent().getSerializableExtra("User_info");
 //FirebaseUser user = mAuth.getInstance().getCurrentUser();
 //int userName = getIntent().getIntExtra("id", 0);
 //FirebaseListAdapter mAdapter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRoleBinding.inflate(getLayoutInflater());
        //setContentView(binding.getRoot());
        setContentView(R.layout.activity_role);
        ToggleButton parent_btn = (ToggleButton) findViewById(R.id.parent_btn); // Кнопка для выбора роли родителя(лидер группы)
        ToggleButton child_btn = (ToggleButton) findViewById(R.id.child_btn);// Кнопка для выбора роли ребёнка
        MyRef = FirebaseDatabase.getInstance().getReference();


        binding.roleChooseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String role;
                boolean on_parent = parent_btn.isChecked();
                boolean on_child = child_btn.isChecked();
                Intent Role = new Intent(Role_choosing.this, RegistrActivity.class);
                if(on_parent){
                    role = "Parent";
                    Role.putExtra("role",role);
                    //user_r.role = "Parent";
                   // MyRef.setValue(My);
                } else if (on_child) {
                    role = "Child";
                    Role.putExtra("role",role);
                    //user_r.role = "Child";
                }
                /*FirebaseAuth.getInstance().createUserWithEmailAndPassword(user_r.email,user_r.password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                               HashMap<String, String> userInfo = new HashMap<>();
                userInfo.put("email",user_r.email);
                userInfo.put("username", user_r.username);
                userInfo.put("role",user_r.role);
                FirebaseDatabase.getInstance().getReference().child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .setValue(userInfo);*/
                Intent account = new Intent(Role_choosing.this, MainActivity.class);
                startActivity(account);
                            //}
                       //  );

            }
        });


        /*parent_btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) return true;
                if (event.getAction() != MotionEvent.ACTION_UP) return false;


                parent_btn.setPressed(true);
                return false;
            }
        });*/
   /* mycodes_Button.setOnTouchListener(new OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            mycodes_Button.setPressed(true);
            return true;
        }
    });*/


    }
}