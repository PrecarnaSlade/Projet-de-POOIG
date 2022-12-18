import Common.Joueur;
import Common.Tuile;

import java.util.ArrayList;

public class IA extends Joueur {
    private ArrayList<Tuile> inventory;

    public IA() {
        inventory = new ArrayList<>();
    }

    //  public void draw(){super.draw();}
    // public void discard(Common.Tuile t){super.discard(t);}
    // public void place(Position p, Common.Tuile t){super.place(p,t);}
}
