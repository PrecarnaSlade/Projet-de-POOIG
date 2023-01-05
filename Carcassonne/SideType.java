package Carcassonne;

import Misc.ColorManagement;

import java.awt.*;
import java.util.Random;

public enum SideType {
    ROAD, FIELD, CITY;

    private static final Random rnd = new Random();

    public static SideType randomSide() {
        SideType[] sideTypes = values();
        return sideTypes[rnd.nextInt(sideTypes.length)];
    }

    public static String toString(SideType sideType) {
        switch (sideType) {
            case CITY:
                return "c";
            case ROAD:
                return "r";
            case FIELD:
                return "f";
        }
        return "";
    }

    public static SideType colorToSideType(Color color) {
        Color oMainColorRoad = new Color(243, 243, 241);
        Color oMainColorCity = new Color(186, 168, 105);
        Color oMainColorField = new Color(186, 209, 89);
        int nRoadDelta = ColorManagement.getColorsDelta(color, oMainColorRoad);
        int nCityDelta = ColorManagement.getColorsDelta(color, oMainColorCity);
        int nFieldDelta = ColorManagement.getColorsDelta(color, oMainColorField);
        if (nRoadDelta < nCityDelta && nRoadDelta < nFieldDelta) {
            return ROAD;
        } else if (nCityDelta < nRoadDelta && nCityDelta < nFieldDelta) {
            return CITY;
        } else if (nFieldDelta < nRoadDelta && nFieldDelta < nCityDelta) {
            return FIELD;
        }
        return null;
    }
}
