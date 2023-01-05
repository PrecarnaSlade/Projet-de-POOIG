package Carcassonne;

import Common.Player;
import Misc.Position;

import javax.swing.*;

public class CarcassonnePlayer extends Player {
    private final Miple[] miples;
    private int placedMiple;
    private boolean hasPlacedMiple;

    public CarcassonnePlayer(String name){
        super(name);
        miples= new Miple[7];
        for(int i=0; i<7; i++){
            miples[i]= new Miple(this);
        }
        this.placedMiple = 0;
        this.hasPlacedMiple =false;
    }

    public void setHasPlacedMiple(boolean hasPlacedMiple) {
        this.hasPlacedMiple = hasPlacedMiple;
    }

    public Miple placeMiple(Position positionOnGrid, SpecialType terrainType) {
        if (placedMiple < 7 && !hasPlacedMiple) {
            miples[placedMiple].place(positionOnGrid, terrainType);
            placedMiple++;
            hasPlacedMiple = true;
            return miples[placedMiple - 1];
        } else {
            if (placedMiple == 7) {
                JOptionPane.showMessageDialog(null, "You already placed all of your 7 miples !!", "No miple left", JOptionPane.INFORMATION_MESSAGE);
            }
            if (hasPlacedMiple) {
                JOptionPane.showMessageDialog(null, "You already placed a miple during your turn !!", "Miple Placed", JOptionPane.INFORMATION_MESSAGE);
            }
            return null;
        }
    }
    public void getBackMiple() {
        placedMiple--;
    }

    public Miple[] getMiples() {
        return miples;
    }

    public int getPlacedMiple() {
        return placedMiple;
    }
}
