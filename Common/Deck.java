package Common;

import Carcassonne.CarcassonneTile;
import Domino.DominoTile;

import java.util.ArrayList;

public class Deck<E> extends InternalObject {
    private DominoTile[] dominoDeck;
    private CarcassonneTile[] carcassonneDeck;
    private int rank;

    public Deck(int tileNumber, String gamePlayed) {
        super();
        if (gamePlayed.equals("Domino")) {
            dominoDeck = new DominoTile[tileNumber];
            carcassonneDeck = null;
            for (int i = 0; i < tileNumber; i++) {
                dominoDeck[i] = new DominoTile();
            }
        } else {
            carcassonneDeck = new CarcassonneTile[tileNumber];
            dominoDeck = null;
            for (int i = 0; i < tileNumber; i++) {
                carcassonneDeck[i] = new CarcassonneTile();
            }
        }
        rank = 0;
    }

    public Common.Tile<E> draw() {
        Tile tile;
        if (carcassonneDeck == null) {
            tile = dominoDeck[rank];
        } else {
            tile = carcassonneDeck[rank];
        }
        rank++;
        return tile;
    }
}