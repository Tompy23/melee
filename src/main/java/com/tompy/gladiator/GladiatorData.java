package com.tompy.gladiator;

public class GladiatorData {
    private static GladiatorData singleton;
    private Player player;
    private Player npc;
    private boolean playerMoveChoice;
    private PlayGladiatorController controller;

    private GladiatorData() {
    }

    public static GladiatorData get() {
        if (singleton == null) {
            singleton = new GladiatorData();
        }
        return singleton;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getNpc() {
        return npc;
    }

    public void setNpc(Player npc) {
        this.npc = npc;
    }

    public boolean isPlayerMoveChoice() {
        return playerMoveChoice;
    }

    public void setPlayerMoveChoice(boolean playerMoveChoice) {
        this.playerMoveChoice = playerMoveChoice;
    }

    public PlayGladiatorController getController() {
        return controller;
    }

    public void setController(PlayGladiatorController controller) {
        this.controller = controller;
    }
}
