package Common;

import MathFuncAndObj.Position;

public class Grid<E> extends InternalObject {
    private Tile[][] grid;

    public Grid(int pWidth, int pHeight) {
        super();
        grid = new Tile[pWidth][pHeight];
    }

    public Tile<E> getTileByPos(Position pPos) {
        return getTileByXY(pPos.getX(), pPos.getY());
    }

    public Tile getTileByXY(int x, int y){
        return grid[x][y];
    }

    public void place(Tile t, Position p){
        grid[p.getX()][p.hashCode()]=t;
    }
}
