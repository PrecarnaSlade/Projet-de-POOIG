package Graphic;

import Common.Sides;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class Tile extends JPanel {
    private int objectIDLinked;

    public Tile(String tileType, int widthParentWindow, int heightParentWindow, float scale, Sides sides, int nObjectIDLinked) {
        if (tileType.equals("domino")) {
            Domino(widthParentWindow, heightParentWindow, scale, sides);
        } else if (tileType.equals("carcassone")) {
            System.out.println("TBD");
        } else {
            return;
        }
        this.objectIDLinked = nObjectIDLinked;
    }

    public int getObjectIDLinked() {
        return objectIDLinked;
    }

    private void Domino(int widthParentWindow, int heightParentWindow, float scale, Sides<int[]> sides) {
        int nSize;
        if (scale == 0) {
            nSize = 100;
        } else {
            nSize = (int) Math.min(widthParentWindow * scale, heightParentWindow * scale);
        }
        int nQuarter = nSize / 4;

        // Setting up basic size and layout to null => can apply absolute coordinates
        this.setSize(nSize, nSize);
        this.setLayout(null);
        this.setBorder(new LineBorder(Color.BLACK));
        this.setBackground(new Color(0,0,0,0)); // alpha set to 0 so transparent

        // Creating the 4 dark grey square on the 4 corners
        Color colorDarkGrey = new Color(169, 169, 169);
        JPanel[] aDarkGreySquare = new JPanel[4];
        int[][] aCoordinates = {{0, 0}, {nSize - nQuarter, 0}, {0, nSize - nQuarter}, {nSize - nQuarter, nSize - nQuarter}};
        for (int i = 0; i < 4; i++) {
            aDarkGreySquare[i] = new JPanel();
            this.add(aDarkGreySquare[i]);
            aDarkGreySquare[i].setBackground(colorDarkGrey);
            aDarkGreySquare[i].setBounds(aCoordinates[i][0], aCoordinates[i][1], nQuarter, nQuarter);
            aDarkGreySquare[i].setBorder(new LineBorder(Color.BLACK));
        }

        // Creating the light grey square in the center
        JPanel oLightGreySquare = new JPanel();
        this.add(oLightGreySquare);
        oLightGreySquare.setBorder(new LineBorder(Color.BLACK));
        oLightGreySquare.setBackground(new Color(211, 211, 211));
        oLightGreySquare.setBounds(nQuarter - 1, nQuarter - 1, nQuarter * 2 + 2, nQuarter * 2 + 2);

        // Creating the 4 side
        int[][] aSides = new int[4][3];
        JPanel[] aValueRectangle = new JPanel[12];
        int nIncrement = 0;
        int nSideSize = 2 * nQuarter;
        int nBiasX, nBiasY;
        JLabel oLabel;
        aSides[0] = sides.getUpSide();
        aSides[1] = sides.getRightSide();
        aSides[2] = sides.getDownSide();
        aSides[3] = sides.getLeftSide();
        aCoordinates[0][0] = nQuarter;
        aCoordinates[0][1] = 0;
        aCoordinates[1][0] = nSize - nQuarter;
        aCoordinates[1][1] = nQuarter;
        aCoordinates[2][0] = nQuarter;
        aCoordinates[2][1] = nSize - nQuarter;
        aCoordinates[3][0] = 0;
        aCoordinates[3][1] = nQuarter;
        int nThirdOfHalf = (int) Math.ceil(Math.ceil(nSideSize / 3.0));
        int[][] aRectangleSize = {{nThirdOfHalf, nQuarter}, {nQuarter, nThirdOfHalf}};
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                if (i % 2 == 0) {
                    nBiasX = aRectangleSize[i % 2][0] * j;
                    nBiasY = 0;
                } else {
                    nBiasX = 0;
                    nBiasY = aRectangleSize[i % 2][1] * j;
                }
                aValueRectangle[nIncrement] = new JPanel();
                oLabel = new JLabel(Integer.toString(aSides[i][j]));
                aValueRectangle[nIncrement].add(oLabel, new GridBagConstraints());
                this.add(aValueRectangle[nIncrement]);
                aValueRectangle[nIncrement].setLayout(new GridBagLayout());
                aValueRectangle[nIncrement].setBorder(new LineBorder(Color.BLACK));
                aValueRectangle[nIncrement].setBackground(new Color(255, 255, 255));
                aValueRectangle[nIncrement].setBounds(aCoordinates[i][0] + nBiasX, aCoordinates[i][1] + nBiasY, aRectangleSize[i % 2][0], aRectangleSize[i % 2][1]);
                nIncrement++;
            }
        }
    }
}