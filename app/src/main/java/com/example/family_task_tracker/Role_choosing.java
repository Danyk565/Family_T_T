package com.example.family_task_tracker;

import androidx.annotation.NonNull;

import com.example.family_task_tracker.databinding.ActivityRoleBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.CompoundButton;
;

import java.util.HashMap;

public class Role_choosing extends RegistrActivity {
    private ActivityRoleBinding binding;
    private FirebaseAuth mAuth;
    private DatabaseReference MyRef;
   // User user_r = (User) getIntent().getSerializableExtra("User_info");
 //FirebaseUser user = mAuth.getInstance().getCurrentUser();
 //int userName = getIntent().getIntExtra("id", 0);
 //FirebaseListAdapter mAdapter;
    //User user =getIntent().getParcelableExtra("User");
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //binding = ActivityRoleBinding.inflate(getLayoutInflater());
       // setContentView(binding.getRoot());
        //Button roleChooseBtn = findViewById(R.id.role_choose_btn);
        setContentView(R.layout.activity_role);
        ToggleButton parent_btn = (ToggleButton) findViewById(R.id.parent_btn); // Кнопка для выбора роли родителя(лидер группы)
        ToggleButton child_btn = (ToggleButton) findViewById(R.id.child_btn);// Кнопка для выбора роли ребёнка
        MyRef = FirebaseDatabase.getInstance().getReference();
        Button RoleChoose = findViewById(R.id.role_choose_btn);
        FirebaseUser User = FirebaseAuth.getInstance().getCurrentUser();

        child_btn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    parent_btn.setChecked(false);
                }
            }
        });

        parent_btn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    child_btn.setChecked(false);
                }
            }
        });
        RoleChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String role;
                User user = getIntent().getParcelableExtra("User_Info");
                boolean on_parent = parent_btn.isChecked();
                boolean on_child = child_btn.isChecked();
                Intent Role = new Intent(Role_choosing.this, RegistrActivity.class);
                if(!on_child&&!on_parent){
                    Toast.makeText(getApplicationContext(),"Choose the role", Toast.LENGTH_SHORT).show();
                }
                else if (on_parent){
                    user.role = "Parent";
                    //child_btn.setEnabled(false);
                }
                else if (on_child) {
                    //parent_btn.setEnabled(false);
                    user.role = "Child";
                }
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(user.email,user.password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    HashMap<String, String> userInfo = new HashMap<>();
                                    userInfo.put("email", user.email);
                                    userInfo.put("username", user.username);
                                    userInfo.put("role", user.role);
                                    FirebaseDatabase.getInstance().getReference().child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .setValue(userInfo);
                                    if(user.role =="Parent"){
                                        Intent account = new Intent(Role_choosing.this, Parent_account.class);
                                        startActivity(account);
                                    }
                                    else {
                                        if(user.role =="Child"){
                                        Intent account = new Intent(Role_choosing.this, Child_account.class);
                                    startActivity(account);
                                        }
                                    }
                                }
                            }
                        });
                /*FirebaseAuth.getInstance().createUserWithEmailAndPassword(user.email, user.password);
                HashMap<String, String> userInfo = new HashMap<>();
                userInfo.put("email", user.email);
                userInfo.put("username", user.username);
                userInfo.put("role", user.role);
                FirebaseDatabase.getInstance().getReference().child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .setValue(userInfo);
                Intent account = new Intent(Role_choosing.this, RegistrActivity.class);
                startActivity(account);*/
                //setResult(RESULT_OK, Role); // Установите результат (RESULT_OK для успешного завершения)
                //finish();
              /* FirebaseAuth.getInstance().createUserWithEmailAndPassword(user.email,user.password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                HashMap<String, String> userInfo = new HashMap<>();
                                userInfo.put("email", user.email);
                                userInfo.put("username", user.username);
                                userInfo.put("role", user.role);
                                FirebaseDatabase.getInstance().getReference().child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(userInfo);
                                Intent account = new Intent(Role_choosing.this, MainActivity.class);
                                startActivity(account);
                            }
                        });*/


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
        }*/
    //});
        }
    });
}
}