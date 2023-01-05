package Carcassonne;

import Common.Sides;
import Common.Tile;
import Common.Window.Display;
import Graphic.Menu.ImageManagement;
import Misc.RandomManagement;
import Misc.StringManagement;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CarcassonneTile extends Tile<SpecialType> {
    private boolean village;
    private boolean town;
    private boolean separatedTown;
    private boolean monastery;
    private String[] correspondingFilePath;
    private String[] possibleNames;
    private BufferedImage display;
    private SpecialType optionalType;
    private int mipleCount;
    private final Miple[] miples; //array in case of multiple miple on same tile
    private final SpecialType[] preciseSides; // correspondingPos = {N, NE, NW, S, SE, SW, E, W, CENTER};

    public CarcassonneTile() {
        super(null, new Sides<>(null, null, null, null), "Carcassonne");
        SpecialType top = SpecialType.randomSide();
        SpecialType right = SpecialType.randomSide();
        SpecialType bottom = SpecialType.randomSide();
        SpecialType left = SpecialType.randomSide();

        this.getSides().setTopSide(top);
        this.getSides().setRightSide(right);
        this.getSides().setBottomSide(bottom);
        this.getSides().setLeftSide(left);

        this.village        = false;
        this.separatedTown  = false;
        this.monastery      = false;
        this.town           = false;
        this.mipleCount     = 0;
        this.optionalType   = null;
        this.miples         = new Miple[5]; // top, left, bottom, left, center
        preciseSides        = new SpecialType[9];

        getPossibleNames(this.getSides());

        String sFirstPossibleName = possibleNames[0];

        int nCityCount = StringManagement.countLetterInString(sFirstPossibleName, 'c');
        int nFieldCount = StringManagement.countLetterInString(sFirstPossibleName, 'f');
        int nRoadCount = StringManagement.countLetterInString(sFirstPossibleName, 'r');

        // Debug for non-existing tiles
        if (nRoadCount == 3 && nCityCount > 0 || nRoadCount == 1 && nCityCount > 1) {
            replaceOneSide('c', 'f');
            nCityCount = 0;
            nFieldCount = 3;
            if (top == SpecialType.CITY) {
                this.getSides().setTopSide(SpecialType.FIELD);
            }
            if (right == SpecialType.CITY) {
                this.getSides().setRightSide(SpecialType.FIELD);
            }
            if (bottom == SpecialType.CITY) {
                this.getSides().setBottomSide(SpecialType.FIELD);
            }
            if (left == SpecialType.CITY) {
                this.getSides().setLeftSide(SpecialType.FIELD);
            }
        }

        if (nCityCount % 2 == 0 && nCityCount != 0) {
            // if 2 or 4 cities, then there is a chance that is becomes a field surrounded by cities (20%)
            this.separatedTown = RandomManagement.isHappening(20);
        }
        if (nFieldCount == 4) {
            // if 4 fields, then there is a monastery in the center (100%)
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
        updateDisplay();
    }

    public void replaceOneSide(char oldSide, char newSide) {
        for (int i = 0; i < possibleNames.length; i++) {
            possibleNames[i] = possibleNames[i].replace(oldSide, newSide);
        }
    }

    public boolean hasOptionalType() {
        return optionalType != null;
    }

    public SpecialType getOptionalType() {
        return optionalType;
    }

    protected void getPossibleNames(Sides<SpecialType> sides) {
        possibleNames = new String[4];
        possibleNames[0] = SpecialType.toString(sides.getTopSide()) + SpecialType.toString(sides.getRightSide()) + SpecialType.toString(sides.getBottomSide()) + SpecialType.toString(sides.getLeftSide());
        possibleNames[1] = SpecialType.toString(sides.getLeftSide()) + SpecialType.toString(sides.getTopSide()) + SpecialType.toString(sides.getRightSide()) + SpecialType.toString(sides.getBottomSide());
        possibleNames[2] = SpecialType.toString(sides.getBottomSide()) + SpecialType.toString(sides.getLeftSide()) + SpecialType.toString(sides.getTopSide()) + SpecialType.toString(sides.getRightSide());
        possibleNames[3] = SpecialType.toString(sides.getRightSide()) + SpecialType.toString(sides.getBottomSide()) + SpecialType.toString(sides.getLeftSide()) + SpecialType.toString(sides.getTopSide());
    }

    protected void updateNames() {
        if (this.village) {
            addToNames("v");
            this.optionalType = SpecialType.VILLAGE;
        }
        if (this.separatedTown) addToNames("s");
        if (this.monastery) {
            addToNames("m");
            this.optionalType = SpecialType.MONASTERY;
        }
        if (this.town) addToNames("t");
        updatePreciseSides();
    }

    protected void updatePreciseSides() {
        for (int i = 0; i < 9; i++) {
            preciseSides[i] = SpecialType.AMBIGUOUS;
        }
        String sName = possibleNames[0];
        preciseSides[0] = SpecialType.toSpecialType(String.valueOf(sName.charAt(0)));
        preciseSides[2] = SpecialType.toSpecialType(String.valueOf(sName.charAt(0)));
        preciseSides[4] = SpecialType.toSpecialType(String.valueOf(sName.charAt(0)));
        preciseSides[6] = SpecialType.toSpecialType(String.valueOf(sName.charAt(0)));
        if (town) {
            preciseSides[8] = SpecialType.CITY;
        }
        if (village) {
            preciseSides[8] = SpecialType.VILLAGE;
        }
        if (monastery) {
            preciseSides[8] = SpecialType.MONASTERY;
        }
        for (int i = 0; i < 4; i++) {
            if (preciseSides[i] == SpecialType.CITY && preciseSides[i * 2] == SpecialType.CITY) {
                if (i * 2 - 1 > 0) {
                    if (separatedTown) {
                        preciseSides[i * 2 - 1] = SpecialType.AMBIGUOUS;
                    } else {
                        preciseSides[i * 2 - 1] = SpecialType.CITY;
                    }
                }
            }
            if (preciseSides[i] == SpecialType.ROAD) {
                if (i * 2 - 1 > 0) {
                    preciseSides[i * 2 - 1] = SpecialType.FIELD;
                }
                preciseSides[i * 2 + 1] = SpecialType.FIELD;
            }
            if (i % 2 == 0) {
                if (preciseSides[i] == SpecialType.CITY && preciseSides[i + 2] == SpecialType.CITY && !separatedTown) {
                    preciseSides[8] = SpecialType.CITY;
                }
            }
        }
    }

    protected void addToNames(String letter) {
        for (int i = 0; i < possibleNames.length; i++) {
            possibleNames[i] += letter;
        }
    }

    public SpecialType[] getPreciseSides() {
        return preciseSides;
    }

    public BufferedImage getDisplay() {
        return display;
    }

    public void updateDisplay() {
        try {
            this.display = ImageManagement.resize(ImageIO.read(new File(correspondingFilePath[0])), Display.TILE_SIZE, Display.TILE_SIZE);
            updateGraphic();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("File name unavailable : " + correspondingFilePath[0]);
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
        rotateSidesClockwise();
        rotateArrayClockWise(possibleNames);
        rotateArrayClockWise(correspondingFilePath);
        updateDisplay();
    }

    @Override
    public void rotateAntiClockwise() {
        rotateSidesAntiClockwise();
        rotateArrayAntiClockWise(possibleNames);
        rotateArrayAntiClockWise(correspondingFilePath);
        updateDisplay();
    }

    private void rotateArrayClockWise(String[] array) {
        String sLast = array[0];
        String sTemp;
        for (int i = 0; i < array.length; i++) {
            sTemp = array[array.length - 1 - i];
            array[array.length - 1 - i] = sLast;
            sLast = sTemp;
        }
    }


    private void rotateArrayAntiClockWise(String[] array) {
        String sLast = array[array.length - 1];
        String sTemp;
        for (int i = 0; i < array.length; i++) {
            sTemp = array[i];
            array[i] = sLast;
            sLast = sTemp;
        }
    }

    private void rotateSidesClockwise() {
        Sides<SpecialType> sides = this.getSides();
        SpecialType specialTypeSave = sides.getTopSide();
        sides.setTopSide(sides.getLeftSide());
        sides.setLeftSide(sides.getBottomSide());
        sides.setBottomSide(sides.getRightSide());
        sides.setRightSide(specialTypeSave);
    }

    private void rotateSidesAntiClockwise() {
        Sides<SpecialType> sides = this.getSides();
        SpecialType specialTypeSave = sides.getLeftSide();
        sides.setLeftSide(sides.getTopSide());
        sides.setTopSide(sides.getRightSide());
        sides.setRightSide(sides.getBottomSide());
        sides.setLeftSide(specialTypeSave);
    }

    public boolean placeMiple(Miple miple, SpecialType typeGoal, SidePosition desiredPosition) {
        if (miple == null) {
            return false;
        }
        if(mipleCount == 0) {
            this.miples[0] = miple;
        } else {
            for (Miple m : miples) {
                if (m.getTerrainType() == typeGoal) {
                    if (m.getSidePosition() == desiredPosition) {
                        return false;
                    }
                }
            }
            this.miples[mipleCount] = miple;
            miple.setSidePosition(desiredPosition);
            miple.setTerrainType(typeGoal);
        }
        this.mipleCount++;
        return true;
    }
}
