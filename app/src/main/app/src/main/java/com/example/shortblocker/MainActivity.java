package com.example.shortblocker;

import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private boolean isBlocking = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button toggleButton = findViewById(R.id.toggleButton);

        toggleButton.setOnClickListener(v -> {
            isBlocking = !isBlocking;
            if (isBlocking) {
                toggleButton.setText("Pause Blocking");
            } else {
                toggleButton.setText("Resume Blocking");
            }
        });
    }
}
