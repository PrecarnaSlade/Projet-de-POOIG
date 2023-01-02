package Common;

import java.util.Scanner;

public class Player extends InternalObject {
    private final Scanner scanner;
    private int points;

    public Player() {
        scanner = new Scanner(System.in);
        points = 0;
    }
}

