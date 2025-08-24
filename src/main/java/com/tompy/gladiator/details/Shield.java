package com.tompy.gladiator.details;

public class Shield {
    public enum ShieldType {
        SMALL ("Small"),
        LARGE ("Large");

        private String text;

        ShieldType(String t) {
            text = t;
        }

        public String getText() {
            return text;
        }
    }
    private final ShieldType type;
    private final int status;

    public Shield(ShieldType type) {
        this.type = type;
        this.status = 17;
    }

    public ShieldType getType() {
        return type;
    }

    public int getStatus() {
        return status;
    }
}
