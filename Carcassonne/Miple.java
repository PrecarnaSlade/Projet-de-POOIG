package Carcassonne;

import Common.Window.Display;
import Graphic.Menu.ImageManagement;
import Misc.Position;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Miple {
    private final CarcassonnePlayer player;
    private boolean used;
    private Position position;
    private SpecialType terrainType;
    private BufferedImage skin;
    private SidePosition sidePosition;

    public Miple(CarcassonnePlayer player){
        this.player= player;
        used= false;
        position= null;
        sidePosition = null;
        try {
            skin = ImageManagement.resize(ImageIO.read(new File("./Data/Resources/Miples/meeple_road_template.png")), Display.MIPLE_SIZE, Display.MIPLE_SIZE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public SidePosition getSidePosition() {
        return sidePosition;
    }

    public void setSidePosition(SidePosition sidePosition) {
        this.sidePosition = sidePosition;
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

    public void place(Position position, SpecialType terrainType) {
        this.position= position;
        this.used= true;
        this.terrainType = terrainType;
    }

    public void unPlace(){
        this.position= null;
        this.used= false;
    }

    public BufferedImage getSkin() {
        return skin;
    }

    public SpecialType getTerrainType() {
        return terrainType;
    }

    public void setTerrainType(SpecialType terrainType) {
        this.terrainType = terrainType;
    }

    public void setSkin(BufferedImage skin) {
        this.skin = skin;
    }
}
