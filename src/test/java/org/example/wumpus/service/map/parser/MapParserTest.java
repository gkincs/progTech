package org.example.wumpus.service.map.parser;

import org.example.wumpus.model.WumpusMapVO;
import org.example.wumpus.service.map.exception.MapParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MapParserTest {
    private MapParser underTest;
    private MapParser parserSix;
    @BeforeEach
    void setUp() {

        underTest = new MapParser(3);
        parserSix = new MapParser(6);
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
                "3 B 5",
                "WWW",
                "WWW",
                "WWW"
        );

        // When, Then
        MapParseException exception = assertThrows(MapParseException.class, () -> {
            underTest.parseMap(invalidNumberOfRows);
        });

        assertEquals("First row contains invalid characters", exception.getMessage());
    }

    @Test
    void testCheckForInvalidValuesShouldThrowMapParseExceptionWhenOtherRowContainsInvalidCharacters() {
        // Given
        final List<String> otherRowInvalidValue = List.of(
                "6 B 5 E",
                "WWJWW",
                "W___PW",
                "WUGP_W",
                "W____W",
                "W__P_W",
                "WWWWWW"
        );

        // When, Then
        MapParseException exception = assertThrows(MapParseException.class, () -> {
            parserSix.parseMap(otherRowInvalidValue);
        });

        assertEquals("Row contains invalid characters", exception.getMessage());
    }
    @Test
    void testMapParserShouldThrowMapValidationExceptionIfMapRowIsInvalid() throws MapParseException {

        //Given
        final List<String> validParserMap = List.of(
                "6 B 5 E",
                "WWWWWW",
                "W___PW",
                "WU_P_W",
                "W____W",
                "W__P_W",
                "WWWWWW"
        );
        char[][] expectedMap = new char[][]{
                {'W', 'W', 'W', 'W', 'W', 'W'},
                {'W', '_', '_', '_', 'P', 'W'},
                {'W', 'U', '_', 'P', '_', 'W'},
                {'W', '_', '_', '_', '_', 'W'},
                {'W', '_', '_', 'P', '_', 'W'},
                {'W', 'W', 'W', 'W', 'W', 'W'}
        };
        //When
            WumpusMapVO result = parserSix.parseMap(validParserMap);

            //Then
            assertArrayEquals(expectedMap, result.getMap());
    }

}