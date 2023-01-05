package Carcassonne;

import Common.Window.Display;
import Exceptions.UnknownCarcassonneTileException;
import Misc.ColorManagement;
import Misc.Position;

import java.awt.*;
import java.util.Random;

public enum SpecialType {
    ROAD, FIELD, CITY, MONASTERY, VILLAGE, AMBIGUOUS;

    private static final Random rnd = new Random();

    public static SpecialType randomSide() {
        SpecialType[] specialTypes = {ROAD, FIELD, CITY};
        return specialTypes[rnd.nextInt(specialTypes.length)];
    }

    public static String toString(SpecialType specialType) {
        return switch (specialType) {
            case CITY -> "c";
            case ROAD -> "r";
            case FIELD -> "f";
            default -> "";
        };
    }

    public static SpecialType toSpecialType(String s) {
        return switch (s) {
            case "c" -> CITY;
            case "r" -> ROAD;
            case "f" -> FIELD;
            default -> AMBIGUOUS;
        };
    }

    public static String toCompleteString(SpecialType specialType) {
        return switch (specialType) {
            case CITY -> "city";
            case ROAD -> "road";
            case FIELD -> "field";
            default -> "";
        };
    }

    public static SpecialType colorToSideType(Color color) throws UnknownCarcassonneTileException {
        Color oMainColorRoad = Color.WHITE;
        Color oMainColorCity = Color.GREEN;
        Color oMainColorField = Color.ORANGE;
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
        throw new UnknownCarcassonneTileException();
    }

    public static SpecialType getRealTypeFromColor(Color color, CarcassonneTile tile, Position clickPosition, int range) throws UnknownCarcassonneTileException {
        SpecialType specialTypeFirstGuess = colorToSideType(color);
        if (specialTypeFirstGuess == FIELD || specialTypeFirstGuess == ROAD || !tile.hasOptionalType()) {
            return specialTypeFirstGuess;
        }
        Position tilePosition = tile.getPosition();
        int nTileX = tilePosition.getX() * Display.TILE_SIZE;
        int nTileY = tilePosition.getY() * Display.TILE_SIZE;
        if (clickPosition.getX() > nTileX + Display.TILE_SIZE / 2. - range && clickPosition.getX() < nTileX + Display.TILE_SIZE / 2. + range &&
            clickPosition.getY() > nTileY + Display.TILE_SIZE / 2. - range && clickPosition.getY() < nTileY + Display.TILE_SIZE / 2. + range) {
            // near the center of the tile
            return tile.getOptionalType();
        }
        throw new UnknownCarcassonneTileException();
    }
}
