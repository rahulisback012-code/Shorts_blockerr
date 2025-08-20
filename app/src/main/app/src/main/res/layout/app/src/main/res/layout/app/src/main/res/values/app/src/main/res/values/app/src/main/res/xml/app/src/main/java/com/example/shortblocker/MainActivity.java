package com.example.shortblocker;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private boolean isBlocking = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView status = findViewById(R.id.statusText);
        Button toggle = findViewById(R.id.toggleButton);
        Button openAccessibility = findViewById(R.id.accessibilityButton);

        isBlocking = getSharedPreferences("prefs", MODE_PRIVATE)
                .getBoolean("blocking_enabled", true);
        updateUI(status, toggle);

        toggle.setOnClickListener(v -> {
            isBlocking = !isBlocking;
            getSharedPreferences("prefs", MODE_PRIVATE)
                    .edit().putBoolean("blocking_enabled", isBlocking).apply();
            updateUI(status, toggle);
        });

        openAccessibility.setOnClickListener(v ->
                startActivity(new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)));
    }

    private void updateUI(TextView status, Button toggle) {
        status.setText(isBlocking ? "Blocking: ON" : "Blocking: OFF");
        toggle.setText(isBlocking ? "Pause" : "Resume");
    }
}
