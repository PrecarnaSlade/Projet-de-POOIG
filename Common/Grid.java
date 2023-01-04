package Common;

import Carcassonne.CarcassonneTile;
import Carcassonne.SideType;
import Domino.DominoTile;
import Exceptions.InvalidMoveException;
import Graphic.GridGraphic;
import Misc.ArrayManagement;
import Misc.Position;

import java.awt.*;

public class Grid<E> extends InternalObject {
    private final Tile[][] grid;
    private final int width;
    private final int height;

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
            grid[p.getX()][p.getY()]=t;
            t.setUsed(true);
            t.setPosition(p);
            gridGraphic.updateGraphic();
        } else {
            throw new InvalidMoveException();
        }
    }

    public void place(Tile t, int x, int y, GridGraphic gridGraphic) throws InvalidMoveException {
        if(isLegalMove(t,x,y)) {
            grid[x][y]=t;
            t.setUsed(true);
            t.setPosition(new Position(x, y));
            gridGraphic.updateGraphic();
        } else {
            throw new InvalidMoveException();
        }
    }

    public void forcePlace(Tile t, Position p, GridGraphic gridGraphic) {
        grid[p.getX()][p.getY()]=t;
        t.setUsed(true);
        gridGraphic.updateGraphic();
    }

    public boolean isLegalMove(Tile t, Position p){
        return isLegalMove(t, p.getX(), p.getY());
    }

    public boolean isLegalMove(Tile t, int x, int y) {
        if (!topExist(x,y) && !rightExist(x,y) && !bottomExist(x,y) && !leftExist(x,y)) return false;
        if(!this.isEmpty(x,y)) return false;
        if (t instanceof DominoTile) {
            return matchSides(x, y, (DominoTile) t);
        } else {
          return matchSides(x, y, (CarcassonneTile) t) ;
        }
    }


    public boolean isEmpty(int x,int y){
        return grid[x][y]==null;
    }

    private boolean matchSides(int x, int y, DominoTile t){
        if(this.topExist(x,y) && !matchSide(t.getSides().getTopSide(), (int[]) grid[x][y+1].getSides().getBottomSide()))
            return false;
        if(this.rightExist(x,y) && !matchSide(t.getSides().getRightSide(), (int[]) grid[x+1][y].getSides().getLeftSide()))
            return false;
        if(this.bottomExist(x,y) &&  !matchSide(t.getSides().getBottomSide(), (int[]) grid[x][y-1].getSides().getTopSide()))
            return false;
        return !this.leftExist(x, y) || matchSide(t.getSides().getLeftSide(), (int[]) grid[x - 1][y].getSides().getRightSide());
    }

    private boolean matchSides(int x, int y, CarcassonneTile t){
        if(this.topExist(x,y) &&
                !matchSide(t.getSides().getTopSide(), (SideType) grid[x][y+1].getSides().getBottomSide()))
            return false;
        if(this.rightExist(x,y) &&
                !matchSide(t.getSides().getRightSide(), (SideType) grid[x+1][y].getSides().getLeftSide()))
            return false;
        if(this.bottomExist(x,y) &&
                !matchSide(t.getSides().getBottomSide(), (SideType) grid[x][y-1].getSides().getTopSide()))
            return false;
        return !this.leftExist(x, y) ||
                matchSide(t.getSides().getLeftSide(), (SideType) grid[x - 1][y].getSides().getRightSide());
    }

    private boolean topExist(int x, int y){
        return y+1<height && grid[x][y+1]!=null;
    }

    private boolean rightExist(int x, int y){
        return x+1<width && grid[x+1][y]!=null;
    }

    private boolean bottomExist(int x, int y){
        return y-1>=0 && grid[x][y-1]!=null;
    }

    private boolean leftExist(int x, int y){
        return x-1>=0 && grid[x-1][y]!=null;
    }

    private boolean matchSide(int[] side1, int[] side2){
        for(int i=0; i<side1.length;i++){
            if(side1[i] != side2[i]) return false;
        }
        return true;
    }

    private boolean matchSide(SideType side1, SideType side2){
        return side1 == side2;
    }

    public boolean canPlaceHere(Tile t, int x, int y) {
        return isLegalMove(t, x, y);
    }

    public Position[] getPositionsAvailable(Tile t) {
        Position[] aPositionAvailable = new Position[0];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < this.height; y++) {
                if (canPlaceHere(t, x, y)) {
                    aPositionAvailable = ArrayManagement.add(aPositionAvailable, new Position(x, y));
                }
            }
        }
        return aPositionAvailable;
    }
}
