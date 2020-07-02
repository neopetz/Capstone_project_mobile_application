package com.jaysonjose.led;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity2 extends AppCompatActivity {

    private ImageView on,off,offFan,onFan;
    private TextView label,label2,tempData,humidData,heatData,soilData;
    private DatabaseReference rootDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        off = findViewById(R.id.off);
        on = findViewById(R.id.on);
        offFan = findViewById(R.id.offFan);
        onFan = findViewById(R.id.onFan);
        label = findViewById(R.id.label);
        label2 = findViewById(R.id.label2);
        tempData = findViewById(R.id.tempData);
        humidData = findViewById(R.id.humidData);
        heatData = findViewById(R.id.heatData);
        soilData = findViewById(R.id.soilData);

        rootDatabase = FirebaseDatabase.getInstance().getReference().child("user");

        rootDatabase.child("Temperature").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String data = snapshot.getValue().toString();
                    tempData.setText(data);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        rootDatabase.child("Humidity").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String data = snapshot.getValue().toString();
                    humidData.setText(data);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        rootDatabase.child("Heat").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String data = snapshot.getValue().toString();
                    heatData.setText(data);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        rootDatabase.child("Soil_Moisture").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String data = snapshot.getValue().toString();
                    soilData.setText(data);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        rootDatabase.child("LedLight").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String data = snapshot.getValue().toString();
                    if(data.equals("OFF")){
                        on.setVisibility(View.GONE);
                        off.setVisibility(View.VISIBLE);
                        label.setText("OFF");
                    }else{
                        off.setVisibility(View.GONE);
                        on.setVisibility(View.VISIBLE);
                        label.setText("ON");
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                off.setVisibility(View.GONE);
                on.setVisibility(View.VISIBLE);
                rootDatabase.child("LedLight").setValue("ON");
                String label1 = label.getText().toString();
                if(label1.equals("OFF")){
                    label.setText("ON");
                }else{
                    label.setText("OFF");
                }

            }
        });

        on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                on.setVisibility(View.GONE);
                off.setVisibility(View.VISIBLE);
                rootDatabase.child("LedLight").setValue("OFF");
                String label1 = label.getText().toString();
                if(label1.equals("OFF")){
                    label.setText("ON");
                }else{
                    label.setText("OFF");
                }
            }
        });



        rootDatabase.child("Fan").child("Power").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String data1 = snapshot.getValue().toString();
                    if(data1.equals("OFF")){
                        onFan.setVisibility(View.GONE);
                        offFan.setVisibility(View.VISIBLE);
                        label2.setText("OFF");
                    }else{
                        offFan.setVisibility(View.GONE);
                        onFan.setVisibility(View.VISIBLE);
                        label2.setText("ON");
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        offFan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                offFan.setVisibility(View.GONE);
                onFan.setVisibility(View.VISIBLE);
                rootDatabase.child("Fan").child("Power").setValue("ON");
                String label3 = label2.getText().toString();
                if(label3.equals("OFF")){
                    label2.setText("ON");
                }else{
                    label2.setText("OFF");
                }

            }
        });

        onFan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFan.setVisibility(View.GONE);
                offFan.setVisibility(View.VISIBLE);
                rootDatabase.child("Fan").child("Power").setValue("OFF");
                String label3 = label2.getText().toString();
                if(label3.equals("OFF")){
                    label2.setText("ON");
                }else{
                    label2.setText("OFF");
                }
            }
        });
    }
}