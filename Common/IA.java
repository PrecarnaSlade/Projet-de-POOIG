package Common;

import Exceptions.InvalidMoveException;
import Graphic.GridGraphic;
import Misc.Position;

import java.io.IOException;

public class IA extends Player {

    public IA(String name) {
        super(name, true);
    }

    public void playTurn(Tile tileToPlace, Grid grid, GridGraphic gridGraphic) {
        Position[] aPossiblePosition = grid.getPositionsAvailable(tileToPlace);
        boolean bool = true;
        while (bool) {
            try {
                grid.place(tileToPlace, aPossiblePosition[0], gridGraphic);
                bool = false;
            } catch (InvalidMoveException e) {
                tileToPlace.rotateClockwise();
            }
        }
    }
}
