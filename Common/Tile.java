package Common;

import Graphic.TileGraphic;
import MathFuncAndObj.Position;

public abstract class Tile<E> extends InternalObject {
    private final Position position;
    private final Sides<E> sides;
    private final String tileType;
    private TileGraphic graphic;
    private boolean isUsed;

    public Tile(Position pos, Sides<E> pSides, String tileType) {
        super();
        this.position = pos;
        this.graphic = new TileGraphic(tileType, pSides);
        sides = pSides;
        isUsed = false;
        this.tileType = tileType;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }

    public Position getPosition() {
        return position;
    }

    public Sides<E> getSides() {
        return sides;
    }

    public void updateGraphic() {
        this.graphic = new TileGraphic(tileType, this.sides);
    }

    @Override
    public String toString() {
        return "Common.Tile{" +
                ", position=" + position +
                '}';
    }

    public abstract String getGraphicalRepresentation();

    public TileGraphic getGraphic() {
        return graphic;
    }
}
