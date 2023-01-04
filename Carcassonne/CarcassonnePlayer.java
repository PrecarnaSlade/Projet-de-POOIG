package Carcassonne;

import Common.Player;

public class CarcassonnePlayer extends Player {
    private Miple[] miples;

    public CarcassonnePlayer(String name){
        super(name);
        miples= new Miple[7];
        for(int i=0; i<7; i++){
            miples[i]= new Miple(this);
        }
    }
}
