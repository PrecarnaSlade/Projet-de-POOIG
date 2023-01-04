package Misc;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class WindowManagement {
    private static final boolean debug = true;

    public static Container getMasterParentWindow(Container startContainer) {
        while (startContainer.getParent() != null) {
            startContainer = startContainer.getParent();
        }
        return startContainer;
    }

    public static void saveImageFromPanel(JPanel panel, String outputName) {
        if (!debug) {
            return;
        }
        BufferedImage bi = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_ARGB);
        panel.paint(bi.getGraphics());
        try {
            File outputFile = new File(outputName);
            ImageIO.write(bi, "png", outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static BufferedImage panelToBufferedImage(JPanel panel) {
        int nWidth = panel.getWidth();
        int nHeight = panel.getHeight();
        BufferedImage bi = new BufferedImage(nWidth, nHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bi.createGraphics();
        panel.setSize(nWidth, nHeight); // or panel.getPreferedSize()
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

    public static BufferedImage resize(BufferedImage img, int newW, int newH) {
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }
}
