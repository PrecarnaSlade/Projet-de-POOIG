package Common;

import Carcassonne.Miple;

import java.util.Scanner;

public class Player extends InternalObject {
    private final Scanner scanner;
    private int points;
    private String name;
    private boolean isIA;
    private Miple[] miples;

    public Player(String name) {
        this(name,false);
    }
    public Player(String name, boolean isIA) {
        scanner = new Scanner(System.in);
        points = 0;
        this.name = name;
        this.isIA = isIA;
    }

    public boolean isIA() {
        return isIA;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void addPoints(int x){
        points+=x;
    }
}

