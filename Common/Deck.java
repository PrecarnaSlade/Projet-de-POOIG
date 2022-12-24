package Common;

import java.util.ArrayList;

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

    public Common.Tile<E> draw(){
        Tile<E> t=tiles.iterator().next();
        tiles.iterator().remove();
        return t;
    }

    public void put(Tile<E> t){
        tiles.add(t);
    }
}
