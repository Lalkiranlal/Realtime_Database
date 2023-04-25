package com.example.firebase_database;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity2 extends AppCompatActivity {
    FirebaseDatabase fd;
    FirebaseAuth fauth;
    EditText text;
    String userId;
    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        text=findViewById(R.id.print);
        fauth=FirebaseAuth.getInstance();
        userId=fauth.getCurrentUser().getUid();
        fd=FirebaseDatabase.getInstance();
        fd.getReference("user").get();


    }
}