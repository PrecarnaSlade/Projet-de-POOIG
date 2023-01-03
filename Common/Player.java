package Common;

import java.util.Scanner;

public class Player extends InternalObject {
    private final Scanner scanner;
    private int points;

    public Player() {
        scanner = new Scanner(System.in);
        points = 0;
    }

    public int getPoints() {
        return points;
    }

    public void addPoints(int x){
        points+=x;
    }
}

