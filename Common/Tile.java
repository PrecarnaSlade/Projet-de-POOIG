package Common;

import MathFuncAndObj.Position;

public abstract class Tile<E> extends InternalObject {
    private final Position position;
    private final Sides<E> sides;
    private Graphic.Tile graphic;

    public Tile(Position pos, Sides<E> pSides) { // !!!!!!!!!!!!! KINDA DEPRECATED, needs to be erased at the end !!!!!!!!!!!!!
        super();
        this.position = pos;
        sides = pSides;
    }

    public Tile(Position pos, Sides<E> pSides, Graphic.Tile graphic) {
        super();
        this.position = pos;
        this.graphic = graphic;
        sides = pSides;
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
