package com.example.simongame;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.simongame.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private final ArrayList<String> buttonIds = new ArrayList<>();
    private ArrayList<String> buttonSequenceGenerated = new ArrayList<>();
    private ArrayList<String> buttonSequenceUser = new ArrayList<>();
    protected ActivityMainBinding binding;
    private int cursore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        setButtonIds(buttonIds);

        binding.buttonStart.setOnClickListener(view -> {

//            buttonSequenceGenerated.add("2131230819");
//            buttonSequenceGenerated.add("2131231202");
//            buttonSequenceGenerated.add("2131231205");
//            buttonSequenceGenerated.add("2131231203");
            generatedSequenceOfButtons(buttonIds, buttonSequenceGenerated);

            selectButtonInGeneratedSequence(cursore,  buttonSequenceGenerated);


        });

        binding.buttonTestBinding1.setOnClickListener(view -> buttonSequenceUser.add(String.valueOf(binding.buttonTestBinding1.getId())));

        binding.buttonTestBinding2.setOnClickListener(view -> buttonSequenceUser.add(String.valueOf(binding.buttonTestBinding2.getId())));

        binding.buttonTestBinding3.setOnClickListener(view -> buttonSequenceUser.add(String.valueOf(binding.buttonTestBinding3.getId())));

        binding.buttonTestBinding4.setOnClickListener(view -> buttonSequenceUser.add(String.valueOf(binding.buttonTestBinding4.getId())));

    }


    private void selectButtonInGeneratedSequence(int cursore, @NonNull ArrayList<String> buttonSequenceGenerated) {
        if (cursore < buttonSequenceGenerated.size()) {
            setTimeout(() -> {
                findViewById(Integer.parseInt(buttonSequenceGenerated.get(cursore))).setAlpha(0.5f);
                setTimeout(() -> {
                    this.runOnUiThread(() -> {
                        findViewById(Integer.parseInt(buttonSequenceGenerated.get(cursore))).setAlpha(1.0f);
                    });
                }, 1000);
                selectButtonInGeneratedSequence(cursore+1, buttonSequenceGenerated);
            }, 2000);
        }

    }

    private void setButtonIds(@NonNull ArrayList<String> buttonIds) {
        buttonIds.add("2131230819");
        buttonIds.add("2131231202");
        buttonIds.add("2131231203");
        buttonIds.add("2131231205");
    }

    private ArrayList<String> generatedSequenceOfButtons(ArrayList<String> buttonIds, ArrayList<String> buttonSequenceGenerated) {
        Random rand = new Random();
        String randomButtonId = buttonIds.get(rand.nextInt(buttonIds.size()));
        buttonSequenceGenerated.add(randomButtonId);
        return buttonSequenceGenerated;
    }

    public static void setTimeout(Runnable runnable, int delay){
        new Thread(() -> {
            try {
                Thread.sleep(delay);
                runnable.run();
            }
            catch (Exception e){
                System.err.println();
            }
        }).start();
    }
}