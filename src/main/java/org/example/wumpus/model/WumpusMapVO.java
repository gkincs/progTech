package org.example.wumpus.model;

import java.util.Arrays;
import java.util.List;

//MAP objektum
public class WumpusMapVO {
    private final char[][] map;

    public WumpusMapVO(char[][] map) {
        this.map = deepCopy(map);
    }
    // konstruktor - tábla méretére
    public WumpusMapVO(int size) {
        this.map = new char[size][size];
    }

    public char[][] getMap() {
        return map;
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WumpusMapVO that = (WumpusMapVO) o;

        return Arrays.deepEquals(map, that.map);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(map);
    }
}