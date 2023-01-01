package Debug;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;


public class MainPanel extends JPanel implements MouseWheelListener, MouseListener, MouseMotionListener {

    private final BufferedImage image;

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

    public MainPanel(BufferedImage image) {

        this.image = image;
        initComponent();

    }

    private void initComponent() {
        addMouseWheelListener(this);
        addMouseMotionListener(this);
        addMouseListener(this);
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
            boolean bForceReseta = false;
            boolean bForceResetb = false;
            AffineTransform at = new AffineTransform();
            if (xOffset + xDiff > 0 || yOffset + yDiff > 0 ) {
                at.translate(0, 0);
                bForceReseta = true;
            } else if (xOffset + xDiff < - this.getWidth() || yOffset + yDiff < - this.getHeight() ) {
                at.translate(- this.getWidth(), - this.getHeight());
                bForceResetb = true;
            } else {
                at.translate(xOffset + xDiff, yOffset + yDiff);
            }
            System.out.println((xOffset + xDiff) + " - " + (yOffset + yDiff));
            at.scale(zoomFactor, zoomFactor);
            g2.transform(at);

            if (released) {
                if (bForceReseta) {
                    xOffset = 0;
                    yOffset = 0;
                } else if (bForceResetb) {
                    xOffset = - this.getWidth();
                    yOffset = - this.getHeight();
                } else {
                    xOffset += xDiff;
                    yOffset += yDiff;
                }
                dragger = false;
            }

        }

        // All drawings go here

        g2.drawImage(image, (int) 0, (int) 0, this);

    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {

        zoomer = true;

        //Zoom in
        if (e.getWheelRotation() < 0) {
            zoomFactor *= 1.1;
            repaint();
        }
        //Zoom out
        if (e.getWheelRotation() > 0) {
            zoomFactor /= 1.1;
            repaint();
        }
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
        dragger = true;
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