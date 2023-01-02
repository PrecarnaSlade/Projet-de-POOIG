package Graphic;

import Common.*;
import Common.Window.Display;
import Common.Window.MainWindow;
import MathFuncAndObj.Position;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class GamePanel extends JPanel implements MouseListener, MouseMotionListener {
    private final Game game;
    private final MainWindow parent;
    private BufferedImage content;
    private GridGraphic grid;

    private boolean dragged;
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
        this.grid = new GridGraphic(grid, parent.getGamePlayed());


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

        if (dragged) {
            Insets insets = parent.getInsets();
            boolean bForceResetTop     = yOffset + yDiff > 0;
            boolean bForceResetRight   = xOffset + xDiff < - (Display.WIDTH - Display.WIDTH / 10. - insets.right - insets.left);
            boolean bForceResetBottom  = yOffset + yDiff < - (Display.HEIGHT - Display.HEIGHT / 10. + insets.bottom);
            boolean bForceResetLeft    = xOffset + xDiff > 0;
//            System.out.println("-----------------\nbForceResetTop    = " + bForceResetTop +
//                                                "\nbForceResetRight  = " + bForceResetRight +
//                                                "\nbForceResetBottom = " + bForceResetBottom +
//                                                "\nbForceResetLeft   = " + bForceResetLeft +
//                              "\n-----------------");
            System.out.println("X = " + this.getX() + "\nY = "  + this.getY());
            AffineTransform at = new AffineTransform();
            if (bForceResetTop || bForceResetRight || bForceResetBottom || bForceResetLeft) {
                if (bForceResetTop && bForceResetLeft || bForceResetBottom && bForceResetRight) {
                    xDiff = 0;
                    yDiff = 0;
                }
                if (bForceResetTop) {
                    yOffset = 0;
                    yDiff = 0;
                }
                if (bForceResetRight) {
                    xOffset = - (Display.WIDTH - Display.WIDTH / 10. - insets.right - insets.left);
                    xDiff = 0;
                }
                if (bForceResetBottom) {
                    yOffset = - (Display.HEIGHT - Display.HEIGHT / 10. + insets.bottom);
                    yDiff = 0;
                }
                if (bForceResetLeft) {
                    xOffset = 0;
                    xDiff = 0;
                }
            }

            at.translate(xOffset + xDiff, yOffset + yDiff);
            System.out.println((xOffset + xDiff) + " - " + (yOffset + yDiff));
            g2.transform(at);

            if (released) {
               xOffset += xDiff;
               yOffset += yDiff;
               dragged = false;
            }

        }

        // All drawings go here

        g2.drawImage(content, 0, 0, this);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Point curPoint = e.getLocationOnScreen();
        xDiff = curPoint.x - startPoint.x;
        yDiff = curPoint.y - startPoint.y;

        dragged = true;
        repaint();

    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Point location = e.getLocationOnScreen();
        System.out.println((location.x - parent.getLocation().x + "  ####  " + (location.y - parent.getLocation().y)));
        dragged = true;
        xDiff = 0;
        yDiff = 0;
        repaint();
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
