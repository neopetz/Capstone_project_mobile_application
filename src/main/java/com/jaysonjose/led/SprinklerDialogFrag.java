package com.jaysonjose.led;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SprinklerDialogFrag extends DialogFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sprinkler_fragment,null);

        final DatabaseReference rootDatabase;

        Button btnSaveSprinkler = view.findViewById(R.id.btnSaveSprinkler);
        Button btnResetSprinkler = view.findViewById(R.id.btnResetSprinkler);


        final TextView sprinklerDataSpeed =  view.findViewById(R.id.sprinklerDataSpeed);
        final TextView sprinklerDataTimer =  view.findViewById(R.id.sprinklerDataTimer);
        final SeekBar sprinklerSpeedSeekBar =  view.findViewById(R.id.sprinklerSpeedSeekBar);
        final SeekBar sprinklerTimerSeekBar =  view.findViewById(R.id.sprinklerTimerSeekBar);

        rootDatabase = FirebaseDatabase.getInstance().getReference().child("user").child("smart123456");


        rootDatabase.child("control").child("sprinkler").child("flow").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    int speedData = Integer.parseInt(snapshot.getValue().toString());
                    sprinklerSpeedSeekBar.setProgress(speedData);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        rootDatabase.child("control").child("sprinkler").child("time").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    int timeData = Integer.parseInt(snapshot.getValue().toString());
                    sprinklerTimerSeekBar.setProgress(timeData);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        sprinklerSpeedSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                sprinklerDataSpeed.setText("" +progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        sprinklerTimerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                sprinklerDataTimer.setText(""+progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        btnSaveSprinkler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String sprinklerSpeed = sprinklerDataSpeed.getText().toString();
                String sprinklerTimer = sprinklerDataTimer.getText().toString();

                rootDatabase.child("control").child("sprinkler").child("flow").setValue(""+sprinklerSpeed);
                rootDatabase.child("control").child("sprinkler").child("time").setValue(""+sprinklerTimer);

                Toast.makeText(getActivity(),"Save Successfully ",Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });

        btnResetSprinkler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sprinklerDataSpeed.setText("0");
                sprinklerDataTimer.setText("0");
                sprinklerSpeedSeekBar.setProgress(0);
                sprinklerTimerSeekBar.setProgress(0);
            }
        });

        return view;
    }
}
