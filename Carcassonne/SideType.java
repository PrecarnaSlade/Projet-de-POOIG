package Carcassonne;

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
}
