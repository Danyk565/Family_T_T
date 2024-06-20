package com.example.family_task_tracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.family_task_tracker.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private DatabaseReference mRef;

    private ActivityLoginBinding binding;
    Button login;
    EditText log,pas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setContentView(R.layout.activity_login);
        login =findViewById(R.id.login_btn);
        log=findViewById(R.id.email_et);
        pas=findViewById(R.id.password_et);

        mRef = FirebaseDatabase.getInstance().getReference();
        FirebaseUser User = mAuth.getInstance().getCurrentUser();
       /* if(User!=null){
            mRef.child("users").child(User.getUid()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String role = snapshot.child("role").getValue(String.class);
                    if(role=="Parent"){
                        startActivity(new Intent(LoginActivity.this,Parent_account.class));
                    } else if (role=="Child") {
                        startActivity(new Intent(LoginActivity.this,Child_account.class));

                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }*/


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(log.getText().toString().isEmpty() || pas.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Fields cannot be empty", Toast.LENGTH_SHORT).show();
                }
                else{
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(log.getText().toString(),pas.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        mRef.child("users").child(User.getUid()).child("role").addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                String role = snapshot.getValue(String.class).toString();
                                                if(role.equals("Parent")){
                                                    startActivity(new Intent(LoginActivity.this,Parent_account.class));
                                                } else if (role.equals("Child")) {
                                                    startActivity(new Intent(LoginActivity.this,Child_account.class));
                                                }
                                            }
                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {

                                            }
                                        });
                                        //FirebaseUser User = mAuth.getCurrentUser();

                                    }
                                    else
                                        Toast.makeText(getApplicationContext(),"Invalid user data", Toast.LENGTH_SHORT).show();

                                }
                            });
                }
            }
        });
    }
    public void onClickregister(View v) {
        Intent intent=new Intent(LoginActivity.this,RegistrActivity.class);
        startActivity(intent);
    }
}