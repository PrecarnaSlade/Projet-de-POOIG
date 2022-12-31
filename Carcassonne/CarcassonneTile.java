package Carcassonne;

import Common.Sides;

public class CarcassonneTile extends Common.Tile {
    boolean village, abbey;
    public CarcassonneTile(SideType top, SideType right, SideType bottom, SideType left, boolean village, boolean abbey){
        super(null, new Sides<>(top, right, bottom, left), "Carcassonne");
        this.village= village;
        this.abbey= abbey;
    }

    public CarcassonneTile() {
        super(null, new Sides<>(null, null, null, null), "Carcassonne");
        // Needs to be changed
    }

    @Override
    public String getGraphicalRepresentation() {
        return null;
    }
}
