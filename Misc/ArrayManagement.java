package Misc;

import Common.Player;

public class ArrayManagement {
    public static Player[] add(Player[] array, Player var) {
        Player[] aNew = new Player[array.length + 1];
        System.arraycopy(array, 0, aNew, 0, array.length);
        aNew[array.length] = var;
        return aNew;
    }

    public static Position[] add(Position[] array, Position var) {
        Position[] aNew = new Position[array.length + 1];
        System.arraycopy(array, 0, aNew, 0, array.length);
        aNew[array.length] = var;
        return aNew;
    }

    public static Player[] removePlayer(Player[] array, int rank) {
        Player[] aNew = new Player[array.length - 1];
        int nBias = 0;
        for (int i = 0; i < array.length; i++) {
            if (i == rank) {
                nBias = 1;
                continue;
            }
            aNew[i - nBias] = array[i];
        }
        return aNew;
    }

    public static int sum(int[] array) {
        int nSum = 0;
        for (int i : array) {
            nSum += i;
        }
        return nSum;
    }
}
