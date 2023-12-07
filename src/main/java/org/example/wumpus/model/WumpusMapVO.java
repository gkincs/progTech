package org.example.wumpus.model;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A játék térképét reprezentáló osztály.
 *
 * Ez az osztály char[][] alapján tartalmazza a térképét.
 * A térkép mérete rögzített, és a konstruktor által megadott char tömbből másolódik.
 *
 * @param map A játék térképét reprezentáló char tömb. A konstruktor deepcopy-t készít róla.
 **/

public class WumpusMapVO {
    private final char[][] map;

    @JsonCreator

    public WumpusMapVO(@JsonProperty("map") char[][] map) {
        this.map = deepCopy(map);
    }

    public char[][] getMap() {
        return deepCopy(map);
    }

    private char[][] deepCopy(char[][] map) {
        char[][] result = new char[map.length][];

        for (int i = 0; i < map.length; i++) {
            String row = Arrays.toString(map[i]);
            result[i] = new char[map[i].length];
            for (int j = 0; j < map[i].length; j++) {
                result[i][j] = map[i][j];
            }
        }

        return result;
    }

    @Override
    public String toString() {
        return "WumpusMapVO{" +
                "map=" + Arrays.deepToString(map) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        WumpusMapVO that = (WumpusMapVO) o;

        return Arrays.deepEquals(map, that.map);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(map);
    }
}