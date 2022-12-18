import Common.Sides;
import Common.Tuile;
import MathFuncAndObj.Position;

import java.util.Arrays;
import java.util.Random;

public class Domino extends Tuile<int[]> {
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
     */
    private int[] upSide;       // must be int[3]
    private int[] rightSide;    // must be int[3]
    private int[] downSide;     // must be int[3]
    private int[] leftSide;     // must be int[3]
    boolean isUsed;

    public Domino(int[] pUp, int[] pRight, int[] pDown, int[] pLeft, Position pPos) {
        super(pPos, new Sides<>(pUp, pRight, pDown, pLeft));
        isUsed = false;
    }

    public Domino(int[] pUp, int[] pRight, int[] pDown, int[] pLeft) {
        super(null, new Sides<>(pUp, pRight, pDown, pLeft));
        isUsed = false;
    }

    public Domino() {
        super(null, new Sides<>(new int[0], new int[3], new int[3], new int[3]));

        int max = 2;
        int[] aTempArray = new int[3];
        Random oRandom = new Random();

        aTempArray[0] = oRandom.nextInt(max) - 1;
        aTempArray[1] = oRandom.nextInt(max) - 1;
        aTempArray[2] = oRandom.nextInt(max) - 1;
        getSides().setUpSide(aTempArray);

        aTempArray[0] = oRandom.nextInt(max) - 1;
        aTempArray[1] = oRandom.nextInt(max) - 1;
        aTempArray[2] = oRandom.nextInt(max) - 1;
        getSides().setLeftSide(aTempArray);

        aTempArray[0] = oRandom.nextInt(max) - 1;
        aTempArray[1] = oRandom.nextInt(max) - 1;
        aTempArray[2] = oRandom.nextInt(max) - 1;
        getSides().setDownSide(aTempArray);

        aTempArray[0] = oRandom.nextInt(max) - 1;
        aTempArray[1] = oRandom.nextInt(max) - 1;
        aTempArray[2] = oRandom.nextInt(max) - 1;
        getSides().setRightSide(aTempArray);

        isUsed = false;

    }

    @Override
    public String toString() {
        return "Domino{" +
                "upSide=" + Arrays.toString(upSide) +
                ", rightSide=" + Arrays.toString(rightSide) +
                ", downSide=" + Arrays.toString(downSide) +
                ", leftSide=" + Arrays.toString(leftSide) +
                '}';
    }

    @Override
    public String getGraphicalRepresentation() {
        String up="";
        String down="";
        String sides="";

        for(int i=0;i<3;i++){
            up+="  "+this.upSide[i];
            down+="  "+this.downSide[i];
            sides+=this.leftSide[i]+"         "+this.rightSide[i]+"\n";
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
        int[][] aInvertedSide = invertArrays(leftSide, rightSide);

        leftSide= downSide;
        downSide= aInvertedSide[1];
        rightSide= upSide;
        upSide= aInvertedSide[0];
    }

    public void rotateAntiClockwise(){
        int[][] aInvertedSide = invertArrays(upSide, downSide);

        upSide= rightSide;
        rightSide= aInvertedSide[1];
        downSide= leftSide;
        leftSide= aInvertedSide[0];
    }
}
