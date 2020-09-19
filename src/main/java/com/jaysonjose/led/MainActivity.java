package com.jaysonjose.led;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
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

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Button btnLogin;
    private TextToSpeech mTTS;
    private DatabaseReference rootDatabase;
    private TextView userText,passText;
    String username="",dbPassword="none",password="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        rootDatabase = FirebaseDatabase.getInstance().getReference().child("user");
        btnLogin = findViewById(R.id.btnLogin);
        userText = findViewById(R.id.userText);
        passText = findViewById(R.id.passText);



        mTTS = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status == TextToSpeech.SUCCESS){
                    int result = mTTS.setLanguage(Locale.US);
                    if(result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                    //    Toast.makeText(MainActivity.this,"language not supported ",Toast.LENGTH_SHORT).show();
                    }else{
                      //  Toast.makeText(MainActivity.this,"language success ",Toast.LENGTH_SHORT).show();
                    }

                }else {
                    //Toast.makeText(MainActivity.this,"Invalid Initialization ",Toast.LENGTH_SHORT).show();
                }
            }
        });





        login();
    }




    void login(){
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                username = userText.getText().toString();
                password = passText.getText().toString();



                            String text = "welcome to smart greenhouse monitoring system";
                           mTTS.setPitch(0.9f);
                           mTTS.setSpeechRate(0.9f);
                            mTTS.speak(text, TextToSpeech.QUEUE_FLUSH,null);
                            Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                            startActivity(intent);



            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();


        if(mTTS != null){
            mTTS.stop();
            mTTS.shutdown();
        }
    }
}

