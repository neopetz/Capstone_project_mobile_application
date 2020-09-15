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

public class SprinklerDialogFrag extends DialogFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sprinkler_fragment,null);


        Button btnSaveSprinkler = view.findViewById(R.id.btnSaveSprinkler);
        final TextView sprinklerDataSpeed =  view.findViewById(R.id.sprinklerDataSpeed);
        final TextView sprinklerDataTimer =  view.findViewById(R.id.sprinklerDataTimer);
        SeekBar sprinklerSpeedSeekBar =  view.findViewById(R.id.sprinklerSpeedSeekBar);
        SeekBar sprinklerTimerSeekBar =  view.findViewById(R.id.sprinklerTimerSeekBar);

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
                Toast.makeText(getActivity(),"Save Successfully ",Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });

        return view;
    }
}
