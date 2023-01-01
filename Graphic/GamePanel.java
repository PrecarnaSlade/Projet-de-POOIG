package Graphic;

import Common.*;
import Common.Window.Display;
import Common.Window.MainWindow;
import Common.Window.Management;
import MathFuncAndObj.Position;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GamePanel extends JPanel implements MouseListener, MouseMotionListener {
    private final Game game;
    private final MainWindow parent;
    private BufferedImage content;
    private GridGraphic grid;

    private boolean dragger;
    private boolean released;
    private double xOffset = 0;
    private double yOffset = 0;
    private int xDiff;
    private int yDiff;
    private Point startPoint;

    public GamePanel(Game game, MainWindow parent) throws IOException {
        this.game = game;
        Common.Grid grid = this.game.getGrid();
        this.setSize(Display.TILE_SIZE * grid.getWidth(), Display.TILE_SIZE * grid.getHeight());
        this.setLayout(null);
        this.parent = parent;
        this.grid = new GridGraphic(grid);


        grid.place(this.game.getDeck().draw(), new Position((grid.getWidth()) / 2, (grid.getHeight()) / 2), this.grid);

        addMouseMotionListener(this);
        addMouseListener(this);
    }

    public Game getGame() {
        return game;
    }

    public void updateImage() {
        this.grid.updateGraphic();
        this.content = grid.getImage();
    }

    public BufferedImage getContent() {
        return content;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2 = (Graphics2D) g;

        if (dragger) {
            AffineTransform at = new AffineTransform();
            at.translate(xOffset + xDiff, yOffset + yDiff);
            g2.transform(at);

            if (released) {
                xOffset += xDiff;
                yOffset += yDiff;
                dragger = false;
            }

        }

        // All drawings go here

        g2.drawImage(content, -this.getWidth() / 2, -this.getHeight() / 2, this);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Point curPoint = e.getLocationOnScreen();
        xDiff = curPoint.x - startPoint.x;
        yDiff = curPoint.y - startPoint.y;

        dragger = true;
        repaint();

    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        released = false;
        startPoint = MouseInfo.getPointerInfo().getLocation();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        released = true;
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
