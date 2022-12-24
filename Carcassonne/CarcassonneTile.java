package Carcassonne;

import Common.Sides;

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
}
