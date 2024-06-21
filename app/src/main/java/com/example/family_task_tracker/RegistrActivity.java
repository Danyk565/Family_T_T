package com.example.family_task_tracker;

import androidx.appcompat.app.AppCompatActivity;
//import com.example.family_task_tracker.utils.MyUtilsClass;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.example.family_task_tracker.User;

import androidx.annotation.NonNull;

import com.example.family_task_tracker.databinding.ActivityRegistrBinding;

public class RegistrActivity extends AppCompatActivity  {
    private ActivityRegistrBinding binding;
    public void onClick_back(View v){
Intent intent_transition = new Intent(RegistrActivity.this,LoginActivity.class);
        startActivity(intent_transition);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistrBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        EditText EmailEt =  findViewById(R.id.Email_et);
        EditText UserNameEt =  findViewById(R.id.user_name_et);
        EditText PasswordEt = findViewById(R.id.Password_et);


        binding.SignUpBtn.setOnClickListener (new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                User user = new User(EmailEt.getText().toString(),UserNameEt.getText().toString(),"", PasswordEt.getText().toString());
                if(EmailEt.getText().toString().isEmpty() || PasswordEt.getText().toString().isEmpty() || UserNameEt.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Fields cannot be empty", Toast.LENGTH_SHORT).show();
                } else if (PasswordEt.length() < 8) {
                    Toast.makeText(getApplicationContext(),"Password should be 8 symbols or more", Toast.LENGTH_SHORT).show();
                } else
                {
                    Intent intent_role = new Intent(RegistrActivity.this,Role_choosing.class);
                    intent_role.putExtra("User_Info",user);
                    startActivity(intent_role);
                   // Intent go_intent_role = new Intent(RegistrActivity.this,Role_choosing.class);

                  //Intent intent_role = new Intent(RegistrActivity.this,Role_choosing.class);
                    //intent_role.putExtra("User_info", user);
                    //startActivity(intent_role);
                    //Intent get_role = getIntent();
                   // user.role = get_role.getStringExtra("role");
                    /*Intent intent_go = new Intent(RegistrActivity.this,Role_choosing.class);
                    startActivity(intent_go);*/
                   // FirebaseAuth.getInstance().createUserWithEmailAndPassword(binding.EmailEt.getText().toString(),binding.PasswordEt.getText().toString())
                            //.addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                               // @Override
                                //public void onComplete(@NonNull Task<AuthResult> task) {
                                    //if(task.isSuccessful()){
                                        /*HashMap<String, String> userInfo = new HashMap<>();
                                        userInfo.put("email",user.email);
                                        userInfo.put("username", user.username);
                                        userInfo.put("role",user.role);
                                        FirebaseDatabase.getInstance().getReference().child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                .setValue(userInfo);*/
                                        //Intent account = new Intent(RegistrActivity.this, MainActivity.class);
                                        //startActivity(account);
                                        //intent_role.putExtra("User_info", (Serializable) user);
                                        //startActivity(intent_role);
                                    }
                                }
                            }   );

                }
            }
        //});
    //}
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

