/*package org.example.wumpus.service.map.parser;

import org.example.wumpus.model.WumpusMapVO;
import org.example.wumpus.service.map.exception.MapParseException;
import org.example.wumpus.service.map.exception.MapValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MapParserTest {
    private MapParser underTest;
    private MapParser underTestMapValidate;
    @BeforeEach
    void setUp() {

        underTest = new MapParser(7);
        underTestMapValidate = new MapParser(6);
    }

    @Test
    void testMapParserShouldThrowMapParserExceptionIfNumberOfRowsAreInvalid() {
        //Given
        final List<String> mapRowInvalidSize = List.of(
                "2 B 5",
                "WW",
                "WW"
        );
        String expectedResult = "Number of rows must be 3";

        //When, Then
        String errorMessage = assertThrows(MapParseException.class, () -> {
            underTest.parseMap(mapRowInvalidSize);
        }).getMessage();

        //String compare
        assertTrue(errorMessage.contains(expectedResult));
    }

    @Test
    void testCheckNumberOfRowsShouldThrowMapParseExceptionWhenNumberOfRowsIsInvalid() {
        // Given
        final List<String> invalidNumberOfRows = List.of(
                "2 B 5",
                "WWW",
                "WWW",
                "WWW"
        );

        // When, Then
        MapParseException exception = assertThrows(MapParseException.class, () -> {
            underTest.parseMap(invalidNumberOfRows);
        });

        assertEquals("Number of rows must be 2", exception.getMessage());
    }

    @Test
    void testCheckForInvalidValuesShouldThrowMapParseExceptionWhenFirstRowContainsInvalidCharacters() {
        // Given
        final List<String> firstRowInvalidValue = List.of(
                "2 B 5 2",
                "WWW",
                "WWW"
        );

        // When, Then
        MapParseException exception = assertThrows(MapParseException.class, () -> {
            underTest.parseMap(firstRowInvalidValue);
        });

        assertEquals("First row contains invalid characters", exception.getMessage());
    }

    @Test
    void testCheckForInvalidValuesShouldThrowMapParseExceptionWhenNonFirstRowContainsInvalidCharacters() {
        // Given
        final List<String> otherRowsInvalidValue = List.of(
                "2 B 5",
                "WJW",
                "WWW"
        );

        // When, Then
        MapParseException exception = assertThrows(MapParseException.class, () -> {
            underTest.parseMap(otherRowsInvalidValue);
        });

        assertEquals("Row contains invalid characters", exception.getMessage());
    }

    @Test
    public void testMapParseShouldReturnValidMap() throws MapValidationException,MapParseException {
        //Given
        final List<String> firstRowOfTheMap = List.of(
                "S B 5",
                "WWWW",
                "W_PW",
                "WG_W",
                "WUPW",
                "WP_W",
                "WWWW"
        );
        final String[][] expectedMap = {
                {"S"," ","B"," ","5"},
                {"W","W","W","W"},
                {"W","_","P","W"},
                {"W","G","_","W"},
                {"W","U","P","W"},
                {"W","P","_","W"},
                {"W","W","W","W"}
        };
        //When
        WumpusMapVO result = underTestMapValidate.parseMap(firstRowOfTheMap);

        //Then
        assertTrue(Arrays.deepEquals(result.getMap(),expectedMap));

    }

}

 */