package com.jaysonjose.led;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity2 extends AppCompatActivity {

    ImageView onLight,offLight,offFan,onFan,onSprinkler,offSprinkler,onStream,offStream,fanPic,sprinklerPic;
    ImageView temperaturePic,humidityPic,soilMoisturePic;
    private TextView tempData,humidData,soilData;
    private DatabaseReference rootDatabase;
    private WebView visualStream;
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
        sprinklerPic = findViewById(R.id.sprinklerPic);
        temperaturePic = findViewById(R.id.temperaturePic);
        humidityPic = findViewById(R.id.humidityPic);
        soilMoisturePic = findViewById(R.id.soilMoisturePic);
        visualStream = findViewById(R.id.visualStream);


        rootDatabase = FirebaseDatabase.getInstance().getReference().child("user").child("smart123456");

           monitoring();
           lights();
           fan();
           sprinkler();
           stream();
           settingConfigFragment();

    }




    void monitoring(){

        rootDatabase.child("monitoring").child("temperature").addValueEventListener(new ValueEventListener() {
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


        rootDatabase.child("monitoring").child("humidity").addValueEventListener(new ValueEventListener() {
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


        rootDatabase.child("monitoring").child("soil").addValueEventListener(new ValueEventListener() {
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

    }

    void lights(){

        rootDatabase.child("control").child("lights").child("status").addValueEventListener(new ValueEventListener() {
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
                rootDatabase.child("control").child("lights").child("status").setValue("ON");



            }
        });

        onLight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLight.setVisibility(View.GONE);
                offLight.setVisibility(View.VISIBLE);
                rootDatabase.child("control").child("lights").child("status").setValue("OFF");

            }
        });

    }

    void fan(){

        rootDatabase.child("control").child("fan").child("status").addValueEventListener(new ValueEventListener() {
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
                rootDatabase.child("control").child("fan").child("status").setValue("ON");


            }
        });

        onFan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFan.setVisibility(View.GONE);
                offFan.setVisibility(View.VISIBLE);
                rootDatabase.child("control").child("fan").child("status").setValue("OFF");


            }
        });

    }

    void sprinkler(){

        rootDatabase.child("control").child("sprinkler").child("status").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String data1 = snapshot.getValue().toString();
                    if(data1.equals("OFF")){
                        onSprinkler.setVisibility(View.GONE);
                        offSprinkler.setVisibility(View.VISIBLE);
                    }else{
                        offSprinkler.setVisibility(View.GONE);
                        onSprinkler.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        offSprinkler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                offSprinkler.setVisibility(View.GONE);
                onSprinkler.setVisibility(View.VISIBLE);
                rootDatabase.child("control").child("sprinkler").child("status").setValue("ON");

            }
        });

        onSprinkler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSprinkler.setVisibility(View.GONE);
                offSprinkler.setVisibility(View.VISIBLE);
                rootDatabase.child("control").child("sprinkler").child("status").setValue("OFF");

            }
        });

    }

    void stream(){

        offStream.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                offStream.setVisibility(View.GONE);
                onStream.setVisibility(View.VISIBLE);
                visualStream.setWebViewClient(new WebViewClient());
                WebSettings webSettings = visualStream.getSettings();
                //webSettings.setBuiltInZoomControls(true);
                visualStream.setInitialScale(10);
                webSettings.setDefaultZoom(WebSettings.ZoomDensity.FAR);
                webSettings.setUseWideViewPort(true);
                webSettings.setJavaScriptEnabled(true);
                visualStream.loadUrl("http://192.168.254.125");
                visualStream.setVisibility(View.VISIBLE);

            }
        });

        onStream.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onStream.setVisibility(View.GONE);
                offStream.setVisibility(View.VISIBLE);
                visualStream.setVisibility(View.GONE);
            }
        });
    }

    void settingConfigFragment(){

        fanPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FanDialogFrag fanDialogFrag = new FanDialogFrag();
                fanDialogFrag.show(getSupportFragmentManager(),"FanDialog");

            }
        });

        sprinklerPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SprinklerDialogFrag sprinklerDialogFrag = new SprinklerDialogFrag();
                sprinklerDialogFrag.show(getSupportFragmentManager(),"SprinklerDialog");
            }
        });


        temperaturePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        humidityPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        soilMoisturePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }


}