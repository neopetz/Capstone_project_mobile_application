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

public class FanDialogFrag extends DialogFragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fan_fragment,null);

        final DatabaseReference rootDatabase;

        Button btnSave = view.findViewById(R.id.btnSave);
        Button btnReset = view.findViewById(R.id.btnReset);
        final TextView fanDataSpeed =  view.findViewById(R.id.fanDataSpeed);
        final TextView fanDataTimer =  view.findViewById(R.id.fanDataTimer);
        final SeekBar fanSpeedSeekBar =  view.findViewById(R.id.fanSpeedSeekBar);
        final SeekBar fanTimerSeekBar =  view.findViewById(R.id.fanTimerSeekBar);

        rootDatabase = FirebaseDatabase.getInstance().getReference().child("user").child("smart123456");


        rootDatabase.child("control").child("fan").child("speed").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    int speedData = Integer.parseInt(snapshot.getValue().toString());
                    fanSpeedSeekBar.setProgress(speedData);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        rootDatabase.child("control").child("fan").child("time").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    int timeData = Integer.parseInt(snapshot.getValue().toString());
                    fanTimerSeekBar.setProgress(timeData);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        fanSpeedSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                fanDataSpeed.setText(""+progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {




            }
        });


        fanTimerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                fanDataTimer.setText(""+progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {


            }
        });



        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fanSpeed = fanDataSpeed.getText().toString();
                String fanTimer = fanDataTimer.getText().toString();

                rootDatabase.child("control").child("fan").child("speed").setValue(""+fanSpeed);
                rootDatabase.child("control").child("fan").child("time").setValue(""+fanTimer);

                Toast.makeText(getActivity(),"Save Successfully ",Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fanDataSpeed.setText("0");
                fanDataTimer.setText("0");
                fanSpeedSeekBar.setProgress(0);
                fanTimerSeekBar.setProgress(0);
            }
        });



        return  view;
    }

}








