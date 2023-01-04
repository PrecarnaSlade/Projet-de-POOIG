package Carcassonne;

import Common.Sides;
import Misc.RandomManagement;
import Misc.StringManagement;

import java.util.Random;

public class CarcassonneTile extends Common.Tile<SideType> {
    private boolean village, town, separated, monastery;
    private String[] correspondingFilePath;
    private String[] possibleNames;


    public CarcassonneTile(SideType top, SideType right, SideType bottom, SideType left, boolean village, boolean separated, boolean monastery, boolean town){
        super(null, new Sides<>(top, right, bottom, left), "Carcassonne");
        this.village= village;
        this.separated = separated;
        this.monastery = monastery;
        this.town = town;
        getPossibleNames(this.getSides());
        updateNames();
        getFilePath();
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

        this.village   = false;
        this.separated = false;
        this.monastery = false;
        this.town      = false;

        getPossibleNames(this.getSides());

        String sFirstPossibleName = possibleNames[0];

        int nCityCount = StringManagement.countLetterInString(sFirstPossibleName, 'c');
        int nFieldCount = StringManagement.countLetterInString(sFirstPossibleName, 'f');
        int nRoadCount = StringManagement.countLetterInString(sFirstPossibleName, 'r');

        if (nCityCount > 1) {
            // if 2 or more city, then there is a chance that is becomes a field surrounded by cities (20%)
            this.separated = RandomManagement.isHappening(20);
        }
        if (nFieldCount == 4) {
            // if 4 fields, then there is a monastery in the center (100%)
            this.monastery = true;
        }
        if (nRoadCount == 0) {
            // if no road, then it's a monastery
            this.monastery = true;
        }
        if (nRoadCount == 1 && nFieldCount == 3) {
            // if 1 road and 3 fields, then it's a village, a town or a monastery (50% for town, 40% for village, 10% for monastery)
            int[] percentages = {50, 40, 10};
            int nChoice = RandomManagement.choiceIsHappening(percentages);
            if (nChoice == 0) {
                this.town = true;
            } else if (nChoice == 1) {
                this.village = true;
            } else {
                this.monastery = true;
            }
        }
        if (nRoadCount > 2) {
            // if 3 or more r, then a village may appear (30%)
            this.village = RandomManagement.isHappening(30);
        }

        updateNames();
        getFilePath();

    }

    protected void getPossibleNames(Sides<SideType> sides) {
        possibleNames = new String[4];
        possibleNames[0] = SideType.toString(sides.getUpSide()) + SideType.toString(sides.getRightSide()) + SideType.toString(sides.getDownSide()) + SideType.toString(sides.getLeftSide());
        possibleNames[1] = SideType.toString(sides.getRightSide()) + SideType.toString(sides.getDownSide()) + SideType.toString(sides.getDownSide()) + SideType.toString(sides.getUpSide());
        possibleNames[2] = SideType.toString(sides.getDownSide()) + SideType.toString(sides.getLeftSide()) + SideType.toString(sides.getUpSide()) + SideType.toString(sides.getRightSide());
        possibleNames[3] = SideType.toString(sides.getLeftSide()) + SideType.toString(sides.getUpSide()) + SideType.toString(sides.getRightSide()) + SideType.toString(sides.getDownSide());
    }

    protected void updateNames() {
        if (this.village) addToNames("v");
        if (this.separated) addToNames("s");
        if (this.monastery) addToNames("m");
        if (this.town) addToNames("t");
    }

    protected void addToNames(String letter) {
        for (int i = 0; i < possibleNames.length; i++) {
            possibleNames[i] += letter;
        }
    }

    protected void getFilePath() {
        String sBasePath = "./Data/Resources/";
        correspondingFilePath = new String[possibleNames.length];
        for (int i = 0; i < possibleNames.length; i++) {
            correspondingFilePath[i] = sBasePath + possibleNames[i] + ".png";
        }
    }

    @Override
    public String getGraphicalRepresentation() {
        return null;
    }

    @Override
    public void rotateClockwise() {
        String sTemp = possibleNames[possibleNames.length - 1];
        for (int i = 1; i < possibleNames.length - 1; i++) {
            possibleNames[i + 1] = possibleNames[i];
        }
        possibleNames[0] = sTemp;
        getFilePath();
    }

    @Override
    public void rotateAntiClockwise() {
        String sTemp = possibleNames[0];
        for (int i = possibleNames.length - 2; i > 0; i++) {
            possibleNames[i - 1] = possibleNames[i];
        }
        possibleNames[possibleNames.length - 1] = sTemp;
        getFilePath();
    }
}
