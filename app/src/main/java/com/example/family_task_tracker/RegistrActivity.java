package com.example.family_task_tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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

import java.net.URL;
import java.util.HashMap;

public class RegistrActivity extends AppCompatActivity {
    private ActivityRegistrBinding binding;
/*public static class User{
    public String email;
    public String username;
    public String role;

}*/
//Button Sign_up_btn = findViewById(R.id.Sign_up_btn);
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
                if(binding.EmailEt.getText().toString().isEmpty() || binding.PasswordEt.getText().toString().isEmpty() || binding.userNameEt.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Fields cannot be empty", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(binding.EmailEt.getText().toString(),binding.PasswordEt.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        HashMap<String, String>userInfo = new HashMap<>();
                                        userInfo.put("email",binding.EmailEt.getText().toString());
                                        userInfo.put("username", binding.userNameEt.getText().toString());
                                        userInfo.put("role","");
                                        FirebaseDatabase.getInstance().getReference().child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                .setValue(userInfo);
                                        Intent intent_role = new Intent(RegistrActivity.this,Role_choosing.class);
                                        startActivity(intent_role);

                                        //Intent intent_id = new Intent(RegistrActivity.this,Role_choosing.class);
                                        //intent_id.putExtra("id", FirebaseDatabase.getInstance().getReference().child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).toString());
                                        //startActivity(intent_id);
                                    }
                                }
                            }   );

                }
            }
        });
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
}
