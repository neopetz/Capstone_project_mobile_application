package com.jaysonjose.led;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private EditText input,input2;
    private Button btn,btnRead,btnUpdate,btnNext;
    private TextView textView,textView2;
    private DatabaseReference rootDatabaseref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        input = findViewById(R.id.input);
        input2 = findViewById(R.id.input2);
        btnRead = findViewById(R.id.btnRead);
        btnNext = findViewById(R.id.btnNext);
        btnUpdate = findViewById(R.id.btnUpdate);
        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        btn =  findViewById(R.id.btn);

        rootDatabaseref = FirebaseDatabase.getInstance().getReference().child("user").child("user1");

        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootDatabaseref.addValueEventListener(new ValueEventListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                           Map<String, Object> map;
                            map = (Map<String, Object>) snapshot.getValue();
                            //String data = snapshot.getValue().toString();
                          //  textView.setText(data);

                            assert map != null;
                            Object id = map.get("ID");
                           String name = (String) map.get("Name");
                            textView.setText(""+id);
                            textView2.setText(""+name);
                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });


     btn.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            int id = Integer.parseInt(input.getText().toString());
            String name = input2.getText().toString();
             HashMap<String, java.io.Serializable> hashMap;
             hashMap = new HashMap<>();

             hashMap.put("ID",id);
             hashMap.put("Name",name);

            rootDatabaseref .setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(MainActivity.this,"Your Data Successfully Added",Toast.LENGTH_LONG).show();

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(MainActivity.this,"Sorry, Permission Denied !!",Toast.LENGTH_LONG).show();
                }
            });
         }
     });
     btnUpdate.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             int id = Integer.parseInt(input.getText().toString());
             String name = input2.getText().toString();
             HashMap<String, Object> hashMap = new HashMap<String, Object>();

             hashMap.put("ID",id);
             hashMap.put("Name",name);
             rootDatabaseref.updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
                 @Override
                 public void onSuccess(Object o) {
                     Toast.makeText(MainActivity.this,"Your Data Successfully Updated",Toast.LENGTH_LONG).show();
                 }
             });
         }
     });

     btnNext.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Intent intent = new Intent(MainActivity.this,MainActivity2.class);
             startActivity(intent);
         }
     });

    }
}