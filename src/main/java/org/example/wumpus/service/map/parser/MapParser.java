package org.example.wumpus.service.map.parser;

import org.example.wumpus.model.WumpusMapVO;
import org.example.wumpus.service.map.exception.MapParseException;

import java.util.List;
import java.util.regex.Pattern;

public class MapParser {
    private static final String VALID_ROW_REGEX = "[0-9]+";

    private int numberOfRows;
    private int numberOfColumns;

    public MapParser(int numberOfRows) {
        this.numberOfRows = numberOfRows;
        //this.numberOfColumns = numberOfColumns;
    }

    public WumpusMapVO parseMap(List<String> rawMap) throws MapParseException {
        /*checkNumberOfRows(rawMap);
        checkNumberOfColumns(rawMap);
        checkForInvalidValues(rawMap);*/

        char[][] map = getMap(rawMap);

        return new WumpusMapVO(map);
    }

    private void checkNumberOfRows(List<String> rows) throws MapParseException {
        if (rows.size() != numberOfRows) {
            throw new MapParseException("Number of rows must be " + numberOfRows);
        }
    }

    private void checkNumberOfColumns(List<String> rows) throws MapParseException {
        for (String row : rows) {
            if (row.length() != numberOfColumns) {
                throw new MapParseException("Number of columns must be " + numberOfColumns);
            }
        }
    }

    private void checkForInvalidValues(List<String> rows) throws MapParseException {
        for (String row : rows) {
            if (!Pattern.matches(VALID_ROW_REGEX, row)) {
                throw new MapParseException("Row contains invalid characters");
            }
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

