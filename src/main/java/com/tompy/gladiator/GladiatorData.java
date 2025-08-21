package com.tompy.gladiator;

public class GladiatorData {
    private static GladiatorData singleton;
    private final Player player;
    private final Player npc;

    private GladiatorData(Builder builder) {
        this.player = builder.player;
        this.npc = builder.npc;
        singleton = this;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static GladiatorData get() {
        if (singleton != null) {
            return singleton;
        } else {
            throw new RuntimeException("Gladiator Data must be initialized");
        }
    }

    public Player getPlayer() {
        return player;
    }

    public static class Builder {
        private Player player;
        private Player npc;

        public Builder player(Player player) {
            this.player = player;
            return this;
        }

        public Builder npc(Player npc) {
            this.npc = npc;
            return this;
        }

        public void init() {
            new GladiatorData(this);
        }
    }
}
