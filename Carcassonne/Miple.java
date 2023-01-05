package Carcassonne;

import Misc.Position;

import java.awt.image.BufferedImage;

public class Miple {
    private CarcassonnePlayer player;
    private boolean used;
    private Position position;
    private SideType terrainType;
    private BufferedImage skin;

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

    public BufferedImage getSkin() {
        return skin;
    }

    public SideType getTerrainType() {
        return terrainType;
    }

    public void setTerrainType(SideType terrainType) {
        this.terrainType = terrainType;
    }

    public void setSkin(BufferedImage skin) {
        this.skin = skin;
    }
}
