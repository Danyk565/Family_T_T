package com.example.family_task_tracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


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
        /*final String[] Role_for_update = {""};
        final String[] Parent = {""};
        mRef.child("users").child(User.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Role_for_update[0] = snapshot.child("role").getValue(String.class).toString();
              //  Parent[0] = snapshot.child("parent").getValue(String.class).toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/









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
                                                    mRef.child("users").child(User.getUid()).child("parent").addValueEventListener(new ValueEventListener() {
                                                        @Override
                                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                                            String parentUid = dataSnapshot.getValue(String.class);
                                                            DatabaseReference ParentRef_bonus_tasks = mRef.child("users").child(parentUid).child("tasks").child("bonus_tasks");
                                                            ParentRef_bonus_tasks.addValueEventListener(new ValueEventListener() {
                                                                @Override
                                                                public void onDataChange(DataSnapshot dataSnapshot) {
                                                                    List<Bonus_Tasks> taskList = new ArrayList<>();
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
                                                                        task.Task_control=false;
                                                                        taskList.add(task);
                                                                    }

                                                                    // Создаем адаптер и устанавливаем его для RecyclerView
                                                                    //TasksAdapter adapter = new TasksAdapter(taskList);
                                                                   /* if (recyclerView != null) {
                                                                        // Создание LayoutManager для RecyclerView
                                                                        LinearLayoutManager layoutManager = new LinearLayoutManager(LoginActivity.this);
                                                                        recyclerView.setLayoutManager(layoutManager);

                                                                        recyclerView.setAdapter(adapter);
                                                                    }*/
                                                                    Intent List = new Intent(LoginActivity.this, Child_account.class);
                                                                    List.putParcelableArrayListExtra("bonusTasksList", (ArrayList<? extends Parcelable>) taskList);
                                                                    startActivity(List);
                                                                }

                                                                @Override
                                                                public void onCancelled(DatabaseError databaseError) {
                                                                    // Обработка ошибок при чтении данных из базы данных
                                                                }
                                                            });
                                                        }

                                                        @Override
                                                        public void onCancelled(DatabaseError databaseError) {
                                                            // Обработка ошибок
                                                        }
                                                    });


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