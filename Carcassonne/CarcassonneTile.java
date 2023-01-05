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

public class CarcassonneTile extends Tile<SideType> {
    private boolean village, town, separated, monastery;
    private String[] correspondingFilePath;
    private String[] possibleNames;
    private BufferedImage display;


    public CarcassonneTile(SideType top, SideType right, SideType bottom, SideType left, boolean village, boolean separated, boolean monastery, boolean town){
        super(null, new Sides<>(top, right, bottom, left), "Carcassonne");
        this.village= village;
        this.separated = separated;
        this.monastery = monastery;
        this.town = town;
        getPossibleNames(this.getSides());
        updateNames();
        getFilePath();
        updateDisplay();
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

        this.getSides().setTopSide(top);
        this.getSides().setRightSide(right);
        this.getSides().setBottomSide(bottom);
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

        // Debug for non-existing tiles
        if (nRoadCount == 3 && nCityCount > 0 || nRoadCount == 1 && nCityCount > 1) {
            replaceOneSide('c', 'f');
            nCityCount = 0;
            nFieldCount = 3;
            if (top == SideType.CITY) {
                this.getSides().setTopSide(SideType.FIELD);
            }
            if (right == SideType.CITY) {
                this.getSides().setRightSide(SideType.FIELD);
            }
            if (bottom == SideType.CITY) {
                this.getSides().setBottomSide(SideType.FIELD);
            }
            if (left == SideType.CITY) {
                this.getSides().setLeftSide(SideType.FIELD);
            }
        }

        if (nCityCount % 2 == 0 && nCityCount != 0) {
            // if 2 or 4 cities, then there is a chance that is becomes a field surrounded by cities (20%)
            this.separated = RandomManagement.isHappening(20);
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

    protected void getPossibleNames(Sides<SideType> sides) {
        possibleNames = new String[4];
        possibleNames[0] = SideType.toString(sides.getTopSide()) + SideType.toString(sides.getRightSide()) + SideType.toString(sides.getBottomSide()) + SideType.toString(sides.getLeftSide());
        possibleNames[1] = SideType.toString(sides.getLeftSide()) + SideType.toString(sides.getTopSide()) + SideType.toString(sides.getRightSide()) + SideType.toString(sides.getBottomSide());
        possibleNames[2] = SideType.toString(sides.getBottomSide()) + SideType.toString(sides.getLeftSide()) + SideType.toString(sides.getTopSide()) + SideType.toString(sides.getRightSide());
        possibleNames[3] = SideType.toString(sides.getRightSide()) + SideType.toString(sides.getBottomSide()) + SideType.toString(sides.getLeftSide()) + SideType.toString(sides.getTopSide());
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
        Sides<SideType> sides = this.getSides();
        SideType sideTypeSave = sides.getTopSide();
        sides.setTopSide(sides.getLeftSide());
        sides.setLeftSide(sides.getBottomSide());
        sides.setBottomSide(sides.getRightSide());
        sides.setRightSide(sideTypeSave);
    }

    private void rotateSidesAntiClockwise() {
        Sides<SideType> sides = this.getSides();
        SideType sideTypeSave = sides.getLeftSide();
        sides.setLeftSide(sides.getTopSide());
        sides.setTopSide(sides.getRightSide());
        sides.setRightSide(sides.getBottomSide());
        sides.setLeftSide(sideTypeSave);
    }
}
