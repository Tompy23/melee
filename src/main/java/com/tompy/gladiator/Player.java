package com.tompy.gladiator;

import com.tompy.counter.Counter;
import com.tompy.gladiator.details.Armor;
import com.tompy.gladiator.details.Shield;
import com.tompy.hexboard.Hex;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Player {
    private final String name;
    private final GladiatorType type;
    private Map<BodyArea, Integer> bodyAreaWoundRecord = new HashMap<>();
    private Map<BodyArea, Armor> bodyArmor = new HashMap<>();

    private Counter counter;
    private int movementRate;
    private int stunFactors;
    private int combatFactors;
    private int training;
    private int strength;
    private int agility;
    private int wounds;
    private int constitution;
    private Shield shield;
    private Hex moveToHex;
    private int moveToRotation;

    public Player(Builder builder) {
        this.name = builder.name;
        this.type = GladiatorType.getType(builder.type);
        this.movementRate = type.movementRate;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Counter getCounter() {
        return counter;
    }

    public void setCounter(Counter counter) {
        this.counter = counter;
    }

    public Hex getMoveToHex() {
        return moveToHex;
    }

    public void setMoveToHex(Hex moveToHex) {
        this.moveToHex = moveToHex;
    }

    public int getMoveToRotation() {
        return moveToRotation;
    }

    public void setMoveToRotation(int moveToRotation) {
        this.moveToRotation = moveToRotation;
    }

    public void setCharacteristics(int[] characteristics) {
        training = characteristics[0];
        strength = characteristics[1];
        agility = characteristics[2];
        constitution = characteristics[3];
        wounds = characteristics[4];

        Arrays.stream(BodyArea.values()).forEach(a -> bodyAreaWoundRecord.put(a, wounds));
        combatFactors = training + strength + agility;
    }

    public Integer getBodyAreaWoundRecord(BodyArea area) {
        return bodyAreaWoundRecord.get(area);
    }

    public Armor getBodyArmor(BodyArea area) {
        return bodyArmor.get(area);
    }

    public void setBodyArmor(BodyArea area, Armor armor) {
        bodyArmor.put(area, armor);
    }

    public String getName() {
        return name;
    }

    public GladiatorType getType() {
        return type;
    }

    public int getMovementRate() {
        return movementRate;
    }

    public int getStunFactors() {
        return stunFactors;
    }

    public void setStunFactors(int stunFactors) {
        this.stunFactors = stunFactors;
    }

    public int getCombatFactors() {
        return combatFactors;
    }

    public int getTraining() {
        return training;
    }

    public int getStrength() {
        return strength;
    }

    public int getAgility() {
        return agility;
    }

    public int getWounds() {
        return wounds;
    }

    public int getConstitution() {
        return constitution;
    }

    public int getNetDefenseFactor() {
        return training + agility;
    }

    public Shield getShield() {
        return shield;
    }

    public void setShield(Shield shield) {
        this.shield = shield;
    }

    public enum BodyArea {
        HEAD("Head", 1),
        CHEST("Chest", 2),
        GROIN("Groin", 3),
        ARMS("Arms", 4),
        LEGS("Legs", 5);

        private String text;
        private int location;

        private Map<Integer, BodyArea> areaMap;

        BodyArea(String t, int l) {
            text = t;
            location = l;
        }

        public String getText() {
            return text;
        }

        public int getLocation() {
            return location;
        }

        public static BodyArea getBodyArea(int l) {
            return Arrays.stream(BodyArea.values()).filter(t -> t.getLocation() == l).findFirst()
                    .orElse(BodyArea.HEAD);
        }
    }

    public enum GladiatorType {
        LIGHT("Light", 6),
        MEDIUM("Medium", 5),
        HEAVY("Heavy", 4),
        RETARIUS("Retarius", 5);

        private String text;
        private int movementRate;

        GladiatorType(String t, int m) {
            text = t;
            movementRate = m;
        }

        public String getText() {
            return text;
        }

        public int getMovementRate() {
            return movementRate;
        }

        public static GladiatorType getType(String s) {
            return Arrays.stream(GladiatorType.values()).filter(t -> t.getText().equals(s)).findFirst()
                    .orElse(GladiatorType.MEDIUM);
        }
    }

    public static class Builder {
        private String name;
        private String type;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder type(String type) {
            this.type = type;
            return this;
        }

        public Player build() {
            return new Player(this);
        }
    }
}
