package Domino;

import Common.Sides;
import Common.Tile;
import MathFuncAndObj.Position;

import java.util.Arrays;
import java.util.Random;

public class DominoTile extends Tile<int[]> {
    /*
    Read left to right and up to down for the numbers, using the PDF example :
            0  2  3                 upSide = {0, 2, 3}
          0         1               rightSide = {1, 1, 4}
          0         1               downSide = {2, 0, 4}
          3         4               leftSide = {0, 0, 3}
            2  0  4

            0  2  3                 upSide = {0, 2, 3}
          0         1               rightSide = {1, 1, 4}
          0         1               downSide = {2, 0, 4}
          3         4               leftSide = {0, 0, 3}
            2  0  4

         !!!!   DEPRECATED !!!!
     */

    public DominoTile(int[] pUp, int[] pRight, int[] pDown, int[] pLeft, Position pPos) {
        super(pPos, new Sides<>(pUp, pRight, pDown, pLeft), "Domino");
        Sides<int[]> sides = new Sides<>(pUp, pRight, pDown, pLeft);
    }

    public DominoTile(int[] pUp, int[] pRight, int[] pDown, int[] pLeft) {
        super(null, new Sides<>(pUp, pRight, pDown, pLeft), "Domino");
    }

    public DominoTile() {
        super(null, new Sides<>(new int[3], new int[3], new int[3], new int[3]), "Domino");

        int max = 2;
        int[] aTempArray = new int[3];
        Random oRandom = new Random();

        aTempArray[0] = oRandom.nextInt(max);
        aTempArray[1] = oRandom.nextInt(max);
        aTempArray[2] = oRandom.nextInt(max);
        getSides().setUpSide(aTempArray);

        aTempArray = new int[3];
        aTempArray[0] = oRandom.nextInt(max);
        aTempArray[1] = oRandom.nextInt(max);
        aTempArray[2] = oRandom.nextInt(max);
        getSides().setLeftSide(aTempArray);

        aTempArray = new int[3];
        aTempArray[0] = oRandom.nextInt(max);
        aTempArray[1] = oRandom.nextInt(max);
        aTempArray[2] = oRandom.nextInt(max);
        getSides().setDownSide(aTempArray);

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
                "upSide=" + Arrays.toString(getSides().getUpSide()) +
                ", rightSide=" + Arrays.toString(getSides().getRightSide()) +
                ", downSide=" + Arrays.toString(getSides().getDownSide()) +
                ", leftSide=" + Arrays.toString(getSides().getLeftSide()) +
                '}';
    }

    @Override
    public String getGraphicalRepresentation() {
        String up="";
        String down="";
        String sides="";

        for(int i=0;i<3;i++){
            up+="  "+this.getSides().getUpSide()[i];
            down+="  "+this.getSides().getDownSide()[i];
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

        getSides().setLeftSide(getSides().getDownSide());
        getSides().setDownSide(aInvertedSide[1]);
        getSides().setRightSide(getSides().getUpSide());
        getSides().setUpSide(aInvertedSide[0]);
    }

    public void rotateAntiClockwise(){
        int[][] aInvertedSide = invertArrays(getSides().getUpSide(), getSides().getDownSide());

        getSides().setUpSide(getSides().getRightSide());
        getSides().setRightSide(aInvertedSide[1]);
        getSides().setDownSide(getSides().getLeftSide());
        getSides().setLeftSide(aInvertedSide[0]);
    }
}
