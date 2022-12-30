package Graphic;

import Common.*;
import MathFuncAndObj.Position;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GamePanel extends JPanel {
    private final Game game;
    private final MainWindow parent;
    private BufferedImage content;
    private JPanel hand;

    private double zoomFactor = 1;
    private double prevZoomFactor = 1;
    private boolean zoomer;
    private boolean dragger;
    private boolean released;
    private double xOffset = 0;
    private double yOffset = 0;
    private int xDiff;
    private int yDiff;
    private Point startPoint;

    public GamePanel(Game game, MainWindow parent) {
        this.game = game;
        Grid grid = this.game.getGrid();
        this.setSize(Display.TILE_SIZE * grid.getWidth(), Display.TILE_SIZE * grid.getHeight());
        this.setLayout(null);
        this.parent = parent;

        grid.place(this.game.getDeck().draw(), new Position((grid.getWidth()) / 2, (grid.getHeight()) / 2));

        hand = new JPanel();
        hand.setSize(Display.TILE_SIZE + Display.DISTANCE_BETWEEN_BUTTONS, Display.TILE_SIZE + Display.DISTANCE_BETWEEN_BUTTONS);
        hand.setLayout(null);
        hand.setLocation(Display.WIDTH - hand.getWidth(), Display.HEIGHT - hand.getHeight());
        JLabel infoLabel = new JLabel("You drew this tile :");
        hand.add(infoLabel);
        infoLabel.setLocation(Display.DISTANCE_BETWEEN_BUTTONS / 4, Display.DISTANCE_BETWEEN_BUTTONS / 4);
        this.add(hand);
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
                        WindowManagement.saveImageFromPanel(oPanelTemp, "domino" + (i+j) + ".png");
                    }

                    this.add(oPanelTemp);
                    oPanelTemp.setLocation(Display.TILE_SIZE * i, Display.TILE_SIZE * j);
                }
            }
            this.content = getScreenShot(this);
            parent.updateFront();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private BufferedImage getScreenShot(JPanel panel){
        BufferedImage bi = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_ARGB);
        panel.paint(bi.getGraphics());
        WindowManagement.saveImageFromPanel(panel, "grid.png");
        return bi;
    }

    public BufferedImage getContent() {
        return content;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2 = (Graphics2D) g;

        if (zoomer) {
            AffineTransform at = new AffineTransform();

            double xRel = MouseInfo.getPointerInfo().getLocation().getX() - getLocationOnScreen().getX();
            double yRel = MouseInfo.getPointerInfo().getLocation().getY() - getLocationOnScreen().getY();

            double zoomDiv = zoomFactor / prevZoomFactor;

            xOffset = (zoomDiv) * (xOffset) + (1 - zoomDiv) * xRel;
            yOffset = (zoomDiv) * (yOffset) + (1 - zoomDiv) * yRel;

            at.translate(xOffset, yOffset);
            at.scale(zoomFactor, zoomFactor);
            prevZoomFactor = zoomFactor;
            g2.transform(at);
            zoomer = false;
        }

        if (dragger) {
            AffineTransform at = new AffineTransform();
            at.translate(xOffset + xDiff, yOffset + yDiff);
            at.scale(zoomFactor, zoomFactor);
            g2.transform(at);

            if (released) {
                xOffset += xDiff;
                yOffset += yDiff;
                dragger = false;
            }

        }

        // All drawings go here

        g2.drawImage(content, 0, 0, this);

    }
}
