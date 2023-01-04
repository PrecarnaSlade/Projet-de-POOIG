package Carcassonne;

import Common.Sides;
import Misc.RandomManagement;
import Misc.StringManagement;

import java.util.Random;

public class CarcassonneTile extends Common.Tile<SideType> {
    private boolean village, abbey, separated, monastery;
    private String[] correspondingFilePath;
    private String[] possibleNames;


    public CarcassonneTile(SideType top, SideType right, SideType bottom, SideType left, boolean village, boolean abbey, boolean separated, boolean monastery){
        super(null, new Sides<>(top, right, bottom, left), "Carcassonne");
        this.village= village;
        this.abbey= abbey;
        this.separated = separated;
        this.monastery = monastery;
        getPossibleNames(this.getSides());
    }

    public CarcassonneTile(SideType top, SideType right, SideType bottom, SideType left) {
        this(top, right, bottom, left, false, false, false, false);
    }

    public CarcassonneTile() {
        super(null, new Sides<>(null, null, null, null), "Carcassonne");
        SideType top = SideType.randomSide();
        SideType right = SideType.randomSide();
        SideType bottom = SideType.randomSide();
        SideType left = SideType.randomSide();

        this.getSides().setUpSide(top);
        this.getSides().setRightSide(right);
        this.getSides().setDownSide(bottom);
        this.getSides().setLeftSide(left);

        getPossibleNames(this.getSides());

        Random rnd = new Random();

        if (StringManagement.countLetterInString(possibleNames[0], 'c') > 1) {
            // if 2 or more sides are city, then x% chance to become a field surrounded by cities (20%)
            this.separated = RandomManagement.isHappening(20);
        }

    }

    protected void getPossibleNames(Sides<SideType> sides) {
        possibleNames = new String[4];
        possibleNames[0] = SideType.toString(sides.getUpSide()) + SideType.toString(sides.getRightSide()) + SideType.toString(sides.getDownSide()) + SideType.toString(sides.getLeftSide());
        possibleNames[1] = SideType.toString(sides.getRightSide()) + SideType.toString(sides.getDownSide()) + SideType.toString(sides.getDownSide()) + SideType.toString(sides.getUpSide());
        possibleNames[2] = SideType.toString(sides.getDownSide()) + SideType.toString(sides.getLeftSide()) + SideType.toString(sides.getUpSide()) + SideType.toString(sides.getRightSide());
        possibleNames[3] = SideType.toString(sides.getLeftSide()) + SideType.toString(sides.getUpSide()) + SideType.toString(sides.getRightSide()) + SideType.toString(sides.getDownSide());
    }

    @Override
    public String getGraphicalRepresentation() {
        return null;
    }

    @Override
    public void rotateClockwise() {

    }

    @Override
    public void rotateAntiClockwise() {

    }
}
