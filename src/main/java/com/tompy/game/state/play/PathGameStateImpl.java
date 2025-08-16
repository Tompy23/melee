package com.tompy.game.state.play;

import com.tompy.game.GameData;
import com.tompy.game.GameProperty;
import com.tompy.game.event.GameFunction;
import com.tompy.game.state.AbstractGameState;
import com.tompy.hexboard.Hex;
import com.tompy.hexboard.state.HexStateFactory;
import com.tompy.hexboard.state.HexStateType;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import java.util.ArrayList;
import java.util.List;

public class PathGameStateImpl extends AbstractGamePlayState {
    private GameProperty<Hex> startHexProp;
    private GameProperty<Hex> targetHexProp;
    private Line line;
    private List<Hex> hexLine;

    public PathGameStateImpl() {
        startHexProp = new GameProperty<>();
        targetHexProp = new GameProperty<>();
        hexLine = new ArrayList<>();
    }

    @Override
    public void beginState() {
        GameData.get().getHexBoard().getHexes().forEach(h -> h.changeState(
                HexStateFactory.builder().type(HexStateType.DRAW_LINE).hex(h).hex2(startHexProp).hex3(targetHexProp).build()));
    }

    @Override
    public void process(long l) {
        if (startHexProp.isChanged() || targetHexProp.isChanged() && startHexProp.peek() != null) {
            hexLine.forEach(h -> h.setFill(Color.TRANSPARENT));
            hexLine.clear();

            hexLine = GameFunction.bfsFindPathCost(startHexProp.get(), targetHexProp.get());

            for (Hex h : hexLine) {
                h.setOpacity(.6);
                h.setFill(Color.PINK);
            }
        }
    }
}
