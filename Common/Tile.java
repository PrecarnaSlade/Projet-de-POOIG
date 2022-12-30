package Common;

import MathFuncAndObj.Position;

public abstract class Tile<E> extends InternalObject {
    private final Position position;
    private final Sides<E> sides;
    private final Graphic.Tile graphic;
    private boolean isUsed;

    public Tile(Position pos, Sides<E> pSides, String tileType) {
        super();
        this.position = pos;
        this.graphic = new Graphic.Tile(tileType, pSides);
        sides = pSides;
        isUsed = false;
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

    @Override
    public String toString() {
        return "Common.Tile{" +
                ", position=" + position +
                '}';
    }

    public abstract String getGraphicalRepresentation();

    public Graphic.Tile getGraphic() {
        return graphic;
    }
}
