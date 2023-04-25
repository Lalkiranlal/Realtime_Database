package com.example.firebase_database;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText name,email,password;
    TextView forgot;
    Button button,button1;
    FirebaseAuth fauth;
   FirebaseDatabase fd;
    String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email=findViewById(R.id.email);
        name=findViewById(R.id.name);
        password= findViewById(R.id.password);
        button= findViewById(R.id.button);
        fauth = FirebaseAuth.getInstance();
        fd=FirebaseDatabase.getInstance();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email=email.getText().toString().trim();
                String Name=name.getText().toString().trim();
                String Password=password.getText().toString();
                fauth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            userId=fauth.getCurrentUser().getUid();
                            Map<String,Object> user =new HashMap<>();
                            user.put("fname",Name);
                            user.put("Email",Email);
                            fd.getReference("user").child(userId).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(MainActivity.this, "data stored", Toast.LENGTH_SHORT).show();
                                    Intent i =new Intent(MainActivity.this,MainActivity2.class);
                                    startActivity(i);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(MainActivity.this, "data couldn't be stored", Toast.LENGTH_SHORT).show();
                                }
                            });

                        }





        }

    });
   }
        });


    }
}