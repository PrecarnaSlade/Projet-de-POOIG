package Graphic;

import Carcassonne.CarcassonneTile;
import Common.Grid;
import Common.Window.Display;
import Domino.DominoTile;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static Common.Window.Management.*;


public class GridGraphic {
    private final Grid grid;
    private final BufferedImage imageEmpty = resize(ImageIO.read(new File("./Data/Resources/Null1.png")), Display.TILE_SIZE, Display.TILE_SIZE);
    private final String gamePlayed;
    private BufferedImage gridImage;

    public GridGraphic(Grid grid, String gamePlayed) throws IOException {
        this.grid = grid;
        this.gamePlayed = gamePlayed;
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
                        imageToAdd = panelToBufferedImage(dominoTile.getGraphic());
                    } else {
                        CarcassonneTile carcassonneTile = (CarcassonneTile) grid.getTileByXY(i, j);
                    }
                }

                newGridImage.drawImage(imageToAdd, Display.TILE_SIZE * i, Display.TILE_SIZE * nActualY, null);
            }
        }
        newGridImage.dispose();
    }

    public BufferedImage getImage() {
        updateGraphic();
        return gridImage;
    }
}
