package com.example.reelblocker;

import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Toast;

public class BlockService extends AccessibilityService {

    private boolean blockingEnabled = true;

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        if (!blockingEnabled) return;

        // Get package name of the current app
        if (event.getPackageName() != null &&
                event.getPackageName().toString().equals("com.google.android.youtube")) {

            String text = event.getText().toString().toLowerCase();

            // If Shorts/Reels detected
            if (text.contains("shorts")) {
                Toast.makeText(this, "⚠ Shorts Blocked", Toast.LENGTH_SHORT).show();
                performGlobalAction(GLOBAL_ACTION_BACK); // Go back instantly
            }
        }
    }

    @Override
    public void onInterrupt() { }

    public void setBlockingEnabled(boolean enabled) {
        this.blockingEnabled = enabled;
    }
}
