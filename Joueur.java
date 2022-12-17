import java.util.ArrayList;
import java.util.Scanner;

public class Joueur extends Object {
    private Scanner scanner;
    private ArrayList<Tuile> inventory;

    public Joueur() {
        inventory = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

//  public void draw() { invertory.add(pioche.get()) }

//    public void discard(Tuile t){ pioche.put(inventory.remove(t)); }

//  public void place(Position p, Tuile t) { grille.place(p,t); inventory.remove(t);}
}

