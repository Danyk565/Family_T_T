package com.example.family_task_tracker;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class Link_parent extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private DatabaseReference mRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_link_parent);

        Button Save_parent = findViewById(R.id.save_parent_btn);
        EditText parentRef = findViewById(R.id.parent_email_et);

        mAuth = FirebaseAuth.getInstance();
        mRef = FirebaseDatabase.getInstance().getReference();
        FirebaseUser User = mAuth.getInstance().getCurrentUser();
        DatabaseReference UserRef = mRef.child("users").child(User.getUid());
        DatabaseReference Users = mRef.child("users");


        Save_parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String parentEmail = parentRef.getText().toString();
                Query query = Users.orderByChild("email").equalTo(parentEmail);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            String parentUID="";
                            for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                                 parentUID = userSnapshot.getKey().toString();
                            }
                            DatabaseReference ParentRef = mRef.child("users").child(parentUID);
                            Map<String,Object> parent_map = new HashMap<>();
                            ParentRef.child("room/"+"/parent/").setValue(parentUID);
                            ParentRef.child("room/"+"/child/").setValue(User.getUid());
                            UserRef.child("parent").setValue(parentUID);
                            /*parent_map.put("users/"+parentUID+"/room/"+"/child",User.getUid());
                            parent_map.put("users/"+parentUID+"/room/"+"/child", parentUID);
                            UserRef.updateChildren(parent_map);*/
                            finish();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
       /*mAuth.fetchSignInMethodsForEmail(parentEmail).
                addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        FirebaseUser parent = mAuth.getCurrentUser();
                        if(parent!=null){
                            String parentUID = parent.getUid();
                            Map<String,Object> parent_map = new HashMap<>();
                            parent_map.put("parent",parentUID);
                            UserRef.updateChildren(parent_map);
                            finish();
                        }
                    }
                });*/
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }
}