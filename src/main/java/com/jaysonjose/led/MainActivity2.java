package com.jaysonjose.led;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity2 extends AppCompatActivity {

    private ImageView onLight,offLight,offFan,onFan,onSprinkler,offSprinkler,onStream,offStream,fanPic;
    private TextView tempData,humidData,heatData,soilData;
    private DatabaseReference rootDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_main2);

        offLight = findViewById(R.id.power_off_1);
        onLight = findViewById(R.id.power_on_1);
        offFan = findViewById(R.id.power_off_2);
        onFan = findViewById(R.id.power_on_2);
        offSprinkler = findViewById(R.id.power_off_3);
        onSprinkler = findViewById(R.id.power_on_3);
        offStream = findViewById(R.id.power_off_4);
        onStream = findViewById(R.id.power_on_4);

        tempData = findViewById(R.id.tempData);
        humidData = findViewById(R.id.humidityData);
        soilData = findViewById(R.id.soilMoistureData);

        fanPic = findViewById(R.id.fanPic);

        rootDatabase = FirebaseDatabase.getInstance().getReference().child("user");

        rootDatabase.child("Temperature").addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String data = snapshot.getValue().toString();
                    tempData.setText(data +" °C");
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        rootDatabase.child("Humidity").addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String data = snapshot.getValue().toString();
                    humidData.setText(data +" %");
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        rootDatabase.child("Soil_Moisture").addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String data = snapshot.getValue().toString();
                    soilData.setText(data +" %");
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
                        onLight.setVisibility(View.GONE);
                        offLight.setVisibility(View.VISIBLE);
                    }else{
                        offLight.setVisibility(View.GONE);
                        onLight.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        offLight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                offLight.setVisibility(View.GONE);
                onLight.setVisibility(View.VISIBLE);
                rootDatabase.child("LedLight").setValue("ON");


            }
        });

        onLight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLight.setVisibility(View.GONE);
                offLight.setVisibility(View.VISIBLE);
                rootDatabase.child("LedLight").setValue("OFF");
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
                    }else{
                        offFan.setVisibility(View.GONE);
                        onFan.setVisibility(View.VISIBLE);
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

            }
        });

        onFan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFan.setVisibility(View.GONE);
                offFan.setVisibility(View.VISIBLE);
                rootDatabase.child("Fan").child("Power").setValue("OFF");

            }
        });

        offSprinkler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                offSprinkler.setVisibility(View.GONE);
                onSprinkler.setVisibility(View.VISIBLE);
            }
        });

        onSprinkler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSprinkler.setVisibility(View.GONE);
                offSprinkler.setVisibility(View.VISIBLE);
            }
        });

        offStream.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                offStream.setVisibility(View.GONE);
                onStream.setVisibility(View.VISIBLE);
            }
        });

        onStream.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onStream.setVisibility(View.GONE);
                offStream.setVisibility(View.VISIBLE);
            }
        });


        fanPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              FanDialogFrag fanDialogFrag = new FanDialogFrag();
              fanDialogFrag.show(getSupportFragmentManager(),"FanDialog");

            }
        });


    }
}