package Common;

import java.util.ArrayList;
import java.util.Scanner;

public class Joueur {
    private final Scanner scanner;
    private ArrayList<Tuile> inventory;

    public Joueur() {
        inventory = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

//  public void draw() { invertory.add(pioche.get()) }

//    public void discard(Common.Tuile t){ pioche.put(inventory.remove(t)); }

//  public void place(Position p, Common.Tuile t) { grille.place(p,t); inventory.remove(t);}
}

