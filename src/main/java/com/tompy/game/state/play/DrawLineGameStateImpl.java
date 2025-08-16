package com.tompy.game.state.play;

import com.tompy.game.GameData;
import com.tompy.game.play.GamePlayData;
import com.tompy.game.GameProperty;
import com.tompy.game.PaneCoordinates;
import com.tompy.game.state.AbstractGameState;
import com.tompy.hexboard.Hex;
import com.tompy.hexboard.HexFunction;
import com.tompy.hexboard.state.HexStateFactory;
import com.tompy.hexboard.state.HexStateType;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import java.util.ArrayList;
import java.util.List;

public class DrawLineGameStateImpl extends AbstractGameState {
    private GameProperty<Hex> startHexProp;
    private GameProperty<Hex> targetHexProp;
    private Line line;
    private List<Hex> hexLine;

    public DrawLineGameStateImpl() {
        startHexProp = new GameProperty<>();
        targetHexProp = new GameProperty<>();
        hexLine = new ArrayList<>();
    }

    @Override
    public void beginState() {
        GameData.get().getHexBoard().getHexes().forEach(h -> h.changeState(HexStateFactory.builder().type(HexStateType.DRAW_LINE).hex(h).hex2(startHexProp).hex3(targetHexProp).build()));
    }

    @Override
    public void process(long l) {
        if (startHexProp.isChanged() || targetHexProp.isChanged() && startHexProp.peek() != null) {
            // reset
            if (line != null) {
                GameData.get().getPaneText().getChildren().remove(line);
            }

            hexLine.forEach(h -> h.setFill(Color.TRANSPARENT));
            hexLine.clear();

            // Set Line
            Hex startHex = startHexProp.get();
            Hex targetHex = targetHexProp.get();

            double startX = startHex.getPaneCoordinates().getX();
            double startY = startHex.getPaneCoordinates().getY();

            double targetX = targetHex.getPaneCoordinates().getX();
            double targetY = targetHex.getPaneCoordinates().getY();

            line = new Line(startX, startY, targetX, targetY);
            GameData.get().getPaneText().getChildren().add(line);

            // Calculate hexes
            long distance = HexFunction.distance(startHex, targetHex);
            List<PaneCoordinates> lerpPoints = new ArrayList<>();
            hexLine.add(startHex);
            for (int i = 1; i < distance + 1; i++) {
                PaneCoordinates pc = lerpCoordinate(startHex.getPaneCoordinates(), targetHex.getPaneCoordinates(), Double.valueOf(i) / Double.valueOf(distance));
                lerpPoints.add(pc);
                Hex h = HexFunction.pixelToHex(pc.getX(), pc.getY());
                if (h != null) {
                    hexLine.add(h);
                }
            }
            for (Hex h : hexLine) {
                h.setOpacity(.6);
                h.setFill(Color.PINK);
            }
        }
    }

    private PaneCoordinates lerpCoordinate(PaneCoordinates start, PaneCoordinates target, double t) {
        return new PaneCoordinates(lerp(start.getX(), target.getX(), t), lerp(start.getY(), target.getY(), t));
    }

    private double lerp(double x, double y, double t) {
        return x * (1.0 - t) + y * t;
    }
}
