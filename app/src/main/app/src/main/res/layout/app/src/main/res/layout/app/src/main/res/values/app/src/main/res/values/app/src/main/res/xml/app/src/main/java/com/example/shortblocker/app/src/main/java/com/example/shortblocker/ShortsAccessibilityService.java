package com.example.shortblocker;

import android.accessibilityservice.AccessibilityService;
import android.content.Intent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

public class ShortsAccessibilityService extends AccessibilityService {

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        if (event == null) return;

        boolean enabled = getSharedPreferences("prefs", MODE_PRIVATE)
                .getBoolean("blocking_enabled", true);
        if (!enabled) return;

        CharSequence pkg = event.getPackageName();
        if (pkg == null || !"com.google.android.youtube".contentEquals(pkg)) return;

        AccessibilityNodeInfo root = getRootInActiveWindow();
        if (root == null) return;

        if (containsShortsText(root)) {
            Intent i = new Intent(this, OverlayActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
        }
    }

    private boolean containsShortsText(AccessibilityNodeInfo node) {
        if (node == null) return false;
        try {
            CharSequence t = node.getText();
            if (t != null) {
                String s = t.toString().toLowerCase();
                if (s.contains("shorts")) return true;
            }
            for (int i = 0; i < node.getChildCount(); i++) {
                if (containsShortsText(node.getChild(i))) return true;
            }
        } catch (Exception ignored) {}
        return false;
    }

    @Override
    public void onInterrupt() { }
}
