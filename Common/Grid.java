package Common;

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

    public void place(Tile t, Position p, GridGraphic gridGraphic){
        if(isLegalMove(t,p)) {
            grid[p.getX()][p.getX()]=t;
            t.setUsed(true);
            gridGraphic.updateGraphic();
        }
    }

    public boolean isLegalMove(Tile t, Position p){
        int x= p.getX();
        int y=p.getY();

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
        if(this.leftExist(x,y) &&
                !matchSide((E[]) grid[x][y].getSides().getLeftSide(),(E[]) grid[x-1][y].getSides().getRightSide()))
            return false;
        return true;
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
}
