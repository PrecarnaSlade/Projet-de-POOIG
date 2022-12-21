package Common;

import java.util.Collection;

public class Deck<E> extends InternalObject {
    Collection<Tile<E>> tiles;

    public Deck(){
        super();
        //doit créer la collection et y ajouter un certain nombre de tuile aléatoire
    }

    public boolean isEmpty(){
        return tiles.isEmpty();
    }

    /*
    public Common.Tuile draw(){
        //.get() sur un element aleatoire de la collection
    }
     */

    public void put(Tile<E> t){
        //remet un element t dans la pioche
    }
}
