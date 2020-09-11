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

public class FanDialogFrag extends DialogFragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fan_fragment,null);

        Button btnSave = view.findViewById(R.id.btnSave);
        final TextView fanDataSpeed =  view.findViewById(R.id.fanDataSpeed);
        final TextView fanDataTimer =  view.findViewById(R.id.fanDataTimer);
        SeekBar fanSpeedSeekBar =  view.findViewById(R.id.fanSpeedSeekBar);
        SeekBar fanTimerSeekBar =  view.findViewById(R.id.fanTimerSeekBar);

        fanSpeedSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                fanDataSpeed.setText("" +progress);
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
                Toast.makeText(getActivity(),"Save Successfully ",Toast.LENGTH_LONG).show();
                dismiss();
            }
        });

        return  view;
    }

}








