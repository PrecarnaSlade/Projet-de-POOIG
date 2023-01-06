package Misc;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;

public class ColorManagement {

    public static boolean areColorsClose(Color color1, Color color2, int threshold) {
        return getColorsDelta(color1, color2) < threshold;
    }

    public static int getColorsDelta(Color color1, Color color2) {
        int nRedDelta = color1.getRed() - color2.getRed();
        int nGreenDelta = color1.getGreen() - color2.getGreen();
        int nBlueDelta = color1.getBlue() - color2.getBlue();
        return Math.abs(nRedDelta) + Math.abs(nGreenDelta) + Math.abs(nBlueDelta);
    }
    public static Color getMainColor(BufferedImage image) {
        int nWidth = image.getWidth();
        int nHeight = image.getHeight();

        Map m = new HashMap();
        for(int i=0; i < nWidth ; i++)
        {
            for(int j=0; j < nHeight ; j++)
            {
                int rgb = image.getRGB(i, j);
                Integer counter = (Integer) m.get(rgb);
                if (counter == null)
                    counter = 0;
                counter++;
                m.put(rgb, counter);

            }
        }
        int[] aColourHex = getMostCommonColour(m);
        return new Color(aColourHex[0], aColourHex[1], aColourHex[2]);
    }

    protected static int[] getMostCommonColour(Map map) {
        LinkedList list = new LinkedList(map.entrySet());
        list.sort((Comparator) (o1, o2) -> ((Comparable) ((Map.Entry) (o1)).getValue())
                .compareTo(((Map.Entry) (o2)).getValue()));
        Map.Entry me = (Map.Entry )list.get(list.size()-1);
         return getRGBArr((Integer)me.getKey());
    }

    protected static int[] getRGBArr(int pixel) {
        int alpha = (pixel >> 24) & 0xff;
        int red = (pixel >> 16) & 0xff;
        int green = (pixel >> 8) & 0xff;
        int blue = (pixel) & 0xff;
        return new int[]{red,green,blue};

    }
    protected static boolean isGray(int[] rgbArr) {
        int rgDiff = rgbArr[0] - rgbArr[1];
        int rbDiff = rgbArr[0] - rgbArr[2];
        // Filter out black, white and grays...... (tolerance within 10 pixels)
        int tolerance = 10;
        if (rgDiff > tolerance || rgDiff < -tolerance)
            return rbDiff <= tolerance && rbDiff >= -tolerance;
        return true;
    }
}
