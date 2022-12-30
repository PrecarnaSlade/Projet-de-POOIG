package Graphic;

import Common.Display;
import Common.Game;
import Common.Grid;
import MathFuncAndObj.Position;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GamePanel extends JPanel {
    private Game game;
    private BufferedImage content;

    public GamePanel(Game game) {
        this.game = game;
        Grid grid = this.game.getGrid();
        this.setSize(Display.TILE_SIZE * grid.getWidth(), Display.TILE_SIZE * grid.getHeight());
        this.setLayout(null);

        grid.place(this.game.getDeck().draw(), new Position((grid.getWidth()) / 2, (grid.getHeight()) / 2));
        updateContent();
    }

    public void updateContent() {
        Grid grid = this.game.getGrid();
        try {
            ImageIcon imageEmpty = new ImageIcon(new ImageIcon(ImageIO.read(new File("./Graphic/Resources/Null1.png"))).getImage().getScaledInstance(Display.TILE_SIZE, Display.TILE_SIZE, Image.SCALE_SMOOTH));
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
                        // resize image
                        oLabelImage.setIcon(imageEmpty);
                        oPanelTemp.add(oLabelImage);
                        oLabelImage.setLocation(0,0);

                    } else {
                        oPanelTemp = this.game.getGrid().getTileByXY(i, j).getGraphic();
                    }

                    this.add(oPanelTemp);
                    oPanelTemp.setLocation(Display.TILE_SIZE * i, Display.TILE_SIZE * j);
                }
            }
            this.content = getScreenShot(this);
            setBackgroundImage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private BufferedImage getScreenShot(JPanel panel){
        BufferedImage bi = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_ARGB);
        panel.paint(bi.getGraphics());
        try {
            File outputfile = new File("saved.png");
            ImageIO.write(bi, "png", outputfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bi;
    }

    private void setBackgroundImage() {
        JLabel oTemp = new JLabel();
        oTemp.setSize(this.getSize());
        oTemp.setIcon(new ImageIcon(content));
        this.add(oTemp);
        oTemp.setLocation(0,0);
    }
}
