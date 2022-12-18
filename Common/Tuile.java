package Common;

import MathFuncAndObj.Position;

public abstract class Tuile<E> {
    private final Position position;
    private final Sides<E> sides;

    public Tuile(Position pos, Sides<E> pSides) {
        super();
        this.position = pos;
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
        return "Common.Tuile{" +
                ", position=" + position +
                '}';
    }

    public abstract String getGraphicalRepresentation();
}
