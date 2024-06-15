package com.example.family_task_tracker;

import androidx.appcompat.app.AppCompatActivity;
//import com.example.family_task_tracker.utils.MyUtilsClass;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.family_task_tracker.databinding.ActivityRegistrBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.net.URL;
import java.util.HashMap;

public class RegistrActivity extends AppCompatActivity  {
    private ActivityRegistrBinding binding;
public static class User implements Serializable {
    public static void doSomething(){};
    public static String email;
    public static String username;
    public static String role;
    public static String password;
    public User(String email, String username, String role, String password){
        this.email = email;
        this.username = username;
        this.role = role;
        this.password = password;
    }

    public User(){};}

    public void onClick_back(View v){
Intent intent_transition = new Intent(RegistrActivity.this,LoginActivity.class);
        startActivity(intent_transition);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistrBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.SignUpBtn.setOnClickListener (new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                User user = new User(binding.EmailEt.getText().toString(),binding.userNameEt.getText().toString(),"", binding.PasswordEt.getText().toString());
                if(binding.EmailEt.getText().toString().isEmpty() || binding.PasswordEt.getText().toString().isEmpty() || binding.userNameEt.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Fields cannot be empty", Toast.LENGTH_SHORT).show();
                }
                else
                {

                    Intent intent_role = new Intent(RegistrActivity.this,Role_choosing.class);
                    //intent_role.putExtra("User_info", user);
                    startActivity(intent_role);
                    Bundle arguments_role = getIntent().getExtras();
                    user.role = arguments_role.get("role").toString();
                    /*Intent intent_go = new Intent(RegistrActivity.this,Role_choosing.class);
                    startActivity(intent_go);*/
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(binding.EmailEt.getText().toString(),binding.PasswordEt.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        HashMap<String, String> userInfo = new HashMap<>();
                                        userInfo.put("email",user.email);
                                        userInfo.put("username", user.username);
                                        userInfo.put("role",user.role);
                                        FirebaseDatabase.getInstance().getReference().child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                .setValue(userInfo);
                                        //intent_role.putExtra("User_info", (Serializable) user);
                                        //startActivity(intent_role);
                                    }
                                }
                            }   );

                }
            }
        });
    }
}
    //binding3
    //binding2
    //binding
    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // calling the action bar
        ActionBar actionBar = getSupportActionBar();

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }*/

