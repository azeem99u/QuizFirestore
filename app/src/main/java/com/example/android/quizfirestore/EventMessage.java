package com.example.android.quizfirestore;

public class EventMessage {
    private boolean isBackPressed = false;

    public EventMessage(boolean isBackPressed) {
        this.isBackPressed = isBackPressed;
    }

    public boolean isBackPressed() {
        return isBackPressed;
    }
}
