package org.example.wumpus.model;

import java.util.Arrays;

public class WumpusMapVO {
    private final int numberOfRows;
    private final int numberOfColumns;
    private final int[][] map;
    private final boolean[][] fixed;

    public WumpusMapVO(int numberOfRows, int numberOfColumns, int[][] map, boolean[][] fixed) {
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
        this.map = deepCopy(map);
        this.fixed = deepCopy(fixed);
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public int getNumberOfColumns() {
        return numberOfColumns;
    }

    public int[][] getMap() {
        return deepCopy(map);
    }

    public boolean[][] getFixed() {
        return deepCopy(fixed);
    }

    private int[][] deepCopy(int[][] map) {
        int[][] result = new int[map.length][];

        for (int i = 0; i < map.length; i++) {
            result[i] = new int[map[i].length];
            for (int j = 0; j < map[i].length; j++) {
                result[i][j] = map[i][j];
            }
        }

        return result;
    }
    private boolean[][] deepCopy(boolean[][] map) {
        boolean[][] result = new boolean[map.length][];

        for (int i = 0; i < map.length; i++) {
            result[i] = new boolean[map[i].length];
            for (int j = 0; j < map[i].length; j++) {
                result[i][j] = map[i][j];
            }
        }

        return result;
    }

    @Override
    public String toString() {
        return "WumpusMapVO{" +
                "numberOfRows=" + numberOfRows +
                ", numberOfColumns=" + numberOfColumns +
                ", map=" + Arrays.deepToString(map) +
                ", fixed=" + Arrays.deepToString(fixed) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WumpusMapVO that = (WumpusMapVO) o;

        if (numberOfRows != that.numberOfRows) return false;
        if (numberOfColumns != that.numberOfColumns) return false;
        if (!Arrays.deepEquals(map, that.map)) return false;
        return Arrays.deepEquals(fixed, that.fixed);
    }

    @Override
    public int hashCode() {
        int result = numberOfRows;
        result = 31 * result + numberOfColumns;
        result = 31 * result + Arrays.deepHashCode(map);
        result = 31 * result + Arrays.deepHashCode(fixed);
        return result;
    }
}