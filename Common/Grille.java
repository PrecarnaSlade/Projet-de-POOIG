package Common;

import MathFuncAndObj.Position;

public class Grille<E> extends InternalObject {
    private Tile[][] grid;

    public Grille(int pWidth, int pHeight) {
        super();
        grid = new Tile[pWidth][pHeight];
    }

    public Tile<E> getTileByPos(Position pPos) {
        return getTileByXY(pPos.getX(), pPos.getY());
    }

    public Tile getTileByXY(int x, int y){
        return grid[x][y];
    }
}
