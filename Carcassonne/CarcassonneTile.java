package Carcassonne;

import Common.Sides;
import Graphic.Tile;

public class CarcassonneTile extends Common.Tile{
    boolean village, abbey;
    public CarcassonneTile(SideType top, SideType right, SideType bottom, SideType left, boolean village, boolean abbey){
        super(null, new Sides<SideType>(top,right,bottom,left));
        this.village= village;
        this.abbey= abbey;
    }

    @Override
    public String getGraphicalRepresentation() {
        return null;
    }

    @Override
    public Tile getGraphic() {
        return null;
    }
}
