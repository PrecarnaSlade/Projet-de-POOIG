package Graphic.Menu;

import Misc.Position;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageManagement {
    private static final boolean debug = true;
    public static BufferedImage getRegionAroundClick(BufferedImage image, Position clickPosition, int range) {
        BufferedImage subImage = new BufferedImage(range * 2, range * 2, BufferedImage.TYPE_INT_ARGB);
        saveImage("mainImage.png", image);

        for (int x = 0; x < range * 2; x++) {
            for (int y = 0; y < range * 2; y++) {
                subImage.setRGB(x, y, image.getRGB(x + clickPosition.getX() - range, y + clickPosition.getY() - range));
            }
        }
        saveImage("subImage.png", subImage);
        return subImage;
    }

    public static BufferedImage resize(BufferedImage img, int newW, int newH) {
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage BuffImg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = BuffImg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return BuffImg;
    }

    public static BufferedImage panelToBufferedImage(JPanel panel) {
        int nWidth = panel.getWidth();
        int nHeight = panel.getHeight();
        BufferedImage bi = new BufferedImage(nWidth, nHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bi.createGraphics();
        panel.setSize(nWidth, nHeight);
        layoutComponent(panel);
        panel.print(g);
        g.dispose();
        return bi;
    }

    private static void layoutComponent(Component component) {
        synchronized (component.getTreeLock()) {
            component.doLayout();

            if (component instanceof Container) {
                for (Component child : ((Container) component).getComponents()) {
                    layoutComponent(child);
                }
            }
        }
    }

    public static void saveImageFromPanel(JPanel panel, String outputName) {
        if (!debug) {
            return;
        }
        BufferedImage bi = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_ARGB);
        panel.paint(bi.getGraphics());
        saveImage(outputName, bi);
    }

    private static void saveImage(String outputName, BufferedImage bi) {
        try {
            File outputFile = new File(outputName);
            ImageIO.write(bi, "png", outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
