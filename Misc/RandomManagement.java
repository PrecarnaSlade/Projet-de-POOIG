package Misc;

import java.util.Random;

public class RandomManagement {
    private static Random rnd = new Random();

    public static boolean isHappening(int percentage) {
        return percentage <= rnd.nextInt(100) + 1; // +1 to convert to base 1
    }
}
