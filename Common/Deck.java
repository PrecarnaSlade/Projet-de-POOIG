package Common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class Deck<E> extends InternalObject {
    ArrayList<Tile<E>> tiles;

    public Deck(int i){
        super();
        tiles= new ArrayList<>(i);
        //Le deck sera rempli avec des tuiles aléatoires lors de l'initialisation des parties.
    }

    public boolean isEmpty(){
        return tiles.isEmpty();
    }

    public Common.Tile draw(){
        Tile t=tiles.iterator().next();
        tiles.iterator().remove();
        return t;
    }

    public void put(Tile<E> t){
        tiles.add(t);
    }
}
