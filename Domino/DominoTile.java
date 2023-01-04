package Domino;

import Common.Sides;
import Common.Tile;
import Misc.Position;

import java.util.Arrays;
import java.util.Random;

public class DominoTile extends Tile<int[]> {

    public DominoTile(int[] pUp, int[] pRight, int[] pDown, int[] pLeft, Position pPos) {
        super(pPos, new Sides<>(pUp, pRight, pDown, pLeft), "Domino");
    }

    public DominoTile(int[] pUp, int[] pRight, int[] pDown, int[] pLeft) {
        this(pUp, pRight, pDown, pLeft, null);
    }

    public DominoTile() {
        super(null, new Sides<>(new int[3], new int[3], new int[3], new int[3]), "Domino");

        int max = 2;
        int[] aTempArray = new int[3];
        Random oRandom = new Random();

        aTempArray[0] = oRandom.nextInt(max);
        aTempArray[1] = oRandom.nextInt(max);
        aTempArray[2] = oRandom.nextInt(max);
        getSides().setTopSide(aTempArray);

        aTempArray = new int[3];
        aTempArray[0] = oRandom.nextInt(max);
        aTempArray[1] = oRandom.nextInt(max);
        aTempArray[2] = oRandom.nextInt(max);
        getSides().setLeftSide(aTempArray);

        aTempArray = new int[3];
        aTempArray[0] = oRandom.nextInt(max);
        aTempArray[1] = oRandom.nextInt(max);
        aTempArray[2] = oRandom.nextInt(max);
        getSides().setBottomSide(aTempArray);

        aTempArray = new int[3];
        aTempArray[0] = oRandom.nextInt(max);
        aTempArray[1] = oRandom.nextInt(max);
        aTempArray[2] = oRandom.nextInt(max);
        getSides().setRightSide(aTempArray);

        this.updateGraphic();
    }

    @Override
    public String toString() {
        return "Domino{" +
                "upSide=" + Arrays.toString(getSides().getTopSide()) +
                ", rightSide=" + Arrays.toString(getSides().getRightSide()) +
                ", downSide=" + Arrays.toString(getSides().getBottomSide()) +
                ", leftSide=" + Arrays.toString(getSides().getLeftSide()) +
                '}';
    }

    @Override
    public String getGraphicalRepresentation() {
        String up="";
        String down="";
        String sides="";

        for(int i=0;i<3;i++){
            up+="  "+this.getSides().getTopSide()[i];
            down+="  "+this.getSides().getBottomSide()[i];
            sides+=this.getSides().getLeftSide()[i]+"         "+this.getSides().getRightSide()[i]+"\n";
        }

        return up+"\n"+sides+down;
    }

    private int[][] invertArrays(int[] pArray1, int[] pArray2) {
        int[] mirror1 = new int[3];
        int[] mirror2 = new int[3];
        int[][] aRtn = new int[2][3];

        for(int i=0;i<3;i++){
            mirror1[i]= pArray1[2-i];
            mirror2[i]= pArray2[2-i];
        }

        aRtn[0] = mirror1;
        aRtn[1] = mirror2;
        return aRtn;
    }

    public void rotateClockwise(){
        int[][] aInvertedSide = invertArrays(getSides().getLeftSide(), getSides().getRightSide());

        getSides().setLeftSide(getSides().getBottomSide());
        getSides().setBottomSide(aInvertedSide[1]);
        getSides().setRightSide(getSides().getTopSide());
        getSides().setTopSide(aInvertedSide[0]);
        updateGraphic();
    }

    public void rotateAntiClockwise(){
        int[][] aInvertedSide = invertArrays(getSides().getTopSide(), getSides().getBottomSide());

        getSides().setTopSide(getSides().getRightSide());
        getSides().setRightSide(aInvertedSide[1]);
        getSides().setBottomSide(getSides().getLeftSide());
        getSides().setLeftSide(aInvertedSide[0]);
        updateGraphic();
    }
}
