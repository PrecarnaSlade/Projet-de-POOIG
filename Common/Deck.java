package Common;

import java.util.ArrayList;
import java.util.Collection;

public class Deck<E> extends InternalObject {
    Collection<Tile<E>> tiles;

    public Deck(int i){
        super();
        tiles= new ArrayList<>(i);
        //Le deck sera rempli avec des tuiles aléatoires lors de l'initialisation des parties.
    }

    public boolean isEmpty(){
        return tiles.isEmpty();
    }

    /*
    public Common.Tile draw(){
        //.get() sur un element aleatoire de la collection
    }
     */

    public void put(Tile<E> t){
        tiles.add(t);
    }
}
