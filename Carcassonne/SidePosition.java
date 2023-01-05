package Carcassonne;

import Common.Window.Display;

public enum SidePosition {
    N, NE, NW, S, SE, SW, E, W, CENTER;
    
    public static int toInt(SidePosition sidePosition) {
        return switch (sidePosition) {
            case N -> 0;
            case NE -> 1;
            case E -> 2;
            case SE -> 3;
            case S -> 4;
            case SW -> 5;
            case W -> 6;
            case NW -> 7;
            case CENTER -> 8;
        };
    }

    public static SidePosition getSidePosFromXY(int x, int y) {
        if (x > Display.TILE_SIZE / 2 - Display.TILE_C_MIDDLE_RATIO && x < Display.TILE_SIZE / 2 + Display.TILE_C_MIDDLE_RATIO && y < Display.TILE_SIZE / 4) {
            return N;
        } else if (x > Display.TILE_SIZE / 2 + Display.TILE_C_MIDDLE_RATIO && y < Display.TILE_SIZE / 4) {
            return NE;
        } else if (y > Display.TILE_SIZE / 2 - Display.TILE_C_MIDDLE_RATIO && y < Display.TILE_SIZE / 2 + Display.TILE_C_MIDDLE_RATIO && x > Display.TILE_SIZE / 4) {
            return E;
        } else if (x > Display.TILE_SIZE / 2 + Display.TILE_C_MIDDLE_RATIO && y > Display.TILE_SIZE / 4) {
            return SE;
        } else if (x > Display.TILE_SIZE / 2 - Display.TILE_C_MIDDLE_RATIO && x < Display.TILE_SIZE / 2 + Display.TILE_C_MIDDLE_RATIO && y > Display.TILE_SIZE / 4) {
            return S;
        } else if (x < Display.TILE_SIZE / 2 - Display.TILE_C_MIDDLE_RATIO && y > Display.TILE_SIZE / 4) {
            return SW;
        } else if (y > Display.TILE_SIZE / 2 - Display.TILE_C_MIDDLE_RATIO && y < Display.TILE_SIZE / 2 + Display.TILE_C_MIDDLE_RATIO && x < Display.TILE_SIZE / 4) {
            return W;
        } else if (x < Display.TILE_SIZE / 2 - Display.TILE_C_MIDDLE_RATIO && y < Display.TILE_SIZE / 4) {
            return NW;
        } else {
            return CENTER;
        }
    }
}
