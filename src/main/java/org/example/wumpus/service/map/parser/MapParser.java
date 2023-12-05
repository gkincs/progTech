package org.example.wumpus.service.map.parser;

import org.example.wumpus.model.WumpusMapVO;
import org.example.wumpus.service.map.exception.MapParseException;

import java.util.List;
import java.util.regex.Pattern;

/**
 * List string átalakítása char[][] tömbbé
 * Map-re vonatkozó feltételek vizsgálata
 */
public class MapParser {
    private static final String MAP_ROW_REGEX = "W[_WPHUG]*W";
    private static  final  String PLAYER_ROW_REGEX = "(6|7|8|9|1[0-9]|20)\\s[^\\d]\\s(0|1?\\d|20)\\s[ESNW]";

    private int numberOfRows;
    private int numberOfColumns;

    public MapParser(int numberOfRows) {
        this.numberOfRows = numberOfRows;
    }

    public WumpusMapVO parseMap(List<String> rawMap) throws MapParseException {
        checkNumberOfRows(rawMap);
        checkForInvalidValues(rawMap);

        char[][] map = getMap(rawMap);
        return new WumpusMapVO(map);
    }

    private void checkNumberOfRows(List<String> rows) throws MapParseException {
        if (rows.size() != numberOfRows +1) {
            throw new MapParseException("Number of rows must be " + numberOfRows);
        }
    }

    private void checkForInvalidValues(List<String> rows) throws MapParseException {
        Boolean firstRow = true;
        for (String row : rows) {
            if (!Pattern.matches(PLAYER_ROW_REGEX, row) && firstRow == true) {
                throw new MapParseException("First row contains invalid characters");
            }

            if (!Pattern.matches(MAP_ROW_REGEX, row) && firstRow == false) {
                throw new MapParseException("Row contains invalid characters");
            }
            firstRow = false;
        }
    }

    private char[][] getMap(List<String> rawMap) {
        char[][] result = new char[numberOfRows][];

        for (int i = 1; i <= numberOfRows; i++) {
            result[i-1] = new char[numberOfRows];

            String line = rawMap.get(i);
            String[] parts = line.split("");

            for (int j = 0; j < numberOfRows; j++) {
               char parsed = parts[j].charAt(0);
                result[i-1][j] = parsed;
            }
        }

        return result;
    }

}

