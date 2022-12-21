package Common;

import java.util.ArrayList;
import java.util.Scanner;

public class Joueur extends InternalObject {
    private final Scanner scanner;
    private ArrayList<Tile> inventory;

    public Joueur() {
        inventory = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

//  public void draw() { invertory.add(pioche.get()) }

//    public void discard(Common.Tile t){ pioche.put(inventory.remove(t)); }

//  public void place(Position p, Common.Tile t) { grille.place(p,t); inventory.remove(t);}
}

