import MathFuncAndObj.Position;

import java.util.Arrays;
import java.util.Random;

public class Domino extends Tuile {
    /*
    Read left to right and up to down for the numbers, using the PDF example :
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
        super(pPos);
        upSide = pUp;
        rightSide = pRight;
        downSide = pDown;
        leftSide = pLeft;
        isUsed = false;
    }

    public Domino(int[] pUp, int[] pRight, int[] pDown, int[] pLeft) {
        super(null);
        upSide = pUp;
        rightSide = pRight;
        downSide = pDown;
        leftSide = pLeft;
        isUsed = false;
    }

    public Domino() {
        super(null);

        int max = 4;
        int[] aTempArray1 = new int[3];
        int[] aTempArray2 = new int[3];
        int[] aTempArray3 = new int[3];
        int[] aTempArray4 = new int[3];
        Random oRandom = new Random();

        aTempArray1[0] = oRandom.nextInt(max);
        aTempArray1[1] = oRandom.nextInt(max);
        aTempArray1[2] = oRandom.nextInt(max);
        upSide = aTempArray1;

        aTempArray2[0] = oRandom.nextInt(max);
        aTempArray2[1] = oRandom.nextInt(max);
        aTempArray2[2] = oRandom.nextInt(max);
        rightSide = aTempArray2;

        aTempArray3[0] = oRandom.nextInt(max);
        aTempArray3[1] = oRandom.nextInt(max);
        aTempArray3[2] = oRandom.nextInt(max);
        downSide = aTempArray3;

        aTempArray4[0] = oRandom.nextInt(max);
        aTempArray4[1] = oRandom.nextInt(max);
        aTempArray4[2] = oRandom.nextInt(max);
        leftSide = aTempArray4;

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
        //need to create for terminal game uses
        return null;
    }

//    public void rotateClockwise()
//    public void rotateAntiClockwise()
}
