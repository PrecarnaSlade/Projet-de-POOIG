package Common;

import MathFuncAndObj.Position;

public class Grille<E> {
    private Tuile[][] grid;

    public Grille(int pWidth, int pHeight) {
        grid = new Tuile[pWidth][pHeight];
    }

    public Tuile<E> getTileByPos(Position pPos) {
        return getTileByXY(pPos.getX(), pPos.getY());
    }

    public Tuile getTileByXY(int x, int y){
        return grid[x][y];
    }
}
