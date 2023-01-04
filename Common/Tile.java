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
        sides = pSides;
        isUsed = false;
        this.tileType = tileType;
        this.graphic = new TileGraphic(tileType, pSides, this);
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

    public E getUpSide(){
        return this.getSides().getUpSide();
    }
    public E getRightSide(){
        return this.getSides().getRightSide();
    }
    public E getDownSide(){
        return this.getSides().getDownSide();
    }
    public E getLeftSide(){
        return this.getSides().getLeftSide();
    }

    public void updateGraphic() {
        this.graphic = new TileGraphic(tileType, this.sides, this);
    }

    @Override
    public String toString() {
        return "Common.Tile{" +
                ", position=" + position +
                '}';
    }

    public abstract String getGraphicalRepresentation();

    public abstract void rotateClockwise();

    public abstract void rotateAntiClockwise();

    public TileGraphic getGraphic() {
        return graphic;
    }
}
