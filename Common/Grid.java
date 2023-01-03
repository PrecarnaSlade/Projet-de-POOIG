package Common;

import Exceptions.InvalidMoveException;
import Graphic.GridGraphic;
import MathFuncAndObj.Position;

import java.awt.*;

public class Grid<E> extends InternalObject {
    private Tile[][] grid;
    private int width;
    private int height;

    public Grid(int pWidth, int pHeight) {
        super();
        grid = new Tile[pWidth][pHeight];
        width= pWidth;
        height= pHeight;
    }

    public Grid(Dimension dimension) {
        this(dimension.width, dimension.height);
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Tile<E> getTileByPos(Position pPos) {
        return getTileByXY(pPos.getX(), pPos.getY());
    }

    public Tile getTileByXY(int x, int y){
        return grid[x][y];
    }

    public void setUp(Tile firstTile){
        grid[width/2][height/2]= firstTile;
    }

    public void place(Tile t, Position p, GridGraphic gridGraphic) throws InvalidMoveException {
        if(isLegalMove(t,p)) {
            grid[p.getX()][p.getX()]=t;
            t.setUsed(true);
            gridGraphic.updateGraphic();
        } else {
            throw new InvalidMoveException("Invalid Move");
        }
    }

    public void place(Tile t, int x, int y) throws InvalidMoveException {
        if(isLegalMove(t,x,y)) {
            grid[x][y]=t;
            t.setUsed(true);
        } else {
            throw new InvalidMoveException("Invalid Move");
        }
    }

    public void forcePlace(Tile t, Position p, GridGraphic gridGraphic) {
        grid[p.getX()][p.getX()]=t;
        t.setUsed(true);
        gridGraphic.updateGraphic();
    }

    public boolean isLegalMove(Tile t, Position p){
        return isLegalMove(t, p.getX(), p.getY());
    }

    public boolean isLegalMove(Tile t, int x, int y) {
        if (!topExist(x,y) && !rightExist(x,y) && !bottomExist(x,y) && !leftExist(x,y)) return false;
        if(!this.isEmpty(x,y)) return false;
        return matchSides(x,y);
    }



    public boolean isEmpty(int x,int y){
        return grid[x][y]==null;
    }

    private boolean matchSides(int x, int y){
        if(this.topExist(x,y) &&
                !matchSide((E[]) grid[x][y].getSides().getUpSide(),(E[]) grid[x][y-1].getSides().getDownSide()))
            return false;
        if(this.rightExist(x,y) &&
                !matchSide((E[]) grid[x][y].getSides().getRightSide(),(E[]) grid[x+1][y].getSides().getLeftSide()))
            return false;
        if(this.bottomExist(x,y) &&
                !matchSide((E[]) grid[x][y].getSides().getDownSide(),(E[]) grid[x][y+1].getSides().getUpSide()))
            return false;
        return !this.leftExist(x, y) ||
                matchSide((E[]) grid[x][y].getSides().getLeftSide(), (E[]) grid[x - 1][y].getSides().getRightSide());
    }

    private boolean topExist(int x, int y){
        return y-1>=0 && grid[x][y]!=null;
    }

    private boolean rightExist(int x, int y){
        return x+1<width && grid[x][y]!=null;
    }

    private boolean bottomExist(int x, int y){
        return y+1<height && grid[x][y]!=null;
    }

    private boolean leftExist(int x, int y){
        return x-1>=0 && grid[x][y]!=null;
    }

    private boolean matchSide(E[] side1, E[] side2){
        for(int i=0; i<side1.length;i++){
            if(!side1[i].equals(side2[i])) return false;
        }
        return true;
    }

    public boolean canPlace(Tile tile) {
//        for (int i = 0; i < 4; i++) {
//            for (int x = 0; x < this.width; x++) {
//                for (int y = 0; y < this.height; y++) {
//                    if (isLegalMove(tile, x, y)) {
//                        return true;
//                    }
//                }
//            }
//            tile.rotateClockwise();
//        }
//        return false;
        return true;
    }
}
