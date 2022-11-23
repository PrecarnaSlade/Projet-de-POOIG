import MathFuncAndObj.Position;

public abstract class Tuile extends Object {
    private final Position position;

    public Tuile(Position pos) {
        super();
        this.position = pos;
    }

    public Position getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "Tuile{" +
                ", position=" + position +
                '}';
    }

    public abstract String getGraphicalRepresentation();
}
