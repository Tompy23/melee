package com.tompy.game;

public class GameProperty<T> {
    private T value;
    private boolean isChanged;

    public GameProperty() {
        value = null;
        isChanged = false;
    }

    public void set(T value) {
        if (!value.equals(this.value)) {
            this.value = value;
            isChanged = true;
        }
    }

    public T get() {
        isChanged = false;
        return value;
    }

    public boolean isChanged() {
        return isChanged;
    }
}
