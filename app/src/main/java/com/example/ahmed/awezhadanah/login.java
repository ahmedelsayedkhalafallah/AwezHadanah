package com.example.ahmed.awezhadanah;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ahmed.awezhadanah.adapter.order;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class login extends AppCompatActivity {

    ProgressDialog progressDialog;
    FirebaseDatabase fdb ;
    DatabaseReference dbr;
    HashMap<String , String> logs;
    EditText user, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("loading");
        progressDialog.setMessage("Please wait");
        progressDialog.setCancelable(false);
        progressDialog.show();

        user = findViewById(R.id.user);
        password = findViewById(R.id.password);
        logs = new HashMap<String, String>();
        //Initialize reference to admins log data
        fdb = FirebaseDatabase.getInstance();
        dbr = fdb.getReference().child("admins");
        //gat all admins data in hashmap
        dbr.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                logs.clear();
                for (DataSnapshot Single:dataSnapshot.getChildren())
                {
                    String name = Single.getKey().toString();
                    String pass = Single.getValue().toString();
                    logs.put(name,pass);

                }
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    //check if the user name and password are found to open request activity
    public void log(View view) {


        //check the inputs
        String name = user.getText().toString();
        String pass = password.getText().toString();
        if(!(name.equals(null))) {
            if (!(logs.isEmpty())) {
                if(pass.equals(logs.get(name))){
                    Intent intent = new Intent(this, orders.class);
                    intent.putExtra("admin",name);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(this, "Wrong user name or password", Toast.LENGTH_SHORT).show();
                }
            }
            else {
                Toast.makeText(this, "Please wait", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(this, "Please Enter your username", Toast.LENGTH_SHORT).show();
        }
    }
}
