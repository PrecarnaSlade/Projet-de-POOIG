package Common;

import Carcassonne.Miple;

import java.util.Scanner;

public class Player extends InternalObject {
    private int points;
    private String name;
    private final boolean isIA;
    private Miple[] miples;

    public Player(String name) {
        this(name,false);
    }
    public Player(String name, boolean isIA) {
        Scanner scanner = new Scanner(System.in);
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

