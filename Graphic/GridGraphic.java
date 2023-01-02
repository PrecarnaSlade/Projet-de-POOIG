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

        for (int i = 0; i < grid.getWidth(); i++) {
            for (int j = 0; j < grid.getHeight(); j++) {
                imageToAdd = imageEmpty;

                if (!grid.isEmpty(i, j)) {
                    if (gamePlayed.equals("Domino")) {
                        DominoTile dominoTile = (DominoTile) grid.getTileByXY(i, j);
                        BufferedImage image = panelToBufferedImage(dominoTile.getGraphic());
                        saveImageFromPanel(dominoTile.getGraphic(), "domino.png");
                        imageToAdd = image;
                    } else {
                        CarcassonneTile carcassonneTile = (CarcassonneTile) grid.getTileByXY(i, j);
                    }
                }

                newGridImage.drawImage(imageToAdd, Display.TILE_SIZE * i, Display.TILE_SIZE * j, null);
            }
        }
        newGridImage.dispose();
    }

    public BufferedImage getImage() {
        updateGraphic();
        return gridImage;
    }
}
