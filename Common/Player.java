package Common;

import MathFuncAndObj.Position;
import java.util.ArrayList;
import java.util.Scanner;

public class Player extends InternalObject {
    private final Scanner scanner;
    private Game game;
    private int points;

    public Player() {
        scanner = new Scanner(System.in);
        points = 0;
    }
}

