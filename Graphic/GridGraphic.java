package Graphic;

import Carcassonne.CarcassonneTile;
import Common.Grid;
import Common.Window.Display;
import Common.Window.MainWindow;
import Common.Window.Management;
import Domino.DominoTile;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static Common.Window.Management.panelToBufferedImage;
import static Common.Window.Management.saveImageFromPanel;


public class GridGraphic extends JPanel {
    private final Grid grid;
    private final ImageIcon imageEmpty = new ImageIcon(new ImageIcon(ImageIO.read(new File("./Graphic/Resources/Null1.png"))).getImage().getScaledInstance(Display.TILE_SIZE, Display.TILE_SIZE, Image.SCALE_SMOOTH));
    private final String gamePlayed;

    public GridGraphic(Grid grid, String gamePlayed) throws IOException {
        this.grid = grid;
        this.gamePlayed = gamePlayed;
        this.setSize(Display.TILE_SIZE * grid.getWidth(), Display.TILE_SIZE * grid.getHeight());
        this.setLayout(null);

        updateGraphic();
    }

    public void updateGraphic() {
        if (gamePlayed.equals("Domino")) {
            DominoTile tile;
        } else {
            CarcassonneTile tile;
        }
        JPanel oPanelTemp;
        JLabel oLabelImage;

        for (int i = 0; i < grid.getWidth(); i++) {
            for (int j = 0; j < grid.getHeight(); j++) {
                oPanelTemp = new JPanel(null);
                oPanelTemp.setSize(Display.TILE_SIZE, Display.TILE_SIZE);

                if (grid.isEmpty(i, j)) {
                    oLabelImage = new JLabel();
                    oLabelImage.setSize(Display.TILE_SIZE, Display.TILE_SIZE);
                    oLabelImage.setIcon(imageEmpty);
                    oPanelTemp.add(oLabelImage);
                    oLabelImage.setLocation(0, 0);

                } else {
                    if (gamePlayed.equals("Domino")) {
                        DominoTile dominoTile = (DominoTile) grid.getTileByXY(i, j);
                        oLabelImage = new JLabel();
                        BufferedImage image = panelToBufferedImage(dominoTile.getGraphic());
                        saveImageFromPanel(dominoTile.getGraphic(), "domino.png");
                        oLabelImage.setIcon(new ImageIcon(image));
                        oPanelTemp.add(oLabelImage);
                        oLabelImage.setLocation(0, 0);
                    } else {
                        CarcassonneTile carcassonneTile = (CarcassonneTile) grid.getTileByXY(i, j);
                    }
                }

                this.add(oPanelTemp);
                oPanelTemp.setLocation(Display.TILE_SIZE * i, Display.TILE_SIZE * j);
            }
        }
    }

    public BufferedImage getImage() {
        updateGraphic();
        return panelToBufferedImage(this);
    }
}
