package Carcassonne;

import Common.Player;
import Misc.Position;

public class CarcassonnePlayer extends Player {
    private Miple[] miples;
    private int placedMiple;

    public CarcassonnePlayer(String name){
        super(name);
        miples= new Miple[7];
        for(int i=0; i<7; i++){
            miples[i]= new Miple(this);
        }
        this.placedMiple = 0;
    }

    public void placeMiple(Position positionOnTile, CarcassonneTile tile) {
        placedMiple++;
        miples[placedMiple].place(positionOnTile);
    }
    public void getBackMiple() {
        placedMiple--;
    }

    public int getPlacedMiple() {
        return placedMiple;
    }
}
