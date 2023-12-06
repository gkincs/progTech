package org.example.wumpus.service.map.parser;

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
    private MapParser parser6;
    @BeforeEach
    void setUp() {

        underTest = new MapParser(3);
        parser6 = new MapParser(6);
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
            parser6.parseMap(otherRowInvalidValue);
        });

        assertEquals("Row contains invalid characters", exception.getMessage());
    }
}