package Misc;

import java.util.Random;

public class RandomManagement {
    private static Random rnd = new Random();

    public static boolean isHappening(int percentage) {
        return percentage <= rnd.nextInt(100) + 1; // +1 to convert to base 1
    }

    public static int choiceIsHappening(int[] percentage) {
        int nRandomChoice = rnd.nextInt(100) + 1;
        int nSum = 0;
        int nLastBound = 0;
        for (int i = 0; i < percentage.length; i++) {
            nSum += percentage[i];
            if (nRandomChoice > nLastBound && nRandomChoice < nSum) {
                return i;
            }
            nLastBound = nSum;
        }
        return 0;
    }
}
