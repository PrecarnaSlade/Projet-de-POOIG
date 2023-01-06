package Graphic;

import Carcassonne.CarcassonnePlayer;
import Carcassonne.CarcassonneTile;
import Carcassonne.Miple;
import Common.Game;
import Common.Grid;
import Common.Player;
import Common.Window.Display;
import Domino.DominoTile;
import Misc.ImageManagement;
import Misc.Position;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class GridGraphic {
    private final Grid grid;
    private final BufferedImage imageEmpty = ImageManagement.resize(ImageIO.read(new File("./Data/Resources/Null1.png")), Display.TILE_SIZE, Display.TILE_SIZE);
    private final String gamePlayed;
    private final BufferedImage gridImage;
    private final Game game;

    public GridGraphic(Grid grid, String gamePlayed, Game game) throws IOException {
        this.grid = grid;
        this.gamePlayed = gamePlayed;
        this.game = game;
        gridImage = new BufferedImage(Display.TILE_SIZE * grid.getWidth(), Display.TILE_SIZE * grid.getHeight(), BufferedImage.TYPE_INT_ARGB);

        updateGraphic();
    }

    public void updateGraphic() {
        BufferedImage imageToAdd;
        Graphics2D newGridImage = this.gridImage.createGraphics();
        int nWidth = grid.getWidth();
        int nHeight = grid.getHeight();
        int nActualY;

        for (int i = 0; i < nWidth; i++) {
            for (int j = 0; j < nHeight; j++) {
                imageToAdd = imageEmpty;
                nActualY = nHeight - j - 1;

                if (!grid.isEmpty(i, j)) {
                    if (gamePlayed.equals("Domino")) {
                        DominoTile dominoTile = (DominoTile) grid.getTileByXY(i, j);
                        imageToAdd = ImageManagement.panelToBufferedImage(dominoTile.getGraphic());
                    } else {
                        CarcassonneTile carcassonneTile = (CarcassonneTile) grid.getTileByXY(i, j);
                        imageToAdd = carcassonneTile.getDisplay();
                    }
                }

                newGridImage.drawImage(imageToAdd, Display.TILE_SIZE * i, Display.TILE_SIZE * nActualY, null);
            }
        }

        if (gamePlayed.equals("Carcassonne")) {
            Position miplePosition;
            for(Player p : this.game.getPlayers()) {
                if (p instanceof CarcassonnePlayer) {
                    Miple[] miples =  ((CarcassonnePlayer) p).getMiples();
                    for (Miple miple : miples) {
                        miplePosition = miple.getPosition();
                        if (miplePosition == null) {
                            continue;
                        }
                        newGridImage.drawImage(miple.getSkin(), miplePosition.getX(), miplePosition.getY(), null);
                    }
                }
            }
        }

        newGridImage.dispose();
    }

    public BufferedImage getImage() {
        updateGraphic();
        return gridImage;
    }
}
