package Common;

import java.util.Collection;

public class Pioche<E> {
    Collection<Tuile<E>> tuiles;

    public Pioche(){
        //doit créer la collection et y ajouter un certain nombre de tuile aléatoire
    }

    public boolean isEmpty(){
        return tuiles.isEmpty();
    }

    /*
    public Common.Tuile draw(){
        //.get() sur un element aleatoire de la collection
    }
     */

    public void put(Tuile<E> t){
        //remet un element t dans la pioche
    }
}
