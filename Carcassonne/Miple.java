package Carcassonne;

import Misc.Position;

public class Miple {
    private CarcassonnePlayer player;
    private boolean used;
    private Position position;

    public Miple(CarcassonnePlayer player){
        this.player= player;
        used= false;
        position= null;
    }

    public CarcassonnePlayer getPlayer() {
        return player;
    }

    public boolean isUsed(){
        return used;
    }

    public Position getPosition() {
        return position;
    }

    public void place(Position position){
        this.position= position;
        this.used= true;
    }

    public void unPlace(){
        this.position= null;
        this.used= false;
    }
}
