package com.haruham.game.util;

/**
 * Created on 10/4/2015.
 */
public enum Event {
    EVENT_CHARACTER_DEATH("default"),
    EVENT_CHARACTER_HIT("default");

    private String value;

    Event(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
