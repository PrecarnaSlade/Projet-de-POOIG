package Graphic;

import Common.Grid;
import Common.Window.Display;
import Common.Window.Management;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static Common.Window.Management.panelToBufferedImage;


public class GridGraphic extends JPanel {
    private final Grid grid;
    private final ImageIcon imageEmpty = new ImageIcon(new ImageIcon(ImageIO.read(new File("./Graphic/Resources/Null1.png"))).getImage().getScaledInstance(Display.TILE_SIZE, Display.TILE_SIZE, Image.SCALE_SMOOTH));

    public GridGraphic(Grid grid) throws IOException {
        this.grid = grid;
        this.setSize(Display.TILE_SIZE * grid.getWidth(), Display.TILE_SIZE * grid.getHeight());
        this.setLayout(null);

        updateGraphic();
    }

    public void updateGraphic() {
        JPanel oPanelTemp;
        JLabel oLabelImage;

        for (int i = 0; i < grid.getWidth(); i++) {
            for (int j = 0; j < grid.getHeight(); j++) {
                oPanelTemp = new JPanel(null);
                oPanelTemp.setSize(Display.TILE_SIZE, Display.TILE_SIZE);
                oPanelTemp.setBorder(new LineBorder(Color.BLACK));

                if (grid.isEmpty(i, j)) {
                    oLabelImage = new JLabel();
                    oLabelImage.setSize(Display.TILE_SIZE, Display.TILE_SIZE);
                    oLabelImage.setIcon(imageEmpty);
                    oPanelTemp.add(oLabelImage);
                    oLabelImage.setLocation(0, 0);

                } else {
                    oLabelImage = new JLabel();
                    oLabelImage.setSize(Display.TILE_SIZE, Display.TILE_SIZE);
                    oLabelImage.setIcon(new ImageIcon(panelToBufferedImage(grid.getTileByXY(i, j).getGraphic())));
                    oPanelTemp.add(oLabelImage);
                    oLabelImage.setLocation(0, 0);
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
