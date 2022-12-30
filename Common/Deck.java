package Common;

import Carcassonne.CarcassonneTile;
import Domino.DominoTile;

import java.util.ArrayList;

public class Deck<E> extends InternalObject {
    ArrayList<Tile> tiles;

    public Deck(int tileNumber, String gamePlayed) {
        super();
        tiles = new ArrayList<>(tileNumber);
        if (gamePlayed.equals("Domino")) {
            for (int i = 0; i < tileNumber; i++) {
                tiles.add(new DominoTile());
            }
        } else {
            for (int i = 0; i < tileNumber; i++) {
                tiles.add(new CarcassonneTile());
            }
        }
    }

    public boolean isEmpty() {
        return tiles.isEmpty();
    }

    public Common.Tile draw() {
        Tile t = tiles.iterator().next();
//        tiles.iterator().remove();
        return t;
    }

    public void put(Tile t) {
        tiles.add(t);
    }
}