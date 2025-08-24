package com.tompy.gladiator.details;

import com.tompy.gladiator.Player;

import java.util.Arrays;

public class Armor {
    private static String[][] lightTable = new String[][] {
            {"A5", "-", "-", "-", "-"},
            {"C6", "-", "-", "-", "-"},
            {"-", "-", "C0", "-", "-"},
            {"-", "-", "-", "-", "-"},
            {"-", "-", "-", "-", "-"},
            {"A7", "-", "C0", "-", "-"}
    };

    private static String[][] mediumTable = new String[][] {
            {"A7", "C0", "C0", "C8", "C8"},
            {"A8", "B7", "-", "B7", "A6"},
            {"A0", "-", "C0", "C8", "A7"},
            {"A0", "-", "C0", "C0", "C8"},
            {"A0", "C6", "-", "C7", "B7"},
            {"A0", "B8", "-", "B7", "A7"}
    };

    private static String[][] heavyTable = new String[][] {
            {"A7", "B8", "C0", "B7", "B7"},
            {"A0", "B8", "C0", "B7", "B7"},
            {"A0", "B7", "C0", "B7", "A8"},
            {"A0", "B7", "A5", "B7", "A7"},
            {"A0", "B7", "C0", "B7", "A7"},
            {"A0", "C7", "C0", "B7", "A7"}
    };

    public enum ArmorType {
        A ("A"),
        B ("B"),
        C ("C"),
        NONE ("-");

        private String text;

        ArmorType(String text) {
            this.text = text;
        }

        public static ArmorType getArmorType(String s) {
            return Arrays.stream(ArmorType.values()).filter(a -> a.text.equals(s)).findFirst().orElse(NONE);
        }
    }
    private final ArmorType type;
    private final int coverage;

    public Armor(ArmorType type, int coverage) {
        this.type = type;
        this.coverage = coverage;
    }

    public ArmorType getType() {
        return type;
    }

    public int getCoverage() {
        return coverage;
    }

    public static String[] getArmorEntry(Player.GladiatorType type, int i) {
        switch (type) {
            case LIGHT:
                return lightTable[i];
            case MEDIUM:
            case RETARIUS:
                return mediumTable[i];
            case HEAVY:
                return heavyTable[i];
            default:
                return null;
        }
    }
}
