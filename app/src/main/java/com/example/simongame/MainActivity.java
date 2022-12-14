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

        binding.buttonTestBinding1.setOnClickListener(view -> {
            buttonSequenceUser.add(String.valueOf(binding.buttonTestBinding1.getId()));
            System.out.println(binding.buttonTestBinding1.getId());
        });

        binding.buttonTestBinding2.setOnClickListener(view -> {
            buttonSequenceUser.add(String.valueOf(binding.buttonTestBinding2.getId()));
            System.out.println(binding.buttonTestBinding2.getId());
        });

        binding.buttonTestBinding3.setOnClickListener(view -> {
            buttonSequenceUser.add(String.valueOf(binding.buttonTestBinding3.getId()));
            System.out.println(binding.buttonTestBinding3.getId());
        });

        binding.buttonTestBinding4.setOnClickListener(view -> {
            buttonSequenceUser.add(String.valueOf(binding.buttonTestBinding4.getId()));
            System.out.println(binding.buttonTestBinding4.getId());
        });

        binding.buttonStart.setOnClickListener(view -> {
            binding.buttonStart.setEnabled(false);
            setButtonIds(buttonIds);
            play();
        });

    }

    private void play() {
        generatedSequenceOfButtons(buttonIds, buttonSequenceGenerated);
//        buttonSequenceGenerated.add("2131230820");
//        buttonSequenceGenerated.add("2131230821");
//        buttonSequenceGenerated.add("2131230823");
//        buttonSequenceGenerated.add("2131230822");
        selectButtonInGeneratedSequence(cursore,  buttonSequenceGenerated);
    }

    private void selectButtonInGeneratedSequence(int cursore, @NonNull ArrayList<String> buttonSequenceGenerated) {
        buttonSequenceUser.clear();
        if (cursore < buttonSequenceGenerated.size()) {
            setTimeout(() -> {
                changeButtonAlpha(buttonSequenceGenerated, cursore, 0.5f);
                setTimeout(() -> {
                    this.runOnUiThread(() -> {
                        changeButtonAlpha(buttonSequenceGenerated, cursore, 1.0f);
                    });
                }, 500);
                selectButtonInGeneratedSequence(cursore + 1, buttonSequenceGenerated);
            }, 1000);
        }
        else {
            setTimeout(() -> checkSequences(buttonSequenceGenerated, buttonSequenceUser), 5000);
        }
    }

    private void checkSequences(ArrayList<String> buttonSequenceGenerated, ArrayList<String> buttonSequenceUser) {
            if (buttonSequenceUser.equals(buttonSequenceGenerated)) {
                play();
            } else {
                System.out.println("NO");
                System.out.println("g: "+buttonSequenceGenerated);
                System.out.println("u: "+buttonSequenceUser);
            }

    }

    private void changeButtonAlpha(ArrayList<String> buttonSequenceGenerated, int cursore, float opacity) {
        findViewById(Integer.parseInt(buttonSequenceGenerated.get(cursore))).setAlpha(opacity);
    }

    private void setButtonIds(@NonNull ArrayList<String> buttonIds) {
        buttonIds.add("2131230820");
        buttonIds.add("2131230821");
        buttonIds.add("2131230823");
        buttonIds.add("2131230822");
    }

    private ArrayList<String> generatedSequenceOfButtons(@NonNull ArrayList<String> buttonIds, @NonNull ArrayList<String> buttonSequenceGenerated) {
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