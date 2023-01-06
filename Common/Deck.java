package Common;

import Carcassonne.CarcassonneTile;
import Domino.DominoTile;
import Exceptions.NoMoreTileInDeckException;

public class Deck<E> extends InternalObject {
    private final DominoTile[] dominoDeck;
    private final CarcassonneTile[] carcassonneDeck;
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

    public Common.Tile<E> draw() throws NoMoreTileInDeckException {
        Tile tile;
        if (carcassonneDeck == null) {
            tile = dominoDeck[rank];
            if (rank + 1 >= this.dominoDeck.length) {
                throw new NoMoreTileInDeckException();
            }
        } else {
            tile = carcassonneDeck[rank];
            if (rank + 1 >= this.carcassonneDeck.length) {
                throw new NoMoreTileInDeckException();
            }
        }
        rank++;
        return tile;
    }

    public void add(Tile t){
        if (t instanceof DominoTile){ rank--; dominoDeck[rank]=(DominoTile) t;}
        else if (t instanceof CarcassonneTile){ rank--; carcassonneDeck[rank]=(CarcassonneTile) t;}
    }
}